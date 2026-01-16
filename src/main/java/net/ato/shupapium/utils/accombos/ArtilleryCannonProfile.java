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

public class ArtilleryCannonProfile implements ShupapiumACProfile {
    @Override
    public ResourceLocation getProfileId() {
        return new ResourceLocation(MainShupapium.MODID, "artillery_cannon_profile");
    }

    @Override
    public ShupapiumACParts parts() {
        return new ShupapiumACParts(ShupapiumBlocks.ARTILLERY_GUN_BARREL.get(),
                ShupapiumBlocks.ARTILLERY_GUN_RECOIL_SPRING.get(),
                ShupapiumBlocks.ARTILLERY_GUN_BREECH.get());
    }

    @Override
    public SoundEvent getFireSound() {
        return CrustyChunksModSounds.CANNONCLOSE.get();
    }

    @Override
    public List<ParticleOptions> getMuzzleParticles() {
        return List.of(
                ParticleTypes.CAMPFIRE_COSY_SMOKE,
                ParticleTypes.SMOKE,
                ParticleTypes.LAVA,
                ParticleTypes.EXPLOSION
        );
    }

    @Override
    public int getCannonFireRate() {
        return 10;
    }

    @Override
    public float getProjectileBaseSpeed() {
        return 6.0F;
    }

    @Override
    public int getProjectilePerShot() {
        return 1;
    }

    @Override
    public float getProjectileSpread() {
        return 0.15F;
    }

    @Override
    public List<Item> getAmmoTypes() {
        return List.of(ShupapiumItems.ARTILLERY_HE_SHELL_AMMO_ITEM.get(),
                ShupapiumItems.ARTILLERY_GAS_SHELL_AMMO_ITEM.get(),
                ShupapiumItems.ARTILLERY_FIRE_SHELL_AMMO_ITEM.get(),
                ShupapiumItems.ARTILLERY_SOLID_SHELL_AMMO_ITEM.get());
    }

    @Override
    public AutocannonMaterial getMainMaterial() {
        return ShupapiumCBCACMaterials.ARTILLERY_GUN;
    }
}
