package net.ato.shupapium.blockentities;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.ato.shupapium.ShupapiumBlocks;
import net.mcreator.crustychunks.procedures.BlockBusterHitProcedure;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class BlockBusterShellProjectile extends AbstractShupapiumBCProjectile {
    public BlockBusterShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.BLOCK_BUSTER_SHELL_BLOCK;
    }

    @Override
    protected void detonate(Position position) {
        BlockBusterHitProcedure.execute(this.level(), position.x(), position.y(), position.z(), this);
    }
}
