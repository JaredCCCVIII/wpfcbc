package net.ato.shupapium.client.gui;

import com.google.common.collect.ImmutableList;
import com.simibubi.create.foundation.gui.AllIcons;
import com.simibubi.create.foundation.gui.menu.AbstractSimiContainerScreen;
import com.simibubi.create.foundation.gui.widget.IconButton;
import net.ato.shupapium.ShupapiumBlocks;
import net.ato.shupapium.ShupapiumGuiTextures;
import net.ato.shupapium.blocks.ShupapiumAmmoContainerBlock;
import net.createmod.catnip.gui.element.GuiGameElement;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import static com.simibubi.create.foundation.gui.AllGuiTextures.PLAYER_INVENTORY;
import static net.ato.shupapium.ShupapiumGuiTextures.*;

public class ShupapiumAmmoContainerScreen extends AbstractSimiContainerScreen<ShupapiumAmmoContainerMenu> {
    protected int lastUpdated = -1;
    protected IconButton confirmButton;
    private List<Rect2i> extraAreas = Collections.emptyList();
    public ShupapiumAmmoContainerScreen(ShupapiumAmmoContainerMenu container, Inventory inv, Component title) {
        super(container, inv, title);
    }

    @Override
    protected void init() {
        boolean isCreative = this.menu.isCreativeContainer();
        ShupapiumGuiTextures bg = isCreative ? CREATIVE_AUTOCANNON_AMMO_CONTAINER_BG : AUTOCANNON_AMMO_CONTAINER_BG;
        this.setWindowSize(bg.width, bg.height + 4 + PLAYER_INVENTORY.getHeight());
        this.setWindowOffset(1, 0);
        super.init();

        this.confirmButton = new IconButton(this.leftPos + this.imageWidth - 33, this.topPos + 59, AllIcons.I_CONFIRM);
        this.confirmButton.withCallback(this::onClose);
        this.addRenderableWidget(this.confirmButton);

        this.extraAreas = ImmutableList.of(new Rect2i(this.leftPos + bg.width, this.topPos + bg.height - 68, 60, 60));
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int invX = this.getLeftOfCentered(PLAYER_INVENTORY.getWidth());
        int invY = this.topPos + AUTOCANNON_AMMO_CONTAINER_BG.height + 4;
        this.renderPlayerInventory(graphics, invX, invY);
        boolean isCreative = this.menu.isCreativeContainer();

        ShupapiumGuiTextures bg = isCreative ? CREATIVE_AUTOCANNON_AMMO_CONTAINER_BG : AUTOCANNON_AMMO_CONTAINER_BG;
        bg.render(graphics, this.leftPos, this.topPos);
        if (isCreative) {
            graphics.drawString(this.font, this.title, this.leftPos + 4, this.topPos + 3, 0x54214f, false);
        } else {
            graphics.drawCenteredString(this.font, this.title, this.leftPos + this.imageWidth / 2 - 4, this.topPos + 3, 0xffffff);
        }
        //ShupapiumGuiTextures sel = isCreative ? CREATIVE_AUTOCANNON_AMMO_CONTAINER_SELECTOR : AUTOCANNON_AMMO_CONTAINER_SELECTOR;
        //sel.render(graphics, this.leftPos + 86, this.topPos + 23);

        BlockState state = isCreative ? ShupapiumBlocks.CREATIVE_SHUPAPIUM_AMMO_CONTAINER.getDefaultState()
                : ShupapiumBlocks.SHUPAPIUM_AMMO_CONTAINER.getDefaultState();
        state = state.setValue(ShupapiumAmmoContainerBlock.CONTAINER_STATE, ShupapiumAmmoContainerBlock.State.getFromFilled(this.menu.isFilled()));
        GuiGameElement.of(state)
                .scale(50)
                .rotate(30, 135, 0)
                .at(this.leftPos + bg.width + 32, this.topPos + bg.height, 200)
                .render(graphics);
    }

    @Override
    protected void renderTooltip(@NotNull GuiGraphics graphics, int x, int y) {
        super.renderTooltip(graphics, x, y);
        if (this.hoveredSlot != null && this.hoveredSlot.index == 1 && !this.hoveredSlot.hasItem()) {
            graphics.renderTooltip(this.font, Component.translatable("shupapium.gui.autocannon_ammo_container.propellant_slot"), x, y);
        }
    }

    @Override
    protected void containerTick() {
        super.containerTick();

        if (this.lastUpdated >= 0) {
            this.lastUpdated++;
        }
        if (this.lastUpdated >= 20) {
            this.lastUpdated = -1;
        }
    }

    @Override
    public void removed() {
        super.removed();
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public List<Rect2i> getExtraAreas() {
        return this.extraAreas;
    }
}
