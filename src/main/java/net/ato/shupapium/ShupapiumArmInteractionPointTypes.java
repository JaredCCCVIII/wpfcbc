package net.ato.shupapium;

import com.simibubi.create.api.registry.CreateBuiltInRegistries;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPoint;
import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPointType;
import net.ato.shupapium.custom_interactions.ShupapiumCannonMountPoint;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import rbasamoyai.createbigcannons.index.CBCArmInteractionPointTypes;

import javax.annotation.Nullable;

public class ShupapiumArmInteractionPointTypes {
    static {
        register("shupaped_cannon_mount", new ShupapedCannonMountType());
    }

    private static <T extends ArmInteractionPointType> void register(String name, T type) {
        Registry.register(CreateBuiltInRegistries.ARM_INTERACTION_POINT_TYPE, MainShupapium.resource(name), type);
    }

    public static class ShupapedCannonMountType extends CBCArmInteractionPointTypes.CannonMountType {
        @Nullable
        @Override
        public ArmInteractionPoint createPoint(Level level, BlockPos pos, BlockState state) {
            return new ShupapiumCannonMountPoint(this, level, pos, state);
        }

        @Override
        public int getPriority() {
            return 1;
        }
    }

    public static void init() {}
}
