package net.ato.shupapium;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.ato.shupapium.cannons.ShupapiumACBarrelBlock;
import net.ato.shupapium.cannons.ShupapiumGACBarrelBlock;
import net.ato.shupapium.cannons.ShupapiumGACBlockItem;
import net.ato.shupapium.client.renderers.MinigunBarrelItemRenderer;
import net.ato.shupapium.client.renderers.RotaryCannonBarrelItemRenderer;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ShupapiumItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, MainShupapium.MOD_ID);

    //Misc
    public static final Holder<Item> DUMMY_RAGDOLL_SPAWN_EGG = ITEMS.register(
            "dummy_ragdoll_spawn_egg", () -> new DeferredSpawnEggItem(ShupapiumEntities.DUMMY_RAGDOLL_ENTITY, 0xFEDFBF, 0xFF8000, new Item.Properties())
    );
    public static final Holder<Item> HEAVY_DUMMY_RAGDOLL_SPAWN_EGG = ITEMS.register(
            "heavy_dummy_ragdoll_spawn_egg", () -> new DeferredSpawnEggItem(ShupapiumEntities.HEAVY_DUMMY_RAGDOLL_ENTITY, 0x7993AC, 0x303A44, new Item.Properties())
    );

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
