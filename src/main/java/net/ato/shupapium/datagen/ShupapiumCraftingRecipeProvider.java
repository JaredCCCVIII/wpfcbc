package net.ato.shupapium.datagen;

import com.simibubi.create.api.data.recipe.MechanicalCraftingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.CommonMetal;
import com.tterrag.registrate.providers.ProviderType;
import com.wariumce.init.CrustyChunksModItems;
import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;
import rbasamoyai.createbigcannons.CBCTags;

import java.util.concurrent.CompletableFuture;

public abstract class ShupapiumCraftingRecipeProvider extends RecipeProvider {
    public ShupapiumCraftingRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    public static void register() {
        MainShupapium.REGISTRATE.addDataGenerator(ProviderType.RECIPE, ShupapiumCraftingRecipeProvider::buildCraftingRecipes);
    }

    public static void buildCraftingRecipes(RecipeOutput recipeOutput) {
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.SMOKE_BOMB_SHELL_BLOCK)
                .key('B', CrustyChunksModItems.BENT_COMPONENT.get()).key('S', CrustyChunksModItems.SMOKE_AGENT.get())
                .patternLine(" B ")
                .patternLine("BSB")
                .patternLine("BSB")
                .patternLine(" B ")
                .build(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ShupapiumBlocks.BARREL_SHELL_BLOCK)
                .requires(CrustyChunksModItems.EXPLOSIVE_BARREL.get()).requires(Tags.Items.GUNPOWDERS).requires(CBCTags.CBCItemTags.GUNPOWDER)
                .unlockedBy(getHasName(CrustyChunksModItems.EXPLOSIVE_BARREL.get()), has(CrustyChunksModItems.EXPLOSIVE_BARREL.get()))
                .unlockedBy(getHasName(Items.GUNPOWDER), has(Tags.Items.GUNPOWDERS))
                .save(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.TOXIC_BOMB_SHELL_BLOCK)
                .key('B', CrustyChunksModItems.BENT_COMPONENT.get()).key('T', CrustyChunksModItems.TOXIC_AGENT.get())
                .patternLine(" B ")
                .patternLine("BTB")
                .patternLine("BTB")
                .patternLine(" B ")
                .build(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.SMALL_BOMB_SHELL_BLOCK)
                .key('B', CrustyChunksModItems.BENT_COMPONENT.get()).key('V', CrustyChunksModItems.VOLATILE_DUST.get())
                .patternLine(" B ")
                .patternLine("BVB")
                .patternLine("BVB")
                .patternLine(" B ")
                .build(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.SMALL_BOMB_CLUSTER_SHELL_BLOCK)
                .key('B', ShupapiumBlocks.SMALL_BOMB_SHELL_BLOCK).key('R', Tags.Items.DUSTS_REDSTONE)
                .patternLine(" B ")
                .patternLine("BRB")
                .patternLine("BRB")
                .patternLine(" B ")
                .build(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.MEDIUM_BOMB_SHELL_BLOCK)
                .key('B', CrustyChunksModItems.BENT_COMPONENT.get()).key('V', CrustyChunksModItems.VOLATILE_DUST.get())
                .patternLine(" BB ")
                .patternLine("BVVB")
                .patternLine("BVVB")
                .patternLine(" BB ")
                .build(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.HEAVY_BOMB_SHELL_BLOCK)
                .key('B', CrustyChunksModItems.BENT_COMPONENT.get()).key('S', CrustyChunksModItems.STEELPLATE.get()).key('L', CrustyChunksModItems.LARGE_VOLATILE_PILE.get())
                .patternLine("BBBB")
                .patternLine("BLLB")
                .patternLine("SLLS")
                .patternLine("SSSS")
                .build(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.FIRE_BOMB_SHELL_BLOCK)
                .key('B', CrustyChunksModItems.BENT_COMPONENT.get()).key('S', CrustyChunksModItems.STEELPLATE.get()).key('F', CrustyChunksModItems.FIRE_AGENT.get())
                .patternLine("BBBB")
                .patternLine("BFFB")
                .patternLine("SFFS")
                .patternLine("SSSS")
                .build(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.SUPER_HEAVY_BOMB_SHELL_BLOCK)
                .key('B', CrustyChunksModItems.BENT_COMPONENT.get()).key('S', CrustyChunksModItems.STEELPLATE.get()).key('L', CrustyChunksModItems.LARGE_VOLATILE_PILE.get())
                .patternLine("SSSSSS")
                .patternLine("BBLLBB")
                .patternLine("BBLLBB")
                .patternLine("SSSSSS")
                .build(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.BLOCK_BUSTER_BOMB_SHELL_BLOCK)
                .key('B', CrustyChunksModItems.BENT_COMPONENT.get()).key('S', CrustyChunksModItems.STEELPLATE.get()).key('L', CrustyChunksModItems.LARGE_VOLATILE_PILE.get())
                .patternLine("SSSSSSSS")
                .patternLine("BBBLLBBB")
                .patternLine("BBBLLBBB")
                .patternLine("SSSSSSSS")
                .build(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.KINETIC_BOMB_SHELL_BLOCK)
                .key('I', CommonMetal.STEEL.ingots).key('S', CrustyChunksModItems.STEELPLATE.get()).key('T', CrustyChunksModItems.STEEL_TUBE.get()).key('O', ShupapiumBlocks.HEAVY_BOMB_SHELL_BLOCK)
                .patternLine("IIIII")
                .patternLine("STOTS")
                .patternLine("STTTS")
                .patternLine("IIIII")
                .build(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.IR_SEEKER_SHELL_BLOCK)
                .key('A', CrustyChunksModItems.ADVANCED_COMPONENT.get()).key('T', CrustyChunksModItems.TECH_COMPONENT.get()).key('W', CrustyChunksModItems.AIMER.get()).key('M', ShupapiumBlocks.MEDIUM_BOMB_SHELL_BLOCK).key('I', CrustyChunksModItems.IR_COMPONENT.get())
                .patternLine("AAIAA")
                .patternLine("AAWAA")
                .patternLine("TTMTT")
                .patternLine("TTTTT")
                .build(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.CLUSTER_BOMB_SHELL_BLOCK)
                .key('C', ShupapiumBlocks.SMALL_BOMB_CLUSTER_SHELL_BLOCK).key('B', CrustyChunksModItems.BENT_COMPONENT.get()).key('S', CrustyChunksModItems.STEELPLATE.get())
                .patternLine("BBBBB")
                .patternLine("BSCSB")
                .patternLine("SSSSS")
                .build(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.FISSION_BOMB_SHELL_BLOCK)
                .key('S', CrustyChunksModItems.STEELPLATE.get()).key('B', CrustyChunksModItems.BERYLLIUM_INGOT.get()).key('A', CrustyChunksModItems.ADVANCED_COMPONENT.get()).key('W', CrustyChunksModItems.FISSION_BOMB.get()).key('R', Items.REPEATER).key('I', CrustyChunksModItems.IMPLOSION_LENS.get()).key('F', CrustyChunksModItems.FISSION_CORE.get())
                .patternLine("SSSSSSSSS")
                .patternLine("SBBIIIBBS")
                .patternLine("SBBIWIBBS")
                .patternLine("SAAIFIAAS")
                .patternLine("SAAIIIAAS")
                .patternLine("SSSSRSSSS")
                .build(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.FUSION_BOMB_SHELL_BLOCK)
                .key('S', CrustyChunksModItems.STEELPLATE.get()).key('B', CrustyChunksModItems.BERYLLIUM_INGOT.get()).key('A', CrustyChunksModItems.ADVANCED_COMPONENT.get()).key('W', CrustyChunksModItems.FUSION_BOMB.get()).key('R', Items.REPEATER).key('I', CrustyChunksModItems.IMPLOSION_LENS.get()).key('F', CrustyChunksModItems.FISSION_CORE.get()).key('C', CrustyChunksModItems.FUSION_CORE.get())
                .patternLine("SSSSRSSSS")
                .patternLine("SBBIIIBBS")
                .patternLine("SBBIWIBBS")
                .patternLine("RABIFIBAR")
                .patternLine("SAAICIAAS")
                .patternLine("SAAIIIAAS")
                .patternLine("SSSSRSSSS")
                .build(recipeOutput);
        MechanicalCraftingRecipeBuilder.shapedRecipe(ShupapiumBlocks.JOKE_BOMB_SHELL_BLOCK)
                .key('S', Items.NETHER_STAR).key('B', ShupapiumBlocks.FUSION_BOMB_SHELL_BLOCK).key('A', Items.DRAGON_EGG).key('W', Items.COMPOSTER).key('R', Items.DRAGON_HEAD).key('I', CrustyChunksModItems.IMPLOSION_LENS.get()).key('F', CrustyChunksModItems.FISSION_CORE.get()).key('C', Items.BEACON)
                .patternLine("SAAIIIAAS")
                .patternLine("SSSSRSSSS")
                .patternLine("SSSSRSSSS")
                .patternLine("SBBIIIBBS")
                .patternLine("SBBIWIBBS")
                .patternLine("RABIFIBAR")
                .patternLine("SAAICIAAS")
                .patternLine("SAAIIIAAS")
                .patternLine("SSSSRSSSS")
                .build(recipeOutput);
    }
}
