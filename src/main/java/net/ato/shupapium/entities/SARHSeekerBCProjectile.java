package net.ato.shupapium.entities;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class SARHSeekerBCProjectile extends AbstractShupapiumBCProjectile {
    public SARHSeekerBCProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return null;
    }

    @Override
    protected void detonate(Position position) {

    }
}
