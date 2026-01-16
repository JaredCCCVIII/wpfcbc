package net.ato.shupapium.events;

import net.ato.shupapium.utils.ShupapiumAmmoProperties;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import org.jetbrains.annotations.NotNull;

public class ShupapiumReloadListener implements ResourceManagerReloadListener {
    @Override
    public void onResourceManagerReload(@NotNull ResourceManager pResourceManager) {
        ShupapiumAmmoProperties.load(pResourceManager);
    }
}
