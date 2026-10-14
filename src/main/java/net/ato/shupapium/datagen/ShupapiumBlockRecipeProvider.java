package net.ato.shupapium.datagen;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.ato.shupapium.MainShupapium;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.crafting.BlockRecipe;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public abstract class ShupapiumBlockRecipeProvider implements DataProvider {
    private static final Gson GSON = (new GsonBuilder()).setPrettyPrinting().create();

    private final PackOutput.PathProvider blockRecipePath;
    private final CompletableFuture<HolderLookup.Provider> registries;
    protected ResourceLocation info;

    protected ShupapiumBlockRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        this.blockRecipePath = output.createPathProvider(PackOutput.Target.DATA_PACK, "createbigcannons/block_recipes");
        this.registries = registries;
    }

    protected static final List<Factory<ShupapiumBlockRecipeProvider>> GENERATORS = new ArrayList<>();

    public static void registerAll(Consumer<Factory<?>> cons, CompletableFuture<HolderLookup.Provider> registries) {
        GENERATORS.add(output -> new ShupapiumDrillBoringRecipeProvider(output, registries));

        cons.accept(output -> new DataProvider() {
            @Override
            public @NotNull CompletableFuture<?> run(@NotNull CachedOutput cache) {
                return CompletableFuture.allOf(GENERATORS.stream().map(gen -> {
                    try {
                        return gen.create(output).run(cache);
                    } catch (Exception e) {
                        throw e;
                    }
                }).toArray(CompletableFuture[]::new));
            }

            @Override
            public @NotNull String getName() {
                return "CBC: Warium Projectiles Block Recipes";
            }
        });
    }

    @Override
    public @NotNull CompletableFuture<?> run(@NotNull CachedOutput cache) {
        return this.registries.thenCompose(reg -> this.run(cache, reg));
    }

    protected CompletableFuture<?> run(CachedOutput cache, HolderLookup.Provider registries) {
        Map<ResourceLocation, BlockRecipe> map = new HashMap<>();
        this.registerRecipes((id, recipe) -> {
            if (map.put(id, recipe) != null)
                throw new IllegalStateException("Duplicate block recipe " + id);
        });
        return CompletableFuture.allOf(map.entrySet().stream()
                .map(e -> {
                    ResourceLocation id = e.getKey();
                    BlockRecipe recipe = e.getValue();
                    return DataProvider.saveStable(cache, registries, BlockRecipe.CODEC, recipe, ShupapiumBlockRecipeProvider.this.blockRecipePath.json(id));
                }).toArray(CompletableFuture[]::new));
    }

    private static void saveRecipe(CachedOutput cache, JsonObject obj, Path path) {
        try {
            String s = GSON.toJson(obj);
            HashCode s1 = Hashing.sha256().hashUnencodedChars(s);
            cache.writeIfNeeded(path, s.getBytes(), s1);
        } catch (IOException e) {
            MainShupapium.LOGGER.error("Couldn't save block recipe {}", path, e);
        }
    }

    protected abstract void registerRecipes(BiConsumer<ResourceLocation, BlockRecipe> cons);

    @Override
    public @NotNull String getName() {
        return "CBC: Warium Projectiles Block Recipes: " + (this.info == null ? "unknown id" : this.info);
    }
}
