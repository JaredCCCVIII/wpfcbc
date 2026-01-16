package net.ato.shupapium.utils;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlock;

public class ShupapiumBuilderTransformers {
    public static <T extends Block & AutocannonBlock, P> NonNullUnaryOperator<BlockBuilder<T, P>> autocannonBarrel(String pathAndMaterial) {
        return ShupapiumBuilderTransformersImpl.autocannonBarrel(pathAndMaterial);
    }

    public static <T extends Block & AutocannonBlock, P> NonNullUnaryOperator<BlockBuilder<T, P>> autocannonBreech(String pathAndMaterial, boolean complete) {
        return ShupapiumBuilderTransformersImpl.autocannonBreech(pathAndMaterial, complete);
    }

    public static <T extends Block & AutocannonBlock, P> NonNullUnaryOperator<BlockBuilder<T, P>> autocannonRecoilSpring(String pathAndMaterial, boolean complete) {
        return ShupapiumBuilderTransformersImpl.autocannonRecoilSpring(pathAndMaterial, complete);
    }

    public static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> autocannonAmmoContainer(boolean isCreative) {
        return ShupapiumBuilderTransformersImpl.autocannonAmmoContainer(isCreative);
    }

    public static <T extends Item, P> NonNullUnaryOperator<ItemBuilder<T, P>> autocannonBreechExtractor(String pathAndMaterial) {
        return ShupapiumBuilderTransformersImpl.autocannonBreechExtractor(pathAndMaterial);
    }
}
