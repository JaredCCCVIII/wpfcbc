package net.ato.shupapium.blocks;

import com.mojang.serialization.MapCodec;
import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.entities.AbstractShupapiumBCProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.DirectionalBlock;
import org.jetbrains.annotations.NotNull;

public class BarrelBCShellBlock extends AbstractShupapiumBCBlock{
    public BarrelBCShellBlock(Properties properties) {
        super(properties);
    }

    @Override
    public EntityType<? extends AbstractShupapiumBCProjectile> getAssociatedEntityType() {
        return ShupapiumEntities.BARREL_SHELL_PROJECTILE.get();
    }

    @Override
    protected @NotNull MapCodec<? extends DirectionalBlock> codec() {
        return simpleCodec(BarrelBCShellBlock::new);
    }
}
