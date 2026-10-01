package net.ato.shupapium.blocks;

import com.mojang.serialization.MapCodec;
import net.ato.shupapium.ShupapiumBlockEntities;
import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.entities.AbstractShupapiumBCProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.munitions.big_cannon.BigCannonProjectileBlockEntity;
import rbasamoyai.createbigcannons.munitions.big_cannon.InertProjectileBlock;

public class IRSeekerBCShellBlock extends InertProjectileBlock {
    public IRSeekerBCShellBlock(Properties properties) {
        super(properties);
    }

    @Override
    public EntityType<? extends AbstractShupapiumBCProjectile> getAssociatedEntityType() {
        return ShupapiumEntities.IR_SEEKER_SHELL_PROJECTILE.get();
    }

    @Override
    public BlockEntityType<? extends BigCannonProjectileBlockEntity> getBlockEntityType() {
        return ShupapiumBlockEntities.SHUPAPED_FUZED_BLOCK.get();
    }

    @Override
    protected @NotNull MapCodec<? extends DirectionalBlock> codec() {
        return simpleCodec(IRSeekerBCShellBlock::new);
    }
}
