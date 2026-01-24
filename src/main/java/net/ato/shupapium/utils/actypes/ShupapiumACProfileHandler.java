package net.ato.shupapium.utils.actypes;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.utils.accombos.JsonCannonProfile;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;

import java.util.*;

public class ShupapiumACProfileHandler {
    private static final Map<ResourceLocation, ShupapiumACProfile> PROFILES = new HashMap<>();

    public static void clear() {
        PROFILES.clear();
    }

    public static void clearJsons() {
        PROFILES.entrySet().removeIf(e -> e.getValue() instanceof JsonCannonProfile);
    }

    public static void register(ShupapiumACProfile profile) {
        if (PROFILES.containsKey(profile.getProfileId())) {
            applyOverride((JsonCannonProfile) profile);
            return;
        }
        if (PROFILES.containsValue(profile)) {
            MainShupapium.LOGGER.warn("{} already registered", profile.getProfileId());
            return;
        }
        PROFILES.put(profile.getProfileId(), profile);
    }

    public static ShupapiumACProfile getProfile(ResourceLocation id) {
        return PROFILES.get(id);
    }

    public static ShupapiumACProfile getProfile(ShupapiumACParts acParts) {
        for (ShupapiumACProfile profile : PROFILES.values()) {
            if (profile.parts().equals(acParts)) {
                return profile;
            }
        }
        return null;
    }

    public static void applyOverride(JsonCannonProfile override) {
        ShupapiumACProfile base = PROFILES.get(override.getProfileId());
        if (base == null) {
            MainShupapium.LOGGER.warn("No profile found with id {}", override.getProfileId());
            return;
        }

        PROFILES.put(base.getProfileId(), new ShupapiumACProfile() {
            @Override
            public ResourceLocation getProfileId() {
                return override.getProfileId() != null ? override.getProfileId() : base.getProfileId();
            }

            @Override
            public ShupapiumACParts parts() {
                return override.parts() != null ? override.parts() : base.parts();
            }

            @Override
            public SoundEvent getFireSound() {
                return override.getFireSound() != null ? override.getFireSound() : base.getFireSound();
            }

            @Override
            public List<ParticleOptions> getMuzzleParticles() {
                return override.getMuzzleParticles() != null ? override.getMuzzleParticles() : base.getMuzzleParticles();
            }

            @Override
            public int getCannonFireRate() {
                return override.getCannonFireRate() > 0 ? override.getCannonFireRate() : base.getCannonFireRate();
            }

            @Override
            public int getProjectilePerShot() {
                return override.getProjectilePerShot() > 0 ? override.getProjectilePerShot() : base.getProjectilePerShot();
            }

            @Override
            public float getProjectileBaseSpeed() {
                return override.getProjectileBaseSpeed() > 0 ? override.getProjectileBaseSpeed() : base.getProjectileBaseSpeed();
            }

            @Override
            public float getProjectileSpread() {
                return override.getProjectileSpread() > 0 ? override.getProjectileSpread() : base.getProjectileSpread();
            }

            @Override
            public List<Item> getAmmoTypes() {
                return override.getAmmoTypes() != null ? override.getAmmoTypes() : base.getAmmoTypes();
            }

            @Override
            public AutocannonMaterial getMainMaterial() {
                return override.getMainMaterial() != null ? override.getMainMaterial() : base.getMainMaterial();
            }

            @Override
            public void cannonSoundEvent(Level level, Vec3 pos) {
                base.cannonSoundEvent(level, pos);
            }

            @Override
            public void cannonMuzzleEvent(ServerLevel level, Vec3 pos, Vec3 vel) {
                base.cannonMuzzleEvent(level, pos, vel);
            }
        });
    }

    public static ShupapiumACProfile fromJson(ResourceLocation id, JsonObject json) {
        JsonObject partsJson = GsonHelper.getAsJsonObject(json, "parts");
        ShupapiumACParts parts = new ShupapiumACParts(
               ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(GsonHelper.getAsString(partsJson, "barrel"))),
                ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(GsonHelper.getAsString(partsJson, "chamber"))),
                ForgeRegistries.BLOCKS.getValue(ResourceLocation.parse(GsonHelper.getAsString(partsJson, "breech")))
        );
        SoundEvent fireSoundId = ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse(GsonHelper.getAsString(json, "fire_sound")));
        List<ParticleOptions> muzzleParticles = new ArrayList<>();
        for (JsonElement e : GsonHelper.getAsJsonArray(json, "muzzle_particles")) {
            muzzleParticles.add((ParticleOptions) ForgeRegistries.PARTICLE_TYPES.getValue(ResourceLocation.parse(e.getAsString())));
        }
        int fireRate = GsonHelper.getAsInt(json, "fire_rate");
        int projectilesPerShot = GsonHelper.getAsInt(json, "projectiles_per_shot");
        float baseSpeed = GsonHelper.getAsFloat(json, "projectile_base_speed");
        float baseSpread = GsonHelper.getAsFloat(json, "projectile_base_spread");
        List<Item> ammoTypes = new ArrayList<>();
        for (JsonElement e : GsonHelper.getAsJsonArray(json, "ammo_types")) {
            ammoTypes.add(ForgeRegistries.ITEMS.getValue(ResourceLocation.parse(e.getAsString())));
        }
        AutocannonMaterial autocannonMaterial = AutocannonMaterial.fromNameOrNull(ResourceLocation.parse(GsonHelper.getAsString(json, "main_material")));
        return new JsonCannonProfile(id, parts, fireSoundId, muzzleParticles, fireRate, projectilesPerShot, baseSpeed, baseSpread, ammoTypes, autocannonMaterial);
    }
}
