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
            .fireSound(CrustyChunksModSounds.BATTLECANNON::get)
            .muzzleParticle(ParticleTypes.EXPLOSION)
            .fireRate(40)
            .projectilesPerShot(1)
            .cannonSpeed(7.0F)
            .cannonSpread(0.3F)
            .ammo(ShupapiumItems.LARGE_BULLET_AMMO_ITEM)
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
    }
}
