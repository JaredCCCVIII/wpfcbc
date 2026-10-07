package net.ato.shupapium.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.ato.shupapium.cannons.ShupapiumGACBarrelBlock;
import net.ato.shupapium.cannons.ShupapiumGACBarrelBlockEntity;
import net.ato.shupapium.client.models.RotaryCannonBarrelModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class RotaryCannonBarrelRenderer extends GeoBlockRenderer<ShupapiumGACBarrelBlockEntity> {
    public RotaryCannonBarrelRenderer(BlockEntityRendererProvider.Context context) {
        super(new RotaryCannonBarrelModel());
    }

    @Override
    public @Nullable RenderType getRenderType(ShupapiumGACBarrelBlockEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(this.getTextureLocation(animatable));
    }

    @Override
    public void preRender(PoseStack poseStack, ShupapiumGACBarrelBlockEntity animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        BlockState state = animatable.getBlockState();
        if (state.getBlock() instanceof ShupapiumGACBarrelBlock barrel) {
            if (barrel.getFacing(state) == Direction.UP) {
                poseStack.translate(0, 0.5, -0.5);
            } else if (barrel.getFacing(state) == Direction.DOWN) {
                poseStack.translate(0, 0.5, 0.5);
            }
        }
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}
