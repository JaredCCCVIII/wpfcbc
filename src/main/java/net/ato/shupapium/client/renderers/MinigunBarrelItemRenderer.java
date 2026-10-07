package net.ato.shupapium.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.ato.shupapium.cannons.ShupapiumGACBarrelBlock;
import net.ato.shupapium.cannons.ShupapiumGACBlockItem;
import net.ato.shupapium.client.models.MinigunBarrelItemModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MinigunBarrelItemRenderer extends GeoItemRenderer<ShupapiumGACBlockItem<ShupapiumGACBarrelBlock>> {

    public MinigunBarrelItemRenderer() {
        super(new MinigunBarrelItemModel());
    }

    @Override
    public @Nullable RenderType getRenderType(ShupapiumGACBlockItem<ShupapiumGACBarrelBlock> animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(this.getTextureLocation(animatable));
    }

    @Override
    public void preRender(PoseStack poseStack, ShupapiumGACBlockItem<ShupapiumGACBarrelBlock> animatable, BakedGeoModel model, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        poseStack.translate(1, -0.13, 0);
        poseStack.scale(0.7F, 0.7F, 0.7F);
        poseStack.mulPose(Axis.XP.rotationDegrees(30));
        poseStack.mulPose(Axis.YP.rotationDegrees(220));
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}
