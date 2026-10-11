package net.ato.shupapium.utils.actypes;

import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class ShupapiumACProfileHandler {
    private static final Map<ShupapiumACParts, ShupapiumACProfile> BY_PARTS = new HashMap<>();
    private static final Map<ResourceLocation, ShupapiumACProfile> BY_ID = new HashMap<>();

    private ShupapiumACProfileHandler() {}

    public static void register(ShupapiumACProfile profile) {
        for (ShupapiumACParts parts : profile.allParts()) {
            ShupapiumACProfile clash = BY_PARTS.putIfAbsent(parts, profile);
            if (clash != null) {
                throw new IllegalStateException("AC Profiles: " + clash.getProfileId() + " and " + profile.getProfileId() + " use the same barrel + breech");
            }
        }
        if (BY_ID.putIfAbsent(profile.getProfileId(), profile) != null) throw new IllegalStateException("Duplicated profile: " + profile.getProfileId());
    }

    public static @Nullable ShupapiumACProfile getProfile(ShupapiumACParts parts) {return BY_PARTS.get(parts);}
    public static @Nullable ShupapiumACProfile getProfile(ResourceLocation id) {return BY_ID.get(id);}
}
