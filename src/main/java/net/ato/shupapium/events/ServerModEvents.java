package net.ato.shupapium.events;

import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumAttachments;
import net.ato.shupapium.ShupapiumMobEffects;
import net.ato.shupapium.mobeffects.JokeEffect;
import net.ato.shupapium.utils.ProjectileManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

@EventBusSubscriber(modid = MainShupapium.MOD_ID)
public class ServerModEvents {
    @SubscribeEvent
    public static void onMobEffectRemoved(MobEffectEvent.Remove event) {
        var instance = event.getEffectInstance();
        if (instance != null && instance.getEffect().value() instanceof JokeEffect jokeEffect) {
            jokeEffect.onEffectRemoved(event.getEntity(), true);
        }
    }

    @SubscribeEvent
    public static void onLevelTick(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ServerLevel level) ProjectileManager.tick(level);
    }

    @SubscribeEvent
    public static void onProjectileJoin(EntityJoinLevelEvent event) {
        if (event.getLevel() instanceof ServerLevel level && event.getEntity() instanceof AbstractArrow projectile && projectile.hasData(ShupapiumAttachments.TRACK)) {
            ProjectileManager.enqueue(level, projectile);
        }
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        ProjectileManager.clear();
    }

    @SubscribeEvent
    public static void onMobEffectExpired(MobEffectEvent.Expired event) {
        var instance = event.getEffectInstance();
        if (instance != null && instance.getEffect().value() instanceof JokeEffect jokeEffect) {
            jokeEffect.onEffectRemoved(event.getEntity(), false);
        }
    }
}
