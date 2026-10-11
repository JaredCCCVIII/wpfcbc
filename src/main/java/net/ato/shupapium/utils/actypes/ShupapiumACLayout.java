package net.ato.shupapium.utils.actypes;

import net.ato.shupapium.cannons.ShupapiumACBarrelBlock;
import net.ato.shupapium.utils.acattachments.ShupapiumACAttachmentBlock;
import net.ato.shupapium.utils.acattachments.ShupapiumACAttachmentDef;
import net.ato.shupapium.utils.acattachments.ShupapiumACAttachmentSlot;
import net.ato.shupapium.utils.acattachments.ShupapiumACModifiers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Map;

public record ShupapiumACLayout(
        ShupapiumACParts parts,
        List<BlockPos> barrelPositions,
        Map<ShupapiumACAttachmentSlot, AttachmentEntry> attachments
) {
    public record AttachmentEntry(BlockPos pos, Block block, ShupapiumACAttachmentDef def) {}

    public ShupapiumACModifiers totalModifiers() {
        ShupapiumACModifiers total = parts.barrel() instanceof ShupapiumACBarrelBlock b ? b.getBarrelModifiers() : ShupapiumACModifiers.NONE;
        for (AttachmentEntry e : attachments.values()) total = total.combine(e.def().modifiers());
        return total;
    }

    public BlockPos endPos() {
        AttachmentEntry muzzle = attachments.get(ShupapiumACAttachmentSlot.MUZZLE);
        return muzzle != null ? muzzle.pos() : barrelPositions.getLast();
    }
}
