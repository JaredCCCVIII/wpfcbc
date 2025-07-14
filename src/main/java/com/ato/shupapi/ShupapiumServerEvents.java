package com.ato.shupapi;

import com.ato.shupapi.utils.ProjectileManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ShupapiumServerEvents {
    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            tickCounter++;
            if (tickCounter % 10 == 0) {
                for (ServerLevel level : event.getServer().getAllLevels()) {
                    ProjectileManager.tick(level);
                }
            }
        }
    }
}
