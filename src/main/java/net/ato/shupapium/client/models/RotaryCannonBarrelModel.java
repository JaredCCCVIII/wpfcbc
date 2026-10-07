package net.ato.shupapium.client.models;

import com.wariumce.CrustyChunksMod;
import net.ato.shupapium.cannons.ShupapiumGACBarrelBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RotaryCannonBarrelModel extends GeoModel<ShupapiumGACBarrelBlockEntity> {
    @Override
    public ResourceLocation getModelResource(ShupapiumGACBarrelBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(CrustyChunksMod.MODID, "geo/racbarrel.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShupapiumGACBarrelBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(CrustyChunksMod.MODID, "textures/block/racbarrel.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShupapiumGACBarrelBlockEntity animatable) {
        return ResourceLocation.fromNamespaceAndPath(CrustyChunksMod.MODID, "animations/racbarrel.animation.json");
    }
}
