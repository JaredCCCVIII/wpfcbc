package net.ato.shupapium.ponder;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.ato.shupapium.ShupapiumBlocks;
import net.ato.shupapium.ShupapiumItems;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import rbasamoyai.createbigcannons.CreateBigCannons;

public class ShupapiumPonderTags {
    public static final ResourceLocation
    MUNITIONS = CreateBigCannons.resource("munitions");

    public static void register(PonderTagRegistrationHelper<ResourceLocation> helper) {
        PonderTagRegistrationHelper<RegistryEntry<?, ?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);

        HELPER.addToTag(MUNITIONS)
                .add(ShupapiumBlocks.SMOKE_BOMB_SHELL_BLOCK)
                .add(ShupapiumBlocks.BARREL_SHELL_BLOCK)
                .add(ShupapiumBlocks.TOXIC_BOMB_SHELL_BLOCK)
                .add(ShupapiumBlocks.SMALL_BOMB_SHELL_BLOCK)
                .add(ShupapiumBlocks.SMALL_BOMB_CLUSTER_SHELL_BLOCK)
                .add(ShupapiumBlocks.MEDIUM_BOMB_SHELL_BLOCK)
                .add(ShupapiumBlocks.HEAVY_BOMB_SHELL_BLOCK)
                .add(ShupapiumBlocks.FIRE_BOMB_SHELL_BLOCK)
                .add(ShupapiumBlocks.SUPER_HEAVY_BOMB_SHELL_BLOCK)
                .add(ShupapiumBlocks.BLOCK_BUSTER_BOMB_SHELL_BLOCK)
                .add(ShupapiumBlocks.KINETIC_BOMB_SHELL_BLOCK)
                .add(ShupapiumBlocks.IR_SEEKER_SHELL_BLOCK)
                .add(ShupapiumBlocks.CLUSTER_BOMB_SHELL_BLOCK)
                .add(ShupapiumBlocks.FISSION_BOMB_SHELL_BLOCK)
                .add(ShupapiumBlocks.FUSION_BOMB_SHELL_BLOCK)
                .add(ShupapiumBlocks.SHUPAPIUM_AMMO_CONTAINER)
                .add(ShupapiumBlocks.CREATIVE_SHUPAPIUM_AMMO_CONTAINER);
    }
}
