package net.ato.shupapium;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.wariumce.init.CrustyChunksModEntities;
import com.wariumce.init.CrustyChunksModItems;
import com.wariumce.procedures.MuzzleFlashProcedure;
import net.ato.shupapium.items.*;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import rbasamoyai.createbigcannons.CBCTags;

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
            .variants(CrustyChunksModEntities.MEDIUM_AI_BULLET)
            .spentItem(CrustyChunksModItems.MEDIUM_CASING)
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
            .spentItem(CrustyChunksModItems.HOLLOWED_LARGE_PROJECTILE)
            .build());
    public static final ItemEntry<BasicShupapiumAmmoItem> SMALL_FLAK_SHELL_AMMO_ITEM = acAmmo("small_flak_shell_ammo_item", "Medium Shell Ammo (FLAK)", () -> ShupapiumAmmoSpec.builder()
            .properties("small_shell")
            .variants(CrustyChunksModEntities.SMALL_FLAK_SHELL_PROJECTILE)
            .spentItem(CrustyChunksModItems.HOLLOWED_LARGE_PROJECTILE)
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
