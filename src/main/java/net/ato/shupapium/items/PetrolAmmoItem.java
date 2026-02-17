package net.ato.shupapium.items;

import net.ato.shupapium.MainShupapium;
import net.mcreator.crustychunks.init.CrustyChunksModEntities;
import net.mcreator.crustychunks.init.CrustyChunksModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import rbasamoyai.createbigcannons.index.CBCItems;

import java.util.List;
import java.util.Objects;

public class PetrolAmmoItem extends AbstractShupapiumACAmmoItem {
    public PetrolAmmoItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected ResourceLocation propertiesLocation() {
        return ResourceLocation.fromNamespaceAndPath(MainShupapium.MODID, "munition_properties/projectiles/petrol_canister");
    }

    @Override
    public @Nullable List<AbstractArrow> getAutocannonProjectile(ItemStack stack, Level level) {
        return List.of(Objects.requireNonNull(CrustyChunksModEntities.FLAME_THROWER_EMBER.get().create(level)));
    }

    @Override
    public @Nullable List<EntityType<?>> getEntityType(ItemStack stack) {
        return List.of(CrustyChunksModEntities.FLAME_THROWER_EMBER.get());
    }

    @Override
    public boolean projectileAffectedByWorldsGravity() {
        return true;
    }

    @Override
    public boolean requiresPropellant(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack getSpentItem(ItemStack stack) {
        return CBCItems.GUNPOWDER_PINCH.get().getDefaultInstance();
    }
}
