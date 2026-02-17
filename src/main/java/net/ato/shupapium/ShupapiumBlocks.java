package net.ato.shupapium;

import com.simibubi.create.foundation.item.ItemDescription;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import net.ato.shupapium.blocks.*;
import net.ato.shupapium.blocks.misc.JokeBombShellBlock;
import net.ato.shupapium.utils.ShupapiumBuilderTransformers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import rbasamoyai.createbigcannons.CBCTags;
import rbasamoyai.createbigcannons.datagen.assets.CBCBuilderTransformers;
import rbasamoyai.createbigcannons.munitions.FuzedProjectileBlockItem;

import static com.simibubi.create.foundation.data.TagGen.axeOrPickaxe;

public class ShupapiumBlocks {
    static {ShupapiumGroup.useModTab(ShupapiumGroup.MAIN_TAB_KEY);}
    //Cannons
    public static final BlockEntry<ShupapiumACBarrelBlock> MACHINE_GUN_BARREL = MainShupapium.REGISTRATE
            .block("machine_gun_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel("autocannon/machine_gun"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> COVERED_GUN_BARREL = MainShupapium.REGISTRATE
            .block("covered_gun_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel("autocannon/machine_gun"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> MACHINE_GUN_BREECH = MainShupapium.REGISTRATE
            .block("machine_gun_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech("autocannon/machine_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> MACHINE_GUN_RECOIL_SPRING = MainShupapium.REGISTRATE
            .block("machine_gun_recoil_spring", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN, ShupapiumBlocks::machineGunBarrel))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring("autocannon/machine_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> LIGHT_GUN_RECOIL_SPRING = MainShupapium.REGISTRATE
            .block("light_gun_recoil_spring", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN, ShupapiumBlocks::machineGunBarrel))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring("autocannon/machine_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> HEAVY_GUN_RECOIL_SPRING = MainShupapium.REGISTRATE
            .block("heavy_gun_recoil_spring", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN, ShupapiumBlocks::machineGunBarrel))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring("autocannon/machine_gun", true))
            .register();
    public static final BlockEntry<RotatoryACBarrel> ROTATORY_CANNON_BARREL = MainShupapium.REGISTRATE
            .block("rotatory_cannon_barrel", p -> new RotatoryACBarrel(p, ShupapiumCBCACMaterials.CANNON_GUN, ShupapiumBlockEntities.ROTARY_CANNON_BARREL))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel("autocannon/cannon_gun"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> CANNON_GUN_BARREL = MainShupapium.REGISTRATE
            .block("cannon_gun_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.CANNON_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel("autocannon/cannon_gun"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> CANNON_GUN_BREECH = MainShupapium.REGISTRATE
            .block("cannon_gun_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.CANNON_GUN))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBreech("autocannon/cannon_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> ROTATORY_CANNON_RECOIL_SPRING = MainShupapium.REGISTRATE
            .block("rotatory_cannon_recoil_spring", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.CANNON_GUN, ShupapiumBlocks::rotatoryCannonBarrel))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring("autocannon/cannon_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> LIGHT_CANNON_RECOIL_SPRING = MainShupapium.REGISTRATE
            .block("light_cannon_recoil_spring", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.CANNON_GUN, ShupapiumBlocks::cannonGunBarrel))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring("autocannon/cannon_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> HEAVY_CANNON_RECOIL_SPRING = MainShupapium.REGISTRATE
            .block("heavy_cannon_recoil_spring", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.CANNON_GUN, ShupapiumBlocks::cannonGunBarrel))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring("autocannon/cannon_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> BATTLE_GUN_BARREL = MainShupapium.REGISTRATE
            .block("battle_gun_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.BATTLE_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(5))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel("autocannon/battle_gun"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> THICK_BATTLE_GUN_BARREL = MainShupapium.REGISTRATE
            .block("thick_battle_gun_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.BATTLE_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(6))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel("autocannon/battle_gun"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> BATTLE_GUN_BREECH = MainShupapium.REGISTRATE
            .block("battle_gun_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.BATTLE_GUN))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBreech("autocannon/battle_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> BATTLE_GUN_RECOIL_SPRING = MainShupapium.REGISTRATE
            .block("battle_gun_recoil_spring", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.BATTLE_GUN, ShupapiumBlocks::battleGunBarrel))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(7))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring("autocannon/battle_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> ARTILLERY_GUN_BARREL = MainShupapium.REGISTRATE
            .block("artillery_gun_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.ARTILLERY_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(6))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel("autocannon/artillery_gun"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> ARTILLERY_GUN_BREECH = MainShupapium.REGISTRATE
            .block("artillery_gun_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.ARTILLERY_GUN))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBreech("autocannon/artillery_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> ARTILLERY_GUN_RECOIL_SPRING = MainShupapium.REGISTRATE
            .block("artillery_gun_recoil_spring", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.ARTILLERY_GUN, ShupapiumBlocks::artilleryGunBarrel))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(7))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring("autocannon/artillery_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> ROCKET_POD_BARREL = MainShupapium.REGISTRATE
            .block("rocket_pod_gun_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.ROCKET_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel("autocannon/rocket_gun"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> ROCKET_POD_BREECH = MainShupapium.REGISTRATE
            .block("rocket_pod_gun_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.ROCKET_GUN))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonBreech("autocannon/rocket_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> ROCKET_POD_RECOIL_SPRING = MainShupapium.REGISTRATE
            .block("rocket_pod_gun_recoil_spring", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.ROCKET_GUN, ShupapiumBlocks::rocketPodBarrel))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring("autocannon/rocket_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> MORTAR_GUN_BARREL = MainShupapium.REGISTRATE
            .block("mortar_gun_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.MORTAR_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel("autocannon/mortar_gun"))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> MORTAR_GUN_BREECH = MainShupapium.REGISTRATE
            .block("mortar_gun_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.MORTAR_GUN))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonBreech("autocannon/mortar_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> MORTAR_GUN_RECOIL_SPRING = MainShupapium.REGISTRATE
            .block("mortar_gun_recoil_spring", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.MORTAR_GUN, ShupapiumBlocks::mortarGunBarrel))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring("autocannon/mortar_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> FLAMETHROWER_GUN_BARREL = MainShupapium.REGISTRATE
            .block("flamethrower_gun_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.FLAME_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel("autocannon/flame_gun"))
            .register();
    public static final BlockEntry<ShupapiumACBarrelBlock> COVERED_FLAMETHROWER_GUN_BARREl = MainShupapium.REGISTRATE
            .block("covered_flamethrower_gun_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.FLAME_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel("autocannon/flame_gun"))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> FLAMETHROWER_GUN_RECOIL_SPRING = MainShupapium.REGISTRATE
            .block("flamethrower_gun_recoil_spring", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.FLAME_GUN, ShupapiumBlocks::flameGunBarrel))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring("autocannon/flame_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> FLAMETHROWER_GUN_BREECH = MainShupapium.REGISTRATE
            .block("flamethrower_gun_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.FLAME_GUN))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBreech("autocannon/flame_gun", true))
            .register();
    public static final BlockEntry<RotatoryACBarrel> MINIGUN_BARREL = MainShupapium.REGISTRATE
            .block("minigun_barrel", p -> new RotatoryACBarrel(p, ShupapiumCBCACMaterials.MACHINE_GUN, ShupapiumBlockEntities.MINIGUN_BARREL))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.steelScrapLoot(1))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel("autocannon/machine_gun"))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> MINIGUN_RECOIL_SPRING = MainShupapium.REGISTRATE
            .block("minigun_recoil_spring", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN, ShupapiumBlocks::minigunBarrel))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.steelScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring("autocannon/machine_gun", true))
            .register();

    //Shells
    public static final BlockEntry<GasBombShellBlock> GAS_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("gas_bomb_shell_block", GasBombShellBlock::new)
            .transform(shell(MapColor.COLOR_YELLOW))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/gas_bomb_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.COPPER))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.gas_bomb_shell_block"))
            .lang("Gas Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/gas_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<SmallBombShellBlock> SMALL_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("small_bomb_shell_block", SmallBombShellBlock::new)
            .transform(shell(MapColor.COLOR_LIGHT_GREEN))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/small_bomb_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.COPPER))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.small_bomb_shell_block"))
            .lang("Small Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/small_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<SmallBombClusterShellBlock> SMALL_BOMB_CLUSTER_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("small_bomb_cluster_shell_block", SmallBombClusterShellBlock::new)
            .transform(shell(MapColor.COLOR_LIGHT_GREEN))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/small_bomb_cluster_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.COPPER))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.small_bomb_cluster_shell_block"))
            .lang("Small Bomb Cluster Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/small_bomb_cluster_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<MediumBombShellBlock> MEDIUM_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("medium_bomb_shell_block", MediumBombShellBlock::new)
            .transform(shell(MapColor.COLOR_LIGHT_GREEN))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/medium_bomb_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.COPPER))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.medium_bomb_shell_block"))
            .lang("Medium Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/medium_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<FireBombShellBlock> FIRE_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("fire_bomb_shell_block", FireBombShellBlock::new)
            .transform(shell(MapColor.COLOR_RED))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/fire_bomb_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.fire_bomb_shell_block"))
            .lang("Fire Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/fire_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<KineticBunkerBusterShellBlock> KINETIC_BUNKER_BUSTER_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("kinetic_bunker_buster_shell_block", KineticBunkerBusterShellBlock::new)
            .transform(shell(MapColor.COLOR_LIGHT_BLUE))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/kinetic_bunker_buster_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.kinetic_bunker_buster_shell_block"))
            .lang("Kinetic Bunker Buster Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/kinetic_bunker_buster_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<IRSeekerMissileShellBlock> IR_SEEKER_MISSILE_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("ir_seeker_missile_shell_block", IRSeekerMissileShellBlock::new)
            .transform(shell(MapColor.COLOR_LIGHT_GRAY))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/ir_seeker_missile_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.ir_seeker_missile_shell_block"))
            .lang("IR Seeker Missile Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/ir_seeker_missile_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<SARHSeekerShellBlock> SARH_SEEKER_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("sarh_seeker_shell_block", SARHSeekerShellBlock::new)
            .transform(shell(MapColor.COLOR_BLUE))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/sarh_seeker_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.sarh_seeker_shell_block"))
            .lang("SARH Seeker Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/sarh_seeker_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<ClusterRocketShellBlock> CLUSTER_ROCKET_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("cluster_rocket_shell_block", ClusterRocketShellBlock::new)
            .transform(shell(MapColor.COLOR_GREEN))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/cluster_rocket_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.cluster_rocket_shell_block"))
            .lang("Cluster Rocket Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/cluster_rocket_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<HeavyBombShellBlock> HEAVY_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("heavy_bomb_shell_block", HeavyBombShellBlock::new)
            .transform(shell(MapColor.COLOR_GREEN))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/heavy_bomb_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.heavy_bomb_shell_block"))
            .lang("Heavy Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/heavy_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<SuperHeavyBombShellBlock> SUPER_HEAVY_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("super_heavy_bomb_shell_block", SuperHeavyBombShellBlock::new)
            .transform(shell(MapColor.COLOR_GREEN))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/super_heavy_bomb_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.super_heavy_bomb_shell_block"))
            .lang("Super Heavy Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/super_heavy_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<BlockBusterShellBlock> BLOCK_BUSTER_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("block_buster_shell_block", BlockBusterShellBlock::new)
            .transform(shell(MapColor.COLOR_GREEN))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/block_buster_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.block_buster_shell_block"))
            .lang("Block Buster Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/block_buster_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .build()
            .register();
    public static final BlockEntry<FissionBombShellBlock> FISSION_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("fission_bomb_shell_block", FissionBombShellBlock::new)
            .transform(shell(MapColor.COLOR_YELLOW))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/fission_bomb_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.fission_bomb_shell_block"))
            .lang("Fission Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/fission_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .properties(p -> p.rarity(Rarity.RARE))
            .build()
            .register();
    public static final BlockEntry<FusionBombShellBlock> FUSION_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("fusion_bomb_shell_block", FusionBombShellBlock::new)
            .transform(shell(MapColor.COLOR_MAGENTA))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/fusion_bomb_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.ANVIL))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v -> ItemDescription.useKey(v, "block.createmissilelangtest.fusion_bomb_shell_block"))
            .lang("Fusion Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/fusion_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .properties(p -> p.rarity(Rarity.EPIC))
            .build()
            .register();
    public static final BlockEntry<JokeBombShellBlock> JOKE_BOMB_SHELL_BLOCK = MainShupapium.REGISTRATE
            .block("joke_bomb_shell_block", JokeBombShellBlock::new)
            .transform(shell(MapColor.PODZOL))
            .transform(axeOrPickaxe())
            .transform(CBCBuilderTransformers.projectile("projectile/joke_bomb_shell"))
            .transform(CBCBuilderTransformers.safeNbt())
            .properties(p -> p.sound(SoundType.FUNGUS))
            .loot(CBCBuilderTransformers.shellLoot())
            .onRegisterAfter(Registries.ITEM, v-> ItemDescription.useKey(v, "block.createmissilelangtest.joke_bomb_shell_block"))
            .lang("Chistoso's Bomb Shell")
            .item(FuzedProjectileBlockItem::new)
            .transform(CBCBuilderTransformers.fuzedProjectileItem("projectile/joke_bomb_shell"))
            .tag(CBCTags.CBCItemTags.BIG_CANNON_PROJECTILES)
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .build()
            .register();

    //Containers
    public static final BlockEntry<ShupapiumAmmoContainerBlock> SHUPAPIUM_AMMO_CONTAINER = MainShupapium.REGISTRATE
            .block("shupapium_ammo_container", ShupapiumAmmoContainerBlock::new)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY))
            .properties(p -> p.strength(0.0F, 3.5F))
            .properties(p -> p.sound(SoundType.CHAIN))
            .properties(BlockBehaviour.Properties::noOcclusion)
            .transform(ShupapiumBuilderTransformers.autocannonAmmoContainer(false))
            .register();
    public static final BlockEntry<ShupapiumAmmoContainerBlock> CREATIVE_SHUPAPIUM_AMMO_CONTAINER = MainShupapium.REGISTRATE
            .block("creative_shupapium_ammo_container", ShupapiumAmmoContainerBlock::new)
            .properties(p -> p.mapColor(MapColor.COLOR_PURPLE))
            .properties(p -> p.strength(0.0F, 4.0F))
            .properties(p -> p.sound(SoundType.CHAIN))
            .properties(BlockBehaviour.Properties::noOcclusion)
            .transform(ShupapiumBuilderTransformers.autocannonAmmoContainer(true))
            .register();

    //Misc
    public static final BlockEntry<ShupapiumACBarrelBlock> TEMPLATE_GUN_BARREL = MainShupapium.REGISTRATE
            .block("template_gun_barrel", p -> new ShupapiumACBarrelBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN))
            .transform(cannonBlock())
            .loot(CBCBuilderTransformers.bronzeScrapLoot(2))
            .transform(ShupapiumBuilderTransformers.autocannonBarrel("autocannon/machine_gun"))
            .register();
    public static final BlockEntry<ShupapiumACRecoilSpringBlock> TEMPLATE_GUN_RECOIL_SPRING = MainShupapium.REGISTRATE
            .block("template_gun_recoil_spring", p -> new ShupapiumACRecoilSpringBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN, ShupapiumBlocks::templateGunBarrel))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.bronzeScrapLoot(3))
            .transform(ShupapiumBuilderTransformers.autocannonRecoilSpring("autocannon/machine_gun", true))
            .register();
    public static final BlockEntry<ShupapiumACBreechBlock> TEMPLATE_GUN_BREECH = MainShupapium.REGISTRATE
            .block("template_gun_breech", p -> new ShupapiumACBreechBlock(p, ShupapiumCBCACMaterials.MACHINE_GUN))
            .transform(cannonBlock(false))
            .loot(CBCBuilderTransformers.bronzeScrapLoot(4))
            .transform(ShupapiumBuilderTransformers.autocannonBreech("autocannon/machine_gun", true))
            .register();

    private static BlockState machineGunBarrel(Direction facing) {
        return MACHINE_GUN_BARREL.getDefaultState().setValue(ShupapiumACBarrelBlock.FACING, facing);
    }
    private static BlockState rotatoryCannonBarrel(Direction facing) {
        return ROTATORY_CANNON_BARREL.getDefaultState().setValue(ShupapiumACBarrelBlock.FACING, facing);
    }
    private static BlockState cannonGunBarrel(Direction facing) {
        return CANNON_GUN_BARREL.getDefaultState().setValue(ShupapiumACBarrelBlock.FACING, facing);
    }
    private static BlockState battleGunBarrel(Direction facing) {
        return BATTLE_GUN_BARREL.getDefaultState().setValue(ShupapiumACBarrelBlock.FACING, facing);
    }
    private static BlockState artilleryGunBarrel(Direction facing) {
        return ARTILLERY_GUN_BARREL.getDefaultState().setValue(ShupapiumACBarrelBlock.FACING, facing);
    }
    private static BlockState mortarGunBarrel(Direction facing) {
        return MORTAR_GUN_BARREL.getDefaultState().setValue(ShupapiumACBarrelBlock.FACING, facing);
    }
    private static BlockState rocketPodBarrel(Direction facing) {
        return ROCKET_POD_BARREL.getDefaultState().setValue(ShupapiumACBarrelBlock.FACING, facing);
    }
    private static BlockState flameGunBarrel(Direction facing) {
        return FLAMETHROWER_GUN_BARREL.getDefaultState().setValue(ShupapiumACBarrelBlock.FACING, facing);
    }
    private static BlockState minigunBarrel(Direction facing) {
        return MINIGUN_BARREL.getDefaultState().setValue(ShupapiumACBarrelBlock.FACING, facing);
    }
    private static BlockState templateGunBarrel(Direction facing) {
        return TEMPLATE_GUN_BARREL.getDefaultState().setValue(ShupapiumACBarrelBlock.FACING, facing);
    }

    private static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> cannonBlock() {
        return cannonBlock(true);
    }

    private static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> cannonBlock(boolean canPassThrough) {
        NonNullUnaryOperator<BlockBuilder<T, P>> transform = b -> b.properties(p -> p.strength(5.0f, 6.0f))
                .properties(p -> p.sound(SoundType.NETHERITE_BLOCK))
                .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .tag(BlockTags.NEEDS_IRON_TOOL);
        return canPassThrough ? transform.andThen(b -> b.tag(CBCTags.CBCBlockTags.DRILL_CAN_PASS_THROUGH)) : transform;
    }

    private static <T extends Block, P> NonNullUnaryOperator<BlockBuilder<T, P>> shell(MapColor color) {
        return b -> b.addLayer(() -> RenderType::solid)
                .properties(p -> p.mapColor(color))
                .properties(p -> p.strength(2.0f, 3.0f))
                .properties(p -> p.sound(SoundType.STONE));
    }

    public static void register() {}
}
