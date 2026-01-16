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

public class SilencedLMGProfile implements ShupapiumACProfile {
    @Override
    public ResourceLocation getProfileId() {
        return new ResourceLocation(MainShupapium.MODID, "silenced_light_machine_gun_profile");
    }

    @Override
    public ShupapiumACParts parts() {
        return new ShupapiumACParts(
                ShupapiumBlocks.COVERED_GUN_BARREL.get(),
                ShupapiumBlocks.LIGHT_GUN_RECOIL_SPRING.get(),
                ShupapiumBlocks.MACHINE_GUN_BREECH.get()
        );
    }

    @Override
    public SoundEvent getFireSound() {
        return CrustyChunksModSounds.SILENCEDSHOT.get();
    }

    @Override
    public List<ParticleOptions> getMuzzleParticles() {
        return List.of(
                ParticleTypes.SMOKE
        );
    }

    @Override
    public int getCannonFireRate() {
        return 200;
    }

    @Override
    public float getProjectileBaseSpeed() {
        return 7.0F;
    }

    @Override
    public int getProjectilePerShot() {
        return 1;
    }

    @Override
    public float getProjectileSpread() {
        return 0.01F;
    }

    @Override
    public List<Item> getAmmoTypes() {
        return List.of(ShupapiumItems.MEDIUM_BULLET_AMMO_ITEM.get());
    }

    @Override
    public AutocannonMaterial getMainMaterial() {
        return ShupapiumCBCACMaterials.MACHINE_GUN;
    }
}
