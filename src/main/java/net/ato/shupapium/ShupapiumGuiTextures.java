package net.ato.shupapium;

import net.createmod.catnip.gui.element.ScreenElement;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public enum ShupapiumGuiTextures implements ScreenElement {
    AUTOCANNON_AMMO_CONTAINER_BG("backgrounds1", 0, 168, 179, 83),
    CREATIVE_AUTOCANNON_AMMO_CONTAINER_BG("backgrounds2", 0, 0, 199, 83);

    private final ResourceLocation texture;
    public final int texX;
    public final int texY;
    public final int width;
    public final int height;

    ShupapiumGuiTextures(String path, int texX, int texY, int width, int height) {
        this(MainShupapium.MODID, path, texX, texY, width, height);
    }

    ShupapiumGuiTextures(String namespace, String path, int texX, int texY, int width, int height) {
        this.texture = ResourceLocation.fromNamespaceAndPath(namespace, "textures/gui/" + path + ".png");
        this.texX = texX;
        this.texY = texY;
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(GuiGraphics graphics, int x, int y) {
        graphics.blit(this.texture, x, y, 0, this.texX, this.texY, this.width, this.height, 256, 256);
    }
}
