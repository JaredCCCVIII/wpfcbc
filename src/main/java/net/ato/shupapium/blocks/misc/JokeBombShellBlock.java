package net.ato.shupapium.blocks.misc;

import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.blockentities.AbstractShupapiumBCProjectile;
import net.ato.shupapium.blocks.AbstractShupapiumBCBlock;
import net.minecraft.world.entity.EntityType;

public class JokeBombShellBlock extends AbstractShupapiumBCBlock {
    public JokeBombShellBlock(Properties properties) {
        super(properties);
    }

    @Override
    public EntityType<? extends AbstractShupapiumBCProjectile> getAssociatedEntityType() {
        return ShupapiumEntities.JOKE_BOMB_SHELL_PROJECTILE.get();
    }
}
