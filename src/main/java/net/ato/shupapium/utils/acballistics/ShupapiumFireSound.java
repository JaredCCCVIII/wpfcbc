package net.ato.shupapium.utils.acballistics;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public record ShupapiumFireSound(
        Supplier<SoundEvent> sound,
        float volume,
        float minPitch,
        float maxPitch,
        SoundSource source
) {
    public static ShupapiumFireSound of(Supplier<SoundEvent> sound, float volume, float minPitch, float maxPitch) {
        return new ShupapiumFireSound(sound, volume, minPitch, maxPitch, SoundSource.BLOCKS);
    }

    public static ShupapiumFireSound byId(String id, float volume, float minPitch, float maxPitch) {
        ResourceLocation loc = ResourceLocation.parse(id);
        return of(() -> BuiltInRegistries.SOUND_EVENT.get(loc), volume, minPitch, maxPitch);
    }

    public void play(Level level, Vec3 pos, float volumeMultiplier) {
        float pitch = Mth.nextFloat(level.random, minPitch, maxPitch);
        level.playSound(null, pos.x, pos.y, pos.z, sound.get(), source, volume * volumeMultiplier, pitch);
    }
}
