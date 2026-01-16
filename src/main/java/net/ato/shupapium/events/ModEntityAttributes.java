package net.ato.shupapium.events;

import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.entities.ShupapiumDummyRagdoll;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityAttributes {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ShupapiumEntities.DUMMY_RAGDOLL_ENTITY.get(), ShupapiumDummyRagdoll.createAttributes().build());
    }
}
