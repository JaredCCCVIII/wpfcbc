package com.ato.shupapi;

import com.ato.shupapi.entities.DaarickCitizen;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ShupapiumEntities {
    public static final DeferredRegister<EntityType<?>> SHUPAPI_MOBS =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MainShupapium.MODID);

    public static final RegistryObject<EntityType<DaarickCitizen>> DAARICK_ENTITY =
            SHUPAPI_MOBS.register("daarick_citizen", () -> EntityType.Builder.of(DaarickCitizen::new, MobCategory.CREATURE)
                    .sized(0.8f, 1.9f).build("daarick_citizen"));

    public static void register(IEventBus eventBus) {
        SHUPAPI_MOBS.register(eventBus);
    }
}
