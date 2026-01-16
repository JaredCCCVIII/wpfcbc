package net.ato.shupapium.blocks;

import net.ato.shupapium.ShupapiumBlockEntities;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

public class RotatoryACBarrel extends ShupapiumACBarrelBlock implements EntityBlock {
    public RotatoryACBarrel(Properties properties, AutocannonMaterial material) {
        super(properties, material);
    }

    @Override
    public BlockEntityType<? extends AutocannonBlockEntity> getBlockEntityType() {
        return ShupapiumBlockEntities.ROTARY_CANNON_BARREL.get();
    }
}
