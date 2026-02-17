package net.ato.shupapium;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.ato.shupapium.blockentities.RotatoryACBarrelBlockEntity;
import net.ato.shupapium.blockentities.ShupapiumACBreechBlockEntity;
import net.ato.shupapium.blockentities.ShupapiumAmmoContainerBlockEntity;
import net.ato.shupapium.client.renderers.ShupapiumACBreechRenderer;
import net.ato.shupapium.utils.ShupapiumIndexPlatform;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.breech.AbstractAutocannonBreechBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringRenderer;
import rbasamoyai.createbigcannons.multiloader.IndexPlatform;

public class ShupapiumBlockEntities {
    public static final BlockEntityEntry<AutocannonBlockEntity> SHUPAPIUM_AUTOCANNON = MainShupapium.REGISTRATE
            .blockEntity("shupapium_autocannon", AutocannonBlockEntity::new)
            .validBlocks(ShupapiumBlocks.MACHINE_GUN_BARREL, ShupapiumBlocks.COVERED_GUN_BARREL, ShupapiumBlocks.ROTATORY_CANNON_BARREL, ShupapiumBlocks.CANNON_GUN_BARREL, ShupapiumBlocks.BATTLE_GUN_BARREL, ShupapiumBlocks.THICK_BATTLE_GUN_BARREL, ShupapiumBlocks.ARTILLERY_GUN_BARREL, ShupapiumBlocks.ROCKET_POD_BARREL, ShupapiumBlocks.MORTAR_GUN_BARREL, ShupapiumBlocks.FLAMETHROWER_GUN_BARREL, ShupapiumBlocks.COVERED_FLAMETHROWER_GUN_BARREl, ShupapiumBlocks.MINIGUN_BARREL, ShupapiumBlocks.TEMPLATE_GUN_BARREL)
            .register();
    public static final BlockEntityEntry<ShupapiumACBreechBlockEntity> SHUPAPIUM_AUTOCANNON_BREECH = MainShupapium.REGISTRATE
            .blockEntity("shupapium_autocannon_breech", ShupapiumIndexPlatform::makeAutocannonBreech)
            .renderer(() -> ShupapiumACBreechRenderer::new)
            .validBlocks(ShupapiumBlocks.MACHINE_GUN_BREECH, ShupapiumBlocks.CANNON_GUN_BREECH, ShupapiumBlocks.BATTLE_GUN_BREECH, ShupapiumBlocks.ARTILLERY_GUN_BREECH, ShupapiumBlocks.ROCKET_POD_BREECH, ShupapiumBlocks.MORTAR_GUN_BREECH, ShupapiumBlocks.FLAMETHROWER_GUN_BREECH, ShupapiumBlocks.TEMPLATE_GUN_BREECH)
            .register();
    public static final BlockEntityEntry<AutocannonRecoilSpringBlockEntity> SHUPAPIUM_AUTOCANNON_RECOIL_SPRING = MainShupapium.REGISTRATE
            .blockEntity("shupapium_autocannon_recoil_spring", AutocannonRecoilSpringBlockEntity::new)
            .renderer(() -> AutocannonRecoilSpringRenderer::new)
            .validBlocks(ShupapiumBlocks.MACHINE_GUN_RECOIL_SPRING, ShupapiumBlocks.LIGHT_GUN_RECOIL_SPRING, ShupapiumBlocks.HEAVY_GUN_RECOIL_SPRING, ShupapiumBlocks.ROTATORY_CANNON_RECOIL_SPRING, ShupapiumBlocks.LIGHT_CANNON_RECOIL_SPRING, ShupapiumBlocks.HEAVY_CANNON_RECOIL_SPRING, ShupapiumBlocks.BATTLE_GUN_RECOIL_SPRING, ShupapiumBlocks.ARTILLERY_GUN_RECOIL_SPRING, ShupapiumBlocks.ROCKET_POD_RECOIL_SPRING, ShupapiumBlocks.MORTAR_GUN_RECOIL_SPRING, ShupapiumBlocks.FLAMETHROWER_GUN_RECOIL_SPRING, ShupapiumBlocks.MINIGUN_RECOIL_SPRING, ShupapiumBlocks.TEMPLATE_GUN_RECOIL_SPRING)
            .register();
    public static final BlockEntityEntry<RotatoryACBarrelBlockEntity> ROTARY_CANNON_BARREL = MainShupapium.REGISTRATE
            .blockEntity("rotary_autocannon_barrel", RotatoryACBarrelBlockEntity::new)
            .validBlocks(ShupapiumBlocks.ROTATORY_CANNON_BARREL)
            .register();
    public static final BlockEntityEntry<RotatoryACBarrelBlockEntity> MINIGUN_BARREL = MainShupapium.REGISTRATE
            .blockEntity("minigun_barrel", RotatoryACBarrelBlockEntity::new)
            .validBlocks(ShupapiumBlocks.MINIGUN_BARREL)
            .register();

    public static final BlockEntityEntry<ShupapiumAmmoContainerBlockEntity> SHUPAPIUM_AMMO_CONTAINER = MainShupapium.REGISTRATE
            .blockEntity("shupapium_ammo_container", ShupapiumAmmoContainerBlockEntity::new)
            .validBlocks(ShupapiumBlocks.SHUPAPIUM_AMMO_CONTAINER, ShupapiumBlocks.CREATIVE_SHUPAPIUM_AMMO_CONTAINER)
            .register();

    public static void register() {}
}
