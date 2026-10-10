package net.ato.shupapium;

import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class ShupapiumConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue MAX_SAC_FIRE_RATE = BUILDER
            .comment("Maximum RPM of an AC?")
            .defineInRange("maxFireRate", 7000, 1, Integer.MAX_VALUE);
    public static final ModConfigSpec.BooleanValue AC_HEAT = BUILDER
            .comment("Do the mod's barrels heat up?")
            .define("acHeat", false);

    static final ModConfigSpec SPEC = BUILDER.build();
}
