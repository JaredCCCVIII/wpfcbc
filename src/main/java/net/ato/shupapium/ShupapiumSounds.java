package net.ato.shupapium;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ShupapiumSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MainShupapium.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> DUMMY_AMBIENT = register("entity.daarick_citizen.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> DUMMY_HURT = register("entity.daarick_citizen.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> DUMMY_DEATH = register("entity.daarick_citizen.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> METAL_DUMMY_AMBIENT = register("entity.metal_ragdoll.ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> METAL_DUMMY_HURT = register("entity.metal_ragdoll.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> METAL_DUMMY_DEATH = register("entity.metal_ragdoll.death");

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(MainShupapium.resource(name)));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
