package net.ato.shupapium;

import com.tterrag.registrate.util.entry.EntityEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.ato.shupapium.blockentities.*;
import net.ato.shupapium.entities.ShupapiumDummyRagdoll;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.multiloader.EntityTypeConfigurator;
import rbasamoyai.createbigcannons.munitions.autocannon.AbstractAutocannonProjectile;
import rbasamoyai.createbigcannons.munitions.autocannon.AutocannonProjectileRenderer;
import rbasamoyai.createbigcannons.munitions.big_cannon.AbstractBigCannonProjectile;
import rbasamoyai.createbigcannons.munitions.big_cannon.BigCannonProjectileRenderer;
import rbasamoyai.createbigcannons.munitions.config.MunitionPropertiesHandler;
import rbasamoyai.createbigcannons.munitions.config.PropertiesTypeHandler;
import rbasamoyai.ritchiesprojectilelib.RPLTags;

import java.util.function.Consumer;

public class ShupapiumEntities {
    public static final DeferredRegister<EntityType<?>> SHUPAPI_ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MainShupapium.MODID);
    public static final RegistryObject<EntityType<ShupapiumDummyRagdoll>> DUMMY_RAGDOLL_ENTITY = SHUPAPI_ENTITIES.register(
            "dummy_ragdoll", () -> EntityType.Builder.of(ShupapiumDummyRagdoll::new, MobCategory.CREATURE).sized(0.8F, 1.9F).build("dummy_ragdoll")
    );

    public static final EntityEntry<GasBombShellProjectile> GAS_BOMB_SHELL_PROJECTILE = cannonProjectile("gas_bomb_shell_projectile", GasBombShellProjectile::new, "Gas Bomb Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<SmallBombShellProjectile> SMALL_BOMB_SHELL_PROJECTILE = cannonProjectile("small_bomb_shell_projectile", SmallBombShellProjectile::new, "Small Bomb Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<SmallBombClusterShellProjectile> SMALL_BOMB_CLUSTER_SHELL_PROJECTILE = cannonProjectile("small_bomb_cluster_shell_projectile", SmallBombClusterShellProjectile::new, "Small Bomb Cluster Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<MediumBombShellProjectile> MEDIUM_BOMB_SHELL_PROJECTILE = cannonProjectile("medium_bomb_shell_projectile", MediumBombShellProjectile::new, "Medium Bomb Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<FireBombShellProjectile> FIRE_BOMB_SHELL_PROJECTILE = cannonProjectile("fire_bomb_shell_projectile", FireBombShellProjectile::new, "Fire Bomb Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<KineticBunkerBusterShellProjectile> KINETIC_BUNKER_BUSTER_SHELL_PROJECTILE = cannonProjectile("kinetic_bunker_buster_shell_projectile", KineticBunkerBusterShellProjectile::new, "Kinetic Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<IRSeekerMissileShellProjectile> IR_SEEKER_MISSILE_SHELL_PROJECTILE = cannonProjectile("ir_seeker_missile_shell_projectile", IRSeekerMissileShellProjectile::new, "IR Seeker Missile Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<SARHSeekerShellProjectile> SARH_SEEKER_SHELL_PROJECTILE = cannonProjectile("sarh_seeker_shell_projectile", SARHSeekerShellProjectile::new, "SARH Seeker Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<ClusterRocketShellProjectile> CLUSTER_ROCKET_SHELL_PROJECTILE = cannonProjectile("cluster_rocket_shell_projectile", ClusterRocketShellProjectile::new, "Cluster Rocket Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<HeavyBombShellProjectile> HEAVY_BOMB_SHELL_PROJECTILE = cannonProjectile("heavy_bomb_shell_projectile", HeavyBombShellProjectile::new, "Heavy Bomb Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<SuperHeavyBombShellProjectile> SUPER_HEAVY_BOMB_SHELL_PROJECTILE = cannonProjectile("super_heavy_bomb_shell_projectile", SuperHeavyBombShellProjectile::new, "Super Heavy Bomb Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<BlockBusterShellProjectile> BLOCK_BUSTER_SHELL_PROJECTILE = cannonProjectile("block_buster_shell_projectile", BlockBusterShellProjectile::new, "Block Buster Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<FissionBombShellProjectile> FISSION_BOMB_SHELL_PROJECTILE = cannonProjectile("fission_bomb_shell_projectile", FissionBombShellProjectile::new, "Fission Bomb Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<FusionBombShellProjectile> FUSION_BOMB_SHELL_PROJECTILE = cannonProjectile("fusion_bomb_shell_projectile", FusionBombShellProjectile::new, "Fusion Bomb Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);

    private static <T extends AbstractAutocannonProjectile> EntityEntry<T>
    autocannonProjectile(String id, EntityType.EntityFactory<T> factory, PropertiesTypeHandler<EntityType<?>, ?> handler) {
        return MainShupapium.REGISTRATE
                .entity(id, factory, MobCategory.MISC)
                .properties(autocannonProperties())
                .renderer(() -> AutocannonProjectileRenderer::new)
                .tag(RPLTags.PRECISE_MOTION)
                .onRegister(type -> MunitionPropertiesHandler.registerProjectileHandler(type, handler))
                .register();
    }

    private static <T extends AbstractAutocannonProjectile> EntityEntry<T>
    autocannonProjectile(String id, EntityType.EntityFactory<T> factory, String enUSdiffLang, PropertiesTypeHandler<EntityType<?>, ?> handler) {
        return MainShupapium.REGISTRATE
                .entity(id, factory, MobCategory.MISC)
                .properties(autocannonProperties())
                .renderer(() -> AutocannonProjectileRenderer::new)
                .lang(enUSdiffLang)
                .tag(RPLTags.PRECISE_MOTION)
                .onRegister(type -> MunitionPropertiesHandler.registerProjectileHandler(type, handler))
                .register();
    }

    private static <T extends AbstractBigCannonProjectile> EntityEntry<T>
    cannonProjectile(String id, EntityType.EntityFactory<T> factory, PropertiesTypeHandler<EntityType<?>, ?> handler) {
        return MainShupapium.REGISTRATE
                .entity(id, factory, MobCategory.MISC)
                .properties(cannonProperties())
                .renderer(() -> BigCannonProjectileRenderer::new)
                .tag(RPLTags.PRECISE_MOTION)
                .onRegister(type -> MunitionPropertiesHandler.registerProjectileHandler(type, handler))
                .register();
    }

    private static <T extends AbstractBigCannonProjectile> EntityEntry<T>
    cannonProjectile(String id, EntityType.EntityFactory<T> factory, String enUSdiffLang, PropertiesTypeHandler<EntityType<?>, ?> handler) {
        return MainShupapium.REGISTRATE
                .entity(id, factory, MobCategory.MISC)
                .properties(cannonProperties())
                .renderer(() -> BigCannonProjectileRenderer::new)
                .lang(enUSdiffLang)
                .tag(RPLTags.PRECISE_MOTION)
                .onRegister(type -> MunitionPropertiesHandler.registerProjectileHandler(type, handler))
                .register();
    }

    private static <T> NonNullConsumer<T> configure(Consumer<EntityTypeConfigurator> cons) {
        return b -> cons.accept(EntityTypeConfigurator.of(b));
    }

    private static <T> NonNullConsumer<T> autocannonProperties() {
        return configure(c -> c.size(0.2f, 0.2f)
                .fireImmune()
                .updateInterval(1)
                .updateVelocity(false) // Mixin ServerEntity to not track motion
                .trackingRange(16));
    }

    private static <T> NonNullConsumer<T> cannonProperties() {
        return configure(c -> c.size(0.8F, 0.8F).fireImmune().updateInterval(1).updateVelocity(false).trackingRange(16));
    }

    public static void register() {
    }

    public static void register(IEventBus eventBus) {
        SHUPAPI_ENTITIES.register(eventBus);
    }

}
