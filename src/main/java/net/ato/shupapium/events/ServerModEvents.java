package net.ato.shupapium.events;

import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumMobEffects;
import net.ato.shupapium.mobeffects.JokeEffect;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;

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
    public static void onMobEffectExpired(MobEffectEvent.Expired event) {
        var instance = event.getEffectInstance();
        if (instance != null && instance.getEffect().value() instanceof JokeEffect jokeEffect) {
            jokeEffect.onEffectRemoved(event.getEntity(), false);
        }
    }
}
