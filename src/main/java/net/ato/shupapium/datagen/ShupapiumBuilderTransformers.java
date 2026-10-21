package net.ato.shupapium.datagen;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import com.wariumce.CrustyChunksMod;
import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumDataComponents;
import net.ato.shupapium.blocks.ShupapiumAmmoContainerBlock;
import net.ato.shupapium.cannons.*;
import net.ato.shupapium.items.ShupapiumAmmoContainerItem;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BlockModel;
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
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.neoforged.neoforge.client.model.generators.*;
import rbasamoyai.createbigcannons.CBCTags;
import rbasamoyai.createbigcannons.CreateBigCannons;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlockItem;

import java.util.function.Function;
import java.util.function.Supplier;

public class ShupapiumBuilderTransformers {
    private static final ResourceLocation EMPTY_BARREL_MODEL = MainShupapium.resource("block/empty_barrel");
    private record BarrelRotation(int x, int y) {}
    private static BarrelRotation standardRotation(Direction dir) {
        return switch (dir) {
            case EAST -> new BarrelRotation(0, 90);
            case SOUTH -> new BarrelRotation(0, 180);
            case WEST -> new BarrelRotation(0, 270);
            case UP -> new BarrelRotation(270, 0);
            case DOWN -> new BarrelRotation(90, 0);
            default -> new BarrelRotation(0, 0);
        };
    }
    private static BarrelRotation mortarRotation(Direction dir) {
        return switch (dir) {
            case NORTH ->  new BarrelRotation(-90, 180);
            case EAST -> new BarrelRotation(-90, 270);
            case SOUTH -> new BarrelRotation(-90, 0);
            case WEST -> new BarrelRotation(-90, 90);
            case UP -> new BarrelRotation(0, 90);
            case DOWN -> new BarrelRotation(180, -90);
        };
    }

    private static <T extends Block> void barrelBlockstate(DataGenContext<Block, T> c, RegistrateBlockstateProvider p, ModelFile model, Function<Direction, BarrelRotation> rotation) {
        ModelFile empty = new ModelFile.UncheckedModelFile(EMPTY_BARREL_MODEL);
        p.getVariantBuilder(c.get()).forAllStates(state -> {
            boolean assembled = state.hasProperty(ShupapiumACBarrelBlock.ASSEMBLED) && state.getValue(ShupapiumACBarrelBlock.ASSEMBLED);
            BarrelRotation rot = rotation.apply(state.getValue(BlockStateProperties.FACING));
            return ConfiguredModel.builder()
                    .modelFile(assembled ? empty : model)
                    .rotationX(Math.floorMod(rot.x, 360))
                    .rotationY(Math.floorMod(rot.y, 360))
                    .build();
        });
    }

    public static <T extends Item, P> NonNullUnaryOperator<ItemBuilder<T, P>> fuzedProjectileItem(String pathAndMaterial) {
        ResourceLocation headFuzeLoc = CreateBigCannons.resource("block/projectile_block_fuze_head");
        ResourceLocation baseFuzeLoc = CreateBigCannons.resource("block/projectile_block_fuze_base");
        ResourceLocation sideLoc = MainShupapium.resource("block/" + pathAndMaterial);
        ResourceLocation topLoc = MainShupapium.resource("block/" + pathAndMaterial + "_top");
        ResourceLocation bottomLoc = MainShupapium.resource("block/" + pathAndMaterial + "_bottom");
        return (b) -> b.model((c, p) -> {
            ItemModelBuilder headFuzeModel = p.withExistingParent(c.getName() + "_head_fuze", headFuzeLoc).texture("side", sideLoc).texture("top", topLoc).texture("bottom", bottomLoc).texture("particle", topLoc);
            ItemModelBuilder baseFuzeModel = p.withExistingParent(c.getName() + "_base_fuze", baseFuzeLoc).texture("side", sideLoc).texture("top", topLoc).texture("bottom", bottomLoc).texture("particle", topLoc);
            p.blockItem(c).override().model(headFuzeModel).predicate(CreateBigCannons.resource("fuze_state"), 1.0F).end().override().model(baseFuzeModel).predicate(CreateBigCannons.resource("fuze_state"), 2.0F).end();
        });
    }

    public static <T extends Item, P> NonNullUnaryOperator<ItemBuilder<T, P>> fuzedProjectileBlockItem(String pathAndMaterial) {
        ResourceLocation headFuzeLoc = MainShupapium.resource("block/projectile_fullblock_fuze_head");
        ResourceLocation baseFuzeLoc = MainShupapium.resource("block/projectile_fullblock_fuze_base");
        ResourceLocation sideLoc = MainShupapium.resource("block/" + pathAndMaterial);
        ResourceLocation topLoc = MainShupapium.resource("block/" + pathAndMaterial + "_top");
        ResourceLocation bottomLoc = MainShupapium.resource("block/" + pathAndMaterial + "_bottom");
        return (b) -> b.model((c, p) -> {
            ItemModelBuilder headFuzeModel = p.withExistingParent(c.getName() + "_head_fuze", headFuzeLoc).texture("side", sideLoc).texture("top", topLoc).texture("bottom", bottomLoc).texture("particle", topLoc);
            ItemModelBuilder baseFuzeModel = p.withExistingParent(c.getName() + "_base_fuze", baseFuzeLoc).texture("side", sideLoc).texture("top", topLoc).texture("bottom", bottomLoc).texture("particle", topLoc);
            p.blockItem(c).override().model(headFuzeModel).predicate(CreateBigCannons.resource("fuze_state"), 1.0F).end().override().model(baseFuzeModel).predicate(CreateBigCannons.resource("fuze_state"), 2.0F).end();
        });
    }

