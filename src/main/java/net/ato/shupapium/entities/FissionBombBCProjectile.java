package net.ato.shupapium.entities;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.wariumce.procedures.FissionExplosionProcedure;
import net.ato.shupapium.ShupapiumBlocks;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class FissionBombBCProjectile extends AbstractShupapiumBCProjectile {
    public FissionBombBCProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.FISSION_BOMB_SHELL_BLOCK;
    }

    @Override
    protected void detonate(Position position) {
        FissionExplosionProcedure.execute(this.level(), position.x(), position.y(), position.z());
    }
}
