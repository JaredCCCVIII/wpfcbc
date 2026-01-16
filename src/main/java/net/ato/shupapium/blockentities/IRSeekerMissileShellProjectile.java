package net.ato.shupapium.blockentities;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.ato.shupapium.ShupapiumBlocks;
import net.mcreator.crustychunks.procedures.ArtilleryHitProcedure;
import net.mcreator.crustychunks.procedures.IRMissileFlightTickProcedure;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class IRSeekerMissileShellProjectile extends AbstractShupapiumBCProjectile {
    public IRSeekerMissileShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) return;
        IRMissileFlightTickProcedure.execute(level(), this.getX(), this.getY(), this.getZ(), this);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.IR_SEEKER_MISSILE_SHELL_BLOCK;
    }

    @Override
    protected void detonate(Position position) {
        ArtilleryHitProcedure.execute(level(), this.getX(), this.getY(), this.getZ(), this);
    }
}
