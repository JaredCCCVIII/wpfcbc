package net.ato.shupapium.items;

import net.ato.shupapium.utils.ProjectileManager;
import net.ato.shupapium.utils.acballistics.ShupapiumAmmoBallistics;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rbasamoyai.createbigcannons.munitions.autocannon.AbstractAutocannonProjectile;
import rbasamoyai.createbigcannons.munitions.autocannon.AutocannonAmmoType;
import rbasamoyai.createbigcannons.munitions.autocannon.config.AutocannonProjectilePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.autocannon.config.InertAutocannonProjectileProperties;

import javax.annotation.Nullable;
import java.util.List;

public interface ShupapiumAmmoItem {
    @Nullable
    List<EntityType<?>> getEntityType(ItemStack stack);

    @Nullable
    AbstractArrow createProjectile(ServerLevel level, ItemStack stack);

    void prepareProjectile(AbstractArrow projectile, ServerLevel level, ItemStack stack, @Nullable Entity owner);

    ShupapiumAmmoBallistics getBallistics(ItemStack stack);

    boolean projectileAffectedByWorldsGravity();

    boolean requiresPropellant(ItemStack stack);

    default void customProjectileMuzzle(ItemStack stack, ServerLevel level, Vec3 pos, Vec3 vel) {}

    InertAutocannonProjectileProperties getMainProperties(ItemStack stack);

    AutocannonProjectilePropertiesComponent getAutocannonProperties(ItemStack stack);

    ItemStack getSpentItem(ItemStack stack);

    default ProjectileManager.ExpireAction getExpireAction(ItemStack stack) {return ProjectileManager.ExpireAction.FALL;}
}
