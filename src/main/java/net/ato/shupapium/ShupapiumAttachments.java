package net.ato.shupapium;

import com.mojang.serialization.Codec;
import net.ato.shupapium.utils.ProjectileManager;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ShupapiumAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MainShupapium.MOD_ID);

    public static final Supplier<AttachmentType<ProjectileManager.TrackData>> TRACK = ATTACHMENTS.register("projectile_track",
            () -> AttachmentType.builder(() -> new ProjectileManager.TrackData(0L, ProjectileManager.ExpireAction.DISCARD)).serialize(ProjectileManager.TrackData.CODEC).build());

    public static void register(IEventBus eventBus) {
        ATTACHMENTS.register(eventBus);
    }
}
