package net.ato.shupapium.blockentities;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.ato.shupapium.ShupapiumBlocks;
import net.mcreator.crustychunks.procedures.ClusterBombTickProcedure;
import net.mcreator.crustychunks.procedures.SmallBombProjectileHitsBlockProcedure;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class SmallBombClusterShellProjectile extends AbstractShupapiumBCProjectile {
    public SmallBombClusterShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.SMALL_BOMB_CLUSTER_SHELL_BLOCK;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        ClusterBombTickProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
        if (this.isInGround()) {
            this.discard();
        }
    }

    @Override
    protected void detonate(Position position) {
        SmallBombProjectileHitsBlockProcedure.execute(level(), this);
    }
}
