package net.ato.shupapium.items;

import com.google.common.collect.Lists;
import net.ato.shupapium.ShupapiumBlocks;
import net.ato.shupapium.ShupapiumDataComponents;
import net.ato.shupapium.ShupapiumMenuTypes;
import net.ato.shupapium.client.gui.ShupapiumAmmoContainerMenu;
import net.minecraft.core.component.DataComponents;
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
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShupapiumAmmoContainerItem extends BlockItem implements MenuProvider {
    public ShupapiumAmmoContainerItem(Block block, Properties properties) {
        super(block, properties);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return this.getDescription();
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, @NotNull Inventory inventory, @NotNull Player player) {
        return ShupapiumAmmoContainerMenu.getServerMenuForItemStack(i, inventory, player.getMainHandItem(), this.isCreative());
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (player.mayBuild()) {
            ItemStack stack = player.getItemInHand(usedHand);
            if (player instanceof ServerPlayer splayer) {
                Component screenName = stack.has(DataComponents.CUSTOM_NAME) ? stack.getHoverName() : this.getDisplayName();

                ShupapiumMenuTypes.SHUPAPIUM_AMMO_CONTAINER.open(splayer, screenName, this, buf -> {
                    buf.writeBoolean(this.isCreative());
                    buf.writeBoolean(false);
                    ItemStack.STREAM_CODEC.encode(buf, new ItemStack(this));
                });
            }
            return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
        }
        return super.use(level, player, usedHand);
    }

    @Override
    public @NotNull InteractionResult useOn(@NotNull UseOnContext context) {
        InteractionResult superResult = super.useOn(context);
        if (superResult.consumesAction()) return superResult;
        assert context.getPlayer() != null;
        return this.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult();
    }

    public static ItemStack getMainAmmoStack(ItemStack container) {
        ItemContainerContents items = container.getOrDefault(ShupapiumDataComponents.AMMO, ItemContainerContents.EMPTY);
        return items.copyOne();
    }

    public static ItemStack getPropellantAmmoStack(ItemStack container) {
        ItemContainerContents items = container.getOrDefault(ShupapiumDataComponents.PROPELLANTS, ItemContainerContents.EMPTY);
        return items.copyOne();
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
                setPropellant(container, propellantAmmo);
            }
        }

        if (isCreative) {
            ret = mainAmmo.copy();
            ret.setCount(1);
        } else {
            ret = mainAmmo.split(1);
            setAmmo(container, mainAmmo);
        }
        return ret;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext ctx, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(stack, ctx, tooltipComponents, isAdvanced);
        String infinity = "∞";

        ItemStack mainAmmo = getMainAmmoStack(stack);
        if (!mainAmmo.isEmpty()) {
            String mainValue = this.isCreative() ? infinity : Integer.toString(mainAmmo.getCount());
            tooltipComponents.add(Component.translatable("block.shupapium.warium_ammo_container.tooltip.main_ammo", mainValue, mainAmmo.getDisplayName()));
        }
        ItemStack propellantAmmo = getPropellantAmmoStack(stack);
        if (!propellantAmmo.isEmpty()) {
            String propellantValue = this.isCreative() ? infinity : Integer.toString(propellantAmmo.getCount());
            tooltipComponents.add(Component.translatable("block.shupapium.warium_ammo_container.tooltip.propellants", propellantValue, propellantAmmo.getDisplayName()));
        }
    }

    public boolean isCreative() {
        return ShupapiumBlocks.CREATIVE_SHUPAPIUM_AMMO_CONTAINER.is(this);
    }

    public static void setAmmo(ItemStack container, ItemStack content) {
        if (content.isEmpty()) container.remove(ShupapiumDataComponents.AMMO);
        else container.set(ShupapiumDataComponents.AMMO, ItemContainerContents.fromItems(Lists.newArrayList(content)));
    }

    public static void setPropellant(ItemStack container, ItemStack content) {
        if (content.isEmpty()) container.remove(ShupapiumDataComponents.PROPELLANTS);
        else container.set(ShupapiumDataComponents.PROPELLANTS, ItemContainerContents.fromItems(Lists.newArrayList(content)));
    }
}
