package com.ato.shupapi.entities;

import net.mcreator.crustychunks.entity.HugeBulletFireEntity;
import net.mcreator.crustychunks.init.CrustyChunksModEntities;
import net.mcreator.crustychunks.init.CrustyChunksModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.autocannon.AbstractAutocannonProjectile;
import rbasamoyai.createbigcannons.munitions.autocannon.config.InertAutocannonProjectileProperties;
import rbasamoyai.createbigcannons.munitions.config.components.BallisticPropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.EntityDamagePropertiesComponent;

public class RotaryGunAmmoEntity extends AbstractAutocannonProjectile {
    public RotaryGunAmmoEntity(EntityType<? extends AbstractAutocannonProjectile> type, Level level) {
        super(type, level);
    }

    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (!this.level().isClientSide()) {
            for (int i = 0; i < 5; i++) {
                HugeBulletFireEntity shupapiumProjectile = new HugeBulletFireEntity(
                        CrustyChunksModEntities.HUGE_BULLET_FIRE.get(),
                        this.level()
                );
                shupapiumProjectile.setNoGravity(true);
                shupapiumProjectile.setPos(this.getX(), this.getY(), this.getZ());
                double spread = 0.5;
                double dx = this.getDeltaMovement().x + (this.level().getRandom().nextDouble() - 0.5) * spread;
                double dy = this.getDeltaMovement().y + (this.level().getRandom().nextDouble() - 0.5) * spread;
                double dz = this.getDeltaMovement().z + (this.level().getRandom().nextDouble() - 0.5);
                shupapiumProjectile.shoot(dx, dy, dz, 20.0F, 1.0F);
                this.level().addFreshEntity(shupapiumProjectile);
            }
            this.level().playSound(null, this.blockPosition(), CrustyChunksModSounds.SMALLEXPLOSION.get(), SoundSource.BLOCKS, 10.0F, (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
            this.discard();
        }
    }

    @Override
    public @NotNull EntityDamagePropertiesComponent getDamageProperties() {
        return this.getAllProperties().damage();
    }

    @Override
    protected @NotNull BallisticPropertiesComponent getBallisticProperties() {
        return this.getAllProperties().ballistics();
    }

    protected InertAutocannonProjectileProperties getAllProperties() {
        return CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE.getPropertiesOf(this);
    }
}
