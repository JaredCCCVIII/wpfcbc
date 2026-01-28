package net.ato.shupapium.entities.misc;

import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.ShupapiumMobEffects;
import net.mcreator.crustychunks.init.CrustyChunksModItems;
import net.mcreator.crustychunks.init.CrustyChunksModParticleTypes;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.Objects;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class JokeCloudDetectorEntity extends AbstractArrow implements ItemSupplier {
    public static final ItemStack PROJECTILE_ITEM;

    public JokeCloudDetectorEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(ShupapiumEntities.JOKE_CLOUD_DETECTOR.get(), world);
    }

    public JokeCloudDetectorEntity(EntityType<? extends JokeCloudDetectorEntity> type, Level world) {
        super(type, world);
    }

    public JokeCloudDetectorEntity(EntityType<? extends JokeCloudDetectorEntity> type, double x, double y, double z, Level world) {
        super(type, x, y, z, world);
    }

    public JokeCloudDetectorEntity(EntityType<? extends JokeCloudDetectorEntity> type, LivingEntity entity, Level world) {
        super(type, entity, world);
    }

    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @OnlyIn(Dist.CLIENT)
    public @NotNull ItemStack getItem() {
        return PROJECTILE_ITEM;
    }

    protected @NotNull ItemStack getPickupItem() {
        return PROJECTILE_ITEM;
    }

    protected void doPostHurtEffects(@NotNull LivingEntity entity) {
        super.doPostHurtEffects(entity);
        entity.setArrowCount(entity.getArrowCount() - 1);
    }

    public void tick() {
        super.tick();
        this.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
        if (this.inGround) {
            this.discard();
        }

    }

    public static JokeCloudDetectorEntity shoot(Level world, LivingEntity entity, RandomSource source) {
        return shoot(world, entity, source, 1.0F, 5.0F, 5);
    }

    public static JokeCloudDetectorEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
        return shoot(world, entity, source, pullingPower, 5.0F, 5);
    }

    public static JokeCloudDetectorEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
        JokeCloudDetectorEntity entityarrow = new JokeCloudDetectorEntity(ShupapiumEntities.JOKE_CLOUD_DETECTOR.get(), entity, world);
        entityarrow.shoot(entity.getViewVector(1.0F).x, entity.getViewVector(1.0F).y, entity.getViewVector(1.0F).z, power * 2.0F, 0.0F);
        entityarrow.setSilent(true);
        entityarrow.setCritArrow(false);
        entityarrow.setBaseDamage(0.0F);
        entityarrow.setKnockback(knockback);
        world.addFreshEntity(entityarrow);
        world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.arrow.shoot"))), SoundSource.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.5F + 1.0F) + power / 2.0F);
        return entityarrow;
    }

    public static JokeCloudDetectorEntity shoot(LivingEntity entity, LivingEntity target) {
        JokeCloudDetectorEntity entityarrow = new JokeCloudDetectorEntity(ShupapiumEntities.JOKE_CLOUD_DETECTOR.get(), entity, entity.level());
        double dx = target.getX() - entity.getX();
        double dy = target.getY() + (double)target.getEyeHeight() - 1.1;
        double dz = target.getZ() - entity.getZ();
        entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * (double)0.2F, dz, 2.0F, 12.0F);
        entityarrow.setSilent(true);
        entityarrow.setBaseDamage(5.0F);
        entityarrow.setKnockback(5);
        entityarrow.setCritArrow(false);
        entity.level().addFreshEntity(entityarrow);
        entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("entity.arrow.shoot"))), SoundSource.PLAYERS, 1.0F, 1.0F / (RandomSource.create().nextFloat() * 0.5F + 1.0F));
        return entityarrow;
    }

    private void execute(LevelAccessor world, double x, double y, double z, Entity immediatesourceentity) {
        if (immediatesourceentity != null) {
            immediatesourceentity.setNoGravity(true);
            immediatesourceentity.noPhysics = true;
            immediatesourceentity.setDeltaMovement(new Vec3(0.0F, 0.0F, 0.0F));
            if (!immediatesourceentity.getPersistentData().getBoolean("Used")) {
                for(int index0 = 0; index0 < 5; ++index0) {
                    world.addParticle(CrustyChunksModParticleTypes.GAS_CLOUD.get(), x + Mth.nextDouble(RandomSource.create(), -6.5F, 7.5F), y + (double)1.0F, z + Mth.nextDouble(RandomSource.create(), -6.5F, 7.5F), Mth.nextDouble(RandomSource.create(), -0.5F, 0.5F), Mth.nextDouble(RandomSource.create(), -0.1, 0.3), Mth.nextDouble(RandomSource.create(), -0.5F, 0.5F));
                }

                immediatesourceentity.getPersistentData().putBoolean("Used", true);
            }

            if (immediatesourceentity.getPersistentData().getDouble("T") <= (double)200.0F) {
                immediatesourceentity.getPersistentData().putDouble("T", immediatesourceentity.getPersistentData().getDouble("T") + (double)1.0F);
                if (1 == Mth.nextInt(RandomSource.create(), 1, 20)) {
                    Vec3 _center = new Vec3(x, y, z);

                    for(Entity entityiterator : world.getEntitiesOfClass(Entity.class, (new AABB(_center, _center)).inflate(15.0F), (e) -> true).stream().sorted(Comparator.comparingDouble((_entcnd) -> _entcnd.distanceToSqr(_center))).toList()) {
                        if (entityiterator instanceof LivingEntity) {
                            immediatesourceentity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3(entityiterator.getX(), entityiterator.getY() + (double)1.0F, entityiterator.getZ()));
                            if (Math.abs(entityiterator.getY() - y) - (Math.abs((double)immediatesourceentity.level().clip(new ClipContext(immediatesourceentity.getEyePosition(1.0F), immediatesourceentity.getEyePosition(1.0F).add(immediatesourceentity.getViewVector(1.0F).scale(8.0F)), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, immediatesourceentity)).getBlockPos().getY() - y) + (double)0.5F) <= (double)0.0F && Math.abs(entityiterator.getX() - x) - (Math.abs((double)immediatesourceentity.level().clip(new ClipContext(immediatesourceentity.getEyePosition(1.0F), immediatesourceentity.getEyePosition(1.0F).add(immediatesourceentity.getViewVector(1.0F).scale(17.0F)), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, immediatesourceentity)).getBlockPos().getX() - x) + (double)0.5F) <= (double)0.0F && Math.abs(entityiterator.getZ() - z) - (Math.abs((double)immediatesourceentity.level().clip(new ClipContext(immediatesourceentity.getEyePosition(1.0F), immediatesourceentity.getEyePosition(1.0F).add(immediatesourceentity.getViewVector(1.0F).scale(17.0F)), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, immediatesourceentity)).getBlockPos().getZ() - z) + (double)0.5F) <= (double)0.0F) {
                                ItemStack var10000;
                                if (entityiterator instanceof LivingEntity _entGetArmor) {
                                    var10000 = _entGetArmor.getItemBySlot(EquipmentSlot.HEAD);
                                } else {
                                    var10000 = ItemStack.EMPTY;
                                }

                                if (!var10000.is(ItemTags.create(ResourceLocation.parse("crusty_chunks:gasmask")))) {
                                    if (entityiterator instanceof LivingEntity _entity) {
                                        if (!_entity.level().isClientSide()) {
                                            if (_entity.hasEffect(ShupapiumMobEffects.JOKE_EFFECT.get())) return;
                                            _entity.addEffect(new MobEffectInstance(ShupapiumMobEffects.JOKE_EFFECT.get(), 1040, 1, false, true));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (!immediatesourceentity.level().isClientSide()) {
                immediatesourceentity.discard();
            }

        }
    }

    static {
        PROJECTILE_ITEM = new ItemStack(CrustyChunksModItems.INVISIBLEITEM.get());
    }
}
