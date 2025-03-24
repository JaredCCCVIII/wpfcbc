package com.ato.shupapi;

import com.ato.shupapi.entities.models.DaarickRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShupapiumRenders {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ShupapiumEntities.DAARICK_ENTITY.get(), DaarickRenderer::new);
    }
}
