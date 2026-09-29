package net.ato.shupapium.entities;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.wariumce.procedures.SmallExplosionProcedure;
import net.ato.shupapium.ShupapiumBlocks;
import net.ato.shupapium.ShupapiumEntities;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.index.CBCItems;
import rbasamoyai.createbigcannons.munitions.big_cannon.AbstractBigCannonProjectile;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class SmallBombClusterBCProjectile extends AbstractShupapiumGSProjectile {
    public SmallBombClusterBCProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public EntityType<? extends AbstractBigCannonProjectile> getClusterEntity() {
        return ShupapiumEntities.SMALL_BOMB_SHELL_PROJECTILE.get();
    }

    @Override
    protected ItemStack getClusterFuze() {
        return CBCItems.IMPACT_FUZE.asStack();
    }

    @Override
    protected int burstProjectileCount() {
        return 5;
    }

    @Override
    protected double burstProjectileSpread() {
        return 0.20D;
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.SMALL_BOMB_CLUSTER_SHELL_BLOCK;
    }

    @Override
    protected void detonate(Position position) {
        SmallExplosionProcedure.execute(this.level(), position.x(), position.y(), position.z());
    }
}
