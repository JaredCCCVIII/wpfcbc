package net.ato.shupapium.entities.misc;

import com.wariumce.init.CrustyChunksModItems;
import com.wariumce.init.CrustyChunksModParticleTypes;
import net.ato.shupapium.ShupapiumMobEffects;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.CBCTags;

import java.util.List;

public class JokeCloudDetectorEntity extends Entity implements ItemSupplier {
    private static final int MAX_LIFETIME = 200;
    private static final double RADIUS = 15.0;
    public JokeCloudDetectorEntity(EntityType<? extends JokeCloudDetectorEntity> entityType, Level level) {
        super(entityType, level);
        this.noPhysics = true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {

    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {

    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {

    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide()) {
            spawnParticles();
            return;
        }
        if (this.tickCount >= MAX_LIFETIME) {
            this.discard();
            return;
        }

        if (this.tickCount % 2 == 0) {
            applyGasEffect();
        }
    }

    private void applyGasEffect() {
        AABB area = new AABB(blockPosition()).inflate(RADIUS);
        List<LivingEntity> entities = this.level().getEntitiesOfClass(LivingEntity.class, area, entity -> entity.isAlive() && !entity.hasEffect(ShupapiumMobEffects.JOKE_EFFECT));
        for (LivingEntity living : entities) {
            if (living.getItemBySlot(EquipmentSlot.HEAD).is(CBCTags.CBCItemTags.GAS_MASKS) || living.getItemBySlot(EquipmentSlot.HEAD).is(ItemTags.create(ResourceLocation.parse("wariumce:gasmask")))) continue;
            living.addEffect(new MobEffectInstance(ShupapiumMobEffects.JOKE_EFFECT, 1040, 0, false, true));
        }
    }

    private void spawnParticles() {
        RandomSource random = level().random;
        for (int i = 0; i < 3; i++) {
            double offsetX = Mth.nextDouble(random, -6.5, 7.5);
            double offsetZ = Mth.nextDouble(random, -6.5, 7.5);
            double velocityX = Mth.nextDouble(random, -0.5, 0.5);
            double velocityY = Mth.nextDouble(random, -0.1, 0.3);
            double velocityZ = Mth.nextDouble(random, -0.5, 0.5);

            level().addParticle(
                    CrustyChunksModParticleTypes.GAS_CLOUD.get(),
                    getX() + offsetX,
                    getY() + 1,
                    getZ() + offsetZ,
                    velocityX,
                    velocityY,
                    velocityZ
            );
        }
    }

    @Override
    public @NotNull ItemStack getItem() {
        return ItemStack.EMPTY;
    }
}
