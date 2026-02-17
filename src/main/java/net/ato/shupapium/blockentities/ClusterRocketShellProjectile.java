package net.ato.shupapium.blockentities;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.ato.shupapium.ShupapiumBlocks;
import net.mcreator.crustychunks.entity.SmallBombProjectileEntity;
import net.mcreator.crustychunks.init.CrustyChunksModEntities;
import net.mcreator.crustychunks.procedures.ClusterBombTickProcedure;
import net.mcreator.crustychunks.procedures.ClusterRocketFlightTickProcedure;
import net.mcreator.crustychunks.procedures.ClusterRocketHitProcedure;
import net.minecraft.core.Position;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class ClusterRocketShellProjectile extends AbstractShupapiumBCProjectile {
    public ClusterRocketShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.CLUSTER_ROCKET_SHELL_BLOCK;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        ClusterRocketFlightTickProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
        if (this.isInGround()) {
            this.discard();
        }
    }

    @Override
    protected void detonate(Position position) {
        ClusterRocketHitProcedure.execute(level(), this);
    }
}
