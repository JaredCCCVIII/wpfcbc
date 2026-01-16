package net.ato.shupapium.blocks;

import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.blockentities.AbstractShupapiumBCProjectile;
import net.minecraft.world.entity.EntityType;

public class FireBombShellBlock extends AbstractShupapiumBCBlock {
    public FireBombShellBlock(Properties properties) {
        super(properties);
    }

    @Override
    public EntityType<? extends AbstractShupapiumBCProjectile> getAssociatedEntityType() {
        return ShupapiumEntities.FIRE_BOMB_SHELL_PROJECTILE.get();
    }
}
