package net.ato.shupapium.events;

import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.client.renderers.ShupapiumDummyRagdollRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MainShupapium.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ShupapiumEntities.DUMMY_RAGDOLL_ENTITY.get(), ShupapiumDummyRagdollRenderer::new);
    }
}
