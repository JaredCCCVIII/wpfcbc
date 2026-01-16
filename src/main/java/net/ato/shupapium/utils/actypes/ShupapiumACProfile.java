package net.ato.shupapium.utils.actypes;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rbasamoyai.createbigcannons.cannon_control.contraption.MountedAutocannonContraption;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

import java.util.List;

public interface ShupapiumACProfile {
    ResourceLocation getProfileId();

    /** Combination of blocks to create the cannon. */
    ShupapiumACParts parts();

    /** Gunshot. */
    SoundEvent getFireSound();

    /** Particles to spawn when shot. */
    List<ParticleOptions> getMuzzleParticles();

    /** Fire rate (in RPM). */
    int getCannonFireRate();

    /** How many projectiles are shot. */
    int getProjectilePerShot();

    /** Default speed for projectiles in this cannon. */
    float getProjectileBaseSpeed();

    /** In radians. */
    float getProjectileSpread();

    /** Ammo that can be used in this cannon. */
    List<Item>  getAmmoTypes();

    /** Material properties that the cannon will use (recommended the same as the blocks). */
    AutocannonMaterial getMainMaterial();

    /** Doesn't work with JSON. */
    default void cannonSoundEvent(Level level, Vec3 pos) {
        level.playSound(null, pos.x, pos.y, pos.z, getFireSound(), SoundSource.BLOCKS, Mth.nextFloat(level.random, 10.0F, 12.0F), Mth.nextFloat(level.random, 0.75F, 1.1F));
    }

    /** Doesn't work with JSON. */
    default void cannonMuzzleEvent(ServerLevel level, Vec3 pos, Vec3 vel) {
        for (ParticleOptions particle : getMuzzleParticles()) {
            level.sendParticles(particle, pos.x, pos.y, pos.z, Mth.nextInt(level.random, 1, 8), vel.x, vel.y, vel.z, Mth.nextFloat(level.random, 0.1F, 1.0F));
        }
    }

    default boolean isValidFor(MountedAutocannonContraption contraption) {
        return true;
    }
}
