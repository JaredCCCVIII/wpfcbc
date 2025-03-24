package com.ato.shupapi.entities;

import com.ato.shupapi.ShupapiumSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombieVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.ForgeEventFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DaarickCitizen extends Zombie {
    public DaarickCitizen(EntityType<? extends Zombie> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
    }

    @Override
    protected void addBehaviourGoals() {
        super.addBehaviourGoals();
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Monster.createMobAttributes().add(Attributes.FOLLOW_RANGE, 64.0F).add(Attributes.MOVEMENT_SPEED, 0.4F).add(Attributes.ATTACK_DAMAGE, 1.0F).add(Attributes.ARMOR, 1.0F).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public boolean isUnderWaterConverting() {
        return false;
    }

    @Override
    public boolean canBreakDoors() {
        return super.canBreakDoors();
    }

    @Override
    public void setCanBreakDoors(boolean pCanBreakDoors) {
        super.setCanBreakDoors(pCanBreakDoors);
    }

    @Override
    protected boolean supportsBreakDoorGoal() {
        return super.supportsBreakDoorGoal();
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public int getExperienceReward() {
        return super.getExperienceReward();
    }

    @Override
    public void setBaby(boolean pChildZombie) {
        super.setBaby(pChildZombie);
    }

    @Override
    public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> pKey) {
        super.onSyncedDataUpdated(pKey);
    }

    @Override
    protected boolean convertsInWater() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void aiStep() {
        super.aiStep();
    }

    @Override
    protected void doUnderWaterConversion() {
        super.doUnderWaterConversion();
    }

    @Override
    protected void convertToZombieType(@NotNull EntityType<? extends Zombie> pEntityType) {
        super.convertToZombieType(pEntityType);
    }

    @Override
    protected boolean isSunSensitive() {
        return false;
    }

    @Override
    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        return super.hurt(pSource, pAmount);
    }

    @Override
    public boolean doHurtTarget(@NotNull Entity pEntity) {
        return super.doHurtTarget(pEntity);
    }

    @Override
    protected @NotNull SoundEvent getAmbientSound() {
        return ShupapiumSounds.DAARICK_AMBIENT.get();
    }

    @Override
    protected @NotNull SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return ShupapiumSounds.DAARICK_HURT.get();
    }

    @Override
    protected @NotNull SoundEvent getDeathSound() {
        return ShupapiumSounds.DAARICK_DEATH.get();
    }

    @Override
    protected @NotNull SoundEvent getStepSound() {
        return SoundEvents.ZOMBIE_VILLAGER_STEP;
    }

    @Override
    protected void playStepSound(@NotNull BlockPos pPos, @NotNull BlockState pBlock) {
        super.playStepSound(pPos, pBlock);
    }

    @Override
    public @NotNull MobType getMobType() {
        return MobType.ILLAGER;
    }

    @Override
    protected void populateDefaultEquipmentSlots(@NotNull RandomSource pRandom, @NotNull DifficultyInstance pDifficulty) {
        super.populateDefaultEquipmentSlots(pRandom, pDifficulty);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
    }

    @Override
    public boolean killedEntity(@NotNull ServerLevel pLevel, @NotNull LivingEntity pEntity) {
        return false;
    }

    @Override
    protected float getStandingEyeHeight(@NotNull Pose pPose, @NotNull EntityDimensions pSize) {
        return super.getStandingEyeHeight(pPose, pSize);
    }

    @Override
    public boolean canHoldItem(@NotNull ItemStack pStack) {
        return super.canHoldItem(pStack);
    }

    @Override
    public boolean wantsToPickUp(@NotNull ItemStack pStack) {
        return super.wantsToPickUp(pStack);
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor pLevel, @NotNull DifficultyInstance pDifficulty, @NotNull MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    protected void handleAttributes(float pDifficulty) {
        super.handleAttributes(pDifficulty);
    }

    @Override
    protected void randomizeReinforcementsChance() {
        super.randomizeReinforcementsChance();
    }

    @Override
    public double getMyRidingOffset() {
        return super.getMyRidingOffset();
    }

    @Override
    protected void dropCustomDeathLoot(@NotNull DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        Entity entity = pSource.getEntity();
        if (entity instanceof Creeper creeper) {
            if (creeper.canDropMobsSkull()) {
                ItemStack itemstack = Items.DIRT.getDefaultInstance();
                if (!itemstack.isEmpty()) {
                    creeper.increaseDroppedSkulls();
                    this.spawnAtLocation(itemstack);
                }
            }
        }
    }

    @Override
    protected @NotNull ItemStack getSkull() {
        return new ItemStack(Items.FEATHER);
    }
}