    public static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> projectile(String pathAndMaterial) {
        return projectile(pathAndMaterial, false);
    }

    public static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> projectileBlock(String pathAndMaterial) {
        ResourceLocation sideLoc = MainShupapium.resource("block/" + pathAndMaterial);
        ResourceLocation topLoc = MainShupapium.resource("block/" + pathAndMaterial + "_top");
        ResourceLocation bottomLoc = MainShupapium.resource("block/" + pathAndMaterial + "_bottom");
        return (b) -> b.properties(BlockBehaviour.Properties::noOcclusion).addLayer(() -> RenderType::solid).blockstate((c, p) -> {
            BlockModelBuilder builder = p.models().cubeBottomTop(c.getName(), sideLoc, bottomLoc, topLoc);
            p.directionalBlock(c.get(), builder);
        });
    }

    public static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> projectile(String pathAndMaterial, boolean useStandardModel) {
        ResourceLocation baseLoc = CreateBigCannons.resource(String.format("block/%sprojectile_block", useStandardModel ? "standard_" : ""));
        ResourceLocation sideLoc = MainShupapium.resource("block/" + pathAndMaterial);
        ResourceLocation topLoc = MainShupapium.resource("block/" + pathAndMaterial + "_top");
        ResourceLocation bottomLoc = MainShupapium.resource("block/" + pathAndMaterial + "_bottom");
        return (b) -> b.properties(BlockBehaviour.Properties::noOcclusion).addLayer(() -> RenderType::solid).blockstate((c, p) -> {
            BlockModelBuilder builder = p.models().withExistingParent(c.getName(), baseLoc).texture("side", sideLoc).texture("top", topLoc).texture("particle", topLoc);
            if (!useStandardModel) {
                builder.texture("bottom", bottomLoc);
            }

            p.directionalBlock(c.get(), builder);
        });
    }

    public static <T extends Block & AutocannonBlock, P> NonNullUnaryOperator<BlockBuilder<T, P>> autocannonBarrel(String mod, String path) {
        ResourceLocation modelLocation = ResourceLocation.fromNamespaceAndPath(mod, path);
        return b -> b
                .properties(BlockBehaviour.Properties::noOcclusion)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate((c, p) -> barrelBlockstate(c, p, p.models().getExistingFile(modelLocation), ShupapiumBuilderTransformers::standardRotation))
                .item(AutocannonBlockItem::new)
                .model((c, p) -> p.withExistingParent(c.getName(), modelLocation))
                .build();
    }

    public static <T extends Block & AutocannonBlock, P> NonNullUnaryOperator<BlockBuilder<T, P>> autocannonMortarBarrel(String mod, String path) {
        ResourceLocation modelLocation = ResourceLocation.fromNamespaceAndPath(mod, path);
        return b -> b
                .properties(BlockBehaviour.Properties::noOcclusion)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate((c, p) -> barrelBlockstate(c, p, p.models().getExistingFile(modelLocation), ShupapiumBuilderTransformers::mortarRotation))
                .item(AutocannonBlockItem::new)
                .model((c, p) -> p.withExistingParent(c.getName(), modelLocation))
                .build();
    }

    public static <T extends Block & AutocannonBlock, P> NonNullUnaryOperator<BlockBuilder<T, P>> geckoAutocannonBarrel(String mod, String path, Supplier<Supplier<BlockEntityWithoutLevelRenderer>> factory) {
        ResourceLocation modelLocation = ResourceLocation.fromNamespaceAndPath(mod, path);
        return b -> b
                .properties(BlockBehaviour.Properties::noOcclusion)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate((c, p) -> barrelBlockstate(c, p, p.models().getExistingFile(modelLocation), ShupapiumBuilderTransformers::standardRotation))
                .item((block, properties) -> new ShupapiumGACBlockItem<>(block, properties, factory))
                .model((c, p) -> p.getBuilder(c.getName()).parent(new ModelFile.UncheckedModelFile("builtin/entity")).guiLight(BlockModel.GuiLight.FRONT))
                .build();
    }

