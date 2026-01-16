package net.ato.shupapium.blockentities;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.ato.shupapium.ShupapiumBlocks;
import net.mcreator.crustychunks.procedures.SuperLargeBombProjectileHitsBlockProcedure;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class SuperHeavyBombShellProjectile extends AbstractShupapiumBCProjectile {
    public SuperHeavyBombShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.SUPER_HEAVY_BOMB_SHELL_BLOCK;
    }

    @Override
    protected void detonate(Position position) {
        SuperLargeBombProjectileHitsBlockProcedure.execute(this.level(), position.x(), position.y(), position.z(), this);
    }
}
