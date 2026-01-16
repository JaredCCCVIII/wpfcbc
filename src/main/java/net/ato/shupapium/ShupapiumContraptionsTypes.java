package net.ato.shupapium;

import com.simibubi.create.api.contraption.ContraptionType;
import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import com.simibubi.create.content.contraptions.Contraption;
import net.ato.shupapium.utils.MountedShupapiumACContraption;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;

import java.util.function.Supplier;

import static com.simibubi.create.AllContraptionTypes.BY_LEGACY_NAME;

public class ShupapiumContraptionsTypes {
    public static final Holder.Reference<ContraptionType>
        MOUNTED_SHUPAPIUM_AC = register("mounted_shupapium_ac", MountedShupapiumACContraption::new);
    private static Holder.Reference<ContraptionType> register(String name, Supplier<? extends Contraption> factory) {
        ContraptionType type = new ContraptionType(factory);
        BY_LEGACY_NAME.put(name, type);
        return Registry.registerForHolder(CreateBuiltInRegistries.CONTRAPTION_TYPE, MainShupapium.resource(name), type);
    }
    public static void init() {}
}
