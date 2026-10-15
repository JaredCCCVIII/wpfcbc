package net.ato.shupapium;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.ato.shupapium.blockentities.ShupapiumAmmoContainerBlockEntity;
import net.ato.shupapium.blockentities.ShupapiumFuzedBlockEntity;
import net.ato.shupapium.cannons.ShupapiumACBreechBlockEntity;
import net.ato.shupapium.cannons.ShupapiumGACBarrelBlockEntity;
import net.ato.shupapium.client.flywheels.ShupapiumACBreechVisual;
import net.ato.shupapium.client.renderers.ShupapiumACBreechRenderer;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringRenderer;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringVisual;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBlockEntityRenderer;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBlockVisual;

public class ShupapiumBlockEntities {
    public static final BlockEntityEntry<AutocannonBlockEntity> SHUPAPIUM_AUTOCANNON = MainShupapium.REGISTRATE
            .blockEntity("shupapium_autocannon", AutocannonBlockEntity::new)
            .validBlocks(ShupapiumBlocks.MACHINE_GUN_BARREL, ShupapiumBlocks.COVERED_MACHINE_GUN_BARREL,
                    ShupapiumBlocks.CANNON_BARREL,
                    ShupapiumBlocks.BATTLE_CANNON_BARREL, ShupapiumBlocks.THICK_BATTLE_CANNON_BARREL, ShupapiumBlocks.BATTLE_CANNON_MUZZLE_BRAKE_TYPE_1, ShupapiumBlocks.BATTLE_CANNON_MUZZLE_BRAKE_TYPE_2,
                    ShupapiumBlocks.ARTILLERY_CANNON_BARREL,
                    ShupapiumBlocks.ROCKET_POD_BARREL, ShupapiumBlocks.LARGE_ROCKET_POD_BARREL,
                    ShupapiumBlocks.LAUNCHER_BARREL, ShupapiumBlocks.FLAMETHROWER_BARREL)
            .register();
    public static final BlockEntityEntry<ShupapiumGACBarrelBlockEntity> MINIGUN_GECKO_CANNON = MainShupapium.REGISTRATE
            .blockEntity("minigun_gecko_cannon", ShupapiumGACBarrelBlockEntity::new)
            .validBlock(ShupapiumBlocks.MINIGUN_BARREL)
            .register();
    public static final BlockEntityEntry<ShupapiumGACBarrelBlockEntity> ROTARY_GECKO_CANNON = MainShupapium.REGISTRATE
            .blockEntity("rotary_gecko_cannon", ShupapiumGACBarrelBlockEntity::new)
            .validBlock(ShupapiumBlocks.ROTARY_CANNON_BARREL)
            .register();
    public static final BlockEntityEntry<ShupapiumACBreechBlockEntity> SHUPAPIUM_AUTOCANNON_BREECH = MainShupapium.REGISTRATE
            .blockEntity("shupapium_autocannon_breech", ShupapiumACBreechBlockEntity::new)
            .visual(() -> ShupapiumACBreechVisual::new)
            .renderer(() -> ShupapiumACBreechRenderer::new)
            .validBlocks(ShupapiumBlocks.MINIGUN_BREECH, ShupapiumBlocks.LIGHT_MACHINE_GUN_BREECH, ShupapiumBlocks.MACHINE_GUN_BREECH, ShupapiumBlocks.HEAVY_MACHINE_GUN_BREECH,
                    ShupapiumBlocks.LIGHT_CANNON_BREECH, ShupapiumBlocks.ROTARY_CANNON_BREECH, ShupapiumBlocks.HEAVY_CANNON_BREECH,
                    ShupapiumBlocks.BATTLE_CANNON_BREECH, ShupapiumBlocks.ARTILLERY_CANNON_BREECH,
                    ShupapiumBlocks.ROCKET_POD_BREECH, ShupapiumBlocks.LARGE_ROCKET_POD_BREECH,
                    ShupapiumBlocks.LAUNCHER_BREECH, ShupapiumBlocks.FLAMETHROWER_BREECH)
            .register();
    public static final BlockEntityEntry<AutocannonRecoilSpringBlockEntity> SHUPAPIUM_AUTOCANNON_RECOIL_SPRING = MainShupapium.REGISTRATE
            .blockEntity("shupapium_autocannon_recoil_spring", AutocannonRecoilSpringBlockEntity::new)
            .visual(() -> AutocannonRecoilSpringVisual::new)
            .renderer(() -> AutocannonRecoilSpringRenderer::new)
            .validBlocks(ShupapiumBlocks.COVERED_BATTLE_CANNON_BARREL,
                    ShupapiumBlocks.COVERED_FLAMETHROWER_BARREL)
            .register();
    public static final BlockEntityEntry<ShupapiumAmmoContainerBlockEntity> SHUPAPIUM_AMMO_CONTAINER = MainShupapium.REGISTRATE
            .blockEntity("shupapium_ammo_container", ShupapiumAmmoContainerBlockEntity::new)
            .validBlocks(ShupapiumBlocks.SHUPAPIUM_AMMO_CONTAINER, ShupapiumBlocks.CREATIVE_SHUPAPIUM_AMMO_CONTAINER)
            .register();
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
