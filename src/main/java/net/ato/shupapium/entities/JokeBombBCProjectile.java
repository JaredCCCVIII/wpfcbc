package net.ato.shupapium.entities;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.ato.shupapium.ShupapiumBlocks;
import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.entities.misc.JokeCloudDetectorEntity;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public class JokeBombBCProjectile extends AbstractShupapiumBCProjectile {
    public JokeBombBCProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.JOKE_BOMB_SHELL_BLOCK;
    }

    @Override
    protected void detonate(Position position) {
        JokeCloud(this.level(), position.x(), position.y(), position.z());
    }

    private static void JokeCloud(Level level, double x, double y, double z) {
        if (level.isClientSide) return;
        level.explode(null, x, y, z, 2.0F, Level.ExplosionInteraction.NONE);
        JokeCloudDetectorEntity cloud = new JokeCloudDetectorEntity(ShupapiumEntities.JOKE_CLOUD_DETECTOR.get(), level);
        cloud.setPos(x, y + 1, z);
        level.addFreshEntity(cloud);
    }
}
