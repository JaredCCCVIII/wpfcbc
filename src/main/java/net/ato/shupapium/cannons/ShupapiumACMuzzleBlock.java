package net.ato.shupapium.cannons;

import net.ato.shupapium.utils.acattachments.ShupapiumACAttachmentBlock;
import net.ato.shupapium.utils.acattachments.ShupapiumACAttachmentDef;
import net.ato.shupapium.utils.acattachments.ShupapiumACAttachmentSlot;
import net.ato.shupapium.utils.acattachments.ShupapiumACModifiers;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

public class ShupapiumACMuzzleBlock extends ShupapiumACBarrelBlock implements ShupapiumACAttachmentBlock {
    private final ShupapiumACAttachmentDef def;
    public ShupapiumACMuzzleBlock(Properties properties, AutocannonMaterial material, ShupapiumACModifiers modifiers) {
        super(properties, material);
        this.def = new ShupapiumACAttachmentDef(ShupapiumACAttachmentSlot.MUZZLE, modifiers);
    }

    @Override
    public ShupapiumACAttachmentDef getAttachmentDef() {
        return def;
    }
}
