package net.ato.shupapium.client.renderers;

import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.entities.ShupapiumDummyRagdoll;
import net.ato.shupapium.entities.ShupapiumMetalRagdoll;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;
import org.jetbrains.annotations.NotNull;

public class ShupapiumDummyRagdollRenderer extends ZombieRenderer {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MainShupapium.MOD_ID, "textures/entity/dummy_ragdoll.png");
    private static final ResourceLocation METAL_TEXTURE = ResourceLocation.fromNamespaceAndPath(MainShupapium.MOD_ID, "textures/entity/hd_ragdoll.png");
    private static final ResourceLocation EG_C_TEXTURE = ResourceLocation.fromNamespaceAndPath(MainShupapium.MOD_ID, "textures/entity/chistoso.png");
    public ShupapiumDummyRagdollRenderer(EntityRendererProvider.Context p_174456_) {
        super(p_174456_);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull Zombie entity) {
        if (entity instanceof ShupapiumDummyRagdoll ragdoll) {
            if (ragdoll.isChistosoConverted()) {
                return EG_C_TEXTURE;
            }
        }
        if (entity instanceof ShupapiumMetalRagdoll) {
            return METAL_TEXTURE;
        } else {
            return TEXTURE;
        }
    }
}
