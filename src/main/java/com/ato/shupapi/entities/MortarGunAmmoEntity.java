package com.ato.shupapi.entities;

import net.mcreator.crustychunks.entity.ArtillerySolidProjectileEntity;
import net.mcreator.crustychunks.entity.MortarProjectileEntity;
import net.mcreator.crustychunks.init.CrustyChunksModEntities;
import net.mcreator.crustychunks.init.CrustyChunksModSounds;
import net.mcreator.crustychunks.procedures.MuzzleFlashProcedure;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
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

public class MortarGunAmmoEntity extends AbstractAutocannonProjectile {
    public MortarGunAmmoEntity(EntityType<? extends AbstractAutocannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (!this.level().isClientSide()) {
            MortarProjectileEntity shupapiumProjectile = new MortarProjectileEntity(
                    CrustyChunksModEntities.MORTAR_PROJECTILE.get(),
                    this.level()
            );
            shupapiumProjectile.addTag("shupapiumProjectile");
            shupapiumProjectile.setOwner(this.getOwner());
            shupapiumProjectile.setBaseDamage(this.getAllProperties().damage().entityDamage() / 10);
            shupapiumProjectile.setKnockback((int) this.getAllProperties().damage().knockback());
            shupapiumProjectile.setPos(this.getX(), this.getY(), this.getZ());
            shupapiumProjectile.shoot(this.getDeltaMovement().x, this.getDeltaMovement().y, this.getDeltaMovement().z, 8.5F, 0.3F);
            this.level().addFreshEntity(shupapiumProjectile);
            this.level().playSound(null, this.blockPosition(), CrustyChunksModSounds.CANNONCLOSE.get(), SoundSource.BLOCKS, 20.0F, (float) Mth.nextDouble(RandomSource.create(), 0.7, 0.8));
            this.discard();
            MuzzleFlashProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ());
            ((ServerLevel) this.level()).sendParticles(
                    ParticleTypes.CAMPFIRE_COSY_SMOKE,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    2,
                    0.1,
                    0.1,
                    0.1,
                    0.01
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
