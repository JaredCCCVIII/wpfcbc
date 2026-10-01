package net.ato.shupapium.entities;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.wariumce.entity.FlareProjectileEntity;
import com.wariumce.procedures.ArtilleryHitProcedure;
import net.ato.shupapium.ShupapiumBlocks;
import net.ato.shupapium.ShupapiumTags;
import net.minecraft.core.Position;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import rbasamoyai.createbigcannons.munitions.ProjectileContext;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

import java.util.Comparator;

public class IRSeekerBCProjectile extends AbstractShupapiumBCProjectile {
    private static final double SEEK_RADIUS = 200.0;
    private static final double TURN_RATE = 0.15;
    private Entity target;
    public IRSeekerBCProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.IR_SEEKER_SHELL_BLOCK;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) return;
        if (this.target == null) {
            this.target = findTarget();
        }
        if (this.target != null) {
            guideTowardsTarget();
        }
    }

    @Override
    protected boolean onImpact(HitResult hitResult, ImpactResult impactResult, ProjectileContext projectileContext) {
        super.onImpact(hitResult, impactResult, projectileContext);
        if (!this.level().isClientSide && this.level().hasChunkAt(this.blockPosition()) && !this.isRemoved()) {
            if (hitResult.getType() != HitResult.Type.MISS) {
                this.detonate(hitResult.getLocation());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    protected void detonate(Position position) {
        ArtilleryHitProcedure.execute(this.level(), position.x(), position.y(), position.z(), this);
    }

    private Entity findTarget() {
        AABB searchBox = this.getBoundingBox().inflate(SEEK_RADIUS);
        return this.level().getEntitiesOfClass(Entity.class,
                searchBox,
                entity -> entity != this && entity.getType().is(ShupapiumTags.WARM_OBJECTIVES))
                .stream()
                .min(Comparator.comparingDouble(this::distanceToSqr))
                .orElse(null);
    }

    private void guideTowardsTarget() {
        Vec3 velocity = this.getDeltaMovement();
        double speed = velocity.length();

        if (speed <= 0.001) return;

        Vec3 targetVelocity = this.target.getDeltaMovement();
        double distance = this.distanceTo(this.target);
        double leadTime = distance / speed;
        Vec3 predictedPosition = this.target.position().add(targetVelocity.scale(leadTime));
        Vec3 direction = predictedPosition.subtract(this.position()).normalize();
        Vec3 desiredVelocity = direction.scale(speed);
        double maxTurn = speed * TURN_RATE;
        Vec3 change = desiredVelocity.subtract(velocity);

        if (change.length() > maxTurn) {
            change = change.normalize().scale(maxTurn);
        }

        this.setDeltaMovement(velocity.add(change));

        if (this.target instanceof FlareProjectileEntity flare && this.position().distanceToSqr(flare.position()) < 4) {
            this.detonate(this.position());
            return;
        }
    }
}
