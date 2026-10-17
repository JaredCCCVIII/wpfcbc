package net.ato.shupapium.ponder;

import net.ato.shupapium.MainShupapium;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.CreateBigCannons;

public class ShupapiumPonderPlugin implements PonderPlugin {
    @Override
    public @NotNull String getModId() {
        return CreateBigCannons.MOD_ID;
    }

    @Override
    public void registerScenes(@NotNull PonderSceneRegistrationHelper<ResourceLocation> helper) {
        ShupapiumPonderScenes.register(helper);
    }

    @Override
    public void registerTags(@NotNull PonderTagRegistrationHelper<ResourceLocation> helper) {
        ShupapiumPonderTags.register(helper);
    }
}
