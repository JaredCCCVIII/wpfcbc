package com.ato.shupapi.blocks;

import com.ato.shupapi.ShupapiumBlockEntities;
import com.ato.shupapi.entityblocks.RedordinanceShellProjectile;
import net.minecraft.world.entity.EntityType;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.big_cannon.SimpleShellBlock;

public class RedordinanceShellBlock extends SimpleShellBlock<RedordinanceShellProjectile> {
    public RedordinanceShellBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isBaseFuze() {
        return CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE.getPropertiesOf(this.getAssociatedEntityType()).fuze().baseFuze();
    }

    @Override
    public EntityType<? extends RedordinanceShellProjectile> getAssociatedEntityType() {
        return ShupapiumBlockEntities.REDORDINANCE_SHELL.get();
    }
}
