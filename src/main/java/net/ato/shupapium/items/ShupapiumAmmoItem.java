package net.ato.shupapium.items;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.munitions.autocannon.AbstractAutocannonProjectile;
import rbasamoyai.createbigcannons.munitions.autocannon.AutocannonAmmoType;
import rbasamoyai.createbigcannons.munitions.autocannon.config.AutocannonProjectilePropertiesComponent;

import javax.annotation.Nullable;

public interface ShupapiumAmmoItem {
    @Nullable
    AbstractAutocannonProjectile getAutocannonProjectile(ItemStack stack, Level level);
    @Nullable
    EntityType<?> getEntityType(ItemStack stack);

    AutocannonProjectilePropertiesComponent getAutocannonProperties(ItemStack itemStack);

    ItemStack getSpentItem(ItemStack stack);

    AutocannonAmmoType getType();
}
