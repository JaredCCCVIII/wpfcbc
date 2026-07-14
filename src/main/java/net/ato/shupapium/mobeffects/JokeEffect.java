package net.ato.shupapium.mobeffects;

import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumMobEffects;
import net.ato.shupapium.entities.ShupapiumDummyRagdoll;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.event.entity.EntityTeleportEvent;
import org.jetbrains.annotations.NotNull;

public class JokeEffect extends MobEffect {
    public JokeEffect() {
        super(MobEffectCategory.HARMFUL, 0x94733A);
    }

    @Override
    public boolean applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
        if (!livingEntity.level().isClientSide) {
            var instance = livingEntity.getEffect(ShupapiumMobEffects.JOKE_EFFECT);
            assert instance != null;
            int remaining = instance.getDuration();
            int total = livingEntity.getPersistentData().getInt("JokeEffectTotalDuration");

            float progress = 1.0F - ((float) remaining / total);
            float pitch = Mth.lerp(progress, 0.0F, 2.0F);

            livingEntity.level().playSound(null, livingEntity.blockPosition(), SoundEvents.CHICKEN_AMBIENT, SoundSource.NEUTRAL, 1.0F, pitch);

            if (!livingEntity.isAlive()) {
                livingEntity.level().explode(livingEntity, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 9, false, Level.ExplosionInteraction.MOB);
                //BlockBusterHitProcedure.execute(pLivingEntity.level(), pLivingEntity);
                return false;
            } else {
                if (livingEntity.hurtTime > 0) {
                    randomTeleport(livingEntity.level(), livingEntity.getLastAttacker());
                }
            }
        }
        return super.applyEffectTick(livingEntity, amplifier);
    }

    @Override
    public void onEffectAdded(@NotNull LivingEntity livingEntity, int amplifier) {
        super.onEffectAdded(livingEntity, amplifier);
        if (!livingEntity.level().isClientSide) {
            var instance = livingEntity.getEffect(ShupapiumMobEffects.JOKE_EFFECT);
            if (instance != null) {
                if (livingEntity instanceof ShupapiumDummyRagdoll ragdoll) ragdoll.setChistosoConverted(true);
                livingEntity.getPersistentData().putInt("JokeEffectTotalDuration", instance.getDuration());
            }
            livingEntity.level().playSound(null, livingEntity.blockPosition(), SoundEvents.SHULKER_TELEPORT, SoundSource.NEUTRAL, 1.0F, Mth.nextFloat(RandomSource.create(), 0.5F, 1.5F));
        }
    }

    public void onEffectRemoved(@NotNull LivingEntity livingEntity, boolean isExternalRemove) {
        if (!livingEntity.level().isClientSide) {
            if (livingEntity instanceof ShupapiumDummyRagdoll ragdoll) ragdoll.setChistosoConverted(false);
            livingEntity.getPersistentData().remove("JokeEffectTotalDuration");
            livingEntity.level().playSound(null, livingEntity.blockPosition(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 1.0F, Mth.nextFloat(RandomSource.create(), 0.5F, 1.5F));
            if (isExternalRemove) {
                MainShupapium.LOGGER.info("{} saved from the chistosada!", livingEntity.getName());
            } else {
                //ExplosionExampleProcedure.execute(pLivingEntity.level(), pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), 5.0F);
                livingEntity.level().explode(livingEntity, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), 5, false, Level.ExplosionInteraction.MOB);
            }
        }
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 4 == 0;
    }

    private void randomTeleport(Level pLevel, LivingEntity pEntityLiving) {
        if (pEntityLiving == null) return;

        for(int i = 0; i < 16; ++i) {
            double d0 = pEntityLiving.getX() + (pEntityLiving.getRandom().nextDouble() - (double)0.5F) * (double)16.0F;
            double d1 = Mth.clamp(pEntityLiving.getY() + (double)(pEntityLiving.getRandom().nextInt(16) - 8), pLevel.getMinBuildHeight(), pLevel.getMinBuildHeight() + ((ServerLevel)pLevel).getLogicalHeight() - 1);
            double d2 = pEntityLiving.getZ() + (pEntityLiving.getRandom().nextDouble() - (double)0.5F) * (double)16.0F;
            if (pEntityLiving.isPassenger()) {
                pEntityLiving.stopRiding();
            }

            Vec3 vec3 = pEntityLiving.position();
            EntityTeleportEvent.ChorusFruit event = EventHooks.onChorusFruitTeleport(pEntityLiving, d0, d1, d2);
            if (event.isCanceled()) return;

            if (pEntityLiving.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
                pLevel.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(pEntityLiving));
                SoundSource soundsource;
                SoundEvent soundevent;
                if (pEntityLiving instanceof Fox) {
                    soundevent = SoundEvents.FOX_TELEPORT;
                    soundsource = SoundSource.NEUTRAL;
                } else {
                    soundevent = SoundEvents.CHORUS_FRUIT_TELEPORT;
                    soundsource = SoundSource.PLAYERS;
                }

                pLevel.playSound(null, pEntityLiving.getX(), pEntityLiving.getY(), pEntityLiving.getZ(), soundevent, soundsource);
                pEntityLiving.resetFallDistance();
                break;
            }
        }
    }
}
