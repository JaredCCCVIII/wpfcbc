package net.ato.shupapium.datagen;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.ato.shupapium.MainShupapium;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import rbasamoyai.createbigcannons.CreateBigCannons;

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
}
