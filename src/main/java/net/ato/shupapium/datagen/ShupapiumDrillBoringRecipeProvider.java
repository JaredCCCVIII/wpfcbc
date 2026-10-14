package net.ato.shupapium.datagen;

import com.wariumce.init.CrustyChunksModBlocks;
import net.ato.shupapium.ShupapiumBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import rbasamoyai.createbigcannons.crafting.BlockRecipe;
import rbasamoyai.createbigcannons.crafting.BlockRecipeIngredient;
import rbasamoyai.createbigcannons.crafting.boring.DrillBoringBlockRecipe;
import rbasamoyai.createbigcannons.utils.CBCRegistryUtils;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ShupapiumDrillBoringRecipeProvider extends ShupapiumBlockRecipeProvider {
    protected ShupapiumDrillBoringRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void registerRecipes(BiConsumer<ResourceLocation, BlockRecipe> biConsumer) {
        recipe(CrustyChunksModBlocks.MINI_GUN_BARREL.get(), ShupapiumBlocks.MINIGUN_BARREL.get(), biConsumer);
        recipe(CrustyChunksModBlocks.MINIGUN.get(), ShupapiumBlocks.MINIGUN_BREECH.get(), biConsumer);
        recipe(CrustyChunksModBlocks.MACHINE_GUN_BARREL.get(), ShupapiumBlocks.MACHINE_GUN_BARREL.get(), biConsumer);
        recipe(CrustyChunksModBlocks.COVERED_MACHINE_GUN_BARREL.get(), ShupapiumBlocks.COVERED_MACHINE_GUN_BARREL.get(), biConsumer);
        recipe(CrustyChunksModBlocks.LIGHT_MACHINE_GUN.get(), ShupapiumBlocks.LIGHT_MACHINE_GUN_BREECH.get(), biConsumer);
        recipe(CrustyChunksModBlocks.MACHINE_GUN.get(), ShupapiumBlocks.MACHINE_GUN_BREECH.get(), biConsumer);
        recipe(CrustyChunksModBlocks.HEAVY_MACHINE_GUN.get(), ShupapiumBlocks.HEAVY_MACHINE_GUN_BREECH.get(), biConsumer);
        recipe(CrustyChunksModBlocks.RAC_BARREL.get(), ShupapiumBlocks.ROTARY_CANNON_BARREL.get(), biConsumer);
        recipe(CrustyChunksModBlocks.AUTOCANNON_BARREL.get(), ShupapiumBlocks.CANNON_BARREL.get(), biConsumer);
        recipe(CrustyChunksModBlocks.ROTARY_AUTO_CANNON.get(), ShupapiumBlocks.ROTARY_CANNON_BREECH.get(), biConsumer);
        recipe(CrustyChunksModBlocks.LIGHT_AUTOCANNON.get(), ShupapiumBlocks.LIGHT_CANNON_BREECH.get(), biConsumer);
        recipe(CrustyChunksModBlocks.AUTOCANNON.get(), ShupapiumBlocks.HEAVY_CANNON_BREECH.get(), biConsumer);
        recipe(CrustyChunksModBlocks.BATTLE_CANNON_BARREL.get(), ShupapiumBlocks.BATTLE_CANNON_BARREL.get(), biConsumer);
        recipe(CrustyChunksModBlocks.THICK_BATTLE_CANNON_BARREL.get(), ShupapiumBlocks.THICK_BATTLE_CANNON_BARREL.get(), biConsumer);
        recipe(CrustyChunksModBlocks.BATTLE_CANNON_MANTLET.get(), ShupapiumBlocks.COVERED_BATTLE_CANNON_BARREL.get(), biConsumer);

        recipe(CrustyChunksModBlocks.BATTLE_CANNON_BREECH.get(), ShupapiumBlocks.BATTLE_CANNON_BREECH.get(), biConsumer);
        recipe(CrustyChunksModBlocks.ARTILLERY_BARREL.get(), ShupapiumBlocks.ARTILLERY_CANNON_BARREL.get(), biConsumer);
        recipe(CrustyChunksModBlocks.ARTILLERYBREECH.get(), ShupapiumBlocks.ARTILLERY_CANNON_BREECH.get(), biConsumer);
        recipe(CrustyChunksModBlocks.ROCKET_POD.get(), ShupapiumBlocks.ROCKET_POD_BARREL.get(), biConsumer);
        recipe(CrustyChunksModBlocks.ROCKET_POD_CHAMBER.get(), ShupapiumBlocks.ROCKET_POD_BREECH.get(), biConsumer);
        recipe(CrustyChunksModBlocks.LARGE_ROCKET_POD.get(), ShupapiumBlocks.LARGE_ROCKET_POD_BARREL.get(), biConsumer);
        recipe(CrustyChunksModBlocks.LARGE_ROCKET_POD_CHAMBER.get(), ShupapiumBlocks.LARGE_ROCKET_POD_BREECH.get(), biConsumer);
        recipe(CrustyChunksModBlocks.MORTAR.get(), ShupapiumBlocks.LAUNCHER_BARREL.get(), biConsumer);
        recipe(CrustyChunksModBlocks.COUNTERMEASURE_DISPENSER.get(), ShupapiumBlocks.LAUNCHER_BREECH.get(), biConsumer);
        recipe(CrustyChunksModBlocks.FLAME_THROWER_BARREL.get(), ShupapiumBlocks.FLAMETHROWER_BARREL.get(), biConsumer);
        recipe(CrustyChunksModBlocks.COVERED_FLAME_THROWER_BARREL.get(), ShupapiumBlocks.COVERED_FLAMETHROWER_BARREL.get(), biConsumer);
        recipe(CrustyChunksModBlocks.FLAME_THROWER.get(), ShupapiumBlocks.FLAMETHROWER_BREECH.get(), biConsumer);
    }

    protected void recipe(Block input, Block result, BiConsumer<ResourceLocation, BlockRecipe> cons) {
        recipe(CBCRegistryUtils.getBlockLocation(result), input, result, cons);
    }

    protected void recipe(ResourceLocation id, Block input, Block result, BiConsumer<ResourceLocation, BlockRecipe> cons) {
        recipe(id, input, result, true, cons);
    }

    protected void recipe(ResourceLocation id, Block input, Block result, boolean obeyFacing, BiConsumer<ResourceLocation, BlockRecipe> cons) {
        cons.accept(id, new DrillBoringBlockRecipe(BlockRecipeIngredient.of(input), result, obeyFacing));
    }

    protected void recipe(TagKey<Block> input, Block result, BiConsumer<ResourceLocation, BlockRecipe> cons) {
        recipe(CBCRegistryUtils.getBlockLocation(result), input, result, cons);
    }

    protected void recipe(ResourceLocation id, TagKey<Block> input, Block result, BiConsumer<ResourceLocation, BlockRecipe> cons) {
        recipe(id, input, result, true, cons);
    }

    protected void recipe(ResourceLocation id, TagKey<Block> input, Block result, boolean obeyFacing, BiConsumer<ResourceLocation, BlockRecipe> cons) {
        cons.accept(id, new DrillBoringBlockRecipe(BlockRecipeIngredient.of(input), result, obeyFacing));
    }
}
