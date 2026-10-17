package net.ato.shupapium.custom_interactions;

import net.ato.shupapium.ShupapiumDataComponents;
import net.ato.shupapium.ShupapiumRecipeTypes;
import net.ato.shupapium.items.GenericPropellantItem;
import net.ato.shupapium.items.ShupapiumAmmoContainerItem;
import net.ato.shupapium.items.ShupapiumAmmoItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShupapiumAmmoContainerFillingDeployerRecipe implements Recipe<RecipeInput> {
    private final ItemStack ammoContainer;
    private final ItemStack insertedAmmo;

    public ShupapiumAmmoContainerFillingDeployerRecipe(ItemStack ammoContainer, ItemStack insertedAmmo) {
        this.ammoContainer = ammoContainer;
        this.insertedAmmo = insertedAmmo;
    }

    public ShupapiumAmmoContainerFillingDeployerRecipe() {
        this(ItemStack.EMPTY, ItemStack.EMPTY);
    }

    public ShupapiumAmmoContainerFillingDeployerRecipe(CraftingBookCategory cat) {
        this();
    }

    @Override
    public boolean matches(@NotNull RecipeInput recipeInput, @NotNull Level level) {
        if (!(this.ammoContainer.getItem() instanceof ShupapiumAmmoContainerItem containerItem) || containerItem.isCreative()) return false;
        boolean propellant = this.insertedAmmo.getItem() instanceof GenericPropellantItem;
        if (!propellant && !(this.insertedAmmo.getItem() instanceof ShupapiumAmmoItem)) return false;
        ItemStack existing = propellant ? ShupapiumAmmoContainerItem.getPropellantAmmoStack(this.ammoContainer) : ShupapiumAmmoContainerItem.getMainAmmoStack(this.ammoContainer);
        if (!existing.isEmpty() && !ItemStack.isSameItemSameComponents(existing, this.insertedAmmo)) return false;
        return ShupapiumAmmoContainerItem.getTotalAmmoCount(this.ammoContainer) < 128 && existing.getCount() < existing.getMaxStackSize();
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull RecipeInput recipeInput, HolderLookup.@NotNull Provider provider) {
        return this.getResultItem(provider);
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(HolderLookup.@NotNull Provider provider) {
        boolean propellant = this.insertedAmmo.getItem() instanceof GenericPropellantItem;
        if (!propellant && !(this.insertedAmmo.getItem() instanceof ShupapiumAmmoItem)) return ItemStack.EMPTY;
        ItemStack result = this.ammoContainer.copy();
        ItemStack existing = propellant ? ShupapiumAmmoContainerItem.getPropellantAmmoStack(result) : ShupapiumAmmoContainerItem.getMainAmmoStack(result);
        ItemStack insert = existing.isEmpty() ? this.insertedAmmo.copyWithCount(1) : existing;
        if (!existing.isEmpty()) insert.grow(1);
        result.set(propellant ? ShupapiumDataComponents.PROPELLANTS : ShupapiumDataComponents.AMMO, ItemContainerContents.fromItems(List.of(insert)));
        return result;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ShupapiumRecipeTypes.SHUPAPIUM_AMMO_CONTAINER_FILLING_DEPLOYER.getSerializer();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return ShupapiumRecipeTypes.SHUPAPIUM_AMMO_CONTAINER_FILLING_DEPLOYER.getType();
    }
}
