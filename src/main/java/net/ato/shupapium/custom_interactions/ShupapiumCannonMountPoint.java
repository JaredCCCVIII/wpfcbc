package net.ato.shupapium.custom_interactions;

import com.simibubi.create.content.kinetics.mechanicalArm.ArmInteractionPointType;
import net.ato.shupapium.cannons.MountedShupapiumACContraption;
import net.ato.shupapium.cannons.ShupapiumACBreechBlockEntity;
import net.ato.shupapium.items.ShupapiumAmmoContainerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import rbasamoyai.createbigcannons.cannon_control.contraption.AbstractMountedCannonContraption;
import rbasamoyai.createbigcannons.cannon_control.contraption.PitchOrientedContraptionEntity;
import rbasamoyai.createbigcannons.cannons.big_cannons.breeches.quickfiring_breech.CannonMountPoint;

public class ShupapiumCannonMountPoint extends CannonMountPoint {
    public ShupapiumCannonMountPoint(ArmInteractionPointType type, Level level, BlockPos pos, BlockState state) {
        super(type, level, pos, state);
    }

    @Override
    public ItemStack getInsertedResultAndDoSomething(ItemStack stack, boolean simulate, AbstractMountedCannonContraption cannon, PitchOrientedContraptionEntity poce) {
        if (cannon instanceof MountedShupapiumACContraption autocannon) {
            return shupapedAutocannonInsert(stack, simulate, autocannon, poce);
        }
        return super.getInsertedResultAndDoSomething(stack, simulate, cannon, poce);
    }

    public static ItemStack shupapedAutocannonInsert(ItemStack stack, boolean simulate, MountedShupapiumACContraption autocannon,
                                             PitchOrientedContraptionEntity poce) {
        if (!ShupapiumAmmoContainerItem.canFire(stack)) return stack;
        if (!(stack.getItem() instanceof ShupapiumAmmoContainerItem)) return stack;
        BlockEntity be = autocannon.presentBlockEntities.get(autocannon.getStartPos());
        if (!(be instanceof ShupapiumACBreechBlockEntity breech)) return stack;

        ItemStack oldContainer = breech.getMagazine();
        if (oldContainer.getItem() instanceof ShupapiumAmmoContainerItem
                && ShupapiumAmmoContainerItem.canFire(oldContainer)) return stack;

        if (simulate) return ItemStack.EMPTY;
        breech.setMagazine(stack);
        return oldContainer.isEmpty() ? ItemStack.EMPTY : oldContainer.copy();
    }
}
