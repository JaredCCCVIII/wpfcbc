package net.ato.shupapium.utils.actypes;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

public interface ShupapiumACProfile {
    ResourceLocation getProfileId();

    /** Combination of blocks to create the cannon. */
    ShupapiumACParts parts();

    /** Compatible attachments for this cannon (muzzle or suppressor). */
    default Set<Block> getCompatibleAttachments() {return Set.of();}

    /** Gunshot. */
    SoundEvent getFireSound();

    /** Particles to spawn when shot. */
    List<ParticleOptions> getMuzzleParticles();

    /** Fire rate (in RPM). */
    int getCannonFireRate();

    /** How many projectiles are shot. */
    int getProjectilePerShot();

    /** Default cannon speed shoot. */
    float getCannonBaseSpeed();

    /** Default cannon spread shoot. */
    float getCanonBaseSpread();

    /** Ammo that can be used in this cannon. */
    List<Item>  getAmmoTypes();

    default void cannonSoundEvent(Level level, Vec3 pos, float volumeMultiplier, @Nullable SoundEvent override) {
        SoundEvent sound = override != null ? override : getFireSound();
        level.playSound(null, pos.x, pos.y, pos.z, sound, SoundSource.BLOCKS, Mth.nextFloat(level.random, 10.0F, 12.0F) * volumeMultiplier, Mth.nextFloat(level.random, 0.75F, 1.1F));
    }

    default void cannonMuzzleEvent(ServerLevel level, Vec3 pos, Vec3 vel, float particleScale) {
        if (particleScale <= 0) return;
        for (ParticleOptions particle : getMuzzleParticles()) {
            int count = Math.max(1, Math.round(Mth.nextInt(level.random, 1, 8) * particleScale));
            level.sendParticles(particle, pos.x, pos.y, pos.z, count, vel.x, vel.y, vel.z, Mth.nextFloat(level.random, 0.1F, 1.0F));
        }
    }
}
