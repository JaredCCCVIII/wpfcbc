package net.ato.shupapium.client.gui;

import net.ato.shupapium.items.GenericPropellantItem;
import net.ato.shupapium.items.ShupapiumAmmoItem;
import net.ato.shupapium.utils.IShupapiumAmmoContainerContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static net.ato.shupapium.utils.IShupapiumAmmoContainerContainer.AMMO_SLOT;
import static net.ato.shupapium.utils.IShupapiumAmmoContainerContainer.PROPELLANT_SLOT;

public class ShupapiumAmmoContainerMenuSlot extends Slot {
    private final IShupapiumAmmoContainerContainer ammoContainer;
    private final boolean isCreative;

    public ShupapiumAmmoContainerMenuSlot(IShupapiumAmmoContainerContainer pContainer, int pSlot, int pX, int pY, boolean pIsCreative) {
        super(pContainer, pSlot, pX, pY);
        this.ammoContainer = pContainer;
        this.isCreative = pIsCreative;
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        if (stack.getItem() instanceof ShupapiumAmmoItem || stack.getItem() instanceof GenericPropellantItem) {
            if (this.getContainerSlot() == AMMO_SLOT && !(stack.getItem() instanceof ShupapiumAmmoItem)) return false;
            if (this.getContainerSlot() == PROPELLANT_SLOT && !(stack.getItem() instanceof GenericPropellantItem)) return false;
            return this.ammoContainer.getTotalCount() < 128;
        } else {
            return false;
        }
    }

    @Override
    public int getMaxStackSize(@NotNull ItemStack stack) {
        if (this.isCreative) return 1;
        int buf = Math.max(128 - this.ammoContainer.getTotalCount(), 0);
        ItemStack item = this.ammoContainer.getItem(this.getContainerSlot());
        return Math.min(item.getCount() + buf, item.getMaxStackSize());
    }
}
