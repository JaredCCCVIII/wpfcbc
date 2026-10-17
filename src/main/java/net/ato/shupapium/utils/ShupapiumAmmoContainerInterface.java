package net.ato.shupapium.utils;

import net.ato.shupapium.blockentities.ShupapiumAmmoContainerBlockEntity;
import net.ato.shupapium.items.GenericPropellantItem;
import net.ato.shupapium.items.ShupapiumAmmoItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;

import javax.annotation.Nonnull;

public record ShupapiumAmmoContainerInterface(ShupapiumAmmoContainerBlockEntity be) implements IItemHandler {
    private static final int AMMO_SLOT = 0;
    private static final int PROPELLANT_SLOT = 1;

    @Override public int getSlots() { return 2; }

    @Nonnull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return switch (slot) {
            case AMMO_SLOT -> this.be.getMainAmmoStack();
            case PROPELLANT_SLOT -> this.be.getPropellantStack();
            default -> ItemStack.EMPTY;
        };
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot != AMMO_SLOT && slot != PROPELLANT_SLOT) return stack;
        if (!(stack.getItem() instanceof ShupapiumAmmoItem) || !(stack.getItem() instanceof GenericPropellantItem)) return stack;

        boolean ammoSlot = slot == AMMO_SLOT;
        ItemStack currentStack = ammoSlot ? this.be.getMainAmmoStack() : this.be.getPropellantStack();
        if (!currentStack.isEmpty() && !ItemStack.isSameItemSameComponents(stack, currentStack)) return stack;

        int currentCapacity;
        currentCapacity = ammoSlot ? this.be.getMainAmmoCapacity() : this.be.getPropellantAmmoCapacity();
        currentCapacity -= currentStack.getCount();
        int canAdd = Math.min(currentCapacity, stack.getCount());
        if (canAdd < 1) return stack;
        if (!simulate) {
            if (currentStack.isEmpty()) {
                ItemStack copy = stack.copy();
                copy.setCount(canAdd);
                this.be.setItem(slot, copy);
            } else {
                currentStack.grow(canAdd);
            }
            this.be.setChanged();
        }
        ItemStack ret = stack.copy();
        ret.shrink(canAdd);
        return ret;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (slot != AMMO_SLOT && slot != PROPELLANT_SLOT) return ItemStack.EMPTY;

        boolean ammoSlot = slot == AMMO_SLOT;
        ItemStack currentStack = ammoSlot ? this.be.getMainAmmoStack() : this.be.getPropellantStack();
        if (currentStack.isEmpty()) return ItemStack.EMPTY;

        int maxRemove = Math.min(currentStack.getCount(), amount);
        if (maxRemove < 1) return ItemStack.EMPTY;
        ItemStack ret = currentStack.copy();
        ret.setCount(maxRemove);
        if (!simulate) {
            currentStack.shrink(maxRemove);
            if (currentStack.isEmpty()) this.be.setItem(slot, ItemStack.EMPTY);
            this.be.setChanged();
        }
        return ret;
    }

    @Override
    public int getSlotLimit(int slot) {
        if (slot != AMMO_SLOT && slot != PROPELLANT_SLOT) return 0;
        return slot == AMMO_SLOT ? this.be.getMainAmmoCapacity() : this.be.getPropellantAmmoCapacity();
    }

    @Override public boolean isItemValid(int slot, @Nonnull ItemStack stack) { return stack.getItem() instanceof ShupapiumAmmoItem; }
}
