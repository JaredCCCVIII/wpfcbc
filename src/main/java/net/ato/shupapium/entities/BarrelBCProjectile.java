package net.ato.shupapium.entities;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.wariumce.procedures.ExplosiveBarrelTriggerProcedure;
import net.ato.shupapium.ShupapiumBlocks;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class BarrelBCProjectile extends AbstractShupapiumBCProjectile {
    public BarrelBCProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.BARREL_SHELL_BLOCK;
    }

    @Override
    protected void detonate(Position position) {
        ExplosiveBarrelTriggerProcedure.execute(this.level(), position.x(), position.y(), position.z());
    }
}
