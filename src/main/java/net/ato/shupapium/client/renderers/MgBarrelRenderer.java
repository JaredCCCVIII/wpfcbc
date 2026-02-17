package net.ato.shupapium.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ato.shupapium.blockentities.RotatoryACBarrelBlockEntity;
import net.ato.shupapium.blocks.RotatoryACBarrel;
import net.ato.shupapium.client.models.MgBarrelModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class MgBarrelRenderer extends GeoBlockRenderer<RotatoryACBarrelBlockEntity> {
    public MgBarrelRenderer(BlockEntityRendererProvider.Context ctx) {
        super(new MgBarrelModel());
    }

    @Override
    public void preRender(PoseStack poseStack, RotatoryACBarrelBlockEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        BlockState state = animatable.getBlockState();
        if (state.getBlock() instanceof RotatoryACBarrel barrel) {
            if (barrel.getFacing(state) == Direction.UP) {
                poseStack.translate(0, 0.5, -0.5);
            } else if (barrel.getFacing(state) == Direction.DOWN) {
                poseStack.translate(0, 0.5, 0.5);
            }
        }
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
