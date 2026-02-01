package net.ato.shupapium.events;

import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumMobEffects;
import net.ato.shupapium.utils.ProjectileManager;
import net.mcreator.crustychunks.entity.LargeSolidProjectileEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
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
    private static int tickCounter = 0;

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

            if (!target.hurt(projectile.level().damageSources().arrow(projectile, projectile.getOwner()), 0.01F)) {
                ProjectileManager.projectileDiscard(projectile, true);
            }
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            tickCounter++;
            if (tickCounter % 10 == 0) {
                for (ServerLevel level : event.getServer().getAllLevels()) {
                    ProjectileManager.tick(level);
                }
            }
        }
    }
}
