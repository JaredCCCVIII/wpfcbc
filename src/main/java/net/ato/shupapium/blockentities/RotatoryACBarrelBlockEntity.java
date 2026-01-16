package net.ato.shupapium.blockentities;

import net.ato.shupapium.blocks.RotatoryACBarrel;
import net.ato.shupapium.utils.MountedShupapiumACContraption;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlockEntity;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class RotatoryACBarrelBlockEntity extends AutocannonBlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public RotatoryACBarrelBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "barrel_controller", 0, this::barrelPredicate));
    }

    private boolean isShooting() {
        if (this.getBlockState().getBlock() instanceof RotatoryACBarrel barrelBlock) {
            if (barrelBlock.getCannonContraption() instanceof MountedShupapiumACContraption cannonContraption) {
                for (BlockEntity blockEntity : cannonContraption.presentBlockEntities.values()) {
                    if (blockEntity instanceof ShupapiumACBreechBlockEntity breech) {
                        return !breech.canFire();
                    }
                }
            }
        }
        return false;
    }

    private <T extends GeoAnimatable> PlayState barrelPredicate(AnimationState<T> event) {
        if (isShooting()) {
            event.getController().setAnimation(RawAnimation.begin().then("3", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        event.getController().stop();
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
