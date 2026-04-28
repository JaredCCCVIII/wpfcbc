package net.ato.shupapium.entities;

import net.ato.shupapium.ShupapiumSounds;
import net.mcreator.crustychunks.init.CrustyChunksModSounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class ShupapiumMetalRagdoll extends ShupapiumDummyRagdoll {
    public ShupapiumMetalRagdoll(EntityType<? extends Zombie> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.@NotNull Builder createAttributes() {
        return Monster.createMobAttributes().add(Attributes.MAX_HEALTH, 40.0D).add(Attributes.FOLLOW_RANGE, 50.0F).add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.ATTACK_DAMAGE, 2.0D).add(Attributes.ARMOR, 2.8D).add(Attributes.SPAWN_REINFORCEMENTS_CHANCE, 0.0D);
    }

    @Override
    protected @NotNull SoundEvent getAmbientSound() {
        return ShupapiumSounds.METAL_DUMMY_AMBIENT.get();
    }

    @Override
    protected @NotNull SoundEvent getHurtSound(@NotNull DamageSource pDamageSource) {
        return ShupapiumSounds.METAL_DUMMY_HURT.get();
    }

    @Override
    protected @NotNull SoundEvent getDeathSound() {
        return ShupapiumSounds.METAL_DUMMY_DEATH.get();
    }

    @Override
    protected @NotNull SoundEvent getStepSound() {
        return SoundEvents.IRON_GOLEM_STEP;
    }

    @Override
    protected @NotNull ItemStack getSkull() {
        return new ItemStack(Items.IRON_INGOT);
    }

    @Override
    protected void playHurtSound(@NotNull DamageSource pSource) {
        super.playHurtSound(pSource);
        this.playSound(CrustyChunksModSounds.MECHSTEP.get(), Mth.nextFloat(RandomSource.create(), 0.5F, 1.0F), Mth.nextFloat(RandomSource.create(), 0.1F, 0.4F));
    }
}
