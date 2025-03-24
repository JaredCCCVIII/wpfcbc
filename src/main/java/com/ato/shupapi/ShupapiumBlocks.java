package com.ato.shupapi;

import com.ato.shupapi.blocks.*;
import com.simibubi.create.foundation.item.ItemDescription;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
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
        ShupapiumGroup.useModTab(ShupapiumGroup.MAIN_TAB_KEY);
    }

    public static final BlockEntry<OrdinanceShellBlock> ORDINANCE_SHELL = MainShupapium.REGISTRATE
            .block("ordinance_shell", OrdinanceShellBlock::new)
            .transform(shell(MapColor.COLOR_LIGHT_GREEN))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/ordinance_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.ordinance_shell"))
            .lang("Ordinance Explosive Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/ordinance_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();

    public static final BlockEntry<RedordinanceShellBlock> REDORDINANCE_SHELL = MainShupapium.REGISTRATE
            .block("redordinance_shell", RedordinanceShellBlock::new)
            .transform(shell(MapColor.COLOR_RED))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/redordinance_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.redordinance_shell"))
            .lang("Fire Explosive Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/redordinance_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();

    public static final BlockEntry<HeavyordinanceShellBlock> HEAVYORDINANCE_SHELL = MainShupapium.REGISTRATE
            .block("heavyordinance_shell", HeavyordinanceShellBlock::new)
            .transform(shell(MapColor.COLOR_GREEN))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/heavyordinance_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.heavyordinance_shell"))
            .lang("Heavy Explosive Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/heavyordinance_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();

    public static final BlockEntry<SuperheavyordinanceShellBlock> SUPERHEAVYORDINANCE_SHELL = MainShupapium.REGISTRATE
            .block("superheavyordinance_shell", SuperheavyordinanceShellBlock::new)
            .transform(shell(MapColor.COLOR_LIGHT_GRAY))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/superheavyordinance_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.superheavyordinance_shell"))
            .lang("Super Heavy Explosive Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/superheavyordinance_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();

    public static final BlockEntry<ClusterShellBlock> CLUSTER_SHELL = MainShupapium.REGISTRATE
            .block("cluster_shell", ClusterShellBlock::new)
            .transform(shell(MapColor.CRIMSON_NYLIUM))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/cluster_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.cluster_shell"))
            .lang("Cluster Ordinance Explosive Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/cluster_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();

    public static final BlockEntry<KineticShellBlock> KINETIC_SHELL = MainShupapium.REGISTRATE
            .block("kinetic_shell", KineticShellBlock::new)
            .transform(shell(MapColor.TERRACOTTA_WHITE))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/kinetic_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.kinetic_shell"))
            .lang("Kinetic Ordinance Explosive Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/kinetic_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();

    public static final BlockEntry<ToxicgasShellBlock> TOXICGAS_SHELL = MainShupapium.REGISTRATE
            .block("toxicgas_shell", ToxicgasShellBlock::new)
            .transform(shell(MapColor.COLOR_YELLOW))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/toxicgas_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.toxicgas_shell"))
            .lang("Toxic Gas Explosive Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/toxicgas_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();

    public static final BlockEntry<FissionShellBlock> FISSION_SHELL = MainShupapium.REGISTRATE
            .block("fission_shell", FissionShellBlock::new)
            .transform(shell(MapColor.COLOR_GRAY))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/fission_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.fission_shell"))
            .lang("Fission Explosive Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/fission_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();

    public static final BlockEntry<FusionShellBlock> FUSION_SHELL = MainShupapium.REGISTRATE
            .block("fusion_shell", FusionShellBlock::new)
            .transform(shell(MapColor.COLOR_MAGENTA))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/fusion_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.fusion_shell"))
            .lang("Fusion Explosive Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/fusion_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();

    private static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> shell(MapColor color) {
        return b -> b.addLayer(() -> RenderType::solid)
                .properties(p -> p.mapColor(color))
                .properties(p -> p.strength(2.0f, 3.0f))
                .properties(p -> p.sound(SoundType.STONE));
    }

    public static void register() {
    }
}
