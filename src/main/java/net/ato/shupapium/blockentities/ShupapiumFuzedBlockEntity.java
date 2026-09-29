package net.ato.shupapium.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBlockEntity;

public class ShupapiumFuzedBlockEntity extends FuzedBlockEntity {
    public ShupapiumFuzedBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
