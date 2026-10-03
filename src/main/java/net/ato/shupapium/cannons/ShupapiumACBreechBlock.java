package net.ato.shupapium.cannons;

import net.ato.shupapium.ShupapiumBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;
import rbasamoyai.createbigcannons.cannons.autocannon.breech.AbstractAutocannonBreechBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.breech.AutocannonBreechBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

public class ShupapiumACBreechBlock extends AutocannonBreechBlock {
    public ShupapiumACBreechBlock(Properties properties, AutocannonMaterial material) {
        super(properties, material);
    }

    @Override
    public BlockEntityType<? extends AbstractAutocannonBreechBlockEntity> getBlockEntityType() {
        return ShupapiumBlockEntities.SHUPAPIUM_AUTOCANNON_BREECH.get();
    }
}
