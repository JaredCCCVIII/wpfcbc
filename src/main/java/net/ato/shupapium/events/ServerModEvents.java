package net.ato.shupapium.events;

import net.ato.shupapium.ShupapiumMobEffects;
import net.ato.shupapium.utils.ProjectileManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ServerModEvents {
    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onUsedItem(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(event.getItem().getItem() instanceof MilkBucketItem)) return;
        if (!player.hasEffect(ShupapiumMobEffects.JOKE_EFFECT.get())) return;

        if (!player.level().isClientSide()) {
            player.getPersistentData().putBoolean("ChistosadaCure", true);
        }
    }

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
