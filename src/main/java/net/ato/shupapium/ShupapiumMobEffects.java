package net.ato.shupapium;

import net.ato.shupapium.mobeffects.JokeEffect;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ShupapiumMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MainShupapium.MOD_ID);

    public static final DeferredHolder<MobEffect, JokeEffect> JOKE_EFFECT = EFFECTS.register("joke_effect", JokeEffect::new);

    public static void register(IEventBus bus) {
        EFFECTS.register(bus);
    }
}
