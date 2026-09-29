package net.ato.shupapium.entities;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.wariumce.procedures.SmokeBombDetonateProcedure;
import net.ato.shupapium.ShupapiumBlocks;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class SmokeBombBCProjectile extends AbstractShupapiumBCProjectile {
    public SmokeBombBCProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.SMOKE_BOMB_SHELL_BLOCK;
    }

    @Override
    protected void detonate(Position position) {
        SmokeBombDetonateProcedure.execute(this.level(), position.x(), position.y(), position.z());
    }
}
