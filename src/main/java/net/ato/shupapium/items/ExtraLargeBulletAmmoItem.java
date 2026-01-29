package net.ato.shupapium.items;

import net.ato.shupapium.MainShupapium;
import net.mcreator.crustychunks.init.CrustyChunksModEntities;
import net.mcreator.crustychunks.init.CrustyChunksModItems;
import net.mcreator.crustychunks.procedures.MuzzleFlashProcedure;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ExtraLargeBulletAmmoItem extends AbstractShupapiumACAmmoItem{
    public ExtraLargeBulletAmmoItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected ResourceLocation propertiesLocation() {
        return ResourceLocation.fromNamespaceAndPath(MainShupapium.MODID, "munition_properties/projectiles/extra_large_bullet");
    }

    @Override
    public @Nullable List<AbstractArrow> getAutocannonProjectile(ItemStack stack, Level level) {
        return List.of(Objects.requireNonNull(CrustyChunksModEntities.EXTRA_LARGE_BULLET_FIRE.get().create(level)));
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
    public void customProjectileMuzzle(ItemStack stack, ServerLevel level, Vec3 pos, Vec3 vel) {
        MuzzleFlashProcedure.execute(level, pos.x,  pos.y, pos.z);
    }

    @Override
    public ItemStack getSpentItem(ItemStack stack) {
        return CrustyChunksModItems.EXTRA_LARGE_CASING.get().getDefaultInstance();
    }
}
