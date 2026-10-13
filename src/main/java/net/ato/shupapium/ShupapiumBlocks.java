package net.ato.shupapium;

import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import com.wariumce.CrustyChunksMod;
import com.wariumce.init.CrustyChunksModSounds;
import net.ato.shupapium.blocks.*;
import net.ato.shupapium.cannons.*;
import net.ato.shupapium.client.renderers.MinigunBarrelItemRenderer;
import net.ato.shupapium.client.renderers.RotaryCannonBarrelItemRenderer;
import net.ato.shupapium.datagen.ShupapiumBuilderTransformers;
import net.ato.shupapium.utils.acattachments.ShupapiumACModifiers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import rbasamoyai.createbigcannons.CBCTags;
import rbasamoyai.createbigcannons.datagen.assets.CBCBuilderTransformers;
import rbasamoyai.createbigcannons.munitions.FuzedProjectileBlockItem;
import rbasamoyai.createbigcannons.munitions.big_cannon.ProjectileBlockItem;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class ShupapiumBlocks {
    static {
        ShupapiumGroup.setDefaultTabToNull();
        MainShupapium.REGISTRATE.setCreativeTab(null);
    }

    // Cannon Blocks
    public static final BlockEntry<ShupapiumGACBarrelBlock> MINIGUN_BARREL = MainShupapium.REGISTRATE
            .block("minigun_barrel", p -> new ShupapiumGACBarrelBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN, ShupapiumBlockEntities.MINIGUN_GECKO_CANNON))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.geckoAutocannonBarrel(CrustyChunksMod.MODID, "custom/mini_gun_barrel_particle", MinigunBarrelItemRenderer::new))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> MACHINE_GUN_BARREL = MainShupapium.REGISTRATE
            .block("machine_gun_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel(CrustyChunksMod.MODID, "block/machine_gun_barrel"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> COVERED_MACHINE_GUN_BARREL = MainShupapium.REGISTRATE
            .block("covered_machine_gun_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN, ShupapiumACModifiers.NONE.withSpread(0.3F).withParticles(0.1F).withSound(CrustyChunksModSounds.SILENCEDSHOT::get)))
            .transform(cannonBlock(false, SoundType.NETHERITE_BLOCK))
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel(CrustyChunksMod.MODID, "block/covered_machine_gun_barrel"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> MINIGUN_BREECH = MainShupapium.REGISTRATE
            .block("minigun_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN))
            .transform(cannonBlock(false, SoundType.ANVIL))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech(CrustyChunksMod.MODID, "block/minigun"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> LIGHT_MACHINE_GUN_BREECH = MainShupapium.REGISTRATE
            .block("light_machine_gun_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN))
            .transform(cannonBlock(false, SoundType.ANVIL))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech(CrustyChunksMod.MODID, "block/light_machine_gun"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> MACHINE_GUN_BREECH = MainShupapium.REGISTRATE
            .block("machine_gun_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN))
            .transform(cannonBlock(false, SoundType.ANVIL))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech(CrustyChunksMod.MODID, "block/machine_gun"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> HEAVY_MACHINE_GUN_BREECH = MainShupapium.REGISTRATE
            .block("heavy_machine_gun_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN))
            .transform(cannonBlock(false, SoundType.ANVIL))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech(CrustyChunksMod.MODID, "block/heavy_machine_gun"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> CANNON_BARREL = MainShupapium.REGISTRATE
            .block("cannon_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.CANNON_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel(CrustyChunksMod.MODID, "block/autocannon_barrel"))
            .register();
    public static final BlockEntry<ShupapiumGACBarrelBlock> ROTARY_CANNON_BARREL = MainShupapium.REGISTRATE
            .block("rotary_cannon_barrel", p -> new ShupapiumGACBarrelBlock(p, ShupapiumCBCACMaterials.CANNON_GUN, ShupapiumBlockEntities.ROTARY_GECKO_CANNON))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.geckoAutocannonBarrel(CrustyChunksMod.MODID, "custom/rac_barrel_particle", RotaryCannonBarrelItemRenderer::new))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> LIGHT_CANNON_BREECH = MainShupapium.REGISTRATE
            .block("light_cannon_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.CANNON_GUN))
            .transform(cannonBlock(false, SoundType.ANVIL))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech(CrustyChunksMod.MODID, "block/light_autocannon"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> ROTARY_CANNON_BREECH = MainShupapium.REGISTRATE
            .block("rotary_cannon_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.CANNON_GUN))
            .transform(cannonBlock(false, SoundType.ANVIL))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech(CrustyChunksMod.MODID, "block/rotary_auto_cannon"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> HEAVY_CANNON_BREECH = MainShupapium.REGISTRATE
            .block("heavy_cannon_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.CANNON_GUN))
            .transform(cannonBlock(false, SoundType.ANVIL))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech(CrustyChunksMod.MODID, "block/autocannon"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> BATTLE_CANNON_BARREL = MainShupapium.REGISTRATE
            .block("battle_cannon_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.BATTLE_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel(CrustyChunksMod.MODID, "block/battle_cannon_barrel"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> THICK_BATTLE_CANNON_BARREL = MainShupapium.REGISTRATE
            .block("thick_battle_cannon_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.BATTLE_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel(CrustyChunksMod.MODID, "block/thick_battle_cannon_barrel"))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> COVERED_BATTLE_CANNON_BARREL = MainShupapium.REGISTRATE
            .block("covered_battle_cannon_barrel", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.BATTLE_GUN, ShupapiumBlocks::battleCannonBarrel, ShupapiumACModifiers.NONE.withRecoil(0.7F)))
            .transform(cannonBlock(false, SoundType.NETHERITE_BLOCK))
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring(CrustyChunksMod.MODID, "block/battle_cannon_mantlet"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> BATTLE_CANNON_BREECH = MainShupapium.REGISTRATE
            .block("battle_cannon_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.BATTLE_GUN))
            .transform(cannonBlock(false, SoundType.ANVIL))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech(CrustyChunksMod.MODID, "block/battle_cannon_breech"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> ARTILLERY_CANNON_BARREL = MainShupapium.REGISTRATE
            .block("artillery_cannon_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.ARTILLERY_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel(CrustyChunksMod.MODID, "block/artillery_barrel"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> ARTILLERY_CANNON_BREECH = MainShupapium.REGISTRATE
            .block("artillery_cannon_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.ARTILLERY_GUN))
            .transform(cannonBlock(false, SoundType.ANVIL))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech(CrustyChunksMod.MODID, "block/artillerybreech"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> ROCKET_POD_BARREL = MainShupapium.REGISTRATE
            .block("rocket_pod_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.ROCKET_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel(CrustyChunksMod.MODID, "block/rocket_pod"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> LARGE_ROCKET_POD_BARREL = MainShupapium.REGISTRATE
            .block("large_rocket_pod_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.LARGE_ROCKET_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel(CrustyChunksMod.MODID, "block/large_rocket_pod"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> ROCKET_POD_BREECH = MainShupapium.REGISTRATE
            .block("rocket_pod_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.ROCKET_GUN))
            .transform(cannonBlock(false, SoundType.ANVIL))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech(CrustyChunksMod.MODID, "block/rocket_pod_chamber"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> LARGE_ROCKET_POD_BREECH = MainShupapium.REGISTRATE
            .block("large_rocket_pod_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.LARGE_ROCKET_GUN))
            .transform(cannonBlock(false, SoundType.ANVIL))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech(CrustyChunksMod.MODID, "block/large_rocket_pod_chamber"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> SMOKE_LAUNCHER_BARREL = MainShupapium.REGISTRATE
            .block("smoke_launcher_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.SMALL_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonMortarBarrel(CrustyChunksMod.MODID, "block/mortar"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> MORTAR_LAUNCHER_BARREL = MainShupapium.REGISTRATE
            .block("mortar_launcher_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.SMALL_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonMortarBarrel(CrustyChunksMod.MODID, "block/mortar"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> LAUNCHER_BREECH = MainShupapium.REGISTRATE
            .block("launcher_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.SMALL_GUN))
            .transform(cannonBlock(false, SoundType.ANVIL))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech(CrustyChunksMod.MODID, "block/rocket_pod_chamber"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> FLAMETHROWER_BARREL = MainShupapium.REGISTRATE
            .block("flamethrower_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.SMALL_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel(CrustyChunksMod.MODID, "block/flame_thrower_barrel"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> COVERED_FLAMETHROWER_BARREL = MainShupapium.REGISTRATE
            .block("covered_flamethrower_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.SMALL_GUN, ShupapiumACModifiers.NONE.withRecoil(0.7F)))
            .transform(cannonBlock(false, SoundType.NETHERITE_BLOCK))
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel(CrustyChunksMod.MODID, "block/covered_flame_thrower_barrel"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> FLAMETHROWER_BREECH = MainShupapium.REGISTRATE
            .block("flamethrower_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.SMALL_GUN))
            .transform(cannonBlock(false, SoundType.ANVIL))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech(CrustyChunksMod.MODID, "block/flame_thrower"))
            .register();

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
    public static final BlockEntry<FireBombBCShellBlock> FIRE_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("fire_bomb_shell_block", FireBombBCShellBlock::new)
            .transform(shell(MapColor.COLOR_RED, SoundType.ANVIL))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/fire_bomb_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Fire Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/fire_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<SuperHeavyBombBCShellBlock> SUPER_HEAVY_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("super_heavy_bomb_shell_block", SuperHeavyBombBCShellBlock::new)
            .transform(shell(MapColor.TERRACOTTA_LIGHT_GREEN, SoundType.ANVIL))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/super_heavy_bomb_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Super Heavy Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/super_heavy_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<BlockBusterBCShellBlock> BLOCK_BUSTER_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("block_buster_shell_block", BlockBusterBCShellBlock::new)
            .transform(shell(MapColor.TERRACOTTA_GREEN, SoundType.ANVIL))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/block_buster_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Block Buster Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/block_buster_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<KineticBombBCShellBlock> KINETIC_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("kinetic_bomb_shell_block", KineticBombBCShellBlock::new)
            .transform(shell(MapColor.COLOR_BLUE, SoundType.ANVIL))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/kinetic_bomb_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Kinetic Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/kinetic_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<IRSeekerBCShellBlock> IR_SEEKER_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("ir_seeker_shell_block", IRSeekerBCShellBlock::new)
            .transform(shell(MapColor.COLOR_GRAY, SoundType.METAL))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/ir_seeker_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("IR Seeker Shell")
            .item(ProjectileBlockItem::new)
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<ClusterBombBCShellBlock> CLUSTER_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("cluster_bomb_shell_block", ClusterBombBCShellBlock::new)
            .transform(shell(MapColor.EMERALD, SoundType.ANVIL))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/cluster_bomb_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Cluster Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/cluster_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<FissionBombBCShellBlock> FISSION_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("fission_bomb_shell_block", FissionBombBCShellBlock::new)
            .transform(shell(MapColor.COLOR_YELLOW, SoundType.ANVIL))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/fission_bomb_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Fission Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/fission_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .properties(p -> p.rarity(Rarity.RARE))
            .build()
            .register();
    public static final BlockEntry<FusionBombBCShellBlock> FUSION_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("fusion_bomb_shell_block", FusionBombBCShellBlock::new)
            .transform(shell(MapColor.COLOR_MAGENTA, SoundType.ANVIL))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/fusion_bomb_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Fusion Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/fusion_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .properties(p -> p.rarity(Rarity.EPIC))
            .build()
            .register();
    public static final BlockEntry<JokeBombBCShellBlock> JOKE_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("joke_bomb_shell_block", JokeBombBCShellBlock::new)
            .transform(shell(MapColor.PODZOL, SoundType.FUNGUS))
            .transform(axeOrPickaxe())
            .transform(ShupapiumBuilderTransformers.projectile("projectile/joke_bomb_shell"))
            .loot(CBCBuilderTransformers.shellLoot())
            .lang("Chistoso's Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(ShupapiumBuilderTransformers.fuzedProjectileItem("projectile/joke_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .build()
            .register();

    // Ammo Container
    public static final BlockEntry<ShupapiumAmmoContainerBlock> SHUPAPIUM_AMMO_CONTAINER = MainShupapium.REGISTRATE
            .block("warium_ammo_container", ShupapiumAmmoContainerBlock::new)
            .properties(p -> p.mapColor(DyeColor.GRAY))
            .properties(p -> p.strength(0.0F, 3.5F))
            .properties(p -> p.sound(SoundType.ANVIL))
            .properties(BlockBehaviour.Properties::noOcclusion)
            .transform(ShupapiumBuilderTransformers.acAmmoContainer(false))
            .register();
    public static final BlockEntry<ShupapiumAmmoContainerBlock> CREATIVE_SHUPAPIUM_AMMO_CONTAINER = MainShupapium.REGISTRATE
            .block("creative_warium_ammo_container", ShupapiumAmmoContainerBlock::new)
            .properties(p -> p.mapColor(DyeColor.PURPLE))
            .properties(p -> p.strength(0.0F, 5.0F))
            .properties(p -> p.sound(SoundType.AMETHYST))
            .properties(BlockBehaviour.Properties::noOcclusion)
            .transform(ShupapiumBuilderTransformers.acAmmoContainer(true))
            .register();

    // AC Facing
    private static BlockState machineGunBarrel(Direction facing) {
        return MACHINE_GUN_BARREL.getDefaultState().setValue(ShupapiumACBarrelBlock.FACING, facing);
    }
    private static BlockState battleCannonBarrel(Direction facing) {
        return BATTLE_CANNON_BARREL.getDefaultState().setValue(ShupapiumACBarrelBlock.FACING, facing);
    }
    private static BlockState flamethrowerBarrel(Direction facing) {
        return FLAMETHROWER_BARREL.getDefaultState().setValue(ShupapiumACBarrelBlock.FACING, facing);
    }

    // Auxiliary Functions
    private static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> cannonBlock() {
        return cannonBlock(true, SoundType.NETHERITE_BLOCK);
    }
    private static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> cannonBlock(boolean canPassThrough, SoundType sound) {
        NonNullUnaryOperator<BlockBuilder<T, P>> transform = b -> b.properties(p -> p.strength(5.0F, 6.0F))
                .properties(p -> p.sound(sound))
                .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .tag(BlockTags.NEEDS_IRON_TOOL);
        return canPassThrough ? transform.andThen(b -> b.tag(CBCTags.CBCBlockTags.DRILL_CAN_PASS_THROUGH)) : transform;
    }
    private static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> shell(MapColor color, SoundType sound) {
        return b -> b.addLayer(() -> RenderType::solid)
                .properties(p -> p.mapColor(color))
                .properties(p -> p.strength(2.0F, 3.0F))
                .properties(p -> p.sound(sound));
    }

    // Registry
    public static void register() {}
}
