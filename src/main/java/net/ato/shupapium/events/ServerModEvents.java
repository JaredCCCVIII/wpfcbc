package net.ato.shupapium.events;

import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumMobEffects;
import net.ato.shupapium.utils.ProjectileManager;
import net.mcreator.crustychunks.entity.LargeSolidProjectileEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ServerModEvents {

    @SubscribeEvent
    public static void onUsedItem(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(event.getItem().getItem() instanceof MilkBucketItem)) return;
        if (!player.hasEffect(ShupapiumMobEffects.JOKE_EFFECT.get())) return;

        if (!player.level().isClientSide()) {
            player.getPersistentData().putBoolean("ChistosadaCure", true);
        }
    }

    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (!(event.getProjectile() instanceof AbstractArrow projectile)) return;
        if (!projectile.getTags().contains("shupapiumProjectile")) return;
        HitResult hit = event.getRayTraceResult();
        if (hit.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHit =  (EntityHitResult) hit;
            Entity target = entityHit.getEntity();

            if (target instanceof LivingEntity living) {
                MainShupapium.LOGGER.info("The {} health is {}", living, living.getHealth());
            }

            if (!target.hurt(projectile.level().damageSources().arrow(projectile, projectile.getOwner()), 0.01F)) {
                ProjectileManager.projectileDiscard(projectile, true);
            }
        }
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.level instanceof ServerLevel svLvl)) return;

        long gameTime = event.level.getGameTime();

        for (Entity entity : svLvl.getAllEntities()) {
            if (!(entity instanceof AbstractArrow projectile)) continue;
            if (!projectile.getTags().contains("shupapiumProjectile")) return;

            long expire = projectile.getPersistentData().getLong("shupapiumLifeTime");

            if (expire != 0 && gameTime >= expire) {
                projectile.getPersistentData().remove("shupapiumLifeTime");
                ProjectileManager.projectileDiscard(projectile, false);
            }
        }
    }
}
