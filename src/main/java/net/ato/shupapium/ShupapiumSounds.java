package net.ato.shupapium;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ShupapiumSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MainShupapium.MODID);

    public static final RegistryObject<SoundEvent> DUMMY_AMBIENT = register("entity.daarick_citizen.ambient");
    public static final RegistryObject<SoundEvent> DUMMY_HURT = register("entity.daarick_citizen.hurt");
    public static final RegistryObject<SoundEvent> DUMMY_DEATH = register("entity.daarick_citizen.death");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(MainShupapium.resource(name)));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
