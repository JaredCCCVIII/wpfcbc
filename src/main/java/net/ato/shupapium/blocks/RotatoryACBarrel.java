package net.ato.shupapium.blocks;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.ato.shupapium.ShupapiumBlockEntities;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

public class RotatoryACBarrel extends ShupapiumACBarrelBlock implements EntityBlock {
    private final BlockEntityEntry<? extends AutocannonBlockEntity> blockEntityEntry;
    public RotatoryACBarrel(Properties properties, AutocannonMaterial material, BlockEntityEntry<? extends AutocannonBlockEntity> blockEntityEntry) {
        super(properties, material);
        this.blockEntityEntry = blockEntityEntry;
    }

    @Override
    public BlockEntityType<? extends AutocannonBlockEntity> getBlockEntityType() {
        return blockEntityEntry.get();
    }
}
