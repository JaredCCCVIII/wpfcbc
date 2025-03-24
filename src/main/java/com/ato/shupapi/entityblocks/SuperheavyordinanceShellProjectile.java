package com.ato.shupapi.entityblocks;

import com.ato.shupapi.ShupapiumBlocks;
import net.mcreator.crustychunks.procedures.BlockBusterHitProcedure;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.ShellExplosion;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonCommonShellProperties;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonFuzePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonProjectilePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.BallisticPropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.EntityDamagePropertiesComponent;

public class SuperheavyordinanceShellProjectile extends FuzedBigCannonProjectile {
    public SuperheavyordinanceShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    protected @NotNull BigCannonFuzePropertiesComponent getFuzeProperties() {
        return this.getAllProperties().fuze();
    }

    @Override
    protected void detonate(Position position) {
        BlockBusterHitProcedure.execute(this.level(), position.x(), position.y(), position.z(), this);
    }

    @Override
    public BlockState getRenderedBlockState() {
        return ShupapiumBlocks.SUPERHEAVYORDINANCE_SHELL.getDefaultState().setValue(BlockStateProperties.FACING, Direction.NORTH);
    }

    @Override
    protected @NotNull BigCannonProjectilePropertiesComponent getBigCannonProjectileProperties() {
        return this.getAllProperties().bigCannonProperties();
    }

    @Override
    public @NotNull EntityDamagePropertiesComponent getDamageProperties() {
        return this.getAllProperties().damage();
    }

    @Override
    protected @NotNull BallisticPropertiesComponent getBallisticProperties() {
        return this.getAllProperties().ballistics();
    }

    protected BigCannonCommonShellProperties getAllProperties() {
        return CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE.getPropertiesOf(this);
    }
}
