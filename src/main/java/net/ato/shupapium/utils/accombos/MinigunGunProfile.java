package net.ato.shupapium.utils.accombos;

import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumBlocks;
import net.ato.shupapium.ShupapiumCBCACMaterials;
import net.ato.shupapium.ShupapiumItems;
import net.ato.shupapium.utils.actypes.ShupapiumACParts;
import net.ato.shupapium.utils.actypes.ShupapiumACProfile;
import net.mcreator.crustychunks.init.CrustyChunksModSounds;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

import java.util.List;

public class MinigunGunProfile implements ShupapiumACProfile {
    private static final ShupapiumACParts PARTS = new ShupapiumACParts(
            ShupapiumBlocks.MINIGUN_BARREL.get(),
            ShupapiumBlocks.MINIGUN_RECOIL_SPRING.get(),
            ShupapiumBlocks.MACHINE_GUN_BREECH.get()
    );

    @Override
    public ResourceLocation getProfileId() {
        return ResourceLocation.fromNamespaceAndPath(MainShupapium.MODID, "minigun_profile");
    }

    @Override
    public ShupapiumACParts parts() {
        return PARTS;
    }

    @Override
    public SoundEvent getFireSound() {
        return CrustyChunksModSounds.LARGESHOT.get();
    }

    @Override
    public List<ParticleOptions> getMuzzleParticles() {
        return List.of(
                ParticleTypes.LARGE_SMOKE
        );
    }

    @Override
    public int getCannonFireRate() {
        return 675;
    }

    @Override
    public float getProjectileBaseSpeed() {
        return 16.4F;
    }

    @Override
    public int getProjectilePerShot() {
        return 1;
    }

    @Override
    public float getProjectileSpread() {
        return 0.035F;
    }

    @Override
    public List<Item> getAmmoTypes() {
        return List.of(ShupapiumItems.LARGE_BULLET_AMMO_ITEM.get());
    }

    @Override
    public AutocannonMaterial getMainMaterial() {
        return ShupapiumCBCACMaterials.MACHINE_GUN;
    }

    @Override
    public void cannonSoundEvent(Level level, Vec3 pos) {
        SoundEvent rSnd;
        if (level.random.nextBoolean()) {
            rSnd = getFireSound();
        } else {
            rSnd = CrustyChunksModSounds.RAC.get();
        }
        level.playSound(null, pos.x, pos.y, pos.z, rSnd, SoundSource.BLOCKS, Mth.nextFloat(level.random, 10.0F, 12.0F), Mth.nextFloat(level.random, 0.75F, 1.1F));
    }
}
