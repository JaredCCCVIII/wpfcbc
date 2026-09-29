package net.ato.shupapium.blocks;

import net.ato.shupapium.ShupapiumBlockEntities;
import net.ato.shupapium.blockentities.ShupapiumFuzedBlockEntity;
import net.ato.shupapium.entities.AbstractShupapiumBCProjectile;
import net.minecraft.world.level.block.entity.BlockEntityType;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBlockEntity;
import rbasamoyai.createbigcannons.munitions.big_cannon.SimpleShellBlock;

public abstract class AbstractShupapiumBCBlock extends SimpleShellBlock<AbstractShupapiumBCProjectile> {
    public AbstractShupapiumBCBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<? extends FuzedBlockEntity> getBlockEntityType() {
        return ShupapiumBlockEntities.SHUPAPED_FUZED_BLOCK.get();
    }

    @Override
    public boolean isBaseFuze() {
        return CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE.getPropertiesOf(this.getAssociatedEntityType()).fuze().baseFuze();
    }
}
