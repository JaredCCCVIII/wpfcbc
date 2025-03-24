package com.ato.shupapi;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ShupapiumSounds {
    public static final DeferredRegister<SoundEvent> SONIDOS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MainShupapium.MODID);

    public static final RegistryObject<SoundEvent> DAARICK_AMBIENT = register("entity.daarick_citizen.ambient");
    public static final RegistryObject<SoundEvent> DAARICK_HURT = register("entity.daarick_citizen.hurt");
    public static final RegistryObject<SoundEvent> DAARICK_DEATH = register("entity.daarick_citizen.death");

    private static RegistryObject<SoundEvent> register(String name) {
        return SONIDOS.register(name, () -> SoundEvent.createVariableRangeEvent(MainShupapium.resource(name)));
    }

    public static void register(IEventBus eventBus) {
        SONIDOS.register(eventBus);
    }
}
