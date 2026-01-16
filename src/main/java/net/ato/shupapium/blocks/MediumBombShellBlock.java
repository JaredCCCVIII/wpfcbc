package net.ato.shupapium.blocks;

import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.blockentities.AbstractShupapiumBCProjectile;
import net.minecraft.world.entity.EntityType;

public class MediumBombShellBlock extends AbstractShupapiumBCBlock {
    public MediumBombShellBlock(Properties properties) {
        super(properties);
    }

    @Override
    public EntityType<? extends AbstractShupapiumBCProjectile> getAssociatedEntityType() {
        return ShupapiumEntities.MEDIUM_BOMB_SHELL_PROJECTILE.get();
    }
}
