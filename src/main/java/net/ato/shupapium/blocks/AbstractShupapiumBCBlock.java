package net.ato.shupapium.blocks;

import com.tterrag.registrate.util.entry.EntityEntry;
import net.ato.shupapium.blockentities.AbstractShupapiumBCProjectile;
import net.minecraft.world.entity.EntityType;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.big_cannon.SimpleShellBlock;

public abstract class AbstractShupapiumBCBlock extends SimpleShellBlock<AbstractShupapiumBCProjectile> {
    public AbstractShupapiumBCBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isBaseFuze() {
        return CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE.getPropertiesOf(this.getAssociatedEntityType()).fuze().baseFuze();
    }
}
