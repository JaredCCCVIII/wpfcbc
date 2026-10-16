package net.ato.shupapium;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.wariumce.init.CrustyChunksModEntities;
import com.wariumce.init.CrustyChunksModItems;
import com.wariumce.procedures.MuzzleFlashProcedure;
import net.ato.shupapium.items.*;
import net.ato.shupapium.utils.ProjectileManager;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import rbasamoyai.createbigcannons.CBCTags;
import rbasamoyai.createbigcannons.index.CBCItems;

import java.util.function.Supplier;

public class ShupapiumItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MainShupapium.MOD_ID);

    // Misc
    public static final Holder<Item> DUMMY_RAGDOLL_SPAWN_EGG = ITEMS.register(
            "dummy_ragdoll_spawn_egg", () -> new DeferredSpawnEggItem(ShupapiumEntities.DUMMY_RAGDOLL_ENTITY, 0xFEDFBF, 0xFF8000, new Item.Properties())
    );
    public static final Holder<Item> HEAVY_DUMMY_RAGDOLL_SPAWN_EGG = ITEMS.register(
            "heavy_dummy_ragdoll_spawn_egg", () -> new DeferredSpawnEggItem(ShupapiumEntities.HEAVY_DUMMY_RAGDOLL_ENTITY, 0x7993AC, 0x303A44, new Item.Properties())
    );

    // Autocannon Munition
    public static final ItemEntry<BasicShupapiumAmmoItem> MEDIUM_BULLET_AMMO_ITEM = acAmmo("medium_bullet_ammo_item", "Medium Bullet Ammo", () -> ShupapiumAmmoSpec.builder()
            .properties("medium_bullet")
            .variants(CrustyChunksModEntities.BULLETFIRE_PROJECTILE)
            .spentItem(CrustyChunksModItems.MEDIUM_CASING)
            .muzzle((level, pos, vel) -> MuzzleFlashProcedure.execute(level, pos.x,  pos.y, pos.z))
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> LARGE_BULLET_AMMO_ITEM = acAmmo("large_bullet_ammo_item", "Large Bullet Ammo", () -> ShupapiumAmmoSpec.builder()
            .properties("large_bullet")
            .variants(CrustyChunksModEntities.GENERICLARGE_BULLET, CrustyChunksModEntities.GENERIC_LARGE_BULLET_GREEN)
            .spentItem(CrustyChunksModItems.LARGE_CASING)
            .muzzle((level, pos, vel) -> MuzzleFlashProcedure.execute(level, pos.x,  pos.y, pos.z))
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> EXTRA_LARGE_BULLET_AMMO_ITEM = acAmmo("extra_large_bullet_ammo_item", "Extra Large Bullet Ammo", () -> ShupapiumAmmoSpec.builder()
            .properties("extra_large_bullet")
            .variants(CrustyChunksModEntities.EXTRA_LARGE_BULLET_FIRE)
            .spentItem(CrustyChunksModItems.EXTRA_LARGE_CASING)
            .muzzle((level, pos, vel) -> MuzzleFlashProcedure.execute(level, pos.x,  pos.y, pos.z))
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> HUGE_BULLET_AMMO_ITEM = acAmmo("huge_bullet_ammo_item", "Small Shell Ammo", () -> ShupapiumAmmoSpec.builder()
            .properties("huge_bullet")
            .variants(CrustyChunksModEntities.HUGE_BULLET_FIRE)
            .spentItem(CrustyChunksModItems.HUGE_CASING)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> HUGE_HE_BULLET_AMMO_ITEM = acAmmo("huge_he_bullet_ammo_item", "Small Shell Ammo (HE)", () -> ShupapiumAmmoSpec.builder()
            .properties("huge_bullet")
            .variants(CrustyChunksModEntities.HUGE_HE_BULLET_FIRE)
            .spentItem(CrustyChunksModItems.HUGE_CASING)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> SMALL_SHELL_AMMO_ITEM = acAmmo("small_shell_ammo_item", "Medium Shell Ammo (HE)", () -> ShupapiumAmmoSpec.builder()
            .properties("small_shell")
            .variants(CrustyChunksModEntities.SMALL_SHELL_FIRE)
            .spentItem(CrustyChunksModItems.HOLLOWED_LARGE_PROJECTILE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> SMALL_AP_SHELL_AMMO_ITEM = acAmmo("small_ap_shell_ammo_item", "Medium Shell Ammo (AP)", () -> ShupapiumAmmoSpec.builder()
            .properties("small_shell")
            .variants(CrustyChunksModEntities.SMALL_AP_CANNON_FIRE)
            .spentItem(CrustyChunksModItems.LEAD_NUGGET)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> SMALL_FLAK_SHELL_AMMO_ITEM = acAmmo("small_flak_shell_ammo_item", "Medium Shell Ammo (FLAK)", () -> ShupapiumAmmoSpec.builder()
            .properties("small_shell")
            .variants(CrustyChunksModEntities.SMALL_FLAK_SHELL_PROJECTILE)
            .spentItem(CrustyChunksModItems.HOLLOWED_LARGE_PROJECTILE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> LARGE_SHELL_AMMO_ITEM = acAmmo("large_shell_ammo_item", "Large Shell Ammo (HE)",() -> ShupapiumAmmoSpec.builder()
            .properties("large_shell")
            .variants(CrustyChunksModEntities.TANK_FIRE_PROJECTILE)
            .spentItem(CrustyChunksModItems.HOLLOWED_EXTRA_LARGE_PROJECTILE)
            .onExpire(ProjectileManager.ExpireAction.DETONATE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> LARGE_SOLID_SHELL_AMMO_ITEM = acAmmo("large_solid_shell_ammo_item", "Large Shell Ammo (SOLID)",() -> ShupapiumAmmoSpec.builder()
            .properties("large_shell")
            .variants(CrustyChunksModEntities.LARGE_SOLID_PROJECTILE)
            .spentItem(CrustyChunksModItems.LEAD_NUGGET)
            .onExpire(ProjectileManager.ExpireAction.DETONATE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> LARGE_HEAT_SHELL_AMMO_ITEM = acAmmo("large_heat_shell_ammo_item", "Large Shell Ammo (HEAT)",() -> ShupapiumAmmoSpec.builder()
            .properties("large_shell")
            .variants(CrustyChunksModEntities.LARGE_HEAT_FIRE)
            .spentItem(CrustyChunksModItems.HOLLOWED_EXTRA_LARGE_PROJECTILE)
            .onExpire(ProjectileManager.ExpireAction.DETONATE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> LARGE_AP_SHELL_AMMO_ITEM = acAmmo("large_ap_shell_ammo_item", "Large Shell Ammo (AP)",() -> ShupapiumAmmoSpec.builder()
            .properties("large_shell")
            .variants(CrustyChunksModEntities.LARGE_AP_FIRE)
            .spentItem(Items.IRON_NUGGET::asItem)
            .onExpire(ProjectileManager.ExpireAction.DETONATE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> LARGE_FLAK_SHELL_AMMO_ITEM = acAmmo("large_flak_shell_ammo_item", "Large Shell Ammo (FLAK)",() -> ShupapiumAmmoSpec.builder()
            .properties("large_shell")
            .variants(CrustyChunksModEntities.LARGE_FLAK_PROJECTILE)
            .spentItem(CrustyChunksModItems.HOLLOWED_EXTRA_LARGE_PROJECTILE)
            .onExpire(ProjectileManager.ExpireAction.DETONATE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> LARGE_SMOKE_SHELL_AMMO_ITEM = acAmmo("large_smoke_shell_ammo_item", "Large Shell Ammo (SMOKE)",() -> ShupapiumAmmoSpec.builder()
            .properties("large_shell")
            .variants(CrustyChunksModEntities.LARGE_SMOKE_FIRE)
            .spentItem(CrustyChunksModItems.HOLLOWED_EXTRA_LARGE_PROJECTILE)
            .onExpire(ProjectileManager.ExpireAction.DETONATE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> ARTILLERY_SHELL_AMMO_ITEM = acAmmo("artillery_shell_ammo_item", "Artillery Shell Ammo (HE)",() -> ShupapiumAmmoSpec.builder()
            .properties("artillery_shell")
            .variants(CrustyChunksModEntities.ARTILLERY_FIRE_PROJECTILE)
            .propellant(true)
            .spentItem(CrustyChunksModItems.HOLLOWED_HUGE_PROJECTILE)
            .onExpire(ProjectileManager.ExpireAction.DETONATE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> ARTILLERY_GAS_SHELL_AMMO_ITEM = acAmmo("artillery_gas_shell_ammo_item", "Artillery Shell Ammo (TOXIC GAS)",() -> ShupapiumAmmoSpec.builder()
            .properties("artillery_shell")
            .variants(CrustyChunksModEntities.GAS_ARTILLERY_PROJECTILE)
            .propellant(true)
            .spentItem(CrustyChunksModItems.HOLLOWED_HUGE_PROJECTILE)
            .onExpire(ProjectileManager.ExpireAction.DETONATE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> ARTILLERY_FIRE_SHELL_AMMO_ITEM = acAmmo("artillery_fire_shell_ammo_item", "Artillery Shell Ammo (FIRE)",() -> ShupapiumAmmoSpec.builder()
            .properties("artillery_shell")
            .variants(CrustyChunksModEntities.FIRE_ARTILLERY_PROJECTILE)
            .propellant(true)
            .spentItem(CrustyChunksModItems.HOLLOWED_HUGE_PROJECTILE)
            .onExpire(ProjectileManager.ExpireAction.DETONATE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> ARTILLERY_SOLID_SHELL_AMMO_ITEM = acAmmo("artillery_solid_shell_ammo_item", "Artillery Shell Ammo (SOLID)",() -> ShupapiumAmmoSpec.builder()
            .properties("artillery_shell")
            .variants(CrustyChunksModEntities.ARTILLERY_SOLID_PROJECTILE)
            .propellant(true)
            .spentItem(CrustyChunksModItems.LEAD_NUGGET)
            .onExpire(ProjectileManager.ExpireAction.DETONATE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> MORTAR_SHELL_AMMO_ITEM = acAmmo("mortar_shell_ammo_item", "Mortar Shell Ammo (HE)",() -> ShupapiumAmmoSpec.builder()
            .properties("mortar_shell")
            .variants(CrustyChunksModEntities.MORTAR_PROJECTILE)
            .gravity(true)
            .spentItem(Items.IRON_NUGGET::asItem)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> SMOKE_MORTAR_SHELL_AMMO_ITEM = acAmmo("smoke_mortar_shell_ammo_item", "Mortar Shell Ammo (SMOKE)",() -> ShupapiumAmmoSpec.builder()
            .properties("mortar_shell")
            .variants(CrustyChunksModEntities.SMOKE_MORTAR_PROJECTILE)
            .gravity(true)
            .spentItem(Items.IRON_NUGGET::asItem)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> ARMOR_PEELER_ROCKET_AMMO_ITEM = acAmmo("armor_peeler_rocket_ammo_item", "Rocket Ammo",() -> ShupapiumAmmoSpec.builder()
            .properties("armor_peeler_rocket")
            .variants(CrustyChunksModEntities.ROCKET)
            .spentItem(Items.IRON_NUGGET::asItem)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> FIRE_SPEAR_ROCKET_AMMO_ITEM = acAmmo("fire_spear_rocket_ammo_item", "Fire Spear Rocket Ammo",() -> ShupapiumAmmoSpec.builder()
            .properties("armor_peeler_rocket")
            .variants(CrustyChunksModEntities.FIRE_SPEAR_ROCKET_PROJECTILE)
            .spentItem(CrustyChunksModItems.HOLLOWED_EXTRA_LARGE_PROJECTILE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> SEEKER_SPEAR_ROCKET_AMMO_ITEM = acAmmo("seeker_spear_rocket_ammo_item", "Seeker Spear Missile Ammo",() -> ShupapiumAmmoSpec.builder()
            .properties("armor_peeler_rocket")
            .variants(CrustyChunksModEntities.SEEKER_SPEAR_MISSILE_PROJECTILE)
            .spentItem(CrustyChunksModItems.HOLLOWED_EXTRA_LARGE_PROJECTILE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> RADAR_SPEAR_MISSILE_AMMO_ITEM = acAmmo("radar_spear_missile_ammo_item", "Radar Spear Missile Ammo",() -> ShupapiumAmmoSpec.builder()
            .properties("armor_peeler_rocket")
            .variants(CrustyChunksModEntities.RADAR_SPEAR_MISSILE_PROJECTILE)
            .spentItem(CrustyChunksModItems.HOLLOWED_EXTRA_LARGE_PROJECTILE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> STRIKE_SPEAR_MISSILE_AMMO_ITEM = acAmmo("strike_spear_missile_ammo_item", "Strike Spear Missile Ammo",() -> ShupapiumAmmoSpec.builder()
            .properties("armor_peeler_rocket")
            .variants(CrustyChunksModEntities.STRIKE_SPEAR_PROJECTILE)
            .spentItem(CrustyChunksModItems.HOLLOWED_EXTRA_LARGE_PROJECTILE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> FLARE_CHARGE_AMMO_ITEM = acAmmo("flare_charge_ammo_item", "Flare Ammo", () -> ShupapiumAmmoSpec.builder()
            .properties("flare_charge")
            .variants(CrustyChunksModEntities.FLARE_PROJECTILE)
            .spentItem(CBCItems.GUNPOWDER_PINCH)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> CHAFF_CHARGE_AMMO_ITEM = acAmmo("chaff_charge_ammo_item", "Chaff Ammo", () -> ShupapiumAmmoSpec.builder()
            .properties("flare_charge")
            .variants(CrustyChunksModEntities.CHAFF)
            .spentItem(CBCItems.GUNPOWDER_PINCH)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> PETROL_CAN_AMMO_ITEM = acAmmo("petrol_can_ammo_item", "Small Petrol Can", () -> ShupapiumAmmoSpec.builder()
            .properties("petrol_can")
            .variants(CrustyChunksModEntities.FLAME_THROWER_EMBER)
            .gravity(true)
            .spentItem(Items.IRON_NUGGET::asItem)
            .build());

    private static ItemEntry<BasicShupapiumAmmoItem> acAmmo(String id, String displayName, Supplier<ShupapiumAmmoSpec> spec) {
        return MainShupapium.REGISTRATE
                .item(id, p -> new BasicShupapiumAmmoItem(p, spec.get()))
                .lang(displayName)
                .tag(CBCTags.CBCItemTags.AUTOCANNON_CARTRIDGES)
                .register();
    }

    public static void register() {}
    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
