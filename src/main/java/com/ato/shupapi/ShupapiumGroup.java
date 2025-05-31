package com.ato.shupapi;

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
        Objects.requireNonNull(ShupapiumBlocks.FISSION_SHELL);
        return createBuilder()
                .title(Component.translatable("itemGroup.shupapium"))
                .icon(ShupapiumBlocks.FISSION_SHELL::asStack)
                .displayItems((pParameters, pOutput) -> {
                    pOutput.accept(ShupapiumBlocks.ORDINANCE_SHELL);
                    pOutput.accept(ShupapiumBlocks.REDORDINANCE_SHELL);
                    pOutput.accept(ShupapiumBlocks.HEAVYORDINANCE_SHELL);
                    pOutput.accept(ShupapiumBlocks.SUPERHEAVYORDINANCE_SHELL);
                    pOutput.accept(ShupapiumBlocks.CLUSTER_SHELL);
                    pOutput.accept(ShupapiumBlocks.KINETIC_SHELL);
                    pOutput.accept(ShupapiumBlocks.TOXICGAS_SHELL);
                    pOutput.accept(ShupapiumBlocks.FISSION_SHELL);
                    pOutput.accept(ShupapiumBlocks.FUSION_SHELL);
                    pOutput.accept(ShupapiumItems.MACHINE_GUN_AMMO_ITEM.get());
                    pOutput.accept(ShupapiumItems.LIGHT_GUN_AMMO_ITEM.get());
                    pOutput.accept(ShupapiumItems.ROTARY_GUN_AMMO_ITEM.get());
                    pOutput.accept(ShupapiumItems.HEAVY_GUN_AMMO_ITEM.get());
                    pOutput.accept(ShupapiumItems.BATTLE_HE_GUN_AMMO_ITEM.get());
                    pOutput.accept(ShupapiumItems.BATTLE_SOLID_GUN_AMMO_ITEM.get());
                    pOutput.accept(ShupapiumItems.BATTLE_HEAT_GUN_AMMO_ITEM.get());
                    pOutput.accept(ShupapiumItems.BATTLE_APFSDS_GUN_AMMO_ITEM.get());
                    pOutput.accept(ShupapiumItems.BATTLE_ARTILLERY_SOLID_GUN_AMMO_ITEM.get());
                    pOutput.accept(ShupapiumItems.BATTLE_ARTILLERY_GAS_GUN_AMMO_ITEM.get());
                    pOutput.accept(ShupapiumItems.BATTLE_ARTILLERY_HE_GUN_AMMO_ITEM.get());
                    pOutput.accept(ShupapiumItems.ARMOR_PEELER_GUN_AMMO_ITEM.get());
                    pOutput.accept(ShupapiumItems.DAARICK_CITIZEN_SPAWN_EGG.get());
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

    public static void register(IEventBus modBus) {
        MainShupapium.REGISTRATE.addRawLang("itemGroup.shupapium", "CBC: Warium Projectiles");
        TAB_REGISTER.register(modBus);
    }
}
