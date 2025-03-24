package com.ato.shupapi.entityblocks;

import com.ato.shupapi.ShupapiumBlocks;
import net.mcreator.crustychunks.procedures.LargeBombProjectileProjectileHitsBlockProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.ShellExplosion;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonCommonShellProperties;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonFuzePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonProjectilePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.BallisticPropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.EntityDamagePropertiesComponent;
import software.bernie.example.registry.SoundRegistry;

public class OrdinanceShellProjectile extends FuzedBigCannonProjectile {

    public OrdinanceShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    protected @NotNull BigCannonFuzePropertiesComponent getFuzeProperties() {
        return this.getAllProperties().fuze();
    }

    @Override
    protected void detonate(Position position) {
        LargeBombProjectileProjectileHitsBlockProcedure.execute(this.level(), position.x(), position.y(), position.z(), this);
    }

    @Override
    public BlockState getRenderedBlockState() {
        return ShupapiumBlocks.ORDINANCE_SHELL.getDefaultState().setValue(BlockStateProperties.FACING, Direction.NORTH);
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
