package net.ato.shupapium.utils.actypes;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class ShupapiumACProfileListener extends SimpleJsonResourceReloadListener {
    private static final Gson GSON = new Gson();
    public ShupapiumACProfileListener() {
        super(GSON, "autocannon_profiles");
    }

    @Override
    protected void apply(@NotNull Map<ResourceLocation, JsonElement> pObject, @NotNull ResourceManager pResourceManager, @NotNull ProfilerFiller pProfiler) {
        ShupapiumACProfileHandler.clearJsons();

        for (var entry : pObject.entrySet()) {
            ResourceLocation id = entry.getKey();
            JsonObject json = entry.getValue().getAsJsonObject();

            ShupapiumACProfile profile = ShupapiumACProfileHandler.fromJson(id, json);
            ShupapiumACProfileHandler.register(profile);
        }
    }
}
