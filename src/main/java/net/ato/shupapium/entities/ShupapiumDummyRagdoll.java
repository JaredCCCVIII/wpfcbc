package net.ato.shupapium.entities;

import net.ato.shupapium.ShupapiumMobEffects;
import net.ato.shupapium.ShupapiumSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ShupapiumDummyRagdoll extends Zombie {
    public ShupapiumDummyRagdoll(EntityType<? extends Zombie> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
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
    public boolean canBreakDoors() {
        return false;
    }

    @Override
    protected boolean convertsInWater() {
        return false;
    }

    @Override
    public boolean canBeLeashed(@NotNull Player pPlayer) {
        return true;
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public int getExperienceReward() {
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
    public @NotNull MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Override
    protected void populateDefaultEquipmentSlots(@NotNull RandomSource pRandom, @NotNull DifficultyInstance pDifficulty) {
        super.populateDefaultEquipmentSlots(pRandom, pDifficulty);
    }

    @Override
    public void setCustomName(@Nullable Component pName) {
        super.setCustomName(pName);
        if (this.hasCustomName()) {
            String name = this.getName().getString();

            if (name.equalsIgnoreCase("Chistoso")) {
                this.addEffect(new MobEffectInstance(ShupapiumMobEffects.JOKE_EFFECT.get(), 400));
            }
        }
    }

    @Override
    protected void dropCustomDeathLoot(@NotNull DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        Entity entity = pSource.getEntity();
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
