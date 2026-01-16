package net.ato.shupapium.items;

import net.ato.shupapium.utils.ShupapiumAmmoProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import rbasamoyai.createbigcannons.munitions.autocannon.config.AutocannonProjectilePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.autocannon.config.InertAutocannonProjectileProperties;

public abstract class AbstractShupapiumACAmmoItem extends Item implements ShupapiumAmmoItem {
    public AbstractShupapiumACAmmoItem(Properties pProperties) {
        super(pProperties);
    }

    protected abstract ResourceLocation propertiesLocation();

    @Override
    public InertAutocannonProjectileProperties getMainProperties(ItemStack stack) {
        return ShupapiumAmmoProperties.get(propertiesLocation());
    }

    @Override
    public AutocannonProjectilePropertiesComponent getAutocannonProperties(ItemStack itemStack) {;
        return getMainProperties(itemStack) != null ? getMainProperties(itemStack).autocannonProperties() : null;
    }
}
