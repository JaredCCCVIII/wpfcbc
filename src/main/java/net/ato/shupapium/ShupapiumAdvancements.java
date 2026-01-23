package net.ato.shupapium;

import net.ato.shupapium.advancements.NuclearDetonationTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class ShupapiumAdvancements {
    public static final NuclearDetonationTrigger NUCLEAR_DETONATION = CriteriaTriggers.register(new NuclearDetonationTrigger());
    public static void register() {}
}
