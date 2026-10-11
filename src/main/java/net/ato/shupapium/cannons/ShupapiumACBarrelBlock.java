package net.ato.shupapium.cannons;

import net.ato.shupapium.ShupapiumBlockEntities;
import net.ato.shupapium.utils.acattachments.ShupapiumACModifiers;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.cannon_control.contraption.AbstractMountedCannonContraption;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBarrelBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

public class ShupapiumACBarrelBlock extends AutocannonBarrelBlock {
    private final ShupapiumACModifiers modifiers;
    public ShupapiumACBarrelBlock(Properties properties, AutocannonMaterial material) {
        this(properties, material, ShupapiumACModifiers.NONE);
    }

    public ShupapiumACBarrelBlock(Properties properties, AutocannonMaterial material, ShupapiumACModifiers modifiers) {
        super(properties, material);
        this.modifiers = modifiers;
    }

    public ShupapiumACModifiers getBarrelModifiers() {
        return this.modifiers;
    }

    @Override
    public BlockEntityType<? extends AutocannonBlockEntity> getBlockEntityType() {
        return ShupapiumBlockEntities.SHUPAPIUM_AUTOCANNON.get();
    }

    @Override
    public @NotNull AbstractMountedCannonContraption getCannonContraption() {
        return new MountedShupapiumACContraption();
    }
}
