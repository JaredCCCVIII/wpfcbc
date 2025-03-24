package com.ato.shupapi;

import com.ato.shupapi.entities.DaarickCitizen;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShupapiumEntityAttributes {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ShupapiumEntities.DAARICK_ENTITY.get(), DaarickCitizen.createAttributes().build());
    }
}
