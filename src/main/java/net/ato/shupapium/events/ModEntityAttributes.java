package net.ato.shupapium.events;

import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.entities.ShupapiumDummyRagdoll;
import net.ato.shupapium.entities.ShupapiumMetalRagdoll;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = MainShupapium.MOD_ID)
public class ModEntityAttributes {
    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ShupapiumEntities.DUMMY_RAGDOLL_ENTITY.get(), ShupapiumDummyRagdoll.createAttributes().build());
        event.put(ShupapiumEntities.HEAVY_DUMMY_RAGDOLL_ENTITY.get(), ShupapiumMetalRagdoll.createAttributes().build());
    }
}
