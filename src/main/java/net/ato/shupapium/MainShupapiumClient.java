package net.ato.shupapium;

import net.ato.shupapium.cannons.ShupapiumACBarrelBlock;
import net.ato.shupapium.client.renderers.MinigunBarrelRenderer;
import net.ato.shupapium.client.renderers.RotaryCannonBarrelRenderer;
import net.ato.shupapium.client.renderers.ShupapiumDummyRagdollRenderer;
import net.ato.shupapium.ponder.ShupapiumPonderPlugin;
import net.ato.shupapium.utils.acattachments.ShupapiumACAttachmentDef;
import net.ato.shupapium.utils.acattachments.ShupapiumACAttachments;
import net.ato.shupapium.utils.acattachments.ShupapiumACModifiers;
import net.ato.shupapium.utils.actypes.ShupapiumACParts;
import net.ato.shupapium.utils.actypes.ShupapiumACProfile;
import net.ato.shupapium.utils.actypes.ShupapiumACProfileHandler;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlock;

import java.util.*;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = MainShupapium.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = MainShupapium.MOD_ID, value = Dist.CLIENT)
public class MainShupapiumClient {
    private static final Map<Item, List<Component>> CACHE = new HashMap<>();
    private enum Polarity {HIGHER_IS_BETTER, LOWER_IS_BETTER, NEUTRAL}

    private static void addList(List<Component> out, String headerKey, Collection<? extends  Component> names) {
        out.add(Component.translatable(headerKey).withStyle(ChatFormatting.GRAY));
        for (Component n : names) out.add(Component.literal(" ").append(n).withStyle(ChatFormatting.BLUE));
    }

    private static void line(List<Component> out, String key, float mult, Polarity pol) {
        int pct = Math.round((mult - 1) * 100);
        if (pct == 0) return;
        ChatFormatting color = pol == Polarity.NEUTRAL ? ChatFormatting.GRAY : ((pct > 0) == (pol == Polarity.HIGHER_IS_BETTER) ? ChatFormatting.GREEN : ChatFormatting.RED);
        out.add(Component.literal(" " + (pct > 0 ? "+" : "") + pct + "% ").append(Component.translatable("tooltip.shupapium.modifier." + key)).withStyle(color));
    }

    private static void addModifiers(List<Component> out, ShupapiumACModifiers m) {
        line(out, "speed", m.speedMultiplier(), Polarity.HIGHER_IS_BETTER);
        line(out, "spread", m.spreadMultiplier(), Polarity.LOWER_IS_BETTER);
        line(out, "recoil", m.recoilMultiplier(), Polarity.LOWER_IS_BETTER);
        line(out, "sound", m.soundVolumeMultiplier(), Polarity.LOWER_IS_BETTER);
        line(out, "muzzle", m.muzzleParticleScale(), Polarity.LOWER_IS_BETTER);
        if (m.fireSoundOverride() != null) {
            out.add(Component.literal(" ").append(Component.translatable("tooltip.shupapium.modifier.sound_override")).withStyle(ChatFormatting.GRAY));
        }
    }

    private static Component profileName(ShupapiumACProfile p) {
        ResourceLocation id = p.getProfileId();
        return Component.translatable("profile." + id.getNamespace() + "." + id.getPath());
    }

    private static boolean accepts(ShupapiumACProfile p, Block attachment) {
        Set<Block> allowed = p.getCompatibleAttachments();
        if (!allowed.isEmpty() && !allowed.contains(attachment)) return false;
        return p.parts().breech() instanceof AutocannonBlock pb && attachment instanceof AutocannonBlock ab && pb.getAutocannonMaterial() == ab.getAutocannonMaterial();
    }

    private static List<Component> build(Item item) {
        List<Component> out = new ArrayList<>();
        Collection<ShupapiumACProfile> profiles = ShupapiumACProfileHandler.getAllProfiles();

        List<ShupapiumACProfile> forAmmo = profiles.stream().filter(p -> p.getAmmoTypes().contains(item)).toList();
        if (!forAmmo.isEmpty()) {
            addList(out, "tooltip.shupapium.ammo_usable_in", forAmmo.stream().map(MainShupapiumClient::profileName).toList());
        }

        if (!(item instanceof BlockItem bi)) return out;
        Block block = bi.getBlock();

        Set<Block> breeches = new LinkedHashSet<>();
        Set<Block> barrels = new LinkedHashSet<>();
        for (ShupapiumACProfile p : profiles) {
            for (ShupapiumACParts pt : p.allParts()) {
                if (pt.barrel() == block) breeches.add(pt.breech());
                if (pt.breech() == block) barrels.add(pt.barrel());
            }
        }
        if (!breeches.isEmpty()) {
            addList(out, "tooltip.shupapium.works_with_breech", breeches.stream().map(Block::getName).toList());
        }
        if (!barrels.isEmpty()) {
            addList(out, "tooltip.shupapium.works_with_barrel", barrels.stream().map(Block::getName).toList());
        }

        ShupapiumACAttachmentDef def = ShupapiumACAttachments.get(block);
        if (def != null) {
            out.add(Component.translatable("tooltip.shupapium.slot." + def.slot().name().toLowerCase(Locale.ROOT)).withStyle(ChatFormatting.GRAY));
            List<Component> cannons = profiles.stream().filter(p -> accepts(p, block)).map(MainShupapiumClient::profileName).toList();
            if (!cannons.isEmpty()) addList(out, "tooltip.shupapium.attach_to", cannons);
        }

        ShupapiumACModifiers mods = def != null ? def.modifiers() : block instanceof ShupapiumACBarrelBlock b ? b.getBarrelModifiers() : null;
        if (mods != null) addModifiers(out, mods);
        return out;
    }

    public MainShupapiumClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        PonderIndex.addPlugin(new ShupapiumPonderPlugin());
    }

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        List<Component> extra = CACHE.computeIfAbsent(event.getItemStack().getItem(), MainShupapiumClient::build);
        if (!extra.isEmpty()) event.getToolTip().addAll(extra);
    }

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ShupapiumEntities.DUMMY_RAGDOLL_ENTITY.get(), ShupapiumDummyRagdollRenderer::new);
        event.registerEntityRenderer(ShupapiumEntities.HEAVY_DUMMY_RAGDOLL_ENTITY.get(), ShupapiumDummyRagdollRenderer::new);
        event.registerEntityRenderer(ShupapiumEntities.JOKE_CLOUD_DETECTOR.get(), ThrownItemRenderer::new);
        event.registerBlockEntityRenderer(ShupapiumBlockEntities.MINIGUN_GECKO_CANNON.get(), MinigunBarrelRenderer::new);
        event.registerBlockEntityRenderer(ShupapiumBlockEntities.ROTARY_GECKO_CANNON.get(), RotaryCannonBarrelRenderer::new);
    }
}
