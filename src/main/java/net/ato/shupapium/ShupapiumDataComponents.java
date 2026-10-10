package net.ato.shupapium;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.component.ItemContainerContents;

import java.util.function.UnaryOperator;

public class ShupapiumDataComponents {
    public static final DataComponentType<ItemContainerContents> AMMO = register(
            "ammo",
            builder -> builder.persistent(ItemContainerContents.CODEC).networkSynchronized(ItemContainerContents.STREAM_CODEC)
    );
    public static final DataComponentType<ItemContainerContents> PROPELLANTS = register(
            "propellants",
            builder -> builder.persistent(ItemContainerContents.CODEC).networkSynchronized(ItemContainerContents.STREAM_CODEC)
    );

    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, MainShupapium.resource(name), builder.apply(DataComponentType.builder()).build());
    }

    public static void init() {}
}
