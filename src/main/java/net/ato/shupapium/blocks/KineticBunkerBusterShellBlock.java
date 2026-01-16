package net.ato.shupapium.blocks;

import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.blockentities.AbstractShupapiumBCProjectile;
import net.minecraft.world.entity.EntityType;

public class KineticBunkerBusterShellBlock extends AbstractShupapiumBCBlock{
    public KineticBunkerBusterShellBlock(Properties properties) {
        super(properties);
    }

    @Override
    public EntityType<? extends AbstractShupapiumBCProjectile> getAssociatedEntityType() {
        return ShupapiumEntities.KINETIC_BUNKER_BUSTER_SHELL_PROJECTILE.get();
    }
}
