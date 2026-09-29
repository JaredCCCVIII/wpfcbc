package net.ato.shupapium.entities;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.wariumce.procedures.LargeBombProjectileProjectileHitsBlockProcedure;
import net.ato.shupapium.ShupapiumBlocks;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class HeavyBombBCProjectile extends AbstractShupapiumBCProjectile{
    public HeavyBombBCProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.HEAVY_BOMB_SHELL_BLOCK;
    }

    @Override
    protected void detonate(Position position) {
        LargeBombProjectileProjectileHitsBlockProcedure.execute(this.level(), position.x(), position.y(), position.z(), this);
    }
}
