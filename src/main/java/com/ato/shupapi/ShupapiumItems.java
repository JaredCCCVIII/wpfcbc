package com.ato.shupapi;

import com.ato.shupapi.items.*;
import com.simibubi.create.foundation.item.ItemDescription;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rbasamoyai.createbigcannons.CBCTags;

public class ShupapiumItems {
    //Normal items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MainShupapium.MODID);

    public static final RegistryObject<Item> DAARICK_CITIZEN_SPAWN_EGG =
            ITEMS.register("daarick_citizen_spawn_egg",
                    () -> new ForgeSpawnEggItem(ShupapiumEntities.DAARICK_ENTITY,
                            0x00FFFF,
                            0x8B4513,
                            new Item.Properties()));
    //Create items
    public static final ItemEntry<MachineGunAmmoItem> MACHINE_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("machine_gun_ammo_item", MachineGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.machinegunammo"))
            .register();
    public static final ItemEntry<LightGunAmmoItem> LIGHT_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("light_gun_ammo_item", LightGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.lightgunammo"))
            .register();
    public static final ItemEntry<RotaryGunAmmoItem> ROTARY_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("rotary_gun_ammo_item", RotaryGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.rotarygunammo"))
            .register();
    public static final ItemEntry<HeavyGunAmmoItem> HEAVY_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("heavy_gun_ammo_item", HeavyGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.heavygunammo"))
            .register();
    public static final ItemEntry<BattleHEGunAmmoItem> BATTLE_HE_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("battle_he_gun_ammo_item", BattleHEGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.battlehegunammo"))
            .register();
    public static final ItemEntry<BattleSolidGunAmmoItem> BATTLE_SOLID_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("battle_solid_gun_ammo_item", BattleSolidGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.battlesolidgunammo"))
            .register();
    public static final ItemEntry<BattleHeatGunAmmoItem> BATTLE_HEAT_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("battle_heat_gun_ammo_item", BattleHeatGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.battleheatgunammo"))
            .register();
    public static final ItemEntry<BattleAPFSDSGunAmmoItem> BATTLE_APFSDS_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("battle_apfsds_gun_ammo_item", BattleAPFSDSGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.battleapfsdsgunammo"))
            .register();
    public static final ItemEntry<BattleArtillerySolidGunAmmoItem> BATTLE_ARTILLERY_SOLID_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("battle_artillery_solid_gun_ammo_item", BattleArtillerySolidGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.battleartillerysolidgunammo"))
            .register();
    public static final ItemEntry<BattleArtilleryGasGunAmmoItem> BATTLE_ARTILLERY_GAS_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("battle_artillery_gas_gun_ammo_item", BattleArtilleryGasGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.battleartillerygasgunammo"))
            .register();
    public static final ItemEntry<BattleArtilleryHEGunAmmoItem> BATTLE_ARTILLERY_HE_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("battle_artillery_he_gun_ammo_item", BattleArtilleryHEGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.battleartilleryhegunammo"))
            .register();
    public static final ItemEntry<ArmorPeelerGunAmmoItem> ARMOR_PEELER_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("armor_peeler_gun_ammo_item", ArmorPeelerGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.armorpeelergunammo"))
            .register();
    public static final ItemEntry<MortarGunAmmoItem> MORTAR_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("mortar_gun_ammo_item", MortarGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.mortargunammo"))
            .register();
    public static final ItemEntry<SmokeGunAmmoItem> SMOKE_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("smoke_gun_ammo_item", SmokeGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.smokegunammo"))
            .register();
    public static final ItemEntry<FireSpearGunAmmoItem> FIRE_SPEAR_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("fire_spear_gun_ammo_item", FireSpearGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.firespeargunammo"))
            .register();
    public static final ItemEntry<SeekerSpearGunAmmoItem> SEEKER_SPEAR_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("seeker_spear_gun_ammo_item", SeekerSpearGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.seekerspeargunammo"))
            .register();
    public static final ItemEntry<StrikeSpearGunAmmoItem> STRIKE_SPEAR_GUN_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("strike_spear_gun_ammo_item", StrikeSpearGunAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.strikespeargunammo"))
            .register();

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
