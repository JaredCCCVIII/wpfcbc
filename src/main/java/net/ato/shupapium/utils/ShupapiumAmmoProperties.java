package net.ato.shupapium.utils;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import rbasamoyai.createbigcannons.munitions.autocannon.config.AutocannonProjectilePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.autocannon.config.InertAutocannonProjectileProperties;
import rbasamoyai.createbigcannons.munitions.config.components.BallisticPropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.EntityDamagePropertiesComponent;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class ShupapiumAmmoProperties {
    private static final Map<ResourceLocation, InertAutocannonProjectileProperties> CACHE = new HashMap<>();
    public static InertAutocannonProjectileProperties get(ResourceLocation id) {
        return CACHE.get(id);
    }

    public static void clear() {
        CACHE.clear();
    }

    public static void load(ResourceManager manager) {
        clear();

        manager.listResources("munition_properties/projectiles",
                rl -> rl.getPath().endsWith(".json"))
                .forEach((id, resource) -> {
                    try (Reader reader = new InputStreamReader(resource.open())) {
                        JsonObject json = GsonHelper.parse(reader);
                        ResourceLocation logicalId = new ResourceLocation(id.getNamespace(), id.getPath().substring(0, id.getPath().length() - 5));
                        BallisticPropertiesComponent ballistics = BallisticPropertiesComponent.fromJson(logicalId.toString(), json);
                        EntityDamagePropertiesComponent damage =  EntityDamagePropertiesComponent.fromJson(logicalId.toString(), json);
                        AutocannonProjectilePropertiesComponent autocannon = AutocannonProjectilePropertiesComponent.fromJson(logicalId.toString(), json);
                        CACHE.put(logicalId, new InertAutocannonProjectileProperties(ballistics, damage, autocannon));
                    } catch (Exception e) {
                        throw new RuntimeException("Failed to load ammo json " + id, e);
                    }
                });
    }
}
