package com.ato.shupapi;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import rbasamoyai.createbigcannons.utils.CBCUtils;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MainShupapium.MODID)
public class MainShupapium
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "shupapium";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);

    static {
        REGISTRATE.setTooltipModifierFactory(item -> new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE)
                .andThen(TooltipModifier.mapNull(KineticStats.create(item))));
    }

    public static ResourceLocation resource(String path) {
        return CBCUtils.location(MODID, path);
    }

    public MainShupapium(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        REGISTRATE.registerEventListeners(modEventBus);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(MainShupapium::init);
        ShupapiumSounds.register(modEventBus);
        ShupapiumGroup.register(modEventBus);
        ShupapiumBlocks.register();
        ShupapiumEntities.register(modEventBus);
        ShupapiumBlockEntities.register();
        ShupapiumItems.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);

    }

    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {

        });
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
