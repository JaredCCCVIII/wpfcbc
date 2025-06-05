package com.ato.shupapi.items;

import com.ato.shupapi.ShupapiumBlockEntities;
import com.simibubi.create.foundation.item.ItemDescription;
import net.mcreator.crustychunks.init.CrustyChunksModEntities;
import net.mcreator.crustychunks.init.CrustyChunksModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.autocannon.AbstractAutocannonProjectile;
import rbasamoyai.createbigcannons.munitions.autocannon.AutocannonAmmoItem;
import rbasamoyai.createbigcannons.munitions.autocannon.AutocannonAmmoType;
import rbasamoyai.createbigcannons.munitions.autocannon.config.AutocannonProjectilePropertiesComponent;

import java.util.List;

public class MachineGunAmmoItem extends Item implements AutocannonAmmoItem {
    public MachineGunAmmoItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public AbstractAutocannonProjectile getAutocannonProjectile(ItemStack itemStack, Level level) {
        return ShupapiumBlockEntities.MACHINE_GUN_AMMO.create(level);
    }

    @Override
    public @Nullable EntityType<?> getEntityType(ItemStack itemStack) {
        return ShupapiumBlockEntities.MACHINE_GUN_AMMO.get();
    }

    @Override
    public AutocannonProjectilePropertiesComponent getAutocannonProperties(ItemStack itemStack) {
        return CBCMunitionPropertiesHandlers.INERT_AUTOCANNON_PROJECTILE.getPropertiesOf(this.getEntityType(itemStack)).autocannonProperties();
    }

    @Override
    public boolean isTracer(ItemStack itemStack) {
        return itemStack.getOrCreateTag().getBoolean("Tracer");
    }

    @Override
    public void setTracer(ItemStack itemStack, boolean b) {
        if (!itemStack.isEmpty()) itemStack.getOrCreateTag().putBoolean("Tracer", b);
    }

    @Override
    public ItemStack getSpentItem(ItemStack itemStack) {
        return CrustyChunksModItems.LARGE_CASING.get().getDefaultInstance();
    }

    @Override
    public AutocannonAmmoType getType() {
        return AutocannonAmmoType.AUTOCANNON;
    }
}
