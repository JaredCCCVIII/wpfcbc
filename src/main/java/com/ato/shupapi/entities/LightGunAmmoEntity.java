package com.ato.shupapi.entities;

import net.mcreator.crustychunks.entity.HugeBulletFireEntity;
import net.mcreator.crustychunks.init.CrustyChunksModEntities;
import net.mcreator.crustychunks.init.CrustyChunksModItems;
import net.mcreator.crustychunks.init.CrustyChunksModSounds;
import net.mcreator.crustychunks.procedures.BulletTracerProcedure;
import net.mcreator.crustychunks.procedures.HugeBulletEntityHitProcedure;
import net.mcreator.crustychunks.procedures.HugeBulletHitProcedure;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.autocannon.AbstractAutocannonProjectile;
import rbasamoyai.createbigcannons.munitions.autocannon.config.InertAutocannonProjectileProperties;
import rbasamoyai.createbigcannons.munitions.config.components.BallisticPropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.EntityDamagePropertiesComponent;

@OnlyIn(
        value = Dist.CLIENT,
        _interface = ItemSupplier.class
)
public class LightGunAmmoEntity extends AbstractAutocannonProjectile implements ItemSupplier {
    public static final ItemStack PROJECTILE_ITEM;
    private boolean hitSomething = false;
    public LightGunAmmoEntity(EntityType<? extends AbstractAutocannonProjectile> type, Level level) {
        super(type, level);
    }

    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @OnlyIn(Dist.CLIENT)
    public @NotNull ItemStack getItem() {
        return PROJECTILE_ITEM;
    }

    public void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        hitSomething = true;
        HugeBulletEntityHitProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), entityHitResult.getEntity(), this);
    }

    public void onHitBlock(@NotNull BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        hitSomething = true;
        HugeBulletHitProcedure.execute(this.level(), (double)blockHitResult.getBlockPos().getX(), (double)blockHitResult.getBlockPos().getY(), (double)blockHitResult.getBlockPos().getZ(), this);
    }

    public void tick() {
        super.tick();
        BulletTracerProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
        if (hitSomething) {
            this.discard();
        }

    }

    static {
        PROJECTILE_ITEM = new ItemStack((ItemLike) CrustyChunksModItems.TINYPROJECTILE_ITEM.get());
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (!this.level().isClientSide()) {
            this.level().playSound(null, this.blockPosition(), CrustyChunksModSounds.AUTOCANNONSHOT.get(), SoundSource.BLOCKS, 10.0F, (float) Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
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
