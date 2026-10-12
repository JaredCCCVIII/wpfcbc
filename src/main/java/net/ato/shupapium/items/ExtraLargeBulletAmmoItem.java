package net.ato.shupapium.items;

import com.wariumce.init.CrustyChunksModEntities;
import com.wariumce.init.CrustyChunksModItems;
import net.ato.shupapium.MainShupapium;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExtraLargeBulletAmmoItem extends AbstractShupapiumACAmmoItem {
    public ExtraLargeBulletAmmoItem(Properties properties) {
        super(properties);
    }

    @Override
    protected ResourceLocation propertiesLocation() {
        return MainShupapium.resource("munition_properties/projectiles/extra_large_bullet");
    }

    @Override
    public @Nullable List<EntityType<?>> getEntityType(ItemStack stack) {
        return List.of(CrustyChunksModEntities.EXTRA_LARGE_BULLET_FIRE.get());
    }

    @Override
    public boolean projectileAffectedByWorldsGravity() {
        return false;
    }

    @Override
    public boolean requiresPropellant(ItemStack stack) {
        return false;
    }

    @Override
    public ItemStack getSpentItem(ItemStack stack) {
        return CrustyChunksModItems.EXTRA_LARGE_CASING.get().getDefaultInstance();
    }
}
