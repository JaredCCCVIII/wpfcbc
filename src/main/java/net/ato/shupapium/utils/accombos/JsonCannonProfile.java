package net.ato.shupapium.utils.accombos;

import net.ato.shupapium.utils.actypes.ShupapiumACParts;
import net.ato.shupapium.utils.actypes.ShupapiumACProfile;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

import java.util.List;

public class JsonCannonProfile implements ShupapiumACProfile {
    private final ResourceLocation id;
    private final ShupapiumACParts parts;
    private final SoundEvent fireSound;
    private final List<ParticleOptions> muzzleParticles;
    private final int rateFire;
    private final int projectilesPerShot;
    private final float baseSpeed;
    private final float baseSpread;
    private final List<Item> ammoTypes;
    private final AutocannonMaterial autocannonMaterial;
    public JsonCannonProfile(ResourceLocation id, ShupapiumACParts parts, SoundEvent fireSound, List<ParticleOptions> muzzleParticles, int fireRate, int projectilesPerShot, float baseSpeed, float baseSpread, List<Item> ammoTypes, AutocannonMaterial autocannonMaterial) {
        this.id = id;
        this.parts = parts;
        this.fireSound = fireSound;
        this.muzzleParticles = muzzleParticles;
        this.rateFire = fireRate;
        this.projectilesPerShot = projectilesPerShot;
        this.baseSpeed = baseSpeed;
        this.baseSpread = baseSpread;
        this.ammoTypes = ammoTypes;
        this.autocannonMaterial = autocannonMaterial;
    }

    @Override
    public ResourceLocation getProfileId() {
        return this.id;
    }

    @Override
    public ShupapiumACParts parts() {
        return this.parts;
    }

    @Override
    public SoundEvent getFireSound() {
        return this.fireSound;
    }

    @Override
    public List<ParticleOptions> getMuzzleParticles() {
        return this.muzzleParticles;
    }

    @Override
    public int getCannonFireRate() {
        return this.rateFire;
    }

    @Override
    public int getProjectilePerShot() {
        return this.projectilesPerShot;
    }

    @Override
    public float getProjectileBaseSpeed() {
        return this.baseSpeed;
    }

    @Override
    public float getProjectileSpread() {
        return this.baseSpread;
    }

    @Override
    public List<Item> getAmmoTypes() {
        return this.ammoTypes;
    }

    @Override
    public AutocannonMaterial getMainMaterial() {
        return this.autocannonMaterial;
    }
}
