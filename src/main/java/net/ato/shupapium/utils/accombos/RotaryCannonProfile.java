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
import net.minecraft.world.item.ItemStack;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

import java.util.List;

public class RotaryCannonProfile implements ShupapiumACProfile {
    @Override
    public ResourceLocation getProfileId() {
        return ResourceLocation.fromNamespaceAndPath(MainShupapium.MODID, "rotary_cannon_profile");
    }

    @Override
    public ShupapiumACParts parts() {
        return new ShupapiumACParts(
                ShupapiumBlocks.ROTATORY_CANNON_BARREL.get(),
                ShupapiumBlocks.ROTATORY_CANNON_RECOIL_SPRING.get(),
                ShupapiumBlocks.CANNON_GUN_BREECH.get()
        );
    }

    @Override
    public SoundEvent getFireSound() {
        return CrustyChunksModSounds.RAC.get();
    }

    @Override
    public List<ParticleOptions> getMuzzleParticles() {
        return List.of(
                ParticleTypes.CAMPFIRE_COSY_SMOKE,
                ParticleTypes.SMOKE,
                ParticleTypes.LARGE_SMOKE
        );
    }

    @Override
    public int getCannonFireRate() {
        return 400;
    }

    @Override
    public float getProjectileBaseSpeed() {
        return 8.0F;
    }

    @Override
    public int getProjectilePerShot() {
        return 1;
    }

    @Override
    public float getProjectileSpread() {
        return 0.1F;
    }

    @Override
    public List<Item> getAmmoTypes() {
        return List.of(ShupapiumItems.SMALL_HE_SHELL_AMMO_ITEM.get(), ShupapiumItems.SMALL_SHELL_AMMO_ITEM.get());
    }

    @Override
    public AutocannonMaterial getMainMaterial() {
        return ShupapiumCBCACMaterials.CANNON_GUN;
    }
}
