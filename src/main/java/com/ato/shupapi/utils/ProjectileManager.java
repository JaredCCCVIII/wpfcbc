package com.ato.shupapi.utils;

import net.mcreator.crustychunks.entity.*;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class ProjectileManager {
    private static final Map<Entity, Long> tracked = new WeakHashMap<>();

    private static void airImpact(Entity e) {
        e.getDirection();
        BlockHitResult hit = new BlockHitResult(
                e.position(),
                e.getDirection(),
                e.blockPosition(),
                false
        );

        if (e instanceof RocketEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof LargeAPFireEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof GasArtilleryProjectileEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof ArtilleryFireProjectileEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof ArtillerySolidProjectileEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof LargeHEATFireEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof LargeFlakProjectileEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof LargeSolidProjectileEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof FireSpearRocketProjectileEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof SmallShellFireEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof HugeBulletFireEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof GenericlargeBulletEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof MortarProjectileEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof SeekerSpearMissileProjectileEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof SmokeLauncherProjectileEntity projectile) projectile.onHitBlock(hit);
        else if (e instanceof StrikeSpearProjectileEntity projectile) projectile.onHitBlock(hit);
        else e.discard();

    }

    public static void track(Entity entity, Level level, long durationTicks) {
        if (!level.isClientSide()) {
            tracked.put(entity, level.getGameTime() + durationTicks);
        }
    }

    public static void tick(Level level) {
        long now = level.getGameTime();
        Iterator<Map.Entry<Entity, Long>> it = tracked.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<Entity, Long> entry = it.next();
            Entity entity = entry.getKey();
            long spawnTime = entry.getValue();

            if (!entity.isAlive()) {
                it.remove();
                continue;
            }

            if (now >= spawnTime) {
                airImpact(entity);
                it.remove();
            }
        }
    }
}
