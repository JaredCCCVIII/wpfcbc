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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

import java.util.List;

public class BigBattleCannonProfile implements ShupapiumACProfile {
    @Override
    public ResourceLocation getProfileId() {
        return ResourceLocation.fromNamespaceAndPath(MainShupapium.MODID, "big_battle_cannon_profile");
    }

    @Override
    public ShupapiumACParts parts() {
        return new ShupapiumACParts(ShupapiumBlocks.THICK_BATTLE_GUN_BARREL.get(),
                ShupapiumBlocks.BATTLE_GUN_RECOIL_SPRING.get(),
                ShupapiumBlocks.BATTLE_GUN_BREECH.get());
    }

    @Override
    public SoundEvent getFireSound() {
        return CrustyChunksModSounds.SMALLEXPLOSION.get();
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
        return 20;
    }

    @Override
    public float getProjectileBaseSpeed() {
        return 8.6F;
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
    public void cannonSoundEvent(Level level, Vec3 pos) {
        level.playSound(null, pos.x, pos.y, pos.z, getFireSound(), SoundSource.BLOCKS, Mth.nextFloat(level.random, 10.0F, 12.0F), Mth.nextFloat(level.random, 0.45F, 0.85F));
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
