package net.ato.shupapium;

import com.simibubi.create.foundation.item.ItemDescription;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.ato.shupapium.blocks.*;
import net.ato.shupapium.datagen.ShupapiumBuilderTransformers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import rbasamoyai.createbigcannons.CBCTags;
import rbasamoyai.createbigcannons.datagen.assets.CBCBuilderTransformers;
import rbasamoyai.createbigcannons.munitions.FuzedProjectileBlockItem;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class ShupapiumBlocks {
    static {
        ShupapiumGroup.setDefaultTabToNull();
        MainShupapium.REGISTRATE.setCreativeTab(null);
    }

    // Shells
    public static final BlockEntry<SmokeBombBCShellBlock> SMOKE_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("smoke_bomb_shell_block", SmokeBombBCShellBlock::new)
            .transform(shell(MapColor.COLOR_LIGHT_GRAY, SoundType.COPPER))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/smoke_bomb_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Smoke Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/smoke_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<BarrelBCShellBlock> BARREL_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("barrel_shell_block", BarrelBCShellBlock::new)
            .transform(shell(MapColor.COLOR_BROWN, SoundType.WOOD))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectileBlock("projectile/barrel_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Explosive Barrel")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileBlockItem("projectile/barrel_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<ToxicBombBCShellBlock> TOXIC_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("toxic_bomb_shell_block", ToxicBombBCShellBlock::new)
            .transform(shell(MapColor.COLOR_YELLOW, SoundType.COPPER))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/toxic_bomb_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Toxic Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/toxic_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<SmallBombBCShellBlock> SMALL_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("small_bomb_shell_block", SmallBombBCShellBlock::new)
            .transform(shell(MapColor.COLOR_LIGHT_GREEN, SoundType.COPPER))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/small_bomb_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Small Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/small_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<SmallBombClusterBCShellBlock> SMALL_BOMB_CLUSTER_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("small_bomb_cluster_shell_block", SmallBombClusterBCShellBlock::new)
            .transform(shell(MapColor.COLOR_LIGHT_GREEN, SoundType.COPPER))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/small_bomb_cluster_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Small Bomb Cluster Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/small_bomb_cluster_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<MediumBombBCShellBlock> MEDIUM_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("medium_bomb_shell_block", MediumBombBCShellBlock::new)
            .transform(shell(MapColor.COLOR_LIGHT_GREEN, SoundType.METAL))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/medium_bomb_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Medium Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/medium_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<HeavyBombBCShellBlock> HEAVY_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("heavy_bomb_shell_block", HeavyBombBCShellBlock::new)
            .transform(shell(MapColor.COLOR_GREEN, SoundType.ANVIL))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/heavy_bomb_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Heavy Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/heavy_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();

    // Auxiliary Functions
    private static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> shell(MapColor color, SoundType sound) {
        return b -> b.addLayer(() -> RenderType::solid)
                .properties(p -> p.mapColor(color))
                .properties(p -> p.strength(2.0F, 3.0F))
                .properties(p -> p.sound(sound));
    }

    // Registry
    public static void register() {}
}
