package net.ato.shupapium.utils.acattachments;

import net.minecraft.sounds.SoundEvent;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public record ShupapiumACModifiers(
        float speedMultiplier,
        float spreadMultiplier,
        float recoilMultiplier,
        float soundVolumeMultiplier,
        float muzzleParticleScale,
        float fireRateMultiplier,
        @Nullable Supplier<SoundEvent> fireSoundOverride
        ) {
    public static final ShupapiumACModifiers NONE = new ShupapiumACModifiers(
            1,
            1,
            1,
            1,
            1,
            1,
            null);

    public ShupapiumACModifiers withSpeed(float v) {
        return new ShupapiumACModifiers(v, spreadMultiplier, recoilMultiplier, soundVolumeMultiplier, muzzleParticleScale, fireRateMultiplier, fireSoundOverride);
    }
    public ShupapiumACModifiers withSpread(float v) {
        return new ShupapiumACModifiers(speedMultiplier, v, recoilMultiplier, soundVolumeMultiplier, muzzleParticleScale, fireRateMultiplier, fireSoundOverride);
    }
    public ShupapiumACModifiers withRecoil(float v) {
        return new ShupapiumACModifiers(speedMultiplier, spreadMultiplier, v, soundVolumeMultiplier, muzzleParticleScale, fireRateMultiplier, fireSoundOverride);
    }
    public ShupapiumACModifiers withVolume(float v) {
        return new ShupapiumACModifiers(speedMultiplier, spreadMultiplier, recoilMultiplier, v, muzzleParticleScale, fireRateMultiplier, fireSoundOverride);
    }
    public ShupapiumACModifiers withParticles(float v) {
        return new ShupapiumACModifiers(speedMultiplier, spreadMultiplier, recoilMultiplier, soundVolumeMultiplier, v, fireRateMultiplier, fireSoundOverride);
    }
    public ShupapiumACModifiers withFireRate(float v) {
        return new ShupapiumACModifiers(speedMultiplier, spreadMultiplier, recoilMultiplier, soundVolumeMultiplier, muzzleParticleScale, v, fireSoundOverride);
    }
    public ShupapiumACModifiers withSound(Supplier<SoundEvent> s) {
        return new ShupapiumACModifiers(speedMultiplier, spreadMultiplier, recoilMultiplier, soundVolumeMultiplier, muzzleParticleScale, fireRateMultiplier, s);
    }

    public ShupapiumACModifiers combine(ShupapiumACModifiers o) {
        return new ShupapiumACModifiers(
                speedMultiplier * o.speedMultiplier,
                spreadMultiplier * o.spreadMultiplier,
                recoilMultiplier * o.recoilMultiplier,
                soundVolumeMultiplier * o.soundVolumeMultiplier,
                muzzleParticleScale * o.muzzleParticleScale,
                fireRateMultiplier * o.fireRateMultiplier,
                o.fireSoundOverride != null ? o.fireSoundOverride : fireSoundOverride
        );
    }
}
