package net.ato.shupapium;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.ato.shupapium.blockentities.ShupapiumFuzedBlockEntity;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBlockEntityRenderer;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBlockVisual;

public class ShupapiumBlockEntities {
    public static final BlockEntityEntry<ShupapiumFuzedBlockEntity> SHUPAPED_FUZED_BLOCK = MainShupapium.REGISTRATE
            .blockEntity("shupaped_fuzed_block", ShupapiumFuzedBlockEntity::new)
            .visual(() -> FuzedBlockVisual::new)
            .renderer(() -> FuzedBlockEntityRenderer::new)
            .validBlocks(ShupapiumBlocks.SMOKE_BOMB_SHELL_BLOCK, ShupapiumBlocks.BARREL_SHELL_BLOCK, ShupapiumBlocks.TOXIC_BOMB_SHELL_BLOCK, ShupapiumBlocks.SMALL_BOMB_SHELL_BLOCK)
            .register();

    public static void register() {}
}
