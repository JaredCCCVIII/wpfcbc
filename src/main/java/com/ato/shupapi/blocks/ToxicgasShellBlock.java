package com.ato.shupapi.blocks;

import com.ato.shupapi.ShupapiumBlockEntities;
import com.ato.shupapi.entityblocks.ToxicgasShellProjectile;
import net.minecraft.world.entity.EntityType;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.big_cannon.SimpleShellBlock;

public class ToxicgasShellBlock extends SimpleShellBlock<ToxicgasShellProjectile> {
    public ToxicgasShellBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isBaseFuze() {
        return CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE.getPropertiesOf(this.getAssociatedEntityType()).fuze().baseFuze();
    }

    @Override
    public EntityType<? extends ToxicgasShellProjectile> getAssociatedEntityType() {
        return ShupapiumBlockEntities.TOXICGAS_SHELL.get();
    }
}
