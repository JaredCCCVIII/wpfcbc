package net.ato.shupapium.items;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rbasamoyai.createbigcannons.munitions.autocannon.config.AutocannonProjectilePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.autocannon.config.InertAutocannonProjectileProperties;

import javax.annotation.Nullable;
import java.util.List;

public interface ShupapiumAmmoItem {
    /** Set of projectiles that the ammo can produce. */
    @Nullable
    List<AbstractArrow> getAutocannonProjectile(ItemStack stack, Level level);

    @Nullable
    List<EntityType<?>> getEntityType(ItemStack stack);

    /** If the projectile can fall like a normal arrow. */
    boolean projectileAffectedByWorldsGravity();

    /** Does the projectile requires a propellant item. */
    boolean requiresPropellant(ItemStack stack);

    /** if the projectile has a different muzzle (it is added to the muzzle of the cannon). */
    default void customProjectileMuzzle(ItemStack stack, ServerLevel level, Vec3 pos, Vec3 vel) {}

    InertAutocannonProjectileProperties getMainProperties(ItemStack stack);

    AutocannonProjectilePropertiesComponent getAutocannonProperties(ItemStack itemStack);

    ItemStack getSpentItem(ItemStack stack);
}
