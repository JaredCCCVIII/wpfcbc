package net.ato.shupapium.blocks;

import com.mojang.serialization.MapCodec;
import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.entities.AbstractShupapiumBCProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.DirectionalBlock;
import org.jetbrains.annotations.NotNull;

public class JokeBombBCShellBlock extends AbstractShupapiumBCBlock {
    public JokeBombBCShellBlock(Properties properties) {
        super(properties);
    }

    @Override
    public EntityType<? extends AbstractShupapiumBCProjectile> getAssociatedEntityType() {
        return ShupapiumEntities.JOKE_BOMB_SHELL_PROJECTILE.get();
    }

    @Override
    protected @NotNull MapCodec<? extends DirectionalBlock> codec() {
        return simpleCodec(JokeBombBCShellBlock::new);
    }
}
