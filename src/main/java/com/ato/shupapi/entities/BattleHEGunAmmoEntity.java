package com.ato.shupapi.entities;

import net.mcreator.crustychunks.entity.LargeFlakProjectileEntity;
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

public class BattleHEGunAmmoEntity extends AbstractAutocannonProjectile {
    public BattleHEGunAmmoEntity(EntityType<? extends AbstractAutocannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (!this.level().isClientSide()) {
            LargeFlakProjectileEntity shupapiumProjectile = new LargeFlakProjectileEntity(
                    CrustyChunksModEntities.LARGE_FLAK_PROJECTILE.get(),
                    this.level()
            );
            shupapiumProjectile.setPos(this.getX(), this.getY(), this.getZ());
            shupapiumProjectile.shoot(this.getDeltaMovement().x, this.getDeltaMovement().y, this.getDeltaMovement().z, 3.4F, 0.9F);
            this.level().addFreshEntity(shupapiumProjectile);
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
