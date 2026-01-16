package net.ato.shupapium.utils;

import com.simibubi.create.foundation.data.BlockStateGen;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.blocks.ShupapiumACBreechBlock;
import net.ato.shupapium.blocks.ShupapiumAmmoContainerBlock;
import net.ato.shupapium.items.ShupapiumAmmoContainerItem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import rbasamoyai.createbigcannons.CBCTags;
import rbasamoyai.createbigcannons.CreateBigCannons;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBarrelBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlockItem;

public class ShupapiumBuilderTransformersImpl {
    public static <T extends Block & AutocannonBlock, P> NonNullUnaryOperator<BlockBuilder<T, P>> autocannonBarrel(String pathAndMaterial) {
        ResourceLocation texLoc = MainShupapium.resource("block/" + pathAndMaterial + "_autocannon");
        return b -> b.properties(BlockBehaviour.Properties::noOcclusion)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate((c, p) -> BlockStateGen.directionalBlockIgnoresWaterlogged(c, p, s -> {
                    String name = c.getName() + "_" + (s.getValue(AutocannonBarrelBlock.ASSEMBLED) ? "assembled" : s.getValue(AutocannonBarrelBlock.BARREL_END).getSerializedName());
                    if (s.getValue(AutocannonBarrelBlock.ASSEMBLED))
                        return p.models().getBuilder(name).texture("particle", texLoc);
                    ResourceLocation loc = switch (s.getValue(AutocannonBarrelBlock.BARREL_END)) {
                        case FLANGED -> CreateBigCannons.resource("block/autocannon/barrel_flanged");
                        default -> CreateBigCannons.resource("block/autocannon/barrel");
                    };
                    return p.models().withExistingParent(name, loc).texture("material", texLoc);
                }))
                .item(AutocannonBlockItem::new)
                .model((c, p) -> p.withExistingParent(c.getName(), CreateBigCannons.resource("block/autocannon/barrel")).texture("material", texLoc))
                .build();
    }

    public static <T extends Block & AutocannonBlock, P> NonNullUnaryOperator<BlockBuilder<T, P>> autocannonBreech(String pathAndMaterial, boolean complete) {
        ResourceLocation texLoc = MainShupapium.resource("block/" + pathAndMaterial + "_autocannon");
        ResourceLocation tex1Loc = MainShupapium.resource("block/" + pathAndMaterial + "_autocannon_1");
        ResourceLocation baseLoc = CreateBigCannons.resource("block/autocannon/breech");
        ResourceLocation handleLoc = CreateBigCannons.resource("block/autocannon/breech_handle");
        NonNullUnaryOperator<BlockBuilder<T, P>> result = b -> b.properties(BlockBehaviour.Properties::noOcclusion)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate((c, p) -> BlockStateGen.directionalBlockIgnoresWaterlogged(c, p,
                        s -> {
                            boolean handle = s.hasProperty(ShupapiumACBreechBlock.HANDLE) && s.getValue(ShupapiumACBreechBlock.HANDLE);
                            return p.models().withExistingParent(handle ? c.getName() + "_handle" : c.getName(), handle ? handleLoc : baseLoc)
                                    .texture("material", texLoc)
                                    .texture("handle", tex1Loc);
                        }));
        if (complete) {
            result = result.andThen(b -> b.item(AutocannonBlockItem::new)
                    .model((c, p) -> p.withExistingParent(c.getName(), CreateBigCannons.resource("block/autocannon/breech_item")).texture("material", texLoc))
                    .build());
        } else {
            result = result.andThen(b -> b.item(AutocannonBlockItem::new)
                    .model((c, p) -> p.blockItem(c))
                    .build());
        }
        return result;
    }

    public static <T extends Block & AutocannonBlock, P> NonNullUnaryOperator<BlockBuilder<T, P>> autocannonRecoilSpring(String pathAndMaterial, boolean complete) {
        ResourceLocation texLoc = MainShupapium.resource("block/" + pathAndMaterial + "_autocannon");
        NonNullUnaryOperator<BlockBuilder<T, P>> result = b -> b.properties(BlockBehaviour.Properties::noOcclusion)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate((c, p) -> BlockStateGen.directionalBlockIgnoresWaterlogged(c, p,
                        $ -> p.models().withExistingParent(c.getName(), CreateBigCannons.resource("block/autocannon/recoil_spring")).texture("material", texLoc)));
        if (complete) {
            result = result.andThen(b -> b.item(AutocannonBlockItem::new)
                    .model((c, p) -> p.withExistingParent(c.getName(), CreateBigCannons.resource("block/autocannon/recoil_spring_item")).texture("material", texLoc))
                    .build());
        } else {
            result = result.andThen(b -> b.item(AutocannonBlockItem::new)
                    .model((c, p) -> p.blockItem(c))
                    .build());
        }
        return result;
    }

    public static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> autocannonAmmoContainer(boolean isCreative) {
        String root = isCreative ? "creative": "regular";
        return b -> b.addLayer(() -> RenderType::cutoutMipped)
                .blockstate((c, p) -> p.getVariantBuilder(c.get())
                        .forAllStatesExcept(state -> {
                            ShupapiumAmmoContainerBlock.State containerState = state.getValue(ShupapiumAmmoContainerBlock.CONTAINER_STATE);
                            String suffix = switch (containerState) {
                                case CLOSED -> "";
                                case EMPTY -> "_empty";
                                case FILLED -> "_filled";
                            };
                            ResourceLocation loc = p.modLoc("block/autocannon_ammo_containers/" + root + suffix);
                            Direction.Axis axis = state.getValue(BlockStateProperties.HORIZONTAL_AXIS);
                            return ConfiguredModel.builder()
                                    .modelFile(p.models().getExistingFile(loc))
                                    .rotationY(axis == Direction.Axis.X ? 90 : 0)
                                    .build();
                        }, BlockStateProperties.WATERLOGGED))
                .loot((t, c) -> {
                    CopyNbtFunction.Builder func = CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                            .copy("Ammo", "Ammo")
                            .copy("Propellants", "Propellants");
                    if (isCreative)
                        func = func.copy("CurrentIndex", "CurrentIndex");
                    t.add(c, LootTable.lootTable()
                            .withPool(t.applyExplosionCondition(c, LootPool.lootPool()
                                            .add(LootItem.lootTableItem(c))
                                            .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY)))
                                    .apply(func)));
                })
                .item(ShupapiumAmmoContainerItem::new)
                .properties(p -> p.stacksTo(1))
                .properties(p -> isCreative ? p.rarity(Rarity.EPIC) : p)
                .tag(CBCTags.CBCItemTags.AUTOCANNON_AMMO_CONTAINERS)
                .model((c, p) -> p.withExistingParent(c.getName(), p.modLoc("block/autocannon_ammo_containers/" + root)))
                .build();
    }

    public static <T extends Item, P> NonNullUnaryOperator<ItemBuilder<T, P>> autocannonBreechExtractor(String pathAndMaterial) {
        return b -> b.model((c, p) -> p.getBuilder(c.getName())
                .parent(p.getExistingFile(CreateBigCannons.resource("block/autocannon/extractor")))
                .texture("material", MainShupapium.resource("block/" + pathAndMaterial + "_autocannon")));
    }
}
