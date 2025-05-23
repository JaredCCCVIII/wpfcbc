package com.ato.shupapi;

import com.ato.shupapi.entityblocks.*;
import com.tterrag.registrate.util.entry.EntityEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.multiloader.EntityTypeConfigurator;
import rbasamoyai.createbigcannons.munitions.big_cannon.AbstractBigCannonProjectile;
import rbasamoyai.createbigcannons.munitions.big_cannon.BigCannonProjectileRenderer;
import rbasamoyai.createbigcannons.munitions.config.MunitionPropertiesHandler;
import rbasamoyai.createbigcannons.munitions.config.PropertiesTypeHandler;
import rbasamoyai.ritchiesprojectilelib.RPLTags;

import java.util.function.Consumer;

public class ShupapiumBlockEntities {

    public static final EntityEntry<FissionShellProjectile> FISSION_SHELL = cannonProjectile("fission_shell", FissionShellProjectile::new, "Fission Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<OrdinanceShellProjectile> ORDINANCE_SHELL = cannonProjectile("ordinance_shell", OrdinanceShellProjectile::new, "Ordinance Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<RedordinanceShellProjectile> REDORDINANCE_SHELL = cannonProjectile("redordinance_shell", RedordinanceShellProjectile::new, "Fire Ordinance Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<HeavyordinanceShellProjectile> HEAVYORDINANCE_SHELL = cannonProjectile("heavyordinance_shell", HeavyordinanceShellProjectile::new, "Heavy Ordinance Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<SuperheavyordinanceShellProjectile> SUPERHEAVYORDINANCE_SHELL = cannonProjectile("superheavyordinance_shell", SuperheavyordinanceShellProjectile::new, "Super Heavy Ordinance Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<ClusterShellProjectile> CLUSTER_SHELL = cannonProjectile("cluster_shell", ClusterShellProjectile::new, "Cluster Ordinance Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<KineticShellProjectile> KINETIC_SHELL = cannonProjectile("kinetic_shell", KineticShellProjectile::new, "Kinetic Ordinance Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<ToxicgasShellProjectile> TOXICGAS_SHELL = cannonProjectile("toxicgas_shell", ToxicgasShellProjectile::new, "Toxic Gas Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);
    public static final EntityEntry<FusionShellProjectile> FUSION_SHELL = cannonProjectile("fusion_shell", FusionShellProjectile::new, "Fusion Explosive Shell", CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE);

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

    private static <T> NonNullConsumer<T> cannonProperties() {
        return configure(c -> c.size(0.8F, 0.8F).fireImmune().updateInterval(1).updateVelocity(false).trackingRange(16));
    }

    public static void register() {
    }
}
