package net.ato.shupapium;

import com.simibubi.create.foundation.item.ItemDescription;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.ato.shupapium.items.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rbasamoyai.createbigcannons.CBCTags;

public class ShupapiumItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MainShupapium.MODID);
    public static final ItemEntry<GenericPropellantItem> PROPELLANT_ITEM = MainShupapium.REGISTRATE
            .item("propellant_item", GenericPropellantItem::new)
            .tag(CBCTags.CBCItemTags.HIGH_EXPLOSIVE_MATERIALS)
            .register();
    public static final ItemEntry<MediumBulletAmmoItem> MEDIUM_BULLET_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("medium_bullet_ammo_item", MediumBulletAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.medium_bullet_ammo_item"))
            .register();
    public static final ItemEntry<LargeBulletAmmoItem> LARGE_BULLET_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("large_bullet_ammo_item", LargeBulletAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.large_bullet_ammo_item"))
            .register();
    public static final ItemEntry<ExtraLargeBulletAmmoItem> EXTRA_LARGE_BULLET_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("extra_large_bullet_ammo_item", ExtraLargeBulletAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.extra_large_bullet_ammo_item"))
            .register();
    public static final ItemEntry<SmallHEShellAmmoItem> SMALL_HE_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("small_he_shell_ammo_item", SmallHEShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.small_he_shell_ammo_item"))
            .register();
    public static final ItemEntry<SmallShellAmmoItem> SMALL_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("small_shell_ammo_item", SmallShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.small_shell_ammo_item"))
            .register();
    public static final ItemEntry<MediumShellAmmoItem> MEDIUM_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("medium_shell_ammo_item", MediumShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.medium_shell_ammo_item"))
            .register();
    public static final ItemEntry<MediumFlakShellAmmoItem> MEDIUM_FLAK_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("medium_flak_shell_ammo_item", MediumFlakShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.medium_flak_shell_ammo_item"))
            .register();
    public static final ItemEntry<LargeSolidShellAmmoItem> LARGE_SOLID_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("large_solid_shell_ammo_item", LargeSolidShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.large_solid_shell_ammo_item"))
            .register();
    public static final ItemEntry<LargeHEShellAmmoItem> LARGE_HE_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("large_he_shell_ammo_item", LargeHEShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.large_he_shell_ammo_item"))
            .register();
    public static final ItemEntry<LargeHeatShellAmmoItem> LARGE_HEAT_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("large_heat_shell_ammo_item", LargeHeatShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.large_heat_shell_ammo_item"))
            .register();
    public static final ItemEntry<LargeAPShellAmmoItem> LARGE_AP_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("large_ap_shell_ammo_item", LargeAPShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.large_ap_shell_ammo_item"))
            .register();
    public static final ItemEntry<LargeFlakShellAmmoItem> LARGE_FLAK_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("large_flak_shell_ammo_item", LargeFlakShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.large_flak_shell_ammo_item"))
            .register();
    public static final ItemEntry<LargeSmokeShellAmmoItem> LARGE_SMOKE_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("large_smoke_shell_ammo_item", LargeSmokeShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.large_smoke_shell_ammo_item"))
            .register();
    public static final ItemEntry<ArtilleryHEShellAmmoItem> ARTILLERY_HE_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("artillery_he_shell_ammo_item", ArtilleryHEShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.artillery_he_shell_ammo_item"))
            .register();
    public static final ItemEntry<ArtilleryGasShellAmmoItem> ARTILLERY_GAS_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("artillery_gas_shell_ammo_item", ArtilleryGasShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.artillery_gas_shell_ammo_item"))
            .register();
    public static final ItemEntry<ArtilleryFireShellAmmoItem> ARTILLERY_FIRE_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("artillery_fire_shell_ammo_item", ArtilleryFireShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.artillery_fire_shell_ammo_item"))
            .register();
    public static final ItemEntry<ArtillerySolidShellAmmoItem> ARTILLERY_SOLID_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("artillery_solid_shell_ammo_item", ArtillerySolidShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.artillery_solid_shell_ammo_item"))
            .register();
    public static final ItemEntry<MortarShellAmmoItem> MORTAR_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("mortar_shell_ammo_item", MortarShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.mortar_shell_ammo_item"))
            .register();
    public static final ItemEntry<SmokeShellAmmoItem> SMOKE_SHELL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("smoke_shell_ammo_item", SmokeShellAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.smoke_shell_ammo_item"))
            .register();
    public static final ItemEntry<ArmorPeelerRocketAmmoItem> ARMOR_PEELER_ROCKET_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("armor_peeler_rocket_ammo_item", ArmorPeelerRocketAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.armor_peeler_rocket_ammo_item"))
            .register();
    public static final ItemEntry<FireSpearRocketAmmoItem> FIRE_SPEAR_ROCKET_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("fire_spear_rocket_ammo_item", FireSpearRocketAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.fire_spear_rocket_ammo_item"))
            .register();
    public static final ItemEntry<SeekerSpearMissileAmmoItem> SEEKER_SPEAR_MISSILE_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("seeker_spear_missile_ammo_item", SeekerSpearMissileAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.seeker_spear_missile_ammo_item"))
            .register();
    public static final ItemEntry<StrikeSpearMissileAmmoItem> STRIKE_SPEAR_MISSILE_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("strike_spear_missile_ammo_item", StrikeSpearMissileAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.strike_spear_missile_ammo_item"))
            .register();
    public static final ItemEntry<RadarSpearMissileAmmoItem> RADAR_SPEAR_MISSILE_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("radar_spear_missile_ammo_item", RadarSpearMissileAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.radar_spear_missile_ammo_item"))
            .register();
    public static final ItemEntry<FlareAmmoItem> FLARE_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("flare_ammo_item", FlareAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.flare_ammo_item"))
            .register();
    public static final ItemEntry<ChaffAmmoItem> CHAFF_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("chaff_ammo_item", ChaffAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.chaff_ammo_item"))
            .register();
    public static final ItemEntry<PetrolAmmoItem> PETROL_AMMO_ITEM = MainShupapium.REGISTRATE
            .item("petrol_ammo_item", PetrolAmmoItem::new)
            .tag(CBCTags.CBCItemTags.AUTOCANNON_ROUNDS)
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "item.shupapium.petrol_ammo_item"))
            .register();

    //Misc
    public static final RegistryObject<Item> DUMMY_RAGDOLL_SPAWN_EGG = ITEMS.register(
            "dummy_ragdoll_spawn_egg", () -> new ForgeSpawnEggItem(ShupapiumEntities.DUMMY_RAGDOLL_ENTITY, 0xFEDFBF, 0xFF8000, new Item.Properties())
    );

    public static void register() {}

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
