package net.ato.shupapium.cannons;

import net.ato.shupapium.MainShupapium;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rbasamoyai.createbigcannons.base.CBCTooltip;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ShupapiumGACBlockItem<T extends Block & AutocannonBlock> extends BlockItem implements GeoItem {
    private final T autocannonBlock;
    private final Supplier<BlockEntityWithoutLevelRenderer> rendererFactory;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public ShupapiumGACBlockItem(T block, Item.Properties properties, Supplier<BlockEntityWithoutLevelRenderer> rendererFactory) {
        super(block, properties);
        this.autocannonBlock = block;
        this.rendererFactory = rendererFactory;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        CBCTooltip.appendTextAutocannon(stack, context, tooltipComponents, tooltipFlag, this.autocannonBlock);
    }

    @Override
    public @NotNull InteractionResult place(@NotNull BlockPlaceContext context) {
        InteractionResult result = super.place(context);
        Player player = context.getPlayer();
        AutocannonMaterial material = this.autocannonBlock.getAutocannonMaterial();
        if (player != null && (material.properties().connectsInSurvival() || player.isCreative())) {
            AutocannonBlock.onPlace(context.getLevel(), context.getClickedPos());
        }

        return result;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private BlockEntityWithoutLevelRenderer renderer;

            @Override
            public @Nullable BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (renderer == null) {
                    renderer = rendererFactory.get();
                }

                return renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // Nothing
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
