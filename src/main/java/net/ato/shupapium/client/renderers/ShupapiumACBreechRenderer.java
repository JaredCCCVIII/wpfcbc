package net.ato.shupapium.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.ato.shupapium.blockentities.ShupapiumACBreechBlockEntity;
import net.ato.shupapium.blocks.ShupapiumACBreechBlock;
import net.ato.shupapium.blocks.ShupapiumAmmoContainerBlock;
import net.ato.shupapium.items.ShupapiumAmmoContainerItem;
import net.createmod.catnip.render.CachedBuffers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlock;
import rbasamoyai.createbigcannons.index.CBCBlockPartials;

public class ShupapiumACBreechRenderer extends SmartBlockEntityRenderer<ShupapiumACBreechBlockEntity> {
    public ShupapiumACBreechRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(ShupapiumACBreechBlockEntity blockEntity, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        super.renderSafe(blockEntity, partialTicks, ms, buffer, light, overlay);
        if (VisualizationManager.supportsVisualization(blockEntity.getLevel())) return;

        BlockState state = blockEntity.getBlockState();
        Direction facing = state.getValue(ShupapiumACBreechBlock.FACING);

        ms.pushPose();

        if (state.getValue(ShupapiumACBreechBlock.HANDLE)) {
            CachedBuffers.partialFacing(CBCBlockPartials.autocannonSeatFor(DyeColor.BLACK), state, facing)
                    .rotateCentered(Axis.YP.rotationDegrees(facing.getAxis().isVertical() ? 180 : 0))
                    .light(light)
                    .renderInto(ms, buffer.getBuffer(RenderType.cutoutMipped()));
        } else {
            Vector3f normal = facing.step();
            normal.mul(blockEntity.getAnimateOffset(partialTicks) * -0.5f);
            CachedBuffers.partialFacing(getPartialModelForState(blockEntity), state, facing)
                    .translate(normal)
                    .rotateCentered(Axis.YP.rotationDegrees(facing.getAxis().isVertical() ? 180 : 0))
                    .light(light)
                    .renderInto(ms, buffer.getBuffer(RenderType.cutoutMipped()));
        }

        ItemStack container = blockEntity.getMagazine();
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
