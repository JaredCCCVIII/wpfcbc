package net.ato.shupapium.entities;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.wariumce.procedures.FusionExplosionProcedure;
import net.ato.shupapium.ShupapiumBlocks;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class FusionBombBCProjectile extends AbstractShupapiumBCProjectile {
    public FusionBombBCProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.FUSION_BOMB_SHELL_BLOCK;
    }

    @Override
    protected void detonate(Position position) {
        FusionExplosionProcedure.execute(this.level(), position.x(), position.y(), position.z());
    }
}
