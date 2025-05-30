package com.ato.shupapi;

import com.ato.shupapi.items.*;
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
    public static final RegistryObject<Item> MACHINE_GUN_AMMO_ITEM =
            ITEMS.register("machine_gun_ammo_item",
                    () -> new MachineGunAmmoItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LIGHT_GUN_AMMO_ITEM =
            ITEMS.register("light_gun_ammo_item",
                    () -> new LightGunAmmoItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> ROTARY_GUN_AMMO_ITEM =
            ITEMS.register("rotary_gun_ammo_item",
                    () -> new RotaryGunAmmoItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> HEAVY_GUN_AMMO_ITEM =
            ITEMS.register("heavy_gun_ammo_item",
                    () -> new HeavyGunAmmoItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BATTLE_HE_GUN_AMMO_ITEM =
            ITEMS.register("battle_he_gun_ammo_item",
                    () -> new BattleHEGunAmmoItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BATTLE_SOLID_GUN_AMMO_ITEM =
            ITEMS.register("battle_solid_gun_ammo_item",
                    () -> new BattleSolidGunAmmoItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BATTLE_HEAT_GUN_AMMO_ITEM =
            ITEMS.register("battle_heat_gun_ammo_item",
                    () -> new BattleHeatGunAmmoItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BATTLE_APFSDS_GUN_AMMO_ITEM =
            ITEMS.register("battle_apfsds_gun_ammo_item",
                    () -> new BattleAPFSDSGunAmmoItem(new Item.Properties().stacksTo(64)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
