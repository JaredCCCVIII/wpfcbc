package net.ato.shupapium.client.models;

import net.ato.shupapium.blockentities.RotatoryACBarrelBlockEntity;
import net.mcreator.crustychunks.CrustyChunksMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class RotatoryACBarrelModel extends GeoModel<RotatoryACBarrelBlockEntity> {
    @Override
    public ResourceLocation getModelResource(RotatoryACBarrelBlockEntity rotatoryACBarrelBlockEntity) {
        return new ResourceLocation(CrustyChunksMod.MODID, "geo/racbarrel.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(RotatoryACBarrelBlockEntity rotatoryACBarrelBlockEntity) {
        return new ResourceLocation(CrustyChunksMod.MODID, "textures/block/racbarrel.png");
    }

    @Override
    public ResourceLocation getAnimationResource(RotatoryACBarrelBlockEntity rotatoryACBarrelBlockEntity) {
        return new ResourceLocation(CrustyChunksMod.MODID, "animations/racbarrel.animation.json");
    }
}
