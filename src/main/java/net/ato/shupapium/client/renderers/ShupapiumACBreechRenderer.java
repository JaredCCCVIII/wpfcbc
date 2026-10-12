package net.ato.shupapium.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.ato.shupapium.blocks.ShupapiumAmmoContainerBlock;
import net.ato.shupapium.cannons.ShupapiumACBreechBlock;
import net.ato.shupapium.cannons.ShupapiumACBreechBlockEntity;
import net.ato.shupapium.items.ShupapiumAmmoContainerItem;
import net.createmod.catnip.render.CachedBuffers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.injection.struct.InjectorGroupInfo;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlock;
import rbasamoyai.createbigcannons.index.CBCBlockPartials;

import java.util.Map;

public class ShupapiumACBreechRenderer extends SmartBlockEntityRenderer<ShupapiumACBreechBlockEntity> {
    public ShupapiumACBreechRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(ShupapiumACBreechBlockEntity breech, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        super.renderSafe(breech, partialTicks, ms, buffer, light, overlay);
        if (VisualizationManager.supportsVisualization(breech.getLevel())) return;

        BlockState state = breech.getBlockState();
        Direction facing = state.getValue(ShupapiumACBreechBlock.FACING);

        ms.pushPose();

        if (state.getValue(ShupapiumACBreechBlock.HANDLE)) {
            if (breech.getSeatColor() != null) {
                CachedBuffers.partialFacing(CBCBlockPartials.autocannonSeatFor(breech.getSeatColor()), state, facing)
                        .rotateCentered(Axis.YP.rotationDegrees(facing.getAxis().isVertical() ? 180 : 0))
                        .light(light)
                        .renderInto(ms, buffer.getBuffer(RenderType.cutoutMipped()));
            }
        } else {
            Vector3f normal = facing.step();
            normal.mul(breech.getAnimateOffset(partialTicks) * -0.5f);
            CachedBuffers.partialFacing(getPartialModelForState(breech), state, facing)
                    .translate(normal)
                    .rotateCentered(Axis.YP.rotationDegrees(facing.getAxis().isVertical() ? 180 : 0))
                    .light(light)
                    .renderInto(ms, buffer.getBuffer(RenderType.cutoutMipped()));
        }

        ItemStack container = breech.getMagazine();
        if (container.getItem() instanceof ShupapiumAmmoContainerItem) {
            boolean flag = facing.getAxis().isVertical();
            Quaternionf q1;
            if (flag) {
                float f = facing == Direction.UP ? 90 : -90;
                q1 = Axis.ZP.rotationDegrees(f);
                q1.mul(Axis.XP.rotationDegrees(f));
            } else {
                q1 = Axis.YP.rotationDegrees(-90 - facing.toYRot());
            }
            Direction offset = flag
                    ? facing.getCounterClockWise(Direction.Axis.Z)
                    : facing.getClockWise(Direction.Axis.Y);
            Vector3f normal = facing == Direction.UP ? offset.getOpposite().step() : offset.step();
            normal.mul(10 / 16f);

            CachedBuffers.block(getAmmoContainerModel(container))
                    .translate(normal)
                    .rotateCentered(q1)
                    .light(light)
                    .renderInto(ms, buffer.getBuffer(RenderType.cutoutMipped()));
        }

        ms.popPose();

        if (!breech.toAnimate.isEmpty()) {
            Direction breechFacing = breech.getBlockState().getValue(BlockStateProperties.FACING);
            float travel = ShupapiumACBreechBlockEntity.RECOIL_TRAVEL * breech.getAnimateOffset(partialTicks);
            Vector3f normal = breechFacing.step();
            normal.mul(-travel);

            BlockRenderDispatcher brd = Minecraft.getInstance().getBlockRenderer();
            for (Map.Entry<BlockPos, BlockState> entry : breech.toAnimate.entrySet()) {
                if (entry.getValue() == null) continue;
                ms.pushPose();
                BlockPos p = entry.getKey();
                ms.translate(p.getX() + normal.x(), p.getY() + normal.y(), p.getZ() + normal.z());
                brd.renderSingleBlock(entry.getValue(), ms, buffer, light, OverlayTexture.NO_OVERLAY);
                ms.popPose();
            }
        }
    }

    private static PartialModel getPartialModelForState(ShupapiumACBreechBlockEntity breech) {
        return breech.getBlockState().getBlock() instanceof AutocannonBlock cBlock
                ? CBCBlockPartials.autocannonEjectorFor(cBlock.getAutocannonMaterial())
                : CBCBlockPartials.CAST_IRON_AUTOCANNON_EJECTOR;
    }

    private static BlockState getAmmoContainerModel(ItemStack stack) {
        BlockState state = ((BlockItem) stack.getItem()).getBlock().defaultBlockState();
        if (state.hasProperty(ShupapiumAmmoContainerBlock.CONTAINER_STATE)) {
            state = state.setValue(ShupapiumAmmoContainerBlock.CONTAINER_STATE,
                    ShupapiumAmmoContainerBlock.State.getFromFilled(ShupapiumAmmoContainerItem.getTotalAmmoCount(stack) > 0));
        }
        return state;
    }
}
