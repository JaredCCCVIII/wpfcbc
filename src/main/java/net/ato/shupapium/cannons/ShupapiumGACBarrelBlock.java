package net.ato.shupapium.cannons;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

public class ShupapiumGACBarrelBlock extends ShupapiumACBarrelBlock {
    private final BlockEntityEntry<? extends AutocannonBlockEntity> blockEntityEntry;
    public ShupapiumGACBarrelBlock(Properties properties, AutocannonMaterial material, BlockEntityEntry<? extends AutocannonBlockEntity> blockEntityEntry) {
        super(properties, material);
        this.blockEntityEntry = blockEntityEntry;
    }

    @Override
    public BlockEntityType<? extends AutocannonBlockEntity> getBlockEntityType() {
        return blockEntityEntry.get();
    }
}
