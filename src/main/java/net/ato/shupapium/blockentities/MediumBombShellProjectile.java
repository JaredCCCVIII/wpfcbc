package net.ato.shupapium.blockentities;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.ato.shupapium.ShupapiumBlocks;
import net.mcreator.crustychunks.procedures.MediumBombProjectileHitsBlockProcedure;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class MediumBombShellProjectile extends AbstractShupapiumBCProjectile {
    public MediumBombShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.MEDIUM_BOMB_SHELL_BLOCK;
    }

    @Override
    protected void detonate(Position position) {
        MediumBombProjectileHitsBlockProcedure.execute(this.level(), this);
    }
}
