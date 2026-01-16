package net.ato.shupapium.utils;

import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class ProjectileManager {
    private static final Map<AbstractArrow, Long> tracked = new WeakHashMap<>();

    private static void projectileDiscard(AbstractArrow projectile) {
        BlockHitResult hit = new BlockHitResult(projectile.position(), projectile.getDirection(), projectile.blockPosition(), false);
        projectile.setNoGravity(false);
    }

    public static void track(AbstractArrow projectile, Level level, long durationSeconds) {
        if (!level.isClientSide()) {
            tracked.put(projectile, level.getGameTime() + (durationSeconds * 20));
        }
    }

    public static void tick(Level level) {
        long now = level.getGameTime();
        Iterator<Map.Entry<AbstractArrow, Long>> it = tracked.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<AbstractArrow, Long> entry = it.next();
            AbstractArrow projectile = entry.getKey();
            long spawnTime = entry.getValue();

            if (!projectile.isAlive()) {
                it.remove();
                continue;
            }

            if (now >= spawnTime) {
                projectileDiscard(projectile);
                it.remove();
            }
        }
    }
}
