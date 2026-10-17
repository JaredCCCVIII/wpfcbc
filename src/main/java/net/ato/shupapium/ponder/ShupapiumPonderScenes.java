package net.ato.shupapium.ponder;

import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.ato.shupapium.ShupapiumBlocks;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import rbasamoyai.createbigcannons.ponder.CannonLoadingScenes;

public class ShupapiumPonderScenes {
    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        PonderSceneRegistrationHelper<ItemProviderEntry<?, ?>> HELPER = helper.withKeyFunction(RegistryEntry::getId);

        HELPER.forComponents(ShupapiumBlocks.SMOKE_BOMB_SHELL_BLOCK, ShupapiumBlocks.BARREL_SHELL_BLOCK, ShupapiumBlocks.TOXIC_BOMB_SHELL_BLOCK, ShupapiumBlocks.SMALL_BOMB_SHELL_BLOCK,
                ShupapiumBlocks.SMALL_BOMB_CLUSTER_SHELL_BLOCK, ShupapiumBlocks.MEDIUM_BOMB_SHELL_BLOCK, ShupapiumBlocks.HEAVY_BOMB_SHELL_BLOCK, ShupapiumBlocks.FIRE_BOMB_SHELL_BLOCK,ShupapiumBlocks.SUPER_HEAVY_BOMB_SHELL_BLOCK, ShupapiumBlocks.BLOCK_BUSTER_BOMB_SHELL_BLOCK,
                        ShupapiumBlocks.KINETIC_BOMB_SHELL_BLOCK, ShupapiumBlocks.IR_SEEKER_SHELL_BLOCK, ShupapiumBlocks.CLUSTER_BOMB_SHELL_BLOCK, ShupapiumBlocks.FISSION_BOMB_SHELL_BLOCK, ShupapiumBlocks.FUSION_BOMB_SHELL_BLOCK)
                .addStoryBoard("munitions/fuzing_munitions", CannonLoadingScenes::fuzingMunitions);

        HELPER.forComponents(ShupapiumBlocks.SHUPAPIUM_AMMO_CONTAINER, ShupapiumBlocks.CREATIVE_SHUPAPIUM_AMMO_CONTAINER)
                .addStoryBoard("munitions/using_autocannon_ammo_container", CannonLoadingScenes::usingAutocannonAmmoContainer)
                .addStoryBoard("munitions/filling_autocannon_ammo_container", CannonLoadingScenes::fillingAutocannonAmmoContainer)
                .addStoryBoard("munitions/automating_autocannon_ammo_container", CannonLoadingScenes::automatingAutocannonAmmoContainer);

    }
}
