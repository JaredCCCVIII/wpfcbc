package net.ato.shupapium;

import com.mojang.serialization.MapCodec;
import com.simibubi.create.content.processing.recipe.StandardProcessingRecipe;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import com.simibubi.create.foundation.utility.CreateLang;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.ato.shupapium.custom_interactions.ShupapiumAmmoContainerFillingDeployerRecipe;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public enum ShupapiumRecipeTypes implements IRecipeTypeInfo {
    SHUPAPIUM_AMMO_CONTAINER_FILLING_DEPLOYER(noSerializer(ShupapiumAmmoContainerFillingDeployerRecipe::new));

    private final ResourceLocation id;
    private final Supplier<RecipeSerializer<?>> serializerObject;
    @Nullable
    private final RecipeType<?> typeObject;
    private final NonNullSupplier<RecipeType<?>> type;

    ShupapiumRecipeTypes(NonNullSupplier<RecipeSerializer<?>> serializerSupplier, NonNullSupplier<RecipeType<?>> typeSupplier, boolean registerType) {
        String name = CreateLang.asId(name());
        id = MainShupapium.resource(name);
        serializerObject = MainShupapium.RECIPE_SERIALIZER_REGISTER.register(this.id.getPath(), serializerSupplier);
        if (registerType) {
            typeObject = typeSupplier.get();
            MainShupapium.RECIPE_TYPE_REGISTER.register(this.id.getPath(), typeSupplier);
            type = typeSupplier;
        } else {
            typeObject = null;
            type = typeSupplier;
        }
    }

    ShupapiumRecipeTypes(NonNullSupplier<RecipeSerializer<?>> serializerSupplier) {
        String name = CreateLang.asId(name());
        id = MainShupapium.resource(name);
        serializerObject = MainShupapium.RECIPE_SERIALIZER_REGISTER.register(this.id.getPath(), serializerSupplier);
        typeObject = simpleType(id);
        type = () -> typeObject;
        MainShupapium.RECIPE_TYPE_REGISTER.register(this.id.getPath(), this.type);
    }

    ShupapiumRecipeTypes(StandardProcessingRecipe.Factory<?> processingFactory) {
        this(() -> new StandardProcessingRecipe.Serializer<>(processingFactory));
    }

    public static <T extends Recipe<?>> RecipeType<T> simpleType(ResourceLocation id) {
        String stringId = id.toString();
        return new RecipeType<>() {
            @Override
            public String toString() {
                return stringId;
            }
        };
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends RecipeSerializer<?>> T getSerializer() {
        return (T) this.serializerObject.get();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <I extends RecipeInput, R extends Recipe<I>> RecipeType<R> getType() {
        return (RecipeType<R>) this.type.get();
    }

    public static void register() {
    }

    private static <T extends Recipe<?>> NonNullSupplier<RecipeSerializer<?>> noSerializer(Supplier<T> prov) {
        return () -> new SimpleRecipeSerializer<>(prov);
    }

    private static class SimpleRecipeSerializer<T extends Recipe<?>> implements RecipeSerializer<T> {
        private final MapCodec<T> codec;
        private final StreamCodec<RegistryFriendlyByteBuf, T> streamCodec;

        public SimpleRecipeSerializer(Supplier<T> constructor) {
            this.codec = MapCodec.unit(constructor);
            this.streamCodec = StreamCodec.of((buf, r) -> {}, buf -> constructor.get());
        }

        @Override public @NotNull MapCodec<T> codec() { return this.codec; }
        @Override public @NotNull StreamCodec<RegistryFriendlyByteBuf, T> streamCodec() { return this.streamCodec; }
    }
}
