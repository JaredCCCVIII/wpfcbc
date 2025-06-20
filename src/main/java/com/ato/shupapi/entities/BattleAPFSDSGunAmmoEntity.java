package com.ato.shupapi.entities;

import net.mcreator.crustychunks.entity.LargeAPFireEntity;
import net.mcreator.crustychunks.init.CrustyChunksModEntities;
import net.mcreator.crustychunks.init.CrustyChunksModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.autocannon.AbstractAutocannonProjectile;
import rbasamoyai.createbigcannons.munitions.autocannon.config.InertAutocannonProjectileProperties;
import rbasamoyai.createbigcannons.munitions.config.components.BallisticPropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.EntityDamagePropertiesComponent;

public class BattleAPFSDSGunAmmoEntity extends AbstractAutocannonProjectile {
    public BattleAPFSDSGunAmmoEntity(EntityType<? extends AbstractAutocannonProjectile> type, Level level) {
        super(type, level);
    }

    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (!this.level().isClientSide()) {
            LargeAPFireEntity shupapiumProjectile = new LargeAPFireEntity(
                    CrustyChunksModEntities.LARGE_AP_FIRE.get(),
                    this.level()
            );
            shupapiumProjectile.setOwner(this.getOwner());
            shupapiumProjectile.setBaseDamage(this.getAllProperties().damage().entityDamage() / 10);
            shupapiumProjectile.setKnockback((int) this.getAllProperties().damage().knockback());
            shupapiumProjectile.setSilent(true);
            shupapiumProjectile.setPos(this.getX(), this.getY(), this.getZ());
            shupapiumProjectile.shoot(this.getDeltaMovement().x, this.getDeltaMovement().y, this.getDeltaMovement().z, 8.0F, 0.9F);
            Vec3 finalMotion = shupapiumProjectile.getDeltaMovement().add(0, this.getAllProperties().ballistics().gravity(), 0);
            shupapiumProjectile.setDeltaMovement(finalMotion);
            shupapiumProjectile.setNoGravity(true);
            this.level().addFreshEntity(shupapiumProjectile);
            this.level().playSound(null, this.blockPosition(), CrustyChunksModSounds.BATTLECANNON.get(), SoundSource.BLOCKS, 15.0F, (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
            this.discard();
            ((ServerLevel) this.level()).sendParticles(
                    ParticleTypes.LAVA,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    3,
                    0,
                    0,
                    0,
                    0.02
            );
            ((ServerLevel) this.level()).sendParticles(
                    ParticleTypes.EXPLOSION,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    1,
                    0,
                    0,
                    0,
                    0.1
            );
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