    public static <T extends Block & AutocannonBlock, P> NonNullUnaryOperator<BlockBuilder<T, P>> autocannonRecoilSpring(String mod, String path) {
        ResourceLocation modelLocation = ResourceLocation.fromNamespaceAndPath(mod, path);
        return b -> b
                .properties(BlockBehaviour.Properties::noOcclusion)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate((c, p) -> {
                    ModelFile model = p.models().getExistingFile(modelLocation);
                    p.getVariantBuilder(c.get())
                            .partialState()
                            .with(ShupapiumACRecoilSpringBlock.FACING, Direction.NORTH)
                            .modelForState()
                            .modelFile(model)
                            .addModel()

                            .partialState()
                            .with(ShupapiumACRecoilSpringBlock.FACING, Direction.EAST)
                            .modelForState()
                            .modelFile(model)
                            .rotationY(90)
                            .addModel()

                            .partialState()
                            .with(ShupapiumACRecoilSpringBlock.FACING, Direction.SOUTH)
                            .modelForState()
                            .modelFile(model)
                            .rotationY(180)
                            .addModel()

                            .partialState()
                            .with(ShupapiumACRecoilSpringBlock.FACING, Direction.WEST)
                            .modelForState()
                            .modelFile(model)
                            .rotationY(270)
                            .addModel()

                            .partialState()
                            .with(ShupapiumACRecoilSpringBlock.FACING, Direction.UP)
                            .modelForState()
                            .modelFile(model)
                            .rotationX(270)
                            .addModel()

                            .partialState()
                            .with(ShupapiumACRecoilSpringBlock.FACING, Direction.DOWN)
                            .modelForState()
                            .modelFile(model)
                            .rotationX(90)
                            .addModel();
                })
                .item(AutocannonBlockItem::new)
                .model((c, p) -> p.withExistingParent(c.getName(), modelLocation))
                .build();
    }

    public static <T extends Block & AutocannonBlock, P> NonNullUnaryOperator<BlockBuilder<T, P>> autocannonBreech(String mod, String path) {
        ResourceLocation breechLocation = ResourceLocation.fromNamespaceAndPath(mod, path);
        ResourceLocation handleLocation = MainShupapium.resource("block/breech_handle");
        return b -> b
                .properties(BlockBehaviour.Properties::noOcclusion)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate((c, p) -> {
                    ModelFile breechModel =  p.models().getExistingFile(breechLocation);
                    ModelFile handleModel =  p.models().getExistingFile(handleLocation);
                    MultiPartBlockStateBuilder builder = p.getMultipartBuilder(c.get());

                    addBreechPart(builder, breechModel, Direction.NORTH, 0, 0);
                    addBreechPart(builder, breechModel, Direction.EAST, 0, 90);
                    addBreechPart(builder, breechModel, Direction.SOUTH, 0, 180);
                    addBreechPart(builder, breechModel, Direction.WEST, 0, 270);
                    addBreechPart(builder, breechModel, Direction.UP, 270, 0);
                    addBreechPart(builder, breechModel, Direction.DOWN, 90, 0);

                    addHandlePart(builder, handleModel, Direction.NORTH, 0, 0);
                    addHandlePart(builder, handleModel, Direction.EAST, 0, 90);
                    addHandlePart(builder, handleModel, Direction.SOUTH, 0, 180);
                    addHandlePart(builder, handleModel, Direction.WEST, 0, 270);
                    addHandlePart(builder, handleModel, Direction.UP, 270, 0);
                    addHandlePart(builder, handleModel, Direction.DOWN, 90, 0);
                })
                .item(AutocannonBlockItem::new)
                .model((c, p) -> p.withExistingParent(c.getName(), breechLocation))
                .build();
    }

    public static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> acAmmoContainer(boolean isCreative) {
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
                            ResourceLocation loc = p.modLoc("block/shupapium_ammo_containers/" + root + suffix);
                            Direction.Axis axis = state.getValue(BlockStateProperties.HORIZONTAL_AXIS);
                            return ConfiguredModel.builder()
                                    .modelFile(p.models().getExistingFile(loc))
                                    .rotationY(axis == Direction.Axis.X ? 90 : 0)
                                    .build();
                        }, BlockStateProperties.WATERLOGGED))
                .loot((t, c) -> {
                    CopyComponentsFunction.Builder func = CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                            .include(ShupapiumDataComponents.AMMO)
                            .include(ShupapiumDataComponents.PROPELLANTS);
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
                .model((c, p) -> p.withExistingParent(c.getName(), p.modLoc("block/shupapium_ammo_containers/" + root)))
                .build();
    }

    private static void addBreechPart(MultiPartBlockStateBuilder builder, ModelFile model, Direction direction, int x, int y) {
        builder.part()
                .modelFile(model)
                .rotationX(x)
                .rotationY(y)
                .addModel()
                .condition(ShupapiumACBreechBlock.FACING, direction)
                .end();
    }

    private static void addHandlePart(MultiPartBlockStateBuilder builder, ModelFile model, Direction direction, int x, int y) {
        builder.part()
                .modelFile(model)
                .rotationX(x)
                .rotationY(y)
                .addModel()
                .condition(ShupapiumACBreechBlock.FACING, direction)
                .condition(ShupapiumACBreechBlock.HANDLE, true)
                .end();
    }
}