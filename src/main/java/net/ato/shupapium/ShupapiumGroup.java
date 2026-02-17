package net.ato.shupapium;

import com.simibubi.create.Create;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public class ShupapiumGroup {
    public static final ResourceKey<CreativeModeTab> MAIN_TAB_KEY = makeKey("shells");
    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MainShupapium.MODID);
    private static Map<ResourceKey<CreativeModeTab>, RegistryObject<CreativeModeTab>> TABS = new HashMap<>();

    public static final Supplier<CreativeModeTab> GROUP = wrapGroup("shells", () -> {
        Objects.requireNonNull(ShupapiumBlocks.FISSION_BOMB_SHELL_BLOCK);
        return createBuilder()
                .title(Component.translatable("itemGroup.shupapium"))
                .icon(ShupapiumBlocks.FISSION_BOMB_SHELL_BLOCK::asStack)
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(ShupapiumItems.MEDIUM_BULLET_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_BULLET_AMMO_ITEM);
                    output.accept(ShupapiumItems.EXTRA_LARGE_BULLET_AMMO_ITEM);
                    output.accept(ShupapiumItems.SMALL_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.SMALL_HE_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.MEDIUM_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.MEDIUM_FLAK_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_SOLID_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_HE_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_HEAT_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_AP_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_FLAK_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.LARGE_SMOKE_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.ARTILLERY_HE_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.ARTILLERY_GAS_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.ARTILLERY_FIRE_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.ARTILLERY_SOLID_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.MORTAR_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.SMOKE_SHELL_AMMO_ITEM);
                    output.accept(ShupapiumItems.ARMOR_PEELER_ROCKET_AMMO_ITEM);
                    output.accept(ShupapiumItems.FIRE_SPEAR_ROCKET_AMMO_ITEM);
                    output.accept(ShupapiumItems.SEEKER_SPEAR_MISSILE_AMMO_ITEM);
                    output.accept(ShupapiumItems.STRIKE_SPEAR_MISSILE_AMMO_ITEM);
                    output.accept(ShupapiumItems.RADAR_SPEAR_MISSILE_AMMO_ITEM);
                    output.accept(ShupapiumItems.PETROL_AMMO_ITEM);
                    output.accept(ShupapiumItems.FLARE_AMMO_ITEM);
                    output.accept(ShupapiumItems.CHAFF_AMMO_ITEM);
                    output.accept(ShupapiumBlocks.GAS_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.SMALL_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.SMALL_BOMB_CLUSTER_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.MEDIUM_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.HEAVY_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.SUPER_HEAVY_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.BLOCK_BUSTER_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.FIRE_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.CLUSTER_ROCKET_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.KINETIC_BUNKER_BUSTER_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.IR_SEEKER_MISSILE_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.SARH_SEEKER_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.FISSION_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.FUSION_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.SHUPAPIUM_AMMO_CONTAINER);
                    output.accept(ShupapiumBlocks.CREATIVE_SHUPAPIUM_AMMO_CONTAINER);
                    output.accept(ShupapiumBlocks.MINIGUN_BARREL);
                    output.accept(ShupapiumBlocks.MACHINE_GUN_BARREL);
                    output.accept(ShupapiumBlocks.COVERED_GUN_BARREL);
                    output.accept(ShupapiumBlocks.MACHINE_GUN_BREECH);
                    output.accept(ShupapiumBlocks.MINIGUN_RECOIL_SPRING);
                    output.accept(ShupapiumBlocks.MACHINE_GUN_RECOIL_SPRING);
                    output.accept(ShupapiumBlocks.LIGHT_GUN_RECOIL_SPRING);
                    output.accept(ShupapiumBlocks.HEAVY_GUN_RECOIL_SPRING);
                    output.accept(ShupapiumBlocks.ROTATORY_CANNON_BARREL);
                    output.accept(ShupapiumBlocks.CANNON_GUN_BARREL);
                    output.accept(ShupapiumBlocks.ROTATORY_CANNON_RECOIL_SPRING);
                    output.accept(ShupapiumBlocks.LIGHT_CANNON_RECOIL_SPRING);
                    output.accept(ShupapiumBlocks.HEAVY_CANNON_RECOIL_SPRING);
                    output.accept(ShupapiumBlocks.CANNON_GUN_BREECH);
                    output.accept(ShupapiumBlocks.BATTLE_GUN_BARREL);
                    output.accept(ShupapiumBlocks.THICK_BATTLE_GUN_BARREL);
                    output.accept(ShupapiumBlocks.BATTLE_GUN_RECOIL_SPRING);
                    output.accept(ShupapiumBlocks.BATTLE_GUN_BREECH);
                    output.accept(ShupapiumBlocks.ARTILLERY_GUN_BARREL);
                    output.accept(ShupapiumBlocks.ARTILLERY_GUN_RECOIL_SPRING);
                    output.accept(ShupapiumBlocks.ARTILLERY_GUN_BREECH);
                    output.accept(ShupapiumBlocks.MORTAR_GUN_BARREL);
                    output.accept(ShupapiumBlocks.MORTAR_GUN_BREECH);
                    output.accept(ShupapiumBlocks.MORTAR_GUN_RECOIL_SPRING);
                    output.accept(ShupapiumBlocks.ROCKET_POD_BARREL);
                    output.accept(ShupapiumBlocks.ROCKET_POD_BREECH);
                    output.accept(ShupapiumBlocks.ROCKET_POD_RECOIL_SPRING);
                    output.accept(ShupapiumBlocks.FLAMETHROWER_GUN_BARREL);
                    output.accept(ShupapiumBlocks.COVERED_FLAMETHROWER_GUN_BARREl);
                    output.accept(ShupapiumBlocks.FLAMETHROWER_GUN_BREECH);
                    output.accept(ShupapiumBlocks.FLAMETHROWER_GUN_RECOIL_SPRING);
                    output.accept(ShupapiumItems.DUMMY_RAGDOLL_SPAWN_EGG.get());
                    output.accept(ShupapiumItems.PROPELLANT_ITEM);
                })
                .build();
    });

    public static Supplier<CreativeModeTab> wrapGroup(String id, Supplier<CreativeModeTab> sup) {
        RegistryObject<CreativeModeTab> obj = TAB_REGISTER.register(id, sup);
        TABS.put(makeKey(id), obj);
        return obj;
    }

    public static CreativeModeTab.Builder createBuilder() {
        return CreativeModeTab.builder().withTabsBefore(Create.asResource("palettes"));
    }

    public static void useModTab(ResourceKey<CreativeModeTab> key) {
        MainShupapium.REGISTRATE.setCreativeTab(TABS.get(key));
    }

    public static ResourceKey<CreativeModeTab> makeKey(String id) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, MainShupapium.resource(id));
    }

    public static void register(IEventBus bus) {
        MainShupapium.REGISTRATE.addRawLang("itemGroup.shupapium", "CBC: Warium Projectiles");
        TAB_REGISTER.register(bus);
    }
}
