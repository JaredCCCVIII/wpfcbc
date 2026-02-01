package net.ato.shupapium.mixin;

import com.happysg.radar.compat.cbc.CannonUtil;
import net.ato.shupapium.utils.MountedShupapiumACContraption;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import rbasamoyai.createbigcannons.cannon_control.contraption.AbstractMountedCannonContraption;

@Mixin(CannonUtil.class)
public abstract class CannonUtilMixin {
    @Inject(
            method = "getProjectileGravity",
            at = @At("HEAD"),
            cancellable = true
    )

    private static void shupapium$overrideGravity(AbstractMountedCannonContraption cannon, ServerLevel level, CallbackInfoReturnable<Double> cir) {
        if (cannon instanceof MountedShupapiumACContraption) {
            cir.setReturnValue(0.0001);
        }
    }
}
