package net.ato.shupapium;

import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterialProperties;

public class ShupapiumCBCACMaterials {
    public static final AutocannonMaterial
        MACHINE_GUN = AutocannonMaterial.register(MainShupapium.resource("machine_gun"),
            AutocannonMaterialProperties.builder()
                    .maxBarrelLength(1)
                    .weight(2.5F)
                    .baseSpread(3.0F)
                    .spreadReductionPerBarrel(1.5F)
                    .maxSpeedIncreases(4)
                    .projectileLifetime(60)
                    .baseRecoil(3)
                    .connectsInSurvival(true)
                    .isWeldable(true)
                    .weldDamage(2)
                    .weldStressPenalty(2)
                    .build()),
        CANNON_GUN = AutocannonMaterial.register(MainShupapium.resource("cannon_gun"),
                AutocannonMaterialProperties.builder()
                        .maxBarrelLength(6)
                        .weight(3)
                        .baseSpread(3.5F)
                        .spreadReductionPerBarrel(2.0F)
                        .maxSpeedIncreases(5)
                        .projectileLifetime(60)
                        .baseRecoil(3)
                        .connectsInSurvival(true)
                        .isWeldable(true)
                        .weldDamage(2)
                        .weldStressPenalty(2)
                        .build()),
            BATTLE_GUN = AutocannonMaterial.register(MainShupapium.resource("battle_gun"),
                    AutocannonMaterialProperties.builder()
                            .maxBarrelLength(11)
                            .weight(4F)
                            .baseSpread(4.0F)
                            .spreadReductionPerBarrel(2.5F)
                            .maxSpeedIncreases(6)
                            .projectileLifetime(60)
                            .baseRecoil(3)
                            .connectsInSurvival(true)
                            .isWeldable(true)
                            .weldDamage(2)
                            .weldStressPenalty(2)
                            .build()),
            ARTILLERY_GUN = AutocannonMaterial.register(MainShupapium.resource("artillery_gun"),
                    AutocannonMaterialProperties.builder()
                            .maxBarrelLength(12)
                            .weight(5.0F)
                            .baseSpread(6.0F)
                            .spreadReductionPerBarrel(3.0F)
                            .maxSpeedIncreases(7)
                            .projectileLifetime(60)
                            .baseRecoil(4)
                            .connectsInSurvival(true)
                            .isWeldable(true)
                            .weldDamage(2)
                            .weldStressPenalty(2)
                            .build()),
    ROCKET_GUN = AutocannonMaterial.register(MainShupapium.resource("rocket_gun"),
            AutocannonMaterialProperties.builder()
                    .maxBarrelLength(2)
                    .weight(3)
                    .baseSpread(3.2F)
                    .spreadReductionPerBarrel(1.9F)
                    .maxSpeedIncreases(5)
                    .projectileLifetime(60)
                    .baseRecoil(3)
                    .connectsInSurvival(true)
                    .isWeldable(true)
                    .weldDamage(2)
                    .weldStressPenalty(2)
                    .build()),
            LARGE_ROCKET_GUN = AutocannonMaterial.register(MainShupapium.resource("large_rocket_gun"),
                    AutocannonMaterialProperties.builder()
                            .maxBarrelLength(3)
                            .weight(3)
                            .baseSpread(3.2F)
                            .spreadReductionPerBarrel(1.9F)
                            .maxSpeedIncreases(5)
                            .projectileLifetime(60)
                            .baseRecoil(3)
                            .connectsInSurvival(true)
                            .isWeldable(true)
                            .weldDamage(2)
                            .weldStressPenalty(2)
                            .build()),
            SMALL_GUN = AutocannonMaterial.register(MainShupapium.resource("small_gun"),
                    AutocannonMaterialProperties.builder()
                            .maxBarrelLength(1)
                            .weight(2.0F)
                            .baseSpread(2.5F)
                            .spreadReductionPerBarrel(1.0F)
                            .maxSpeedIncreases(4)
                            .projectileLifetime(60)
                            .baseRecoil(2)
                            .connectsInSurvival(true)
                            .isWeldable(true)
                            .weldDamage(2)
                            .weldStressPenalty(2)
                            .build());
}
