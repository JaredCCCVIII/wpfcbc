package net.ato.shupapium.blocks;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.block.IBE;
import net.ato.shupapium.ShupapiumBlockEntities;
import net.ato.shupapium.ShupapiumMenuTypes;
import net.ato.shupapium.blockentities.ShupapiumAmmoContainerBlockEntity;
import net.ato.shupapium.items.ShupapiumAmmoContainerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.index.CBCShapes;

import javax.annotation.Nullable;
import java.util.Locale;

public class ShupapiumAmmoContainerBlock extends Block implements IWrenchable, SimpleWaterloggedBlock, IBE<ShupapiumAmmoContainerBlockEntity> {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<State> CONTAINER_STATE = EnumProperty.create("state", State.class);
    public ShupapiumAmmoContainerBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(CONTAINER_STATE, State.CLOSED).setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AXIS).add(CONTAINER_STATE).add(WATERLOGGED);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return this.defaultBlockState().setValue(AXIS, context.getHorizontalDirection().getAxis())
                .setValue(WATERLOGGED, waterlogged);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return CBCShapes.AUTOCANNON_AMMO_CONTAINER.get(state.getValue(AXIS));
    }

    @Override
    public void setPlacedBy(Level level, @NotNull BlockPos pos, @NotNull BlockState state, @Nullable LivingEntity placer, @NotNull ItemStack stack) {
        if (level.getBlockEntity(pos) instanceof ShupapiumAmmoContainerBlockEntity be) {
            if (stack.hasCustomHoverName()) be.setCustomName(stack.getHoverName());
            be.setMainAmmoDirect(ShupapiumAmmoContainerItem.getMainAmmoStack(stack));
            be.setPropellantsDirect(ShupapiumAmmoContainerItem.getPropellantAmmoStack(stack));
        }
    }

    @Override
    public void playerWillDestroy(Level level, @NotNull BlockPos pos, @NotNull BlockState state, @NotNull Player player) {
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof ShupapiumAmmoContainerBlockEntity be && player.isCreative() && be.canDropInCreative()) {
            ItemStack stack = new ItemStack(this.asItem());
            be.saveToItem(stack);
            if (be.hasCustomName()) stack.setHoverName(be.getCustomName());

            ItemEntity itemEntity = new ItemEntity(level, (double) pos.getX() + 0.5, (double) pos.getY() + 0.5, (double) pos.getZ() + 0.5, stack);
            itemEntity.setDefaultPickUpDelay();
            level.addFreshEntity(itemEntity);
        }
        super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void onRemove(BlockState state, @NotNull Level level, @NotNull BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            if (level.getBlockEntity(pos) instanceof ShupapiumAmmoContainerBlockEntity) {
                level.updateNeighbourForOutputSignal(pos, state.getBlock());
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public @NotNull ItemStack getCloneItemStack(@NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull BlockState state) {
        ItemStack itemStack = super.getCloneItemStack(level, pos, state);
        if (level.getBlockEntity(pos) instanceof ShupapiumAmmoContainerBlockEntity be) be.saveToItem(itemStack);
        return itemStack;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (level.getBlockEntity(pos) instanceof ShupapiumAmmoContainerBlockEntity be) {
            if (player instanceof ServerPlayer splayer) {
                ShupapiumMenuTypes.SHUPAPIUM_AMMO_CONTAINER.open(splayer, be.getDisplayName(), be, buf -> {
                    buf.writeBoolean(be.isCreativeContainer());
                    buf.writeBoolean(true);
                    buf.writeBlockPos(pos);
                });
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public int getAnalogOutputSignal(@NotNull BlockState state, Level level, @NotNull BlockPos pos) {
        if (!(level.getBlockEntity(pos) instanceof ShupapiumAmmoContainerBlockEntity be)) return 0;
        if (be.isCreativeContainer()) return be.getTotalCount() == 0 ? 0 : 15;
        return (int) Math.floor((float) be.getTotalCount() / (float) 128 * 15f);
    }

    @Override
    public void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        super.tick(state, level, pos, random);
        if (level.getBlockEntity(pos) instanceof ShupapiumAmmoContainerBlockEntity be) be.recheckOpen();
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    public @NotNull BlockState updateShape(BlockState state, @NotNull Direction face, @NotNull BlockState otherState, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos otherPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, face, otherState, level, pos, otherPos);
    }

    @Override
    public Class<ShupapiumAmmoContainerBlockEntity> getBlockEntityClass() {
        return ShupapiumAmmoContainerBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends ShupapiumAmmoContainerBlockEntity> getBlockEntityType() {
        return ShupapiumBlockEntities.SHUPAPIUM_AMMO_CONTAINER.get();
    }

    public enum State implements StringRepresentable {
        CLOSED,
        EMPTY,
        FILLED;

        private final String id = this.name().toLowerCase(Locale.ROOT);

        @Override public @NotNull String getSerializedName() { return this.id; }

        public static State getFromFilled(boolean filled) { return filled ? FILLED : EMPTY; }
    }
}
