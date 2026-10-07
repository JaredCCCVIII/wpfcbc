package net.ato.shupapium.cannons;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlockEntity;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ShupapiumGACBarrelBlockEntity extends AutocannonBlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public ShupapiumGACBarrelBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    private boolean isShooting() {
        if (this.getBlockState().getBlock() instanceof ShupapiumACBarrelBlock barrelBlock) {
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

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> event) {
        if (isShooting()) {
            event.getController().setAnimation(RawAnimation.begin().then("3", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        event.getController().stop();
        return PlayState.STOP;
    }
}
