package net.ato.shupapium.client.gui;

import com.mojang.datafixers.util.Pair;
import net.ato.shupapium.ShupapiumMenuTypes;
import net.ato.shupapium.blockentities.ShupapiumAmmoContainerBlockEntity;
import net.ato.shupapium.items.GenericPropellantItem;
import net.ato.shupapium.items.ShupapiumAmmoItem;
import net.ato.shupapium.utils.IShupapiumAmmoContainerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.CreateBigCannons;

public class ShupapiumAmmoContainerMenu extends AbstractContainerMenu {
    private static final ResourceLocation PROPELLANT_SLOT = CreateBigCannons.resource("item/tracer_slot");

    public static ShupapiumAmmoContainerMenu getServerMenuForItemStack(int id, Inventory playerInv, ItemStack stack, boolean isCreative) {
        IShupapiumAmmoContainerContainer ct = new ShupapiumAmmoContainerItemContainer(stack);
        return new ShupapiumAmmoContainerMenu(ShupapiumMenuTypes.SHUPAPIUM_AMMO_CONTAINER.get(), id, playerInv, ct, isCreative, true);
    }

    public static ShupapiumAmmoContainerMenu getServerMenuForBlockEntity(int id, Inventory playerInv, ShupapiumAmmoContainerBlockEntity be, boolean isCreative) {
        return new ShupapiumAmmoContainerMenu(ShupapiumMenuTypes.SHUPAPIUM_AMMO_CONTAINER.get(), id, playerInv, be, isCreative, false);
    }

    public static ShupapiumAmmoContainerMenu getClientMenu(MenuType<ShupapiumAmmoContainerMenu> type, int id, Inventory playerInv, RegistryFriendlyByteBuf buf) {
        boolean isCreative = buf.readBoolean();
        boolean isBlock = buf.readBoolean();
        IShupapiumAmmoContainerContainer ct;
        if (isBlock) {
            BlockPos pos = buf.readBlockPos();
            BlockEntity be = playerInv.player.level().getBlockEntity(pos);
            ct = new ShupapiumAmmoContainerBEContainerWrapper(be instanceof ShupapiumAmmoContainerBlockEntity abe ? abe : null, pos);
        } else {
            ct = new ShupapiumAmmoContainerItemContainer(ItemStack.STREAM_CODEC.decode(buf));
        }
        return new ShupapiumAmmoContainerMenu(type, id, playerInv, ct, isCreative, !isBlock);
    }

    private final boolean isCreative;
    private final IShupapiumAmmoContainerContainer container;
    private final Inventory playerInv;
    private final boolean isItem;

    protected ShupapiumAmmoContainerMenu(MenuType<? extends ShupapiumAmmoContainerMenu> type, int id, Inventory playerInv, IShupapiumAmmoContainerContainer ct, boolean isCreative, boolean isItem) {
        super(type, id);
        this.addSlot(new ShupapiumAmmoContainerMenuSlot(ct, IShupapiumAmmoContainerContainer.AMMO_SLOT, 32, 26, isCreative));
        this.addSlot(new ShupapiumAmmoContainerMenuSlot(ct, IShupapiumAmmoContainerContainer.PROPELLANT_SLOT, 59, 26, isCreative) {
            @Override
            public @NotNull Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, PROPELLANT_SLOT);
            }
        });

        int add = isCreative ? 18 : 8;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(playerInv, row * 9 + col + 9, col * 18 + add, row * 18 + 105));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInv, i, i * 18 + add, 163));
        }

        this.container = ct;
        this.playerInv = playerInv;
        this.isCreative = isCreative;
        this.isItem = isItem;

        this.container.startOpen(playerInv.player);
    }

    @Override public boolean stillValid(@NotNull Player player) { return this.container.stillValid(player); }

    public boolean isCreativeContainer() { return this.isCreative; }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemStack2 = slot.getItem();
            itemStack = itemStack2.copy();
            if (index < 2) {
                if (!this.moveItemStackTo(itemStack2, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                int buf = Math.max(128 - this.container.getTotalCount(), 0);
                if (buf < 1) return ItemStack.EMPTY;
                if (itemStack2.getItem() instanceof ShupapiumAmmoItem) {
                    Slot insertSlot = this.slots.get(IShupapiumAmmoContainerContainer.AMMO_SLOT);
                    insertSlot.safeInsert(itemStack2);
                } else if (itemStack2.getItem() instanceof GenericPropellantItem) {
                    Slot insertSlot = this.slots.get(IShupapiumAmmoContainerContainer.PROPELLANT_SLOT);
                    insertSlot.safeInsert(itemStack2);
                } else {
                    return ItemStack.EMPTY;
                }
            }

            if (itemStack2.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();

            if (itemStack2.getCount() == itemStack.getCount()) return ItemStack.EMPTY;
            slot.onTake(player, itemStack2);
        }
        return itemStack;
    }

    public boolean isFilled() { return true; }

    @Override
    public void clicked(int slotId, int button, @NotNull ClickType clickType, @NotNull Player player) {
        if (slotId == this.playerInv.selected + 29 && clickType != ClickType.THROW && this.isItem) return;
        super.clicked(slotId, button, clickType, player);
    }

    public IShupapiumAmmoContainerContainer getContainer() { return this.container; }

    @Override
    public void removed(@NotNull Player player) {
        super.removed(player);
        this.container.stopOpen(player);
    }
}
