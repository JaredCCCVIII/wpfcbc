package com.ato.shupapi;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ShupapiumItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MainShupapium.MODID);

    public static final RegistryObject<Item> DAARICK_CITIZEN_SPAWN_EGG =
            ITEMS.register("daarick_citizen_spawn_egg",
                    () -> new ForgeSpawnEggItem(ShupapiumEntities.DAARICK_ENTITY,
                            0x00FFFF,
                            0x8B4513,
                            new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
