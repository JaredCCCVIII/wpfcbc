package net.ato.shupapium.mobeffects;

import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.entities.ShupapiumDummyRagdoll;
import net.mcreator.crustychunks.procedures.BlockBusterHitProcedure;
import net.mcreator.crustychunks.procedures.ExplosiveBarrelTriggerProcedure;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ChorusFruitItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class JokeEffect extends MobEffect {
    public JokeEffect() {
        super(MobEffectCategory.HARMFUL, 0x94733A);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide) {
            var instance = pLivingEntity.getEffect(this);
            if (instance == null) return;

            int remaining = instance.getDuration();
            int total = pLivingEntity.getPersistentData().getInt("JokeEffectTotalDuration");
            if (total <= 0) return;

            float progress = 1.0F - ((float) remaining / total);
            float pitch = Mth.lerp(progress, 0.0F, 2.0F);

            pLivingEntity.level().playSound(null, pLivingEntity.blockPosition(), SoundEvents.CHICKEN_AMBIENT, SoundSource.NEUTRAL, 1.0F, pitch);

            if (!pLivingEntity.isAlive()) {
                BlockBusterHitProcedure.execute(pLivingEntity.level(), pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), pLivingEntity);
            } else {
                if (pLivingEntity.hurtTime > 0) {
                    randomTeleport(pLivingEntity.level(), pLivingEntity.getLastAttacker());
                }
            }
        }
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity pLivingEntity, @NotNull AttributeMap pAttributeMap, int pAmplifier) {
        super.addAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
        if (!pLivingEntity.level().isClientSide) {
            var instance = pLivingEntity.getEffect(this);
            if (instance != null) {
                if (pLivingEntity instanceof ShupapiumDummyRagdoll ragdoll) ragdoll.setChistosoConverted(true);
                pLivingEntity.getPersistentData().putInt("JokeEffectTotalDuration", instance.getDuration());
            }
            pLivingEntity.level().playSound(null, pLivingEntity.blockPosition(), SoundEvents.SHULKER_TELEPORT, SoundSource.NEUTRAL, 1.0F, Mth.nextFloat(RandomSource.create(), 0.5F, 1.5F));
        }
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, @NotNull AttributeMap pAttributeMap, int pAmplifier) {
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
        if (!pLivingEntity.level().isClientSide) {
            if (pLivingEntity instanceof ShupapiumDummyRagdoll ragdoll) ragdoll.setChistosoConverted(false);
            pLivingEntity.getPersistentData().remove("JokeEffectTotalDuration");
            pLivingEntity.level().playSound(null, pLivingEntity.blockPosition(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.NEUTRAL, 1.0F, Mth.nextFloat(RandomSource.create(), 0.5F, 1.5F));
            if (pLivingEntity instanceof Player playa) {
                if (playa.getPersistentData().getBoolean("ChistosadaCure")) {
                    MainShupapium.LOGGER.info("{} saved from the chistosada!", pLivingEntity.getName());
                    playa.getPersistentData().remove("ChistosadaCure");
                } else {
                    ExplosiveBarrelTriggerProcedure.execute(pLivingEntity.level(), pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ());
                }
            } else {
                ExplosiveBarrelTriggerProcedure.execute(pLivingEntity.level(), pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ());
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 4 == 0;
    }

    private void randomTeleport(Level pLevel, LivingEntity pEntityLiving) {
        if (pEntityLiving == null) return;
        double d0 = pEntityLiving.getX();
        double d1 = pEntityLiving.getY();
        double d2 = pEntityLiving.getZ();

        for(int i = 0; i < 16; ++i) {
            double d3 = pEntityLiving.getX() + (pEntityLiving.getRandom().nextDouble() - 0.5D) * 16.0D;
            double d4 = Mth.clamp(pEntityLiving.getY() + (double)(pEntityLiving.getRandom().nextInt(16) - 8), (double)pLevel.getMinBuildHeight(), (double)(pLevel.getMinBuildHeight() + ((ServerLevel)pLevel).getLogicalHeight() - 1));
            double d5 = pEntityLiving.getZ() + (pEntityLiving.getRandom().nextDouble() - 0.5D) * 16.0D;
            if (pEntityLiving.isPassenger()) {
                pEntityLiving.stopRiding();
            }

            Vec3 vec3 = pEntityLiving.position();
            pLevel.gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(pEntityLiving));
            net.minecraftforge.event.entity.EntityTeleportEvent.ChorusFruit event = net.minecraftforge.event.ForgeEventFactory.onChorusFruitTeleport(pEntityLiving, d3, d4, d5);
            if (event.isCanceled()) return;
            if (pEntityLiving.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true)) {
                SoundEvent soundevent = pEntityLiving instanceof Fox ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
                pLevel.playSound(null, d0, d1, d2, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
                pEntityLiving.playSound(soundevent, 1.0F, 1.0F);
                break;
            }
        }
    }
}
