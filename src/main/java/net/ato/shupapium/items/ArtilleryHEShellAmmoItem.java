package net.ato.shupapium.items;

import net.ato.shupapium.MainShupapium;
import net.mcreator.crustychunks.init.CrustyChunksModEntities;
import net.mcreator.crustychunks.init.CrustyChunksModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ArtilleryHEShellAmmoItem extends AbstractShupapiumACAmmoItem{
    public ArtilleryHEShellAmmoItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected ResourceLocation propertiesLocation() {
        return new ResourceLocation(MainShupapium.MODID, "munition_properties/projectiles/artillery_he_shell");
    }

    @Override
    public @Nullable List<AbstractArrow> getAutocannonProjectile(ItemStack stack, Level level) {
        return List.of(Objects.requireNonNull(CrustyChunksModEntities.ARTILLERY_FIRE_PROJECTILE.get().create(level)));
    }

    @Override
    public @Nullable List<EntityType<?>> getEntityType(ItemStack stack) {
        return List.of(CrustyChunksModEntities.ARTILLERY_FIRE_PROJECTILE.get());
    }

    @Override
    public boolean projectileAffectedByWorldsGravity() {
        return false;
    }

    @Override
    public boolean requiresPropellant(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getSpentItem(ItemStack stack) {
        return CrustyChunksModItems.HOLLOWED_HUGE_PROJECTILE.get().getDefaultInstance();
    }
}
