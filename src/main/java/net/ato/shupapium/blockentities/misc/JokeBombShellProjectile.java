package net.ato.shupapium.blockentities.misc;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.ato.shupapium.ShupapiumBlocks;
import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.blockentities.AbstractShupapiumBCProjectile;
import net.ato.shupapium.entities.misc.JokeCloudDetectorEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

import java.util.Objects;

public class JokeBombShellProjectile extends AbstractShupapiumBCProjectile {
    public JokeBombShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.JOKE_BOMB_SHELL_BLOCK;
    }

    @Override
    protected void detonate(Position position) {
        this.execute(this.level(), position.x(), position.y(), position.z());
    }

    private void execute(LevelAccessor world, double x, double y, double z) {
        if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
                _level.explode(null, x, y, z, 2.0F, Level.ExplosionInteraction.NONE);
            }
        }

        if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
                _level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("crusty_chunks:smallexplosion"))), SoundSource.NEUTRAL, 5.0F, (float)Mth.nextDouble(RandomSource.create(), 1.2, 1.4));
            } else {
                _level.playLocalSound(x, y, z, Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("crusty_chunks:smallexplosion"))), SoundSource.NEUTRAL, 5.0F, (float)Mth.nextDouble(RandomSource.create(), 1.2, 1.4), false);
            }
        }

        if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
                _level.playSound(null, BlockPos.containing(x, y, z), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("crusty_chunks:medium_small_explosion_distant"))), SoundSource.NEUTRAL, 20.0F, (float)Mth.nextDouble(RandomSource.create(), 0.9, 1.1));
            } else {
                _level.playLocalSound(x, y, z, Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("crusty_chunks:medium_small_explosion_distant"))), SoundSource.NEUTRAL, 20.0F, (float)Mth.nextDouble(RandomSource.create(), 0.9, 1.1), false);
            }
        }

        for(int index0 = 0; index0 < 10; ++index0) {
            if (world instanceof ServerLevel projectileLevel) {
                AbstractArrow entityToSpawn = new JokeCloudDetectorEntity(ShupapiumEntities.JOKE_CLOUD_DETECTOR.get(), projectileLevel);
                entityToSpawn.setBaseDamage(damage);
                entityToSpawn.setSilent(true);
                entityToSpawn.setPos(x, y + (double)3.0F, z);
                entityToSpawn.shoot(Mth.nextDouble(RandomSource.create(), -1.0F, 1.0F), Mth.nextDouble(RandomSource.create(), 0.0F, 1.0F), Mth.nextDouble(RandomSource.create(), -1.0F, 1.0F), 5.0F, 0.0F);
                projectileLevel.addFreshEntity(entityToSpawn);
            }
        }

    }
}
