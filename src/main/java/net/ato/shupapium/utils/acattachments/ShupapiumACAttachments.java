package net.ato.shupapium.utils.acattachments;

import net.minecraft.world.level.block.Block;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringBlock;

import javax.annotation.Nullable;

public final class ShupapiumACAttachments {
    private static final ShupapiumACAttachmentDef CBC_SPRING = new ShupapiumACAttachmentDef(
            ShupapiumACAttachmentSlot.RECOIL, ShupapiumACModifiers.NONE
    );

    private ShupapiumACAttachments() {}

    public static @Nullable ShupapiumACAttachmentDef get(Block block) {
        if (block instanceof ShupapiumACAttachmentBlock own) return own.getAttachmentDef();
        if (block instanceof AutocannonRecoilSpringBlock) return CBC_SPRING;
        return null;
    }
}
