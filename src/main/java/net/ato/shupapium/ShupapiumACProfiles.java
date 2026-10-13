package net.ato.shupapium;

import com.wariumce.init.CrustyChunksModSounds;
import com.wariumce.procedures.LargeFireSoundProcedure;
import com.wariumce.procedures.MediumFireSoundProcedure;
import net.ato.shupapium.utils.acprofiles.BasicShupapiumACProfile;
import net.ato.shupapium.utils.actypes.ShupapiumACProfile;
import net.ato.shupapium.utils.actypes.ShupapiumACProfileHandler;
import net.minecraft.core.particles.ParticleTypes;

public class ShupapiumACProfiles {
    public static final ShupapiumACProfile MINI_GUN_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("mini_gun_profile"))
            .parts(ShupapiumBlocks.MINIGUN_BARREL, ShupapiumBlocks.MINIGUN_BREECH)
            .fireSound(CrustyChunksModSounds.MEDIUMSHOT::get)
            .muzzleParticle(ParticleTypes.LARGE_SMOKE)
            .fireRate(300)
            .projectilesPerShot(1)
            .cannonSpeed(1.0F)
            .cannonSpread(0.1F)
            .ammo(ShupapiumItems.MEDIUM_BULLET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile LIGHT_MACHINE_GUN_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("light_machine_gun_profile"))
            .parts(ShupapiumBlocks.MACHINE_GUN_BARREL, ShupapiumBlocks.LIGHT_MACHINE_GUN_BREECH)
            .alternateBarrels(ShupapiumBlocks.COVERED_MACHINE_GUN_BARREL)
            .fireSound(CrustyChunksModSounds.SPARKS::get)
            .customFireSound((level, pos) -> MediumFireSoundProcedure.execute(level, pos.x, pos.y, pos.z))
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.SMOKE)
            .fireRate(400)
            .projectilesPerShot(1)
            .cannonSpeed(2.0F)
            .cannonSpread(0.15F)
            .ammo(ShupapiumItems.MEDIUM_BULLET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile MACHINE_GUN_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("machine_gun_profile"))
            .parts(ShupapiumBlocks.MACHINE_GUN_BARREL, ShupapiumBlocks.MACHINE_GUN_BREECH)
            .alternateBarrels(ShupapiumBlocks.COVERED_MACHINE_GUN_BARREL)
            .fireSound(CrustyChunksModSounds.LARGESHOT::get)
            .customFireSound((level, pos) -> LargeFireSoundProcedure.execute(level, pos.x, pos.y, pos.z))
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.SMOKE)
            .fireRate(500)
            .projectilesPerShot(1)
            .cannonSpeed(3.0F)
            .cannonSpread(0.2F)
            .ammo(ShupapiumItems.LARGE_BULLET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile HEAVY_MACHINE_GUN_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("heavy_machine_gun_profile"))
            .parts(ShupapiumBlocks.MACHINE_GUN_BARREL, ShupapiumBlocks.HEAVY_MACHINE_GUN_BREECH)
            .alternateBarrels(ShupapiumBlocks.COVERED_MACHINE_GUN_BARREL)
            .fireSound(CrustyChunksModSounds.AUTOCANNONSHOT::get)
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.SMOKE)
            .fireRate(600)
            .projectilesPerShot(1)
            .cannonSpeed(4.0F)
            .cannonSpread(0.3F)
            .ammo(ShupapiumItems.EXTRA_LARGE_BULLET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile ROTARY_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("rotary_cannon_profile"))
            .parts(ShupapiumBlocks.ROTARY_CANNON_BARREL, ShupapiumBlocks.ROTARY_CANNON_BREECH)
            .fireSound(CrustyChunksModSounds.AUTOCANNONSHOT::get)
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.SMOKE)
            .muzzleParticle(ParticleTypes.LARGE_SMOKE)
            .fireRate(600)
            .projectilesPerShot(3)
            .cannonSpeed(4.5F)
            .cannonSpread(0.5F)
            .ammo(ShupapiumItems.HUGE_BULLET_AMMO_ITEM, ShupapiumItems.HUGE_HE_BULLET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile LIGHT_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("light_cannon_profile"))
            .parts(ShupapiumBlocks.CANNON_BARREL, ShupapiumBlocks.LIGHT_CANNON_BREECH)
            .fireSound(CrustyChunksModSounds.AUTOCANNONSHOT::get)
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.SMOKE)
            .muzzleParticle(ParticleTypes.SNOWFLAKE)
            .fireRate(500)
            .projectilesPerShot(1)
            .cannonSpeed(4.5F)
            .cannonSpread(0.4F)
            .ammo(ShupapiumItems.HUGE_BULLET_AMMO_ITEM, ShupapiumItems.HUGE_HE_BULLET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile HEAVY_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("heavy_cannon_profile"))
            .parts(ShupapiumBlocks.CANNON_BARREL, ShupapiumBlocks.HEAVY_CANNON_BREECH)
            .fireSound(CrustyChunksModSounds.HEAVYAUTOCANNONSHOT::get)
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.SMOKE)
            .muzzleParticle(ParticleTypes.LAVA)
            .fireRate(400)
            .projectilesPerShot(1)
            .cannonSpeed(5.0F)
            .cannonSpread(0.4F)
            .ammo(ShupapiumItems.SMALL_SHELL_AMMO_ITEM, ShupapiumItems.SMALL_AP_SHELL_AMMO_ITEM, ShupapiumItems.SMALL_FLAK_SHELL_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile BATTLE_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("battle_cannon_profile"))
            .parts(ShupapiumBlocks.BATTLE_CANNON_BARREL, ShupapiumBlocks.BATTLE_CANNON_BREECH)
            .alternateBarrels(ShupapiumBlocks.THICK_BATTLE_CANNON_BARREL)
            .fireSound(CrustyChunksModSounds.BATTLECANNON::get)
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.EXPLOSION)
            .fireRate(40)
            .projectilesPerShot(1)
            .cannonSpeed(6.0F)
            .cannonSpread(0.5F)
            .ammo(ShupapiumItems.LARGE_SHELL_AMMO_ITEM, ShupapiumItems.LARGE_SOLID_SHELL_AMMO_ITEM, ShupapiumItems.LARGE_HEAT_SHELL_AMMO_ITEM, ShupapiumItems.LARGE_AP_SHELL_AMMO_ITEM, ShupapiumItems.LARGE_FLAK_SHELL_AMMO_ITEM, ShupapiumItems.LARGE_SMOKE_SHELL_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile ARTILLERY_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("artillery_cannon_profile"))
            .parts(ShupapiumBlocks.ARTILLERY_CANNON_BARREL, ShupapiumBlocks.ARTILLERY_CANNON_BREECH)
            .fireSound(CrustyChunksModSounds.CANNONCLOSE::get)
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.SMOKE)
            .muzzleParticle(ParticleTypes.LAVA)
            .muzzleParticle(ParticleTypes.EXPLOSION)
            .fireRate(20)
            .projectilesPerShot(1)
            .cannonSpeed(6.0F)
            .cannonSpread(0.55F)
            .ammo(ShupapiumItems.ARTILLERY_SHELL_AMMO_ITEM, ShupapiumItems.ARTILLERY_SOLID_SHELL_AMMO_ITEM, ShupapiumItems.ARTILLERY_GAS_SHELL_AMMO_ITEM, ShupapiumItems.ARTILLERY_FIRE_SHELL_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile LAUNCHER_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("launcher_cannon_profile"))
            .parts(ShupapiumBlocks.LAUNCHER_BARREL, ShupapiumBlocks.LAUNCHER_BREECH)
            .fireSound(CrustyChunksModSounds.ROCKET_LAUNCH::get)
            .muzzleParticle(ParticleTypes.SNOWFLAKE)
            .fireRate(50)
            .projectilesPerShot(1)
            .cannonSpeed(2.0F)
            .cannonSpread(0.25F)
            .ammo(ShupapiumItems.MORTAR_SHELL_AMMO_ITEM, ShupapiumItems.SMOKE_MORTAR_SHELL_AMMO_ITEM, ShupapiumItems.FLARE_CHARGE_AMMO_ITEM, ShupapiumItems.CHAFF_CHARGE_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile ROCKET_POD_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("rocket_pod_profile"))
            .parts(ShupapiumBlocks.ROCKET_POD_BARREL, ShupapiumBlocks.ROCKET_POD_BREECH)
            .fireSound(CrustyChunksModSounds.ROCKET_LAUNCH::get)
            .muzzleParticle(ParticleTypes.SNOWFLAKE)
            .muzzleParticle(ParticleTypes.EXPLOSION)
            .fireRate(30)
            .projectilesPerShot(1)
            .cannonSpeed(5.0F)
            .cannonSpread(0.25F)
            .ammo(ShupapiumItems.ARMOR_PEELER_ROCKET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile LARGE_ROCKET_POD_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("large_rocket_pod_profile"))
            .parts(ShupapiumBlocks.LARGE_ROCKET_POD_BARREL, ShupapiumBlocks.LARGE_ROCKET_POD_BREECH)
            .fireSound(CrustyChunksModSounds.HEAVYLAUNCH::get)
            .muzzleParticle(ParticleTypes.LAVA)
            .muzzleParticle(ParticleTypes.EXPLOSION)
            .fireRate(25)
            .projectilesPerShot(1)
            .cannonSpeed(6.0F)
            .cannonSpread(0.25F)
            .ammo(ShupapiumItems.FIRE_SPEAR_ROCKET_AMMO_ITEM, ShupapiumItems.SEEKER_SPEAR_ROCKET_AMMO_ITEM, ShupapiumItems.RADAR_SPEAR_MISSILE_AMMO_ITEM, ShupapiumItems.STRIKE_SPEAR_MISSILE_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile FLAMETHROWER_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("flamethrower_cannon_profile"))
            .parts(ShupapiumBlocks.FLAMETHROWER_BARREL, ShupapiumBlocks.FLAMETHROWER_BREECH)
            .fireSound(CrustyChunksModSounds.WIZZ::get)
            .muzzleParticle(ParticleTypes.LAVA)
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.LARGE_SMOKE)
            .fireRate(180)
            .projectilesPerShot(5)
            .cannonSpeed(1.5F)
            .cannonSpread(0.85F)
            .ammo(ShupapiumItems.PETROL_CAN_AMMO_ITEM)
            .build();

    public static void register() {
        ShupapiumACProfileHandler.register(MINI_GUN_PROFILE);
        ShupapiumACProfileHandler.register(LIGHT_MACHINE_GUN_PROFILE);
        ShupapiumACProfileHandler.register(MACHINE_GUN_PROFILE);
        ShupapiumACProfileHandler.register(HEAVY_MACHINE_GUN_PROFILE);
        ShupapiumACProfileHandler.register(ROTARY_CANNON_PROFILE);
        ShupapiumACProfileHandler.register(LIGHT_CANNON_PROFILE);
        ShupapiumACProfileHandler.register(HEAVY_CANNON_PROFILE);
        ShupapiumACProfileHandler.register(BATTLE_CANNON_PROFILE);
        ShupapiumACProfileHandler.register(ARTILLERY_CANNON_PROFILE);
        ShupapiumACProfileHandler.register(LAUNCHER_CANNON_PROFILE);
        ShupapiumACProfileHandler.register(ROCKET_POD_PROFILE);
        ShupapiumACProfileHandler.register(LARGE_ROCKET_POD_PROFILE);
        ShupapiumACProfileHandler.register(FLAMETHROWER_CANNON_PROFILE);
    }
}
