package com.ato.shupapi.entities;

import net.mcreator.crustychunks.init.CrustyChunksModItems;
import net.mcreator.crustychunks.procedures.GasArtilleryTracerProcedure;
import net.mcreator.crustychunks.procedures.GasBombHitsBlockProcedure;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
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
public class BattleArtilleryGasGunAmmoEntity extends AbstractAutocannonProjectile implements ItemSupplier {
    public static final ItemStack PROJECTILE_ITEM;
    private boolean hitSomething = false;

    public BattleArtilleryGasGunAmmoEntity(EntityType<? extends AbstractAutocannonProjectile> type, Level level) {
        super(type, level);
    }

    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        GasBombHitsBlockProcedure.execute(this.level(), this);
        hitSomething = true;
    }

    public void onHitBlock(@NotNull BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        GasBombHitsBlockProcedure.execute(this.level(), this);
        hitSomething = true;
    }

    public void tick() {
        super.tick();
        GasArtilleryTracerProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
        if (hitSomething) {
            this.discard();
        }

    }

    @OnlyIn(Dist.CLIENT)
    public @NotNull ItemStack getItem() {
        return PROJECTILE_ITEM;
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

    static {
        PROJECTILE_ITEM = new ItemStack((ItemLike)CrustyChunksModItems.GAS_ARTILLERY_SHELL.get());
    }
}
