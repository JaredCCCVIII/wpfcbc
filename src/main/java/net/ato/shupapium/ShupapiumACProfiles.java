package net.ato.shupapium;

import com.wariumce.init.CrustyChunksModSounds;
import net.ato.shupapium.utils.acprofiles.BasicShupapiumACProfile;
import net.ato.shupapium.utils.actypes.ShupapiumACProfile;
import net.ato.shupapium.utils.actypes.ShupapiumACProfileHandler;
import net.minecraft.core.particles.ParticleTypes;

public class ShupapiumACProfiles {
    public static final ShupapiumACProfile MACHINE_GUN_PROFILE = BasicShupapiumACProfile
            .builder(MainShupapium.resource("machine_gun_profile"))
            .parts(ShupapiumBlocks.MACHINE_GUN_BARREL, ShupapiumBlocks.MACHINE_GUN_BREECH)
            .alternateBarrels(ShupapiumBlocks.COVERED_MACHINE_GUN_BARREL)
            .fireSound(CrustyChunksModSounds.LARGESHOT::get)
            .muzzleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE)
            .muzzleParticle(ParticleTypes.SMOKE)
            .fireRate(400)
            .projectilesPerShot(1)
            .cannonSpeed(5.0F)
            .cannonSpread(0.02F)
            .ammo(ShupapiumItems.LARGE_BULLET_AMMO_ITEM)
            .build();

    public static void register() {
        ShupapiumACProfileHandler.register(MACHINE_GUN_PROFILE);
    }
}
