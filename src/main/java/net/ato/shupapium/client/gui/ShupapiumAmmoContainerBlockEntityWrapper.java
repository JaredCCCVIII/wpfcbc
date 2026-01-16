package net.ato.shupapium.client.gui;

import net.ato.shupapium.blockentities.ShupapiumAmmoContainerBlockEntity;
import net.ato.shupapium.utils.IShupapiumAmmoContainerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ShupapiumAmmoContainerBlockEntityWrapper implements IShupapiumAmmoContainerContainer {
    @Nullable
    private final ShupapiumAmmoContainerBlockEntity be;
    private final BlockPos pos;
    public ShupapiumAmmoContainerBlockEntityWrapper(@Nullable ShupapiumAmmoContainerBlockEntity be, BlockPos pos) {
        this.be = be;
        this.pos = pos;
    }

    @Override public ItemStack getMainAmmoStack() { return this.be == null ? ItemStack.EMPTY : this.be.getMainAmmoStack(); }
    @Override public ItemStack getPropellantStack() { return this.be == null ? ItemStack.EMPTY : this.be.getPropellantStack(); }
    @Override public @NotNull ItemStack removeItem(int slot, int amount) { return this.be == null ? ItemStack.EMPTY : this.be.removeItem(slot, amount); }
    @Override public @NotNull ItemStack removeItemNoUpdate(int slot) { return this.be == null ? ItemStack.EMPTY : this.be.removeItemNoUpdate(slot); }
    @Override public void setItem(int slot, @NotNull ItemStack stack) { if (this.be != null) this.be.setItem(slot, stack); }
    @Override public void setChanged() { if (this.be != null) this.be.setChanged(); }
    @Override public void clearContent() { if (this.be != null) this.be.clearContent(); }

    @Override
    public boolean canPlaceItem(int index, @NotNull ItemStack stack) {
        return this.be != null && this.be.canPlaceItem(index, stack);
    }

    @Override
    public boolean stillValid(Player player) {
        return this.pos.closerThan(player.blockPosition(), 4);
    }
}
