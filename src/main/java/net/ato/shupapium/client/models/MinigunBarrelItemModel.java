package net.ato.shupapium.client.models;

import com.wariumce.CrustyChunksMod;
import net.ato.shupapium.cannons.ShupapiumGACBarrelBlock;
import net.ato.shupapium.cannons.ShupapiumGACBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MinigunBarrelItemModel extends GeoModel<ShupapiumGACBlockItem<ShupapiumGACBarrelBlock>> {
    @Override
    public ResourceLocation getModelResource(ShupapiumGACBlockItem<ShupapiumGACBarrelBlock> animatable) {
        return ResourceLocation.fromNamespaceAndPath(CrustyChunksMod.MODID, "geo/minigunbarrel.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShupapiumGACBlockItem<ShupapiumGACBarrelBlock> animatable) {
        return ResourceLocation.fromNamespaceAndPath(CrustyChunksMod.MODID, "textures/block/minigunbarrel.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShupapiumGACBlockItem<ShupapiumGACBarrelBlock> animatable) {
        return ResourceLocation.fromNamespaceAndPath(CrustyChunksMod.MODID, "animations/minigunbarrel.animation.json");
    }
}
