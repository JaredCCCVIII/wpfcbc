package net.ato.shupapium.items;

import com.wariumce.util.ProjectileCompat;
import net.ato.shupapium.utils.ShupapiumAmmoProperties;
import net.ato.shupapium.utils.acballistics.ShupapiumAmmoBallistics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.createbigcannons.munitions.autocannon.config.AutocannonProjectilePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.autocannon.config.InertAutocannonProjectileProperties;

import javax.annotation.Nullable;
import java.util.List;

public abstract class AbstractShupapiumACAmmoItem extends Item implements ShupapiumAmmoItem {
    private final ShupapiumAmmoBallistics ballistics;
    public AbstractShupapiumACAmmoItem(Properties properties) {
        this(properties, ShupapiumAmmoBallistics.DEFAULT);
    }

    public AbstractShupapiumACAmmoItem(Properties properties, ShupapiumAmmoBallistics ballistics) {
        super(properties);
        this.ballistics = ballistics;
    }

    protected abstract ResourceLocation propertiesLocation();

    @Override
    public InertAutocannonProjectileProperties getMainProperties(ItemStack stack) {
        return ShupapiumAmmoProperties.get(propertiesLocation());
    }

    @Override
    public AutocannonProjectilePropertiesComponent getAutocannonProperties(ItemStack itemStack) {
        return getMainProperties(itemStack) != null ? getMainProperties(itemStack).autocannonProperties() : AutocannonProjectilePropertiesComponent.DEFAULT;
    }

    @Override
    public ShupapiumAmmoBallistics getBallistics(ItemStack stack) {
        return ballistics;
    }

    @Override
    public @Nullable AbstractArrow createProjectile(ServerLevel level, ItemStack stack) {
        List<EntityType<?>> types = getEntityType(stack);
        if (types == null || types.isEmpty()) return null;
        EntityType<?> type = types.get(level.random.nextInt(types.size()));
        return type.create(level) instanceof AbstractArrow projectile ? projectile : null;
    }

    @Override
    public void prepareProjectile(AbstractArrow projectile, ServerLevel level, ItemStack stack, @Nullable Entity owner) {
        InertAutocannonProjectileProperties props = getMainProperties(stack);
        projectile.addTag("shupapiumProjectile");
        projectile.setOwner(owner);
        projectile.setSilent(true);
        projectile.setNoGravity(!projectileAffectedByWorldsGravity());
        if (props != null) {
            projectile.setBaseDamage(props.damage().entityDamage() / 100);
            ProjectileCompat.setKnockback(projectile, (int) props.damage().knockback());
        }
    }
}
