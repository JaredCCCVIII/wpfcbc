package com.ato.shupapi;

import com.ato.shupapi.entities.*;
import com.ato.shupapi.entityblocks.*;
import com.tterrag.registrate.util.entry.EntityEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.multiloader.EntityTypeConfigurator;
import rbasamoyai.createbigcannons.munitions.autocannon.AbstractAutocannonProjectile;
import rbasamoyai.createbigcannons.munitions.autocannon.AutocannonProjectileRenderer;
import rbasamoyai.createbigcannons.munitions.big_cannon.AbstractBigCannonProjectile;
import rbasamoyai.createbigcannons.munitions.big_cannon.BigCannonProjectileRenderer;
import rbasamoyai.createbigcannons.munitions.config.MunitionPropertiesHandler;
import rbasamoyai.createbigcannons.munitions.config.PropertiesTypeHandler;
import rbasamoyai.ritchiesprojectilelib.RPLTags;

import java.util.function.Consumer;

public class ShupapiumBlockEntities {

    public static final EntityEntry<FissionShellProjectile> FISSION_SHELL = cannonProjectile("fission_shell", FissionShellProjectile::new, "Fission Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<OrdinanceShellProjectile> ORDINANCE_SHELL = cannonProjectile("ordinance_shell", OrdinanceShellProjectile::new, "Ordinance Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<RedordinanceShellProjectile> REDORDINANCE_SHELL = cannonProjectile("redordinance_shell", RedordinanceShellProjectile::new, "Fire Ordinance Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<HeavyordinanceShellProjectile> HEAVYORDINANCE_SHELL = cannonProjectile("heavyordinance_shell", HeavyordinanceShellProjectile::new, "Heavy Ordinance Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<SuperheavyordinanceShellProjectile> SUPERHEAVYORDINANCE_SHELL = cannonProjectile("superheavyordinance_shell", SuperheavyordinanceShellProjectile::new, "Super Heavy Ordinance Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<ClusterShellProjectile> CLUSTER_SHELL = cannonProjectile("cluster_shell", ClusterShellProjectile::new, "Cluster Ordinance Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<KineticShellProjectile> KINETIC_SHELL = cannonProjectile("kinetic_shell", KineticShellProjectile::new, "Kinetic Ordinance Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<ToxicgasShellProjectile> TOXICGAS_SHELL = cannonProjectile("toxicgas_shell", ToxicgasShellProjectile::new, "Toxic Gas Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<FusionShellProjectile> FUSION_SHELL = cannonProjectile("fusion_shell", FusionShellProjectile::new, "Fusion Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<MachineGunAmmoEntity> MACHINE_GUN_AMMO = autocannonProjectile("machine_gun_ammo", MachineGunAmmoEntity::new, CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE);
    public static final EntityEntry<LightGunAmmoEntity> LIGHT_GUN_AMMO = autocannonProjectile("light_gun_ammo", LightGunAmmoEntity::new, CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE);
    public static final EntityEntry<RotaryGunAmmoEntity> ROTARY_GUN_AMMO = autocannonProjectile("rotary_gun_ammo", RotaryGunAmmoEntity::new, CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE);
    public static final EntityEntry<HeavyGunAmmoEntity> HEAVY_GUN_AMMO = autocannonProjectile("heavy_gun_ammo", HeavyGunAmmoEntity::new, CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE);
    public static final EntityEntry<BattleHEGunAmmoEntity> BATTLE_HE_GUN_AMMO = autocannonProjectile("battle_he_gun_ammo", BattleHEGunAmmoEntity::new, CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE);
    public static final EntityEntry<BattleSolidGunAmmoEntity> BATTLE_SOLID_GUN_AMMO = autocannonProjectile("battle_solid_gun_ammo", BattleSolidGunAmmoEntity::new, CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE);
    public static final EntityEntry<BattleHeatGunAmmoEntity> BATTLE_HEAT_GUN_AMMO = autocannonProjectile("battle_heat_gun_ammo", BattleHeatGunAmmoEntity::new, CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE);
    public static final EntityEntry<BattleAPFSDSGunAmmoEntity> BATTLE_APFSDS_GUN_AMMO = autocannonProjectile("battle_apfsds_gun_ammo", BattleAPFSDSGunAmmoEntity::new, CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE);
    public static final EntityEntry<BattleArtillerySolidGunAmmoEntity> BATTLE_ARTILLERY_SOLID_GUN_AMMO = autocannonProjectile("battle_artillery_solid_gun_ammo", BattleArtillerySolidGunAmmoEntity::new, CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE);
    public static final EntityEntry<BattleArtilleryGasGunAmmoEntity> BATTLE_ARTILLERY_GAS_GUN_AMMO = autocannonProjectile("battle_artillery_gas_gun_ammo", BattleArtilleryGasGunAmmoEntity::new, CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE);
    public static final EntityEntry<BattleArtilleryHEGunAmmoEntity> BATTLE_ARTILLERY_HE_GUN_AMMO = autocannonProjectile("battle_artillery_he_gun_ammo", BattleArtilleryHEGunAmmoEntity::new, CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE);
    public static final EntityEntry<ArmorPeelerGunAmmoEntity> ARMOR_PEELER_GUN_AMMO = autocannonProjectile("armor_peeler_gun_ammo", ArmorPeelerGunAmmoEntity::new, CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE);

    private static <T extends AbstractBigCannonProjectile> EntityEntry<T>
    cannonProjectile(String id, EntityType.EntityFactory<T> factory, PropertiesTypeHandler<EntityType<?>, ?> handler) {
        return MainShupapium.REGISTRATE
                .entity(id, factory, MobCategory.MISC)
                .properties(cannonProperties())
                .renderer(() -> BigCannonProjectileRenderer::new)
                .tag(RPLTags.PRECISE_MOTION)
                .onRegister(type -> MunitionPropertiesHandler.registerProjectileHandler(type, handler))
                .register();
    }

    private static <T extends AbstractBigCannonProjectile> EntityEntry<T>
    cannonProjectile(String id, EntityType.EntityFactory<T> factory, String enUSdiffLang, PropertiesTypeHandler<EntityType<?>, ?> handler) {
        return MainShupapium.REGISTRATE
                .entity(id, factory, MobCategory.MISC)
                .properties(cannonProperties())
                .renderer(() -> BigCannonProjectileRenderer::new)
                .lang(enUSdiffLang)
                .tag(RPLTags.PRECISE_MOTION)
                .onRegister(type -> MunitionPropertiesHandler.registerProjectileHandler(type, handler))
                .register();
    }

    private static <T extends AbstractAutocannonProjectile> EntityEntry<T>
    autocannonProjectile(String id, EntityType.EntityFactory<T> factory, PropertiesTypeHandler<EntityType<?>, ?> handler) {
        return MainShupapium.REGISTRATE
                .entity(id, factory, MobCategory.MISC)
                .properties(autocannonProperties())
                .renderer(() -> AutocannonProjectileRenderer::new)
                .tag(RPLTags.PRECISE_MOTION)
                .onRegister(type -> MunitionPropertiesHandler.registerProjectileHandler(type, handler))
                .register();
    }

    private static <T extends AbstractAutocannonProjectile> EntityEntry<T>
    autocannonProjectile(String id, EntityType.EntityFactory<T> factory, String enUSdiffLang, PropertiesTypeHandler<EntityType<?>, ?> handler) {
        return MainShupapium.REGISTRATE
                .entity(id, factory, MobCategory.MISC)
                .properties(autocannonProperties())
                .renderer(() -> AutocannonProjectileRenderer::new)
                .lang(enUSdiffLang)
                .tag(RPLTags.PRECISE_MOTION)
                .onRegister(type -> MunitionPropertiesHandler.registerProjectileHandler(type, handler))
                .register();
    }

    private static <T> NonNullConsumer<T> configure(Consumer<EntityTypeConfigurator> cons) {
        return b -> cons.accept(EntityTypeConfigurator.of(b));
    }

    private static <T> NonNullConsumer<T> autocannonProperties() {
        return configure(c -> c.size(0.2f, 0.2f)
                .fireImmune()
                .updateInterval(1)
                .updateVelocity(false) // Mixin ServerEntity to not track motion
                .trackingRange(16));
    }

    private static <T> NonNullConsumer<T> cannonProperties() {
        return configure(c -> c.size(0.8F, 0.8F).fireImmune().updateInterval(1).updateVelocity(false).trackingRange(16));
    }

    public static void register() {
    }
}
