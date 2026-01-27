package net.ato.shupapium.blocks;

import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.ato.shupapium.ShupapiumBlockEntities;
import net.ato.shupapium.utils.MountedShupapiumACContraption;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rbasamoyai.createbigcannons.cannon_control.contraption.AbstractMountedCannonContraption;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringBlockEntity;

public class ShupapiumACRecoilSpringBlock extends AutocannonRecoilSpringBlock {
    public ShupapiumACRecoilSpringBlock(Properties properties, AutocannonMaterial material, NonNullFunction<Direction, BlockState> movingBlockFunction) {
        super(properties, material, movingBlockFunction);
    }

    @Override
    public void setPlacedBy(@NotNull Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @Nullable LivingEntity pPlacer, @NotNull ItemStack pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        if (!(pLevel instanceof ServerLevel server)) return;
        server.getServer().execute(() -> AutocannonBlock.onPlace(pLevel, pPos));
    }

    @Override
    public BlockEntityType<? extends AutocannonRecoilSpringBlockEntity> getBlockEntityType() {
        return ShupapiumBlockEntities.SHUPAPIUM_AUTOCANNON_RECOIL_SPRING.get();
    }

    @Override
    public @NotNull AbstractMountedCannonContraption getCannonContraption() {
        return new MountedShupapiumACContraption();
    }
}
