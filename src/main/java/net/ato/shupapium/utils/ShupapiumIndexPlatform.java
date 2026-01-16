package net.ato.shupapium.utils;

import net.ato.shupapium.blockentities.ShupapiumACBreechBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ShupapiumIndexPlatform {
    public static ShupapiumACBreechBlockEntity makeAutocannonBreech(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        return ShupapiumIndexPlatformImpl.makeAutocannonBreech(type, pos, state);
    }
}
