package net.ato.shupapium;

import net.ato.shupapium.client.renderers.ShupapiumDummyRagdollRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = MainShupapium.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = MainShupapium.MOD_ID, value = Dist.CLIENT)
public class MainShupapiumClient {
    public MainShupapiumClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code

    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ShupapiumEntities.DUMMY_RAGDOLL_ENTITY.get(), ShupapiumDummyRagdollRenderer::new);
        event.registerEntityRenderer(ShupapiumEntities.HEAVY_DUMMY_RAGDOLL_ENTITY.get(), ShupapiumDummyRagdollRenderer::new);
    }
}
