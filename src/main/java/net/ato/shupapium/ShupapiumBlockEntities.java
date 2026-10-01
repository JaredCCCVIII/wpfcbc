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
            .validBlocks(
                    ShupapiumBlocks.SMOKE_BOMB_SHELL_BLOCK,
                    ShupapiumBlocks.BARREL_SHELL_BLOCK,
                    ShupapiumBlocks.TOXIC_BOMB_SHELL_BLOCK,
                    ShupapiumBlocks.SMALL_BOMB_SHELL_BLOCK,
                    ShupapiumBlocks.SMALL_BOMB_CLUSTER_SHELL_BLOCK,
                    ShupapiumBlocks.MEDIUM_BOMB_SHELL_BLOCK,
                    ShupapiumBlocks.HEAVY_BOMB_SHELL_BLOCK,
                    ShupapiumBlocks.FIRE_BOMB_SHELL_BLOCK,
                    ShupapiumBlocks.SUPER_HEAVY_BOMB_SHELL_BLOCK,
                    ShupapiumBlocks.BLOCK_BUSTER_BOMB_SHELL_BLOCK,
                    ShupapiumBlocks.KINETIC_BOMB_SHELL_BLOCK,
                    ShupapiumBlocks.IR_SEEKER_SHELL_BLOCK,
                    ShupapiumBlocks.CLUSTER_BOMB_SHELL_BLOCK,
                    ShupapiumBlocks.FISSION_BOMB_SHELL_BLOCK,
                    ShupapiumBlocks.FUSION_BOMB_SHELL_BLOCK,
                    ShupapiumBlocks.JOKE_BOMB_SHELL_BLOCK)
            .register();

    public static void register() {}
}
