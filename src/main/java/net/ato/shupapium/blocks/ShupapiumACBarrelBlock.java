package net.ato.shupapium.blocks;

import net.ato.shupapium.ShupapiumBlockEntities;
import net.ato.shupapium.utils.MountedShupapiumACContraption;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.cannon_control.contraption.AbstractMountedCannonContraption;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBarrelBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

public class ShupapiumACBarrelBlock extends AutocannonBarrelBlock {
    public ShupapiumACBarrelBlock(Properties properties, AutocannonMaterial material) {
        super(properties, material);
    }

    @Override
    public BlockEntityType<? extends AutocannonBlockEntity> getBlockEntityType() {
        return ShupapiumBlockEntities.SHUPAPIUM_AUTOCANNON.get();
    }

    @Override
    public @NotNull AbstractMountedCannonContraption getCannonContraption() {
        return new MountedShupapiumACContraption();
    }
}
