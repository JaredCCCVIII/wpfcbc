package com.ato.shupapi;

import com.ato.shupapi.entities.DaarickCitizen;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ShupapiumEvents {
    @SubscribeEvent

    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Mob mob) {
            Level level = mob.level();
            for (LivingEntity entity : level.getEntitiesOfClass(DaarickCitizen.class, mob.getBoundingBox().inflate(20))) {
                mob.setTarget(entity);
            }
        }
    }
}
