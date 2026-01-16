package net.ato.shupapium.blocks;

import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.blockentities.AbstractShupapiumBCProjectile;
import net.minecraft.world.entity.EntityType;

public class FissionBombShellBlock extends AbstractShupapiumBCBlock {
    public FissionBombShellBlock(Properties properties) {
        super(properties);
    }

    @Override
    public EntityType<? extends AbstractShupapiumBCProjectile> getAssociatedEntityType() {
        return ShupapiumEntities.FISSION_BOMB_SHELL_PROJECTILE.get();
    }
}
