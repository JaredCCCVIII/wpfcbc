package net.ato.shupapium.client.models;

import net.ato.shupapium.blockentities.RotatoryACBarrelBlockEntity;
import net.mcreator.crustychunks.CrustyChunksMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MgBarrelModel extends GeoModel<RotatoryACBarrelBlockEntity> {
    @Override
    public ResourceLocation getModelResource(RotatoryACBarrelBlockEntity rotatoryACBarrelBlockEntity) {
        return ResourceLocation.fromNamespaceAndPath(CrustyChunksMod.MODID, "geo/minigunbarrel.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RotatoryACBarrelBlockEntity rotatoryACBarrelBlockEntity) {
        return ResourceLocation.fromNamespaceAndPath(CrustyChunksMod.MODID, "textures/block/minigunbarrel.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RotatoryACBarrelBlockEntity rotatoryACBarrelBlockEntity) {
        return ResourceLocation.fromNamespaceAndPath(CrustyChunksMod.MODID, "animations/minigunbarrel.animation.json");
    }
}
