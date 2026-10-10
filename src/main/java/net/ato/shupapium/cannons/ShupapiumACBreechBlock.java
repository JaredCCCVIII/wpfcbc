package net.ato.shupapium.cannons;

import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.contraptions.actors.seat.SeatBlock;
import net.ato.shupapium.ShupapiumBlockEntities;
import net.ato.shupapium.items.ShupapiumAmmoContainerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.cannon_control.contraption.PitchOrientedContraptionEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.breech.AbstractAutocannonBreechBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.breech.AutocannonBreechBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;
import rbasamoyai.createbigcannons.index.CBCSoundEvents;

import static rbasamoyai.createbigcannons.cannons.big_cannons.BigCannonBlock.writeAndSyncSingleBlockData;

public class ShupapiumACBreechBlock extends AutocannonBreechBlock {
    public ShupapiumACBreechBlock(Properties properties, AutocannonMaterial material) {
        super(properties, material);
    }

    @Override
    public BlockEntityType<? extends AbstractAutocannonBreechBlockEntity> getBlockEntityType() {
        return ShupapiumBlockEntities.SHUPAPIUM_AUTOCANNON_BREECH.get();
    }

    @Override
    public @NotNull ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (level.getBlockEntity(pos) instanceof ShupapiumACBreechBlockEntity breech) {
            if (breech.getSeatColor() == null
                    && state.getValue(HANDLE)
                    && stack.getItem() instanceof BlockItem blockItem
                    && blockItem.getBlock() instanceof SeatBlock seat) {
                if (!level.isClientSide) {
                    breech.setSeatColor(seat.getColor());
                    SoundType soundType = seat.defaultBlockState().getSoundType();
                    level.playSound(null, pos, soundType.getPlaceSound(), SoundSource.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);
                    if (!player.isCreative()) stack.shrink(1);
                }
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
            ItemStack container = breech.getMagazine();
            boolean changed = false;
            boolean tryAdd = false;
            if (!container.isEmpty()) {
                if (!level.isClientSide) {
                    tryAdd = true;
                    breech.setMagazine(ItemStack.EMPTY);
                }
                changed = true;
            }
            if (stack.getItem() instanceof ShupapiumAmmoContainerItem) {
                if (!level.isClientSide) {
                    breech.setMagazine(stack);
                    player.setItemInHand(hand, ItemStack.EMPTY);
                    CBCSoundEvents.PLACE_AUTOCANNON_AMMO_CONTAINER.playOnServer(level, pos);
                }
                changed = true;
            }

            if (tryAdd && !player.addItem(container)) {
                Vec3 spawnLoc = Vec3.atCenterOf(pos);
                ItemEntity dropEntity = new ItemEntity(level, spawnLoc.x, spawnLoc.y, spawnLoc.z, container);
                level.addFreshEntity(dropEntity);
            }
            if (changed) {
                breech.notifyUpdate();
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public boolean onInteractWhileAssembled(Player player, BlockPos localPos, Direction side, InteractionHand interactionHand,
                                            Level level, Contraption contraption, BlockEntity be, StructureTemplate.StructureBlockInfo info,
                                            PitchOrientedContraptionEntity entity) {
        if (!(be instanceof AbstractAutocannonBreechBlockEntity breech)) return false;

        ItemStack stack = player.getItemInHand(interactionHand);
        ItemStack container = breech.getMagazine();
        Vec3 globalPos = entity.toGlobalVector(Vec3.atCenterOf(localPos), 0);

        boolean insertingContainer = stack.getItem() instanceof ShupapiumAmmoContainerItem;
        boolean canRemove = insertingContainer || stack.isEmpty() && (ShupapiumAmmoContainerItem.getTotalAmmoCount(container) == 0 || player.isShiftKeyDown());

        boolean changed = false;
        boolean tryAdd = false;
        if (!container.isEmpty() && canRemove) {
            if (!level.isClientSide) {
                tryAdd = true;
                breech.setMagazine(ItemStack.EMPTY);
            }
            changed = true;
        }
        if (breech.getMagazine().isEmpty() && insertingContainer) {
            if (!level.isClientSide) {
                breech.setMagazine(stack);
                player.setItemInHand(interactionHand, ItemStack.EMPTY);
                CBCSoundEvents.PLACE_AUTOCANNON_AMMO_CONTAINER.playOnServer(level, BlockPos.containing(globalPos));
            }
            changed = true;
        }

        if (tryAdd && !player.addItem(container)) {
            ItemEntity dropEntity = new ItemEntity(level, globalPos.x, globalPos.y, globalPos.z, container);
            level.addFreshEntity(dropEntity);
        }
        if (changed && !level.isClientSide) writeAndSyncSingleBlockData(be, info, entity, contraption);
        return changed;
    }
}
