package net.ato.shupapium;

import net.ato.shupapium.entities.ShupapiumDummyRagdoll;
import net.ato.shupapium.entities.ShupapiumMetalRagdoll;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ShupapiumEntities {
    public static final DeferredRegister<EntityType<?>> SHUPAPI_ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MainShupapium.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<ShupapiumDummyRagdoll>> DUMMY_RAGDOLL_ENTITY = SHUPAPI_ENTITIES.register(
            "dummy_ragdoll", () -> EntityType.Builder.of(ShupapiumDummyRagdoll::new, MobCategory.CREATURE).sized(0.8F, 1.9F).build("dummy_ragdoll")
    );
    public static final DeferredHolder<EntityType<?>, EntityType<ShupapiumMetalRagdoll>> HEAVY_DUMMY_RAGDOLL_ENTITY = SHUPAPI_ENTITIES.register(
            "heavy_dummy_ragdoll", () -> EntityType.Builder.of(ShupapiumMetalRagdoll::new, MobCategory.CREATURE).sized(0.8F, 1.9F).build("heavy_dummy_ragdoll")
    );

    public static void register(IEventBus eventBus) {
        SHUPAPI_ENTITIES.register(eventBus);
    }
}
