package net.ato.shupapium.blocks;

import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.blockentities.AbstractShupapiumBCProjectile;
import net.minecraft.world.entity.EntityType;

public class SmallBombClusterShellBlock extends AbstractShupapiumBCBlock {
    public SmallBombClusterShellBlock(Properties properties) {
        super(properties);
    }

    @Override
    public EntityType<? extends AbstractShupapiumBCProjectile> getAssociatedEntityType() {
        return ShupapiumEntities.SMALL_BOMB_CLUSTER_SHELL_PROJECTILE.get();
    }
}
