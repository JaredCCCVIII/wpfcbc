package net.ato.shupapium;

import com.simibubi.create.foundation.item.ItemDescription;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.ato.shupapium.blocks.BarrelBCShellBlock;
import net.ato.shupapium.blocks.SmallBombBCShellBlock;
import net.ato.shupapium.blocks.SmokeBombBCShellBlock;
import net.ato.shupapium.blocks.ToxicBombBCShellBlock;
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
