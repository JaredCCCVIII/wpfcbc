package net.ato.shupapium.cannons;

import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.ato.shupapium.ShupapiumBlockEntities;
import net.ato.shupapium.utils.acattachments.ShupapiumACAttachmentBlock;
import net.ato.shupapium.utils.acattachments.ShupapiumACAttachmentDef;
import net.ato.shupapium.utils.acattachments.ShupapiumACAttachmentSlot;
import net.ato.shupapium.utils.acattachments.ShupapiumACModifiers;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.cannon_control.contraption.AbstractMountedCannonContraption;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringBlockEntity;

public class ShupapiumACRecoilSpringBlock extends AutocannonRecoilSpringBlock implements ShupapiumACAttachmentBlock {
    private final ShupapiumACAttachmentDef attachmentDef;
    public ShupapiumACRecoilSpringBlock(Properties properties, AutocannonMaterial material, NonNullFunction<Direction, BlockState> movingBlockFunction, ShupapiumACModifiers modifiers) {
        super(properties, material, movingBlockFunction);
        this.attachmentDef = new ShupapiumACAttachmentDef(ShupapiumACAttachmentSlot.RECOIL, modifiers);
    }

    @Override
    public BlockEntityType<? extends AutocannonRecoilSpringBlockEntity> getBlockEntityType() {
        return ShupapiumBlockEntities.SHUPAPIUM_AUTOCANNON_RECOIL_SPRING.get();
    }

    @Override
    public @NotNull AbstractMountedCannonContraption getCannonContraption() {
        return new MountedShupapiumACContraption();
    }

    @Override
    public ShupapiumACAttachmentDef getAttachmentDef() {
        return attachmentDef;
    }
}
