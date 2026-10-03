package net.ato.shupapium.datagen;

import com.simibubi.create.foundation.data.BlockStateGen;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import com.wariumce.CrustyChunksMod;
import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.cannons.ShupapiumACBarrelBlock;
import net.ato.shupapium.cannons.ShupapiumACBreechBlock;
import net.ato.shupapium.cannons.ShupapiumACRecoilSpringBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.client.model.generators.*;
import rbasamoyai.createbigcannons.CreateBigCannons;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlockItem;
import rbasamoyai.createbigcannons.cannons.autocannon.breech.AutocannonBreechBlock;

public class ShupapiumBuilderTransformers {
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
                .blockstate((c, p) -> {
                    ModelFile model = p.models().getExistingFile(modelLocation);
                    p.getVariantBuilder(c.get())
                            .partialState()
                            .with(ShupapiumACBarrelBlock.FACING, Direction.NORTH)
                            .modelForState()
                            .modelFile(model)
                            .addModel()

                            .partialState()
                            .with(ShupapiumACBarrelBlock.FACING, Direction.EAST)
                            .modelForState()
                            .modelFile(model)
                            .rotationY(90)
                            .addModel()

                            .partialState()
                            .with(ShupapiumACBarrelBlock.FACING, Direction.SOUTH)
                            .modelForState()
                            .modelFile(model)
                            .rotationY(180)
                            .addModel()

                            .partialState()
                            .with(ShupapiumACBarrelBlock.FACING, Direction.WEST)
                            .modelForState()
                            .modelFile(model)
                            .rotationY(270)
                            .addModel()

                            .partialState()
                            .with(ShupapiumACBarrelBlock.FACING, Direction.UP)
                            .modelForState()
                            .modelFile(model)
                            .rotationX(270)
                            .addModel()

                            .partialState()
                            .with(ShupapiumACBarrelBlock.FACING, Direction.DOWN)
                            .modelForState()
                            .modelFile(model)
                            .rotationX(90)
                            .addModel();
                })
                .item(AutocannonBlockItem::new)
                .model((c, p) -> p.withExistingParent(c.getName(), modelLocation))
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
        ResourceLocation handleLocation = ResourceLocation.fromNamespaceAndPath(CrustyChunksMod.MODID, "block/manual_aimer");
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
                .rotationY((y + 180) % 360)
                .addModel()
                .condition(ShupapiumACBreechBlock.FACING, direction)
                .condition(ShupapiumACBreechBlock.HANDLE, true)
                .end();
    }
}