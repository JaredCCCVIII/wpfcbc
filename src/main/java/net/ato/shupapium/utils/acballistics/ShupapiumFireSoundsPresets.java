package net.ato.shupapium.utils.acballistics;

import com.wariumce.init.CrustyChunksModSounds;
import net.minecraft.sounds.SoundEvent;

import java.util.List;
import java.util.function.Supplier;

public final class ShupapiumFireSoundsPresets {
    private ShupapiumFireSoundsPresets() {}

    private static ShupapiumFireSound s(Supplier<SoundEvent> sound, float vol, float min, float max) {
        return ShupapiumFireSound.of(sound, vol, min, max);
    }

    public static final List<ShupapiumFireSound> PISTOL = List.of(
            s(CrustyChunksModSounds.DISTANTGUNFIRE::get, 80F, 1.1F, 1.2F),
            s(CrustyChunksModSounds.SMALLSHOT::get, 15F, 0.95F, 1.05F)
    );

    public static final List<ShupapiumFireSound> MEDIUM_RIFLE = List.of(
            s(CrustyChunksModSounds.DISTANTGUNFIRE::get, 80F, 0.9F, 1.1F),
            s(CrustyChunksModSounds.MIDRANGESHOT::get, 20F, 0.9F, 1.1F),
            s(CrustyChunksModSounds.MEDIUMSHOT::get, 10F, 0.9F, 1.02F)
    );

    public static final List<ShupapiumFireSound> LARGE_RIFLE = List.of(
            s(CrustyChunksModSounds.DISTANTSHOTMEDIUM::get, 80F, 0.8F, 0.9F),
            s(CrustyChunksModSounds.MIDRANGESHOT::get, 20F, 0.9F, 1.1F),
            s(CrustyChunksModSounds.LARGESHOT::get, 10F, 0.9F, 1.1F)
    );

    public static final List<ShupapiumFireSound> SHOTGUN = List.of(
            s(CrustyChunksModSounds.DISTANTSHOTMEDIUM::get, 80F, 0.8F, 0.9F),
            s(CrustyChunksModSounds.MIDRANGESHOT::get, 20F, 0.7F, 0.8F),
            s(CrustyChunksModSounds.LARGESHOT::get, 15F, 0.9F, 1.1F)
    );

    public static final List<ShupapiumFireSound> SUPPRESSED = List.of(
            s(CrustyChunksModSounds.DISTANTSHOT::get, 8F, 0.9F, 1.05F),
            s(CrustyChunksModSounds.SILENCEDSHOT::get, 5F, 0.9F, 1.05F),
            s(CrustyChunksModSounds.GUNMECHANISM::get, 1F, 0.9F, 1.05F)
    );
}
