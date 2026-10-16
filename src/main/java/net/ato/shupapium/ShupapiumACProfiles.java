package net.ato.shupapium;

import com.wariumce.init.CrustyChunksModParticleTypes;
import net.ato.shupapium.utils.acballistics.ShupapiumFireSound;
import net.ato.shupapium.utils.acballistics.ShupapiumFireSoundsPresets;
import net.ato.shupapium.utils.acprofiles.BasicShupapiumACProfile;
import net.ato.shupapium.utils.actypes.ShupapiumACProfile;
import net.ato.shupapium.utils.actypes.ShupapiumACProfileHandler;
import net.minecraft.core.particles.ParticleTypes;

public class ShupapiumACProfiles {
    public static final ShupapiumACProfile MINI_GUN_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("mini_gun_profile"))
            .parts(ShupapiumBlocks.MINIGUN_BARREL, ShupapiumBlocks.MINIGUN_BREECH)
            .sound(ShupapiumFireSound.byId("wariumce:brtttfar", 80F, 1.2F, 1.4F))
            .sound(ShupapiumFireSound.byId("wariumce:distantshot", 80F, 0.9F, 1.1F))
            .sound(ShupapiumFireSound.byId("wariumce:rac", 10F, 1.4F, 1.5F))
            .sound(ShupapiumFireSound.byId("wariumce:largeshot", 10F, 0.9F, 1.1F))
            .muzzleParticle(ParticleTypes.SMOKE)
            .muzzleParticle(ParticleTypes.WHITE_SMOKE)
            .fireRate(450)
            .projectilesPerShot(1)
            .cannonSpeed(9.0F)
            .cannonSpread(0.0211F)
            .ammo(ShupapiumItems.LARGE_BULLET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile LIGHT_MACHINE_GUN_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("light_machine_gun_profile"))
            .parts(ShupapiumBlocks.MACHINE_GUN_BARREL, ShupapiumBlocks.LIGHT_MACHINE_GUN_BREECH)
            .alternateBarrels(ShupapiumBlocks.COVERED_MACHINE_GUN_BARREL)
            .sounds(ShupapiumFireSoundsPresets.MEDIUM_RIFLE)
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.SMOKE)
            .fireRate(300)
            .projectilesPerShot(1)
            .cannonSpeed(7.0F)
            .cannonSpread(0.005F)
            .ammo(ShupapiumItems.MEDIUM_BULLET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile MACHINE_GUN_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("machine_gun_profile"))
            .parts(ShupapiumBlocks.MACHINE_GUN_BARREL, ShupapiumBlocks.MACHINE_GUN_BREECH)
            .alternateBarrels(ShupapiumBlocks.COVERED_MACHINE_GUN_BARREL)
            .sounds(ShupapiumFireSoundsPresets.LARGE_RIFLE)
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.SMOKE)
            .fireRate(400)
            .projectilesPerShot(1)
            .cannonSpeed(8.0F)
            .cannonSpread(0.01F)
            .ammo(ShupapiumItems.LARGE_BULLET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile HEAVY_MACHINE_GUN_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("heavy_machine_gun_profile"))
            .parts(ShupapiumBlocks.MACHINE_GUN_BARREL, ShupapiumBlocks.HEAVY_MACHINE_GUN_BREECH)
            .alternateBarrels(ShupapiumBlocks.COVERED_MACHINE_GUN_BARREL)
            .sound(ShupapiumFireSound.byId("wariumce:autocannonshot", 20F, 0.9F, 1.1F))
            .sound(ShupapiumFireSound.byId("wariumce:distantshotmedium", 60F, 0.7F, 0.8F))
            .sound(ShupapiumFireSound.byId("wariumce:largeshot", 15F, 0.7F, 0.8F))
            .muzzleParticle(ParticleTypes.SMOKE)
            .fireRate(500)
            .projectilesPerShot(1)
            .cannonSpeed(9.0F)
            .cannonSpread(0.0164F)
            .ammo(ShupapiumItems.EXTRA_LARGE_BULLET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile ROTARY_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("rotary_cannon_profile"))
            .parts(ShupapiumBlocks.ROTARY_CANNON_BARREL, ShupapiumBlocks.ROTARY_CANNON_BREECH)
            .sound(ShupapiumFireSound.byId("wariumce:brtttfar", 60F, 1.2F, 1.4F))
            .sound(ShupapiumFireSound.byId("wariumce:rac", 20F, 0.95F, 1.05F))
            .sound(ShupapiumFireSound.byId("wariumce:autocannonshot", 8F, 1.5F, 1.6F))
            .muzzleParticle(ParticleTypes.SMOKE)
            .fireRate(250)
            .projectilesPerShot(5)
            .cannonSpeed(10.0F)
            .cannonSpread(0.0938F)
            .ammo(ShupapiumItems.HUGE_BULLET_AMMO_ITEM, ShupapiumItems.HUGE_HE_BULLET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile LIGHT_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("light_cannon_profile"))
            .parts(ShupapiumBlocks.CANNON_BARREL, ShupapiumBlocks.LIGHT_CANNON_BREECH)
            .sound(ShupapiumFireSound.byId("wariumce:medium_small_explosion_distant", 80F, 0.9F, 1.1F))
            .sound(ShupapiumFireSound.byId("wariumce:autocannonshot", 20F, 0.9F, 1.1F))
            .muzzleParticle(ParticleTypes.SMOKE)
            .fireRate(200)
            .projectilesPerShot(1)
            .cannonSpeed(9.5F)
            .cannonSpread(0.0938F)
            .ammo(ShupapiumItems.HUGE_BULLET_AMMO_ITEM, ShupapiumItems.HUGE_HE_BULLET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile HEAVY_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("heavy_cannon_profile"))
            .parts(ShupapiumBlocks.CANNON_BARREL, ShupapiumBlocks.HEAVY_CANNON_BREECH)
            .sound(ShupapiumFireSound.byId("wariumce:smallfarblast", 80F, 0.95F, 1.05F))
            .sound(ShupapiumFireSound.byId("wariumce:cannonfar", 24F, 1.3F, 1.4F))
            .sound(ShupapiumFireSound.byId("wariumce:heavyautocannonshot", 15F, 0.95F, 1.05F))
            .muzzleParticle(ParticleTypes.LAVA)
            .muzzleParticle(ParticleTypes.SMOKE)
            .fireRate(100)
            .projectilesPerShot(1)
            .cannonSpeed(11.0F)
            .cannonSpread(0.0938F)
            .ammo(ShupapiumItems.SMALL_SHELL_AMMO_ITEM, ShupapiumItems.SMALL_AP_SHELL_AMMO_ITEM, ShupapiumItems.SMALL_FLAK_SHELL_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile BATTLE_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("battle_cannon_profile"))
            .parts(ShupapiumBlocks.BATTLE_CANNON_BARREL, ShupapiumBlocks.BATTLE_CANNON_BREECH)
            .alternateBarrels(ShupapiumBlocks.THICK_BATTLE_CANNON_BARREL)
            .sound(ShupapiumFireSound.byId("wariumce:battlecannon", 15F, 0.9F, 1.1F))
            .sound(ShupapiumFireSound.byId("wariumce:cannonfar", 60F, 1.2F, 1.3F))
            .sound(ShupapiumFireSound.byId("wariumce:farblast", 120F, 1.2F, 1.3F))
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.EXPLOSION)
            .fireRate(30)
            .projectilesPerShot(1)
            .cannonSpeed(12.0F)
            .cannonSpread(0.0938F)
            .ammo(ShupapiumItems.LARGE_SHELL_AMMO_ITEM, ShupapiumItems.LARGE_SOLID_SHELL_AMMO_ITEM, ShupapiumItems.LARGE_HEAT_SHELL_AMMO_ITEM, ShupapiumItems.LARGE_AP_SHELL_AMMO_ITEM, ShupapiumItems.LARGE_FLAK_SHELL_AMMO_ITEM, ShupapiumItems.LARGE_SMOKE_SHELL_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile ARTILLERY_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("artillery_cannon_profile"))
            .parts(ShupapiumBlocks.ARTILLERY_CANNON_BARREL, ShupapiumBlocks.ARTILLERY_CANNON_BREECH)
            .sound(ShupapiumFireSound.byId("wariumce:farblast", 120F, 0.9F, 0.9F))
            .sound(ShupapiumFireSound.byId("wariumce:cannonfar", 60F, 0.9F, 0.9F))
            .sound(ShupapiumFireSound.byId("wariumce:cannonclose", 20F, 0.8F, 0.8F))
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.SMOKE)
            .muzzleParticle(ParticleTypes.LAVA)
            .muzzleParticle(ParticleTypes.EXPLOSION)
            .fireRate(20)
            .projectilesPerShot(1)
            .cannonSpeed(13.0F)
            .cannonSpread(0.1563F)
            .ammo(ShupapiumItems.ARTILLERY_SHELL_AMMO_ITEM, ShupapiumItems.ARTILLERY_SOLID_SHELL_AMMO_ITEM, ShupapiumItems.ARTILLERY_GAS_SHELL_AMMO_ITEM, ShupapiumItems.ARTILLERY_FIRE_SHELL_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile LAUNCHER_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("launcher_cannon_profile"))
            .parts(ShupapiumBlocks.LAUNCHER_BARREL, ShupapiumBlocks.LAUNCHER_BREECH)
            .sound(ShupapiumFireSound.byId("wariumce:explosion", 20F, 1.5F, 1.5F))
            .sound(ShupapiumFireSound.byId("wariumce:explosion_distant", 100F, 1.5F, 1.5F))
            .muzzleParticle(ParticleTypes.SMOKE)
            .muzzleParticle(ParticleTypes.FLASH)
            .fireRate(40)
            .projectilesPerShot(1)
            .cannonSpeed(10.5F)
            .cannonSpread(0.1172F)
            .ammo(ShupapiumItems.MORTAR_SHELL_AMMO_ITEM, ShupapiumItems.SMOKE_MORTAR_SHELL_AMMO_ITEM, ShupapiumItems.FLARE_CHARGE_AMMO_ITEM, ShupapiumItems.CHAFF_CHARGE_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile ROCKET_POD_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("rocket_pod_profile"))
            .parts(ShupapiumBlocks.ROCKET_POD_BARREL, ShupapiumBlocks.ROCKET_POD_BREECH)
            .sound(ShupapiumFireSound.byId("wariumce:peelerpod", 10F, 0.9F, 1.1F))
            .sound(ShupapiumFireSound.byId("wariumce:peelerpodfar", 80F, 0.9F, 1.1F))
            .muzzleParticle(CrustyChunksModParticleTypes.PUFF.get())
            .fireRate(60)
            .projectilesPerShot(1)
            .cannonSpeed(9.5F)
            .cannonSpread(0.0469F)
            .ammo(ShupapiumItems.ARMOR_PEELER_ROCKET_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile LARGE_ROCKET_POD_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("large_rocket_pod_profile"))
            .parts(ShupapiumBlocks.LARGE_ROCKET_POD_BARREL, ShupapiumBlocks.LARGE_ROCKET_POD_BREECH)
            .sound(ShupapiumFireSound.byId("wariumce:peelerpod", 10F, 0.8F, 0.9F))
            .sound(ShupapiumFireSound.byId("wariumce:peelerpodfar", 80F, 0.8F, 0.9F))
            .sound(ShupapiumFireSound.byId("wariumce:heavylaunch", 10F, 1.2F, 1.4F))
            .muzzleParticle(CrustyChunksModParticleTypes.PUFF.get())
            .fireRate(50)
            .projectilesPerShot(1)
            .cannonSpeed(11.5F)
            .cannonSpread(0.0469F)
            .ammo(ShupapiumItems.FIRE_SPEAR_ROCKET_AMMO_ITEM, ShupapiumItems.SEEKER_SPEAR_ROCKET_AMMO_ITEM, ShupapiumItems.RADAR_SPEAR_MISSILE_AMMO_ITEM, ShupapiumItems.STRIKE_SPEAR_MISSILE_AMMO_ITEM)
            .build();
    public static final ShupapiumACProfile FLAMETHROWER_CANNON_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("flamethrower_cannon_profile"))
            .parts(ShupapiumBlocks.FLAMETHROWER_BARREL, ShupapiumBlocks.FLAMETHROWER_BREECH)
            .alternateBarrels(ShupapiumBlocks.COVERED_FLAMETHROWER_BARREL)
            .sound(ShupapiumFireSound.byId("minecraft:item.firecharge.use", 10F, 0.9F, 1.1F))
            .muzzleParticle(ParticleTypes.LAVA)
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.LARGE_SMOKE)
            .fireRate(1000)
            .projectilesPerShot(3)
            .cannonSpeed(2.8F)
            .cannonSpread(0.211F)
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
