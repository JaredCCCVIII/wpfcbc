package net.ato.shupapium.items;

import net.ato.shupapium.utils.ProjectileManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class BasicShupapiumAmmoItem extends AbstractShupapiumACAmmoItem{
    private final ShupapiumAmmoSpec spec;
    private List<EntityType<?>> resolvedVariants;
    public BasicShupapiumAmmoItem(Properties properties, ShupapiumAmmoSpec spec) {
        super(properties, spec.ballistics());
        this.spec = spec;
    }

    @Override
    protected ResourceLocation propertiesLocation() {
        return spec.properties();
    }

    @Override
    public @Nullable List<EntityType<?>> getEntityType(ItemStack stack) {
        if (resolvedVariants == null) {
            resolvedVariants = spec.variants().stream().<EntityType<?>>map(Supplier::get).toList();
        }
        return resolvedVariants;
    }

    @Override
    public boolean projectileAffectedByWorldsGravity() {
        return spec.affectedByGravity();
    }

    @Override
    public boolean requiresPropellant(ItemStack stack) {
        return spec.requiresPropellant();
    }

    @Override
    public ItemStack getSpentItem(ItemStack stack) {
        return spec.spentItem() == null ? ItemStack.EMPTY : spec.spentItem().get().getDefaultInstance();
    }

    @Override
    public void customProjectileMuzzle(ItemStack stack, ServerLevel level, Vec3 pos, Vec3 vel) {
        if (spec.muzzle() != null) spec.muzzle().apply(level, pos, vel);
    }

    @Override
    public ProjectileManager.ExpireAction getExpireAction(ItemStack stack) {
        return spec.expireAction();
    }
}
