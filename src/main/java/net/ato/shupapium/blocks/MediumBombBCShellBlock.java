package net.ato.shupapium.blocks;

import com.mojang.serialization.MapCodec;
import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.entities.AbstractShupapiumBCProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.DirectionalBlock;
import org.jetbrains.annotations.NotNull;

public class MediumBombBCShellBlock extends AbstractShupapiumBCBlock {
    public MediumBombBCShellBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends DirectionalBlock> codec() {
        return simpleCodec(MediumBombBCShellBlock::new);
    }

    @Override
    public EntityType<? extends AbstractShupapiumBCProjectile> getAssociatedEntityType() {
        return ShupapiumEntities.MEDIUM_BOMB_SHELL_PROJECTILE.get();
    }
}
