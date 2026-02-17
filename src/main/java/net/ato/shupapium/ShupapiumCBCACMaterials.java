package net.ato.shupapium;

import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterialProperties;

public class ShupapiumCBCACMaterials {
    public static final AutocannonMaterial
        MACHINE_GUN = AutocannonMaterial.register(MainShupapium.resource("machine_gun"),
            AutocannonMaterialProperties.builder()
                    .maxBarrelLength(2)
                    .weight(1.0F)
                    .baseSpread(1.0F)
                    .spreadReductionPerBarrel(0.1F)
                    .baseSpeed(3.0F)
                    .maxSpeedIncreases(4)
                    .projectileLifetime(5)
                    .baseRecoil(3F)
                    .connectsInSurvival(false)
                    .isWeldable(true)
                    .weldDamage(2)
                    .weldStressPenalty(2)
                    .build());
    public static final AutocannonMaterial
            CANNON_GUN = AutocannonMaterial.register(MainShupapium.resource("cannon_gun"),
            AutocannonMaterialProperties.builder()
                    .maxBarrelLength(4)
                    .weight(2.0F)
                    .baseSpread(1.2F)
                    .spreadReductionPerBarrel(0.2F)
                    .baseSpeed(3.2F)
                    .maxSpeedIncreases(4)
                    .projectileLifetime(5)
                    .baseRecoil(3.2F)
                    .connectsInSurvival(false)
                    .isWeldable(true)
                    .weldDamage(3)
                    .weldStressPenalty(3)
                    .build());
    public static final AutocannonMaterial
            BATTLE_GUN = AutocannonMaterial.register(MainShupapium.resource("battle_gun"),
            AutocannonMaterialProperties.builder()
                    .maxBarrelLength(6)
                    .weight(3.0F)
                    .baseSpread(1.4F)
                    .spreadReductionPerBarrel(0.3F)
                    .baseSpeed(3.4F)
                    .maxSpeedIncreases(4)
                    .projectileLifetime(5)
                    .baseRecoil(3.4F)
                    .connectsInSurvival(false)
                    .isWeldable(true)
                    .weldDamage(4)
                    .weldStressPenalty(4)
                    .build());
    public static final AutocannonMaterial
            ARTILLERY_GUN = AutocannonMaterial.register(MainShupapium.resource("artillery_gun"),
            AutocannonMaterialProperties.builder()
                    .maxBarrelLength(8)
                    .weight(4.0F)
                    .baseSpread(1.6F)
                    .spreadReductionPerBarrel(0.4F)
                    .baseSpeed(3.6F)
                    .maxSpeedIncreases(4)
                    .projectileLifetime(5)
                    .baseRecoil(3.6F)
                    .connectsInSurvival(false)
                    .isWeldable(true)
                    .weldDamage(5)
                    .weldStressPenalty(5)
                    .build());
    public static final AutocannonMaterial
            MORTAR_GUN = AutocannonMaterial.register(MainShupapium.resource("mortar_gun"),
            AutocannonMaterialProperties.builder()
                    .maxBarrelLength(2)
                    .weight(1.0F)
                    .baseSpread(3.0F)
                    .spreadReductionPerBarrel(0.6F)
                    .baseSpeed(3.5F)
                    .maxSpeedIncreases(4)
                    .projectileLifetime(5)
                    .baseRecoil(3.2F)
                    .connectsInSurvival(false)
                    .isWeldable(true)
                    .weldDamage(2)
                    .weldStressPenalty(2)
                    .build());
    public static final AutocannonMaterial
            ROCKET_GUN = AutocannonMaterial.register(MainShupapium.resource("rocket_gun"),
            AutocannonMaterialProperties.builder()
                    .maxBarrelLength(3)
                    .weight(1.3F)
                    .baseSpread(3.4F)
                    .spreadReductionPerBarrel(0.5F)
                    .baseSpeed(3.5F)
                    .maxSpeedIncreases(4)
                    .projectileLifetime(5)
                    .baseRecoil(3.3F)
                    .connectsInSurvival(false)
                    .isWeldable(true)
                    .weldDamage(3)
                    .weldStressPenalty(3)
                    .build());
    public static final AutocannonMaterial
            FLAME_GUN = AutocannonMaterial.register(MainShupapium.resource("flame_gun"),
            AutocannonMaterialProperties.builder()
                    .maxBarrelLength(2)
                    .weight(1.5F)
                    .baseSpread(3.4F)
                    .spreadReductionPerBarrel(0.2F)
                    .baseSpeed(4.5F)
                    .maxSpeedIncreases(4)
                    .projectileLifetime(5)
                    .baseRecoil(3.2F)
                    .connectsInSurvival(false)
                    .isWeldable(true)
                    .weldDamage(3)
                    .weldStressPenalty(3)
                    .build());
}
