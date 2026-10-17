package net.ato.shupapium;

import com.simibubi.create.Create;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import rbasamoyai.createbigcannons.index.CBCItems;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ShupapiumGroup {
    public static final ResourceKey<CreativeModeTab> MAIN_TAB_KEY = makeKey("shells");
    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MainShupapium.MOD_ID);
    private static Map<ResourceKey<CreativeModeTab>, DeferredHolder<CreativeModeTab, CreativeModeTab>> TABS = new HashMap<>();

    public static final Supplier<CreativeModeTab> GROUP = wrapGroup("shells", () -> {
        ShupapiumBlocks.FISSION_BOMB_SHELL_BLOCK.asItem();
        return createBuilder()
                .title(Component.translatable("itemGroup.shupapium"))
                .icon(ShupapiumBlocks.FISSION_BOMB_SHELL_BLOCK::asStack)
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(ShupapiumItems.MEDIUM_BULLET_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_BULLET_AMMO_ITEM);
                    output.accept(ShupapiumItems.EXTRA_LARGE_BULLET_AMMO_ITEM);
                    output.accept(ShupapiumItems.HUGE_BULLET_AMMO_ITEM);
                    output.accept(ShupapiumItems.HUGE_HE_BULLET_AMMO_ITEM);
                    output.accept(ShupapiumItems.SMALL_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.SMALL_AP_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.SMALL_FLAK_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_SOLID_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_HEAT_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_AP_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_FLAK_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_SMOKE_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.ARTILLERY_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.ARTILLERY_SOLID_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.ARTILLERY_GAS_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.ARTILLERY_FIRE_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.MORTAR_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.SMOKE_MORTAR_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.ARMOR_PEELER_ROCKET_AMMO_ITEM);
                    output.accept(ShupapiumItems.FIRE_SPEAR_ROCKET_AMMO_ITEM);
                    output.accept(ShupapiumItems.SEEKER_SPEAR_ROCKET_AMMO_ITEM);
                    output.accept(ShupapiumItems.RADAR_SPEAR_MISSILE_AMMO_ITEM);
                    output.accept(ShupapiumItems.STRIKE_SPEAR_MISSILE_AMMO_ITEM);
                    output.accept(ShupapiumItems.FLARE_CHARGE_AMMO_ITEM);
                    output.accept(ShupapiumItems.CHAFF_CHARGE_AMMO_ITEM);
                    output.accept(ShupapiumItems.PETROL_CAN_AMMO_ITEM);
                    output.accept(ShupapiumItems.PROPELLANT_ITEM);
                    output.accept(ShupapiumBlocks.SHUPAPIUM_AMMO_CONTAINER);
                    output.accept(ShupapiumBlocks.CREATIVE_SHUPAPIUM_AMMO_CONTAINER);
                    output.accept(ShupapiumBlocks.MINIGUN_BARREL);
                    output.accept(ShupapiumBlocks.MACHINE_GUN_BARREL);
                    output.accept(ShupapiumBlocks.COVERED_MACHINE_GUN_BARREL);
                    output.accept(ShupapiumBlocks.MINIGUN_BREECH);
                    output.accept(ShupapiumBlocks.LIGHT_MACHINE_GUN_BREECH);
                    output.accept(ShupapiumBlocks.MACHINE_GUN_BREECH);
                    output.accept(ShupapiumBlocks.HEAVY_MACHINE_GUN_BREECH);
                    output.accept(ShupapiumBlocks.CANNON_BARREL);
                    output.accept(ShupapiumBlocks.ROTARY_CANNON_BARREL);
                    output.accept(ShupapiumBlocks.LIGHT_CANNON_BREECH);
                    output.accept(ShupapiumBlocks.ROTARY_CANNON_BREECH);
                    output.accept(ShupapiumBlocks.HEAVY_CANNON_BREECH);
                    output.accept(ShupapiumBlocks.BATTLE_CANNON_BARREL);
                    output.accept(ShupapiumBlocks.COVERED_BATTLE_CANNON_BARREL);
                    output.accept(ShupapiumBlocks.THICK_BATTLE_CANNON_BARREL);
                    output.accept(ShupapiumBlocks.BATTLE_CANNON_MUZZLE_BRAKE_TYPE_1);
                    output.accept(ShupapiumBlocks.BATTLE_CANNON_MUZZLE_BRAKE_TYPE_2);
                    output.accept(ShupapiumBlocks.BATTLE_CANNON_BREECH);
                    output.accept(ShupapiumBlocks.ARTILLERY_CANNON_BARREL);
                    output.accept(ShupapiumBlocks.ARTILLERY_CANNON_BREECH);
                    output.accept(ShupapiumBlocks.ROCKET_POD_BARREL);
                    output.accept(ShupapiumBlocks.LARGE_ROCKET_POD_BARREL);
                    output.accept(ShupapiumBlocks.ROCKET_POD_BREECH);
                    output.accept(ShupapiumBlocks.LARGE_ROCKET_POD_BREECH);
                    output.accept(ShupapiumBlocks.LAUNCHER_BARREL);
                    output.accept(ShupapiumBlocks.LAUNCHER_BREECH);
                    output.accept(ShupapiumBlocks.FLAMETHROWER_BARREL);
                    output.accept(ShupapiumBlocks.COVERED_FLAMETHROWER_BARREL);
                    output.accept(ShupapiumBlocks.FLAMETHROWER_BREECH);
                    output.accept(ShupapiumBlocks.SMOKE_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.BARREL_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.TOXIC_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.SMALL_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.SMALL_BOMB_CLUSTER_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.MEDIUM_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.HEAVY_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.FIRE_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.SUPER_HEAVY_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.BLOCK_BUSTER_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.KINETIC_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.IR_SEEKER_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.CLUSTER_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.FISSION_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.FUSION_BOMB_SHELL_BLOCK);
                    output.accept(CBCItems.IMPACT_FUZE);
                    output.accept(CBCItems.TIMED_FUZE);
                    output.accept(CBCItems.PROXIMITY_FUZE);
                    output.accept(CBCItems.DELAYED_IMPACT_FUZE);
                    output.accept(CBCItems.WIRED_FUZE);
                    output.accept(CBCItems.INERTIA_FUZE);
                    output.accept(CBCItems.DELAYED_INERTIA_FUZE);
                    output.accept(ShupapiumItems.DUMMY_RAGDOLL_SPAWN_EGG.value());
                    output.accept(ShupapiumItems.HEAVY_DUMMY_RAGDOLL_SPAWN_EGG.value());
                })
                .build();
    });

    public static Supplier<CreativeModeTab> wrapGroup(String id, Supplier<CreativeModeTab> sup) {
        DeferredHolder<CreativeModeTab, CreativeModeTab> obj = TAB_REGISTER.register(id, sup);
        TABS.put(makeKey(id), obj);
        return obj;
    }

    public static CreativeModeTab.Builder createBuilder() {
        return CreativeModeTab.builder().withTabsBefore(Create.asResource("palettes"));
    }

    public static void setDefaultTabToNull() {
        MainShupapium.REGISTRATE.defaultCreativeTab((ResourceKey<CreativeModeTab>) null);
    }

    public static ResourceKey<CreativeModeTab> makeKey(String id) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, MainShupapium.resource(id));
    }

    public static void register(IEventBus bus) {
        MainShupapium.REGISTRATE.addRawLang("itemGroup.shupapium", "CBC: Warium Projectiles");
        TAB_REGISTER.register(bus);
    }
}
