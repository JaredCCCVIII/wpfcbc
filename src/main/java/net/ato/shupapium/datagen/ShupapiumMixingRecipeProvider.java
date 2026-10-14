package net.ato.shupapium.datagen;

import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import com.wariumce.init.CrustyChunksModItems;
import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumBlocks;
import net.ato.shupapium.ShupapiumItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import rbasamoyai.createbigcannons.index.CBCBlocks;
import rbasamoyai.createbigcannons.utils.CBCRegistryUtils;

import java.util.concurrent.CompletableFuture;

public class ShupapiumMixingRecipeProvider extends MixingRecipeGen {
    public ShupapiumMixingRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, MainShupapium.MOD_ID);
    }

    GeneratedRecipe

    PETROL_CAN_AMMO = create(MainShupapium.resource("petrol_can_ammo"), b -> b
            .require(CrustyChunksModItems.ALUMINUM_PLATE.get())
            .require(CrustyChunksModItems.STEEL_TUBE.get())
            .require(CrustyChunksModItems.BORED_COMPONENT.get())
            .require(fluidTag("forge", "gasoline"), 100)
            .output(ShupapiumItems.PETROL_CAN_AMMO_ITEM)),
    SHUPAPIUM_AMMO_CONTAINER = create(MainShupapium.resource("shupapium_ammo_container"), b -> b.require(CBCBlocks.AUTOCANNON_AMMO_CONTAINER)
            .require(fluidTag("c","molten_steel"), 500)
            .output(ShupapiumBlocks.SHUPAPIUM_AMMO_CONTAINER));

    private static TagKey<Fluid> fluidTag(String origin, String path) {
        return TagKey.create(CBCRegistryUtils.getFluidRegistryKey(), ResourceLocation.fromNamespaceAndPath(origin, path));
    }
}
