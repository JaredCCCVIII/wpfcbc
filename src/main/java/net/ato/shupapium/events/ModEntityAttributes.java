package net.ato.shupapium.events;

import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.datagen.ShupapiumCraftingRecipeProvider;
import net.ato.shupapium.datagen.ShupapiumLangGen;
import net.ato.shupapium.entities.ShupapiumDummyRagdoll;
import net.ato.shupapium.entities.ShupapiumMetalRagdoll;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = MainShupapium.MOD_ID)
public class ModEntityAttributes {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onGatherRegistrateData(GatherDataEvent event) {
        if (!event.getMods().contains(MainShupapium.MOD_ID)) return;
        ShupapiumLangGen.prepare();
        ShupapiumCraftingRecipeProvider.register();
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ShupapiumEntities.DUMMY_RAGDOLL_ENTITY.get(), ShupapiumDummyRagdoll.createAttributes().build());
        event.put(ShupapiumEntities.HEAVY_DUMMY_RAGDOLL_ENTITY.get(), ShupapiumMetalRagdoll.createAttributes().build());
    }
}
