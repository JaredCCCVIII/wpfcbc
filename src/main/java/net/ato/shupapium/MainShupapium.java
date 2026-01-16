package net.ato.shupapium;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.ato.shupapium.client.renderers.RotatoryACBarrelRenderer;
import net.ato.shupapium.events.ShupapiumReloadListener;
import net.ato.shupapium.utils.accombos.*;
import net.ato.shupapium.utils.actypes.ShupapiumACProfileHandler;
import net.createmod.catnip.lang.FontHelper;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;
import rbasamoyai.createbigcannons.utils.CBCUtils;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MainShupapium.MODID)
public class MainShupapium
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "shupapium";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);

    static {
        REGISTRATE.setTooltipModifierFactory(item -> new ItemDescription.Modifier(item, FontHelper.Palette.STANDARD_CREATE)
                .andThen(TooltipModifier.mapNull(KineticStats.create(item))));
    }

    public static ResourceLocation resource(String path) {
        return CBCUtils.location(MODID, path);
    }

    public MainShupapium(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        REGISTRATE.registerEventListeners(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ShupapiumGroup.register(modEventBus);
        ShupapiumItems.register();
        ShupapiumItems.register(modEventBus);
        ShupapiumMenuTypes.register();
        ShupapiumEntities.register();
        ShupapiumEntities.register(modEventBus);
        ShupapiumBlocks.register();
        ShupapiumBlockEntities.register();
        ShupapiumSounds.register(modEventBus);
        modEventBus.addListener(this::onRegisterForge);
        MinecraftForge.EVENT_BUS.addListener((AddReloadListenerEvent e) -> e.addListener(new ShupapiumReloadListener()));

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        //context.registerConfig(ModConfig.Type.COMMON, ShupapiumConfig.SPEC);
    }

    private void onRegisterForge(RegisterEvent event) {
        ShupapiumContraptionsTypes.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        ShupapiumACProfileHandler.clear();
        event.enqueueWork(ShupapiumACProfiles::register);
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            BlockEntityRenderers.register(ShupapiumBlockEntities.ROTARY_CANNON_BARREL.get(), RotatoryACBarrelRenderer::new);

            ShupapiumFlywheelVisuals.registerVisuals();
        }
    }
}
