package net.ato.shupapium.utils;

import net.ato.shupapium.items.GenericPropellantItem;
import net.ato.shupapium.items.ShupapiumAmmoItem;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface IShupapiumAmmoContainerContainer extends Container {
    int AMMO_SLOT = 0;
    int PROPELLANT_SLOT = 1;

    ItemStack getMainAmmoStack();
    ItemStack getPropellantStack();

    default int getMainAmmoCapacity() {
        int remainder = Math.max(0, 128 - this.getTotalCount());
        ItemStack stack = this.getMainAmmoStack();
        return Math.min(stack.getCount() + remainder, stack.getMaxStackSize());
    }

    default int getPropellantAmmoCapacity() {
        int remainder = Math.max(0, 128 - this.getTotalCount());
        ItemStack stack = this.getPropellantStack();
        return Math.min(stack.getCount() + remainder, stack.getMaxStackSize());
    }

    default int getTotalCount() { return this.getMainAmmoStack().getCount() + this.getPropellantStack().getCount(); }

    @Override default int getContainerSize() { return 2; }

    @Override
    default boolean isEmpty() {
        return this.getMainAmmoStack().isEmpty() && this.getPropellantStack().isEmpty();
    }

    @Override
    default @NotNull ItemStack getItem(int slot) {
        return switch (slot) {
            case 0 -> this.getMainAmmoStack();
            case 1 -> this.getPropellantStack();
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    default boolean canPlaceItem(int index, @NotNull ItemStack stack) {
        if (index != AMMO_SLOT && index != PROPELLANT_SLOT) return false;
        if (!(stack.getItem() instanceof ShupapiumAmmoItem) || !(stack.getItem() instanceof GenericPropellantItem)) return false;
        if (index == AMMO_SLOT && !(stack.getItem() instanceof ShupapiumAmmoItem)) return false;
        if (index == PROPELLANT_SLOT && !(stack.getItem() instanceof GenericPropellantItem)) return false;
        boolean ammoSlot = index == AMMO_SLOT;
        int currentCapacity;
        currentCapacity = ammoSlot ? this.getMainAmmoCapacity() : this.getPropellantAmmoCapacity();
        currentCapacity -= this.getItem(index).getCount();
        return currentCapacity > 0;
    }
}
