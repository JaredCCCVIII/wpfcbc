package net.ato.shupapium.items;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class GenericPropellantItem extends Item {
    public GenericPropellantItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canAttackBlock(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player) {
        return false;
    }

    @Override
    public int getMaxStackSize(@NotNull ItemStack stack) {
        return 64;
    }

    @Override
    public int getMaxDamage(@NotNull ItemStack stack) {
        return 256;
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        return false;
    }

    @Override
    public boolean isRepairable(@NotNull ItemStack stack) {
        return false;
    }
}
