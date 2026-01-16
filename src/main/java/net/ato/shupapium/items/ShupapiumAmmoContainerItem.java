package net.ato.shupapium.items;

import net.ato.shupapium.ShupapiumBlocks;
import net.ato.shupapium.ShupapiumMenuTypes;
import net.ato.shupapium.client.gui.ShupapiumAmmoContainerMenu;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShupapiumAmmoContainerItem extends BlockItem implements MenuProvider {
    public ShupapiumAmmoContainerItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return this.getDescription();
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, Player pPlayer) {
        return ShupapiumAmmoContainerMenu.getServerMenuForItemStack(pContainerId, pPlayerInventory, pPlayer.getMainHandItem(), this.isCreative());
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
        if (player.mayBuild()) {
            ItemStack stack = player.getItemInHand(hand);
            if (player instanceof ServerPlayer splayer) {
                Component screenName = stack.hasCustomHoverName() ? stack.getHoverName() : this.getDisplayName();

                ShupapiumMenuTypes.SHUPAPIUM_AMMO_CONTAINER.open(splayer, screenName, this, buf -> {
                    buf.writeBoolean(this.isCreative());
                    buf.writeBoolean(false);
                    buf.writeItem(new ItemStack(this));
                });
            }
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }
        return super.use(level, player, hand);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        InteractionResult superResult = super.useOn(context);
        if (superResult.consumesAction()) return superResult;
        assert context.getPlayer() != null;
        return this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult();
    }

    public static ItemStack getMainAmmoStack(ItemStack container) {
        CompoundTag tag = container.getOrCreateTag();
        return tag.contains("Ammo") ? ItemStack.of(tag.getCompound("Ammo")) : ItemStack.EMPTY;
    }

    public static ItemStack getPropellantAmmoStack(ItemStack container) {
        CompoundTag tag = container.getOrCreateTag();
        return tag.contains("Propellants") ? ItemStack.of(tag.getCompound("Propellants")) : ItemStack.EMPTY;
    }

    public static boolean shouldPullPropellant(ItemStack container) {
        ItemStack mainAmmo = getMainAmmoStack(container);
        if (mainAmmo.getItem() instanceof ShupapiumAmmoItem ammoItem) return ammoItem.requiresPropellant(mainAmmo);
        return false;
    }

    public static int getTotalAmmoCount(ItemStack container) {
        return getMainAmmoStack(container).getCount() + getPropellantAmmoStack(container).getCount();
    }

    public static ItemStack pollItemFromContainer(ItemStack container) {
        if (!(container.getItem() instanceof ShupapiumAmmoContainerItem ctItem)) return ItemStack.EMPTY;
        ItemStack mainAmmo = getMainAmmoStack(container);
        ItemStack propellantAmmo = getPropellantAmmoStack(container);
        ItemStack ret = ItemStack.EMPTY;
        boolean isCreative = ctItem.isCreative();

        if (shouldPullPropellant(container)) {
            if (propellantAmmo.isEmpty()) return ret;
            if (isCreative) {
                propellantAmmo.setCount(1);
            } else {
                propellantAmmo.split(1);
                container.getOrCreateTag().put("Propellants", propellantAmmo.isEmpty() ? new CompoundTag() : propellantAmmo.save(new CompoundTag()));
            }
        }

        if (isCreative) {
            ret = mainAmmo.copy();
            ret.setCount(1);
        } else {
            ret = mainAmmo.split(1);
            container.getOrCreateTag().put("Ammo", mainAmmo.isEmpty() ? new CompoundTag() : mainAmmo.save(new CompoundTag()));
        }

        return ret;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
        String infinity = "∞";

        ItemStack mainAmmo = getMainAmmoStack(stack);
        if (!mainAmmo.isEmpty()) {
            String mainValue = this.isCreative() ? infinity : Integer.toString(mainAmmo.getCount());
            tooltipComponents.add(Component.translatable("block.shupapium.shupapium_ammo_container.tooltip.main_ammo", mainValue, mainAmmo.getDisplayName()));
        }
        ItemStack propellantAmmo = getPropellantAmmoStack(stack);
        if (!propellantAmmo.isEmpty()) {
            String propellantValue = this.isCreative() ? infinity : Integer.toString(propellantAmmo.getCount());
            tooltipComponents.add(Component.translatable("block.shupapium.shupapium_ammo_container.tooltip.propellants", propellantValue, propellantAmmo.getDisplayName()));
        }
    }

    public boolean isCreative() {
        return ShupapiumBlocks.CREATIVE_SHUPAPIUM_AMMO_CONTAINER.is(this);
    }
}
