package net.ato.shupapium.utils;

import net.ato.shupapium.MainShupapium;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class ProjectileManager {
    public static void projectileDiscard(AbstractArrow projectile, boolean activeHit) {
        MainShupapium.LOGGER.info("Projectile Discarded!: {}", projectile);
        if (activeHit) {
            BlockHitResult hit = new BlockHitResult(projectile.position(), projectile.getDirection(), projectile.blockPosition(), false);
            onHitBlock(projectile, hit);
        } else {
            projectile.setNoGravity(false);
        }
    }

    public static void track(AbstractArrow projectile, Level level, long durationSeconds) {
        if (!level.isClientSide()) {
            projectile.getPersistentData().putLong("shupapiumLifeTime", level.getGameTime() + (durationSeconds * 20));
        }
    }

    private static void onHitBlock(AbstractArrow projectile, BlockHitResult pResult) {
        BlockState blockstate = projectile.level().getBlockState(pResult.getBlockPos());
        blockstate.onProjectileHit(projectile.level(), blockstate, pResult, projectile);
        Vec3 vec3 = pResult.getLocation().subtract(projectile.getX(), projectile.getY(), projectile.getZ());
        projectile.setDeltaMovement(vec3);
        Vec3 vec31 = vec3.normalize().scale(0.05F);
        projectile.setPosRaw(projectile.getX() - vec31.x, projectile.getY() - vec31.y, projectile.getZ() - vec31.z);
        projectile.shakeTime = 7;
        projectile.setCritArrow(false);
        projectile.setPierceLevel((byte)0);
        projectile.setShotFromCrossbow(false);
    }
}
