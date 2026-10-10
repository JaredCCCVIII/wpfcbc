package net.ato.shupapium.items;

import com.wariumce.init.CrustyChunksModEntities;
import com.wariumce.init.CrustyChunksModItems;
import com.wariumce.procedures.MuzzleFlashProcedure;
import net.ato.shupapium.MainShupapium;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LargeBulletAmmoItem extends AbstractShupapiumACAmmoItem{
    public LargeBulletAmmoItem(Properties properties) {
        super(properties);
    }

    @Override
    protected ResourceLocation propertiesLocation() {
        return ResourceLocation.fromNamespaceAndPath(MainShupapium.MOD_ID, "munition_properties/projectiles/large_bullet");
    }

    @Override
    public @Nullable List<EntityType<?>> getEntityType(ItemStack stack) {
        return List.of(CrustyChunksModEntities.GENERICLARGE_BULLET.get(), CrustyChunksModEntities.GENERIC_LARGE_BULLET_GREEN.get());
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
    public void customProjectileMuzzle(ItemStack stack, ServerLevel level, Vec3 pos, Vec3 vel) {
        MuzzleFlashProcedure.execute(level, pos.x, pos.y, pos.z);
    }

    @Override
    public ItemStack getSpentItem(ItemStack stack) {
        return CrustyChunksModItems.LARGE_CASING.get().getDefaultInstance();
    }
}
