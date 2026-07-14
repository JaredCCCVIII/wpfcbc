package net.ato.shupapium.entities;

import net.ato.shupapium.ShupapiumMobEffects;
import net.ato.shupapium.ShupapiumSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ShupapiumDummyRagdoll extends Zombie {
    private static final EntityDataAccessor<Boolean> CHISTE_CONVERTED_ID = SynchedEntityData.defineId(ShupapiumDummyRagdoll.class, EntityDataSerializers.BOOLEAN);
    public ShupapiumDummyRagdoll(EntityType<? extends Zombie> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Mob.class, 10, true, false, entity -> !(entity instanceof ShupapiumDummyRagdoll)));
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FOLLOW_RANGE, 50.0F).add(Attributes.MOVEMENT_SPEED, 0.25F).add(Attributes.ATTACK_DAMAGE, 1.0D).add(Attributes.ARMOR, 0.5D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.0D);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(CHISTE_CONVERTED_ID, false);
    }

    public boolean isChistosoConverted() {
        return this.getEntityData().get(CHISTE_CONVERTED_ID);
    }

    public void setChistosoConverted(boolean pChistosoConverted) {
        this.getEntityData().set(CHISTE_CONVERTED_ID, pChistosoConverted);
    }

    @Override
    public boolean canBreakDoors() {
        return false;
    }

    @Override
    protected boolean convertsInWater() {
        return false;
    }

    @Override
    public boolean canBeLeashed() {
        return true;
    }

    @Override
    public boolean isBaby() {
        return false;
    }


    @Override
    protected int getBaseExperienceReward() {
        return 1;
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    protected @NotNull SoundEvent getAmbientSound() {
        return ShupapiumSounds.DUMMY_AMBIENT.get();
    }

    @Override
    protected @NotNull SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return ShupapiumSounds.DUMMY_HURT.get();
    }

    @Override
    protected @NotNull SoundEvent getDeathSound() {
        return ShupapiumSounds.DUMMY_DEATH.get();
    }

    @Override
    protected @NotNull SoundEvent getStepSound() {
        return SoundEvents.BAMBOO_WOOD_STEP;
    }

    @Override
    protected void populateDefaultEquipmentSlots(@NotNull RandomSource random, @NotNull DifficultyInstance difficulty) {
        super.populateDefaultEquipmentSlots(random, difficulty);
    }

    @Override
    public void setCustomName(Component pName) {
        super.setCustomName(pName);
        if (this.hasCustomName()) {
            String name = this.getName().getString();

            if (this.hasEffect(ShupapiumMobEffects.JOKE_EFFECT)) return;
            if (name.equalsIgnoreCase("Chistoso") || name.equalsIgnoreCase("Pedro")) {
                this.addEffect(new MobEffectInstance(ShupapiumMobEffects.JOKE_EFFECT, 400));
            }
        }
    }

    @Override
    protected void dropCustomDeathLoot(@NotNull ServerLevel level, @NotNull DamageSource damageSource, boolean recentlyHit) {
        super.dropCustomDeathLoot(level, damageSource, recentlyHit);
        Entity entity = damageSource.getEntity();
        if (entity instanceof Creeper creeper) {
            if (creeper.canDropMobsSkull()) {
                ItemStack itemstack = Items.FEATHER.getDefaultInstance();
                if (!itemstack.isEmpty()) {
                    creeper.increaseDroppedSkulls();
                    this.spawnAtLocation(itemstack);
                }
            }
        }
    }

    @Override
    protected @NotNull ItemStack getSkull() {
        return new ItemStack(Items.PAPER);
    }
}
