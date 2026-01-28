package net.ato.shupapium;

import net.ato.shupapium.mobeffects.JokeEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ShupapiumMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MainShupapium.MODID);

    public static final RegistryObject<MobEffect> JOKE_EFFECT =
            EFFECTS.register("joke_effect", JokeEffect::new);

    public static void register(IEventBus bus) {
        EFFECTS.register(bus);
    }
}
