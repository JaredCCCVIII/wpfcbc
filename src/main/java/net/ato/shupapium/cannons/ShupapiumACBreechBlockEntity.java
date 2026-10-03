package net.ato.shupapium.cannons;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import rbasamoyai.createbigcannons.cannons.autocannon.breech.AutocannonBreechBlockEntity;

public class ShupapiumACBreechBlockEntity extends AutocannonBreechBlockEntity {
    public ShupapiumACBreechBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }
}
