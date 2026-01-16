package net.ato.shupapium;

import com.tterrag.registrate.util.entry.MenuEntry;
import net.ato.shupapium.client.gui.ShupapiumAmmoContainerMenu;
import net.ato.shupapium.client.gui.ShupapiumAmmoContainerScreen;

public class ShupapiumMenuTypes {
    public static final MenuEntry<ShupapiumAmmoContainerMenu> SHUPAPIUM_AMMO_CONTAINER = MainShupapium.REGISTRATE
            .menu("shupapium_ammo_container", ShupapiumAmmoContainerMenu::getClientMenu, () -> ShupapiumAmmoContainerScreen::new)
            .register();

    public static void register() {}
}
