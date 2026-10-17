package net.ato.shupapium.events;

import com.simibubi.create.content.kinetics.deployer.DeployerBlockEntity;
import com.simibubi.create.content.kinetics.deployer.DeployerRecipeSearchEvent;
import com.tterrag.registrate.providers.ProviderType;
import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumBlockEntities;
import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.ShupapiumRecipeTypes;
import net.ato.shupapium.custom_interactions.ShupapiumAmmoContainerFillingDeployerRecipe;
import net.ato.shupapium.datagen.*;
import net.ato.shupapium.entities.ShupapiumDummyRagdoll;
import net.ato.shupapium.entities.ShupapiumMetalRagdoll;
import net.ato.shupapium.ponder.ShupapiumPonderPlugin;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import rbasamoyai.createbigcannons.CBCCommonNeoForgeEvents;
import rbasamoyai.createbigcannons.CreateBigCannons;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

@EventBusSubscriber(modid = MainShupapium.MOD_ID)
public class CommonModEvents {
    @SubscribeEvent
    public static void onAddDeployerRecipes(DeployerRecipeSearchEvent event) {
        DeployerBlockEntity deployer = event.getBlockEntity();
        RecipeInput container = event.getInventory();
        BiConsumer<Supplier<Optional<? extends RecipeHolder<? extends Recipe<? extends RecipeInput>>>>, Integer> cons = event::addRecipe;
        Level level = deployer.getLevel();
        ItemStack containerItem = container.getItem(0);
        ItemStack deployerItem = container.getItem(1);

        ShupapiumAmmoContainerFillingDeployerRecipe ammoContainerRecipe = new ShupapiumAmmoContainerFillingDeployerRecipe(containerItem, deployerItem);
        assert level != null;
        if (ammoContainerRecipe.matches(container, level)) {
            cons.accept(() -> Optional.of(new RecipeHolder<>(ShupapiumRecipeTypes.SHUPAPIUM_AMMO_CONTAINER_FILLING_DEPLOYER.getId(), ammoContainerRecipe)), 25);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onGatherRegistrateData(GatherDataEvent event) {
        if (!event.getMods().contains(MainShupapium.MOD_ID)) return;
        MainShupapium.REGISTRATE.addDataGenerator(ProviderType.LANG, prov -> providePonderLang(prov::add));
        ShupapiumLangGen.prepare();
        ShupapiumCraftingRecipeProvider.register();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onGatherDataEvent(GatherDataEvent event) {
        if (!event.getMods().contains(MainShupapium.MOD_ID)) return;
        DataGenerator generator = event.getGenerator();
        boolean includeServer = event.includeServer();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> registries = event.getLookupProvider();
        ShupapiumBlockRecipeProvider.registerAll(provider -> generator.addProvider(includeServer, provider.create(output)), registries);
        generator.addProvider(includeServer, new ShupapiumMixingRecipeProvider(output, registries));
        generator.addProvider(includeServer, new ShupapiumSequencedAssemblyRecipeProvider(output, registries));
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ShupapiumBlockEntities.SHUPAPIUM_AMMO_CONTAINER.get(), CBCCommonNeoForgeEvents::getMixinBlockEntityItemHandler);
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(ShupapiumEntities.DUMMY_RAGDOLL_ENTITY.get(), ShupapiumDummyRagdoll.createAttributes().build());
        event.put(ShupapiumEntities.HEAVY_DUMMY_RAGDOLL_ENTITY.get(), ShupapiumMetalRagdoll.createAttributes().build());
    }

    private static void providePonderLang(BiConsumer<String, String> cons) {
        PonderIndex.addPlugin(new ShupapiumPonderPlugin());
        PonderIndex.getLangAccess().provideLang(CreateBigCannons.MOD_ID, cons);
    }
}
