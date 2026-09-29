package net.ato.shupapium;

import com.tterrag.registrate.util.entry.EntityEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.ato.shupapium.entities.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.multiloader.EntityTypeConfigurator;
import rbasamoyai.createbigcannons.munitions.big_cannon.BigCannonProjectileRenderer;
import rbasamoyai.createbigcannons.munitions.config.MunitionPropertiesHandler;
import rbasamoyai.createbigcannons.munitions.config.PropertiesTypeHandler;
import rbasamoyai.ritchiesprojectilelib.RPLTags;

import java.util.function.Consumer;

public class ShupapiumEntities {
    public static final DeferredRegister<EntityType<?>> SHUPAPI_ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MainShupapium.MOD_ID);

    // Mobs
    public static final DeferredHolder<EntityType<?>, EntityType<ShupapiumDummyRagdoll>> DUMMY_RAGDOLL_ENTITY = SHUPAPI_ENTITIES.register(
            "dummy_ragdoll", () -> EntityType.Builder.of(ShupapiumDummyRagdoll::new, MobCategory.CREATURE).sized(0.8F, 1.9F).build("dummy_ragdoll")
    );
    public static final DeferredHolder<EntityType<?>, EntityType<ShupapiumMetalRagdoll>> HEAVY_DUMMY_RAGDOLL_ENTITY = SHUPAPI_ENTITIES.register(
            "heavy_dummy_ragdoll", () -> EntityType.Builder.of(ShupapiumMetalRagdoll::new, MobCategory.CREATURE).sized(0.8F, 1.9F).build("heavy_dummy_ragdoll")
    );

    // Big Cannons Projectiles
    public static final EntityEntry<SmokeBombBCProjectile> SMOKE_BOMB_SHELL_PROJECTILE = cannonProjectile(
            "smoke_bomb_shell_projectile",
            SmokeBombBCProjectile::new,
            "Smoke Bomb Shell",
            CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE
    );
    public static final EntityEntry<BarrelBCProjectile> BARREL_SHELL_PROJECTILE = cannonProjectile(
            "barrel_shell_projectile",
            BarrelBCProjectile::new,
            "Explosive Barrel",
            CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE
    );
    public static final EntityEntry<ToxicBombBCProjectile> TOXIC_BOMB_SHELL_PROJECTILE = cannonProjectile(
            "toxic_bomb_shell_projectile",
            ToxicBombBCProjectile::new,
            "Toxic Bomb Shell",
            CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE
    );
    public static final EntityEntry<SmallBombBCProjectile> SMALL_BOMB_SHELL_PROJECTILE = cannonProjectile(
            "small_bomb_shell_projectile",
            SmallBombBCProjectile::new,
            "Small Bomb Shell",
            CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE
    );

    // Auxiliary Functions
    private static <T extends AbstractShupapiumBCProjectile> EntityEntry<T> cannonProjectile(String id, EntityType.EntityFactory<T> factory, PropertiesTypeHandler<EntityType<?>, ?> handler) {
        return MainShupapium.REGISTRATE
                .entity(id, factory, MobCategory.MISC)
                .properties(cannonProperties())
                .renderer(() -> BigCannonProjectileRenderer::new)
                .tag(RPLTags.PRECISE_MOTION)
                .onRegister(type -> MunitionPropertiesHandler.registerProjectileHandler(type, handler))
                .register();
    }
    private static <T extends AbstractShupapiumBCProjectile> EntityEntry<T> cannonProjectile(String id, EntityType.EntityFactory<T> factory, String enUSDiffLang, PropertiesTypeHandler<EntityType<?>, ?> handler) {
        return MainShupapium.REGISTRATE
                .entity(id, factory, MobCategory.MISC)
                .properties(cannonProperties())
                .renderer(() -> BigCannonProjectileRenderer::new)
                .lang(enUSDiffLang)
                .tag(RPLTags.PRECISE_MOTION)
                .onRegister(type -> MunitionPropertiesHandler.registerProjectileHandler(type, handler))
                .register();
    }
    private static <T> NonNullConsumer<T> cannonProperties() {
        return configure(c -> c.size(0.8f, 0.8f)
                .fireImmune()
                .updateInterval(1)
                .updateVelocity(false)
                .trackingRange(16));
    }
    private static <T> NonNullConsumer<T> configure(Consumer<EntityTypeConfigurator> cons) {
        return b -> cons.accept(EntityTypeConfigurator.of(b));
    }

    // Registries
    public static void register() {} // For CBC
    public static void register(IEventBus eventBus) {
        SHUPAPI_ENTITIES.register(eventBus);
    } // For regular mc
}
