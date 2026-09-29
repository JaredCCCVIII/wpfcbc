package net.ato.shupapium;

import com.simibubi.create.Create;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ShupapiumGroup {
    public static final ResourceKey<CreativeModeTab> MAIN_TAB_KEY = makeKey("shells");
    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MainShupapium.MOD_ID);
    private static Map<ResourceKey<CreativeModeTab>, DeferredHolder<CreativeModeTab, CreativeModeTab>> TABS = new HashMap<>();

    public static final Supplier<CreativeModeTab> GROUP = wrapGroup("shells", () -> {
        Blocks.REDSTONE_BLOCK.asItem();
        return createBuilder()
                .title(Component.translatable("itemGroup.shupapium"))
                .icon(Blocks.REDSTONE_BLOCK.asItem()::getDefaultInstance)
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(ShupapiumBlocks.SMOKE_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.BARREL_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.TOXIC_BOMB_SHELL_BLOCK);
                    output.accept(ShupapiumBlocks.SMALL_BOMB_SHELL_BLOCK);
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
