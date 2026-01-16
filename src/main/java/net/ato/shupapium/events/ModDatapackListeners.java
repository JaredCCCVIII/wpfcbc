package net.ato.shupapium.events;

import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.utils.actypes.ShupapiumACProfileListener;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MainShupapium.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModDatapackListeners {
    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(new ShupapiumACProfileListener());
    }
}
