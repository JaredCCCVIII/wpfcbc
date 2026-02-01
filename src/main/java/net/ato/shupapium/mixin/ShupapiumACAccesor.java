package net.ato.shupapium.mixin;

import net.ato.shupapium.utils.MountedShupapiumACContraption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

@Mixin(MountedShupapiumACContraption.class)
public interface ShupapiumACAccesor {
    @Accessor(value = "cannonMaterial", remap = false)
    AutocannonMaterial getMaterial();
}
