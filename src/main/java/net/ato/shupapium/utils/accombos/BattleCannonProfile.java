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

public class BattleCannonProfile implements ShupapiumACProfile {
    @Override
    public ResourceLocation getProfileId() {
        return ResourceLocation.fromNamespaceAndPath(MainShupapium.MODID, "battle_cannon_profile");
    }

    @Override
    public ShupapiumACParts parts() {
        return new ShupapiumACParts(ShupapiumBlocks.BATTLE_GUN_BARREL.get(),
                ShupapiumBlocks.BATTLE_GUN_RECOIL_SPRING.get(),
                ShupapiumBlocks.BATTLE_GUN_BREECH.get());
    }

    @Override
    public SoundEvent getFireSound() {
        return CrustyChunksModSounds.BATTLECANNON.get();
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
        return 30;
    }

    @Override
    public float getProjectileBaseSpeed() {
        return 22.1F;
    }

    @Override
    public int getProjectilePerShot() {
        return 1;
    }

    @Override
    public float getProjectileSpread() {
        return 0.06F;
    }

    @Override
    public List<Item> getAmmoTypes() {
        return List.of(ShupapiumItems.LARGE_SOLID_SHELL_AMMO_ITEM.get(),
                ShupapiumItems.LARGE_HE_SHELL_AMMO_ITEM.get(),
                ShupapiumItems.LARGE_HEAT_SHELL_AMMO_ITEM.get(),
                ShupapiumItems.LARGE_AP_SHELL_AMMO_ITEM.get(),
                ShupapiumItems.LARGE_FLAK_SHELL_AMMO_ITEM.get(),
                ShupapiumItems.LARGE_SMOKE_SHELL_AMMO_ITEM.get());
    }

    @Override
    public AutocannonMaterial getMainMaterial() {
        return ShupapiumCBCACMaterials.BATTLE_GUN;
    }
}
