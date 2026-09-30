package net.ato.shupapium.entities;

import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rbasamoyai.createbigcannons.munitions.big_cannon.AbstractBigCannonProjectile;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

public abstract class AbstractShupapiumGSProjectile extends AbstractShupapiumBCProjectile {
    private ItemStack clusterFuze = ItemStack.EMPTY;
    public AbstractShupapiumGSProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    public abstract EntityType<? extends AbstractBigCannonProjectile> getClusterEntity();
    protected abstract int burstProjectileCount();
    protected abstract double burstProjectileSpread();

    @Override
    public void setFuze(ItemStack stack) {
        super.setFuze(stack);
        this.clusterFuze = stack != null && !stack.isEmpty() ? stack.copy() : ItemStack.EMPTY;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide) {
            spawnConeBurst(this.level(), this.getClusterEntity(), this.position(), this.getDeltaMovement(), this.burstProjectileCount(), this.burstProjectileSpread());
            this.discard();
        }
    }

    private <T extends AbstractBigCannonProjectile> void spawnConeBurst(Level level, EntityType<T> type, Vec3 position,
                                                                        Vec3 initialVelocity, int count, double spread) {

        Vec3 forward = initialVelocity.normalize();
        Vec3 right = forward.cross(new Vec3(Direction.UP.step()));
        if (right.lengthSqr() < 1e-6d)
            right = new Vec3(1, 0, 0);
        Vec3 up = forward.cross(right);
        double length = initialVelocity.length();
        RandomSource random = level.getRandom();
        for (int i = 0; i < count; ++i) {
            T burst = type.create(level);
            assert burst != null;
            double velScale = length * (1.4d + 0.2d * random.nextDouble());
            double rx = (random.nextDouble() - random.nextDouble()) * 0.0625d;
            double ry = (random.nextDouble() - random.nextDouble()) * 0.0625d;
            double rz = (random.nextDouble() - random.nextDouble()) * 0.0625d;
            Vec3 vel = forward.scale(velScale)
                    .add(right.scale((random.nextDouble() - random.nextDouble()) * velScale * spread))
                    .add(up.scale((random.nextDouble() - random.nextDouble()) * velScale * spread));
            burst.setPos(position.add(rx, ry, rz));
            burst.setDeltaMovement(vel.x, vel.y, vel.z);
            level.addFreshEntity(burst);
            if (burst instanceof FuzedBigCannonProjectile bas) {
                bas.setFuze(clusterFuze);
            }
        }
    }
}
