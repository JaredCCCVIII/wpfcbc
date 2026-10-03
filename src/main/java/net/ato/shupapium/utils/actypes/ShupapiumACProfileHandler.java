package net.ato.shupapium.utils.actypes;

import net.ato.shupapium.MainShupapium;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class ShupapiumACProfileHandler {
    private static final Map<ResourceLocation, ShupapiumACProfile> PROFILES = new HashMap<>();

    public static void clear() {
        PROFILES.clear();
    }

    public static void register(ShupapiumACProfile profile) {
        if (PROFILES.containsValue(profile)) {
            MainShupapium.LOGGER.warn("{} already registered", profile.getProfileId());
            return;
        }
        PROFILES.put(profile.getProfileId(), profile);
    }

    public static ShupapiumACProfile getProfile(ResourceLocation id) {
        return PROFILES.get(id);
    }

    public static ShupapiumACProfile getProfile(ShupapiumACParts acParts) {
        for (ShupapiumACProfile profile : PROFILES.values()) {
            if (profile.parts().equals(acParts)) {
                return profile;
            }
        }
        return null;
    }
}
