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
import net.minecraft.world.item.Item;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

import java.util.List;

public class MortarCannonProfile implements ShupapiumACProfile {
    @Override
    public ResourceLocation getProfileId() {
        return ResourceLocation.fromNamespaceAndPath(MainShupapium.MODID, "mortar_cannon_profile");
    }

    @Override
    public ShupapiumACParts parts() {
        return new ShupapiumACParts(ShupapiumBlocks.MORTAR_GUN_BARREL.get(),
                ShupapiumBlocks.MORTAR_GUN_RECOIL_SPRING.get(),
                ShupapiumBlocks.MORTAR_GUN_BREECH.get());
    }

    @Override
    public SoundEvent getFireSound() {
        return CrustyChunksModSounds.MIDRANGESHOT.get();
    }

    @Override
    public List<ParticleOptions> getMuzzleParticles() {
        return List.of(
                ParticleTypes.SMALL_FLAME,
                ParticleTypes.SMOKE
        );
    }

    @Override
    public int getCannonFireRate() {
        return 45;
    }

    @Override
    public int getProjectilePerShot() {
        return 1;
    }

    @Override
    public float getProjectileBaseSpeed() {
        return 6.0F;
    }

    @Override
    public float getProjectileSpread() {
        return 0.01F;
    }

    @Override
    public List<Item> getAmmoTypes() {
        return List.of(ShupapiumItems.MORTAR_SHELL_AMMO_ITEM.get(), ShupapiumItems.SMOKE_SHELL_AMMO_ITEM.get());
    }

    @Override
    public AutocannonMaterial getMainMaterial() {
        return ShupapiumCBCACMaterials.MORTAR_GUN;
    }
}
