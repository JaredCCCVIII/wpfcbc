package net.ato.shupapium.advancements;

import com.google.gson.JsonObject;
import net.ato.shupapium.MainShupapium;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import org.jetbrains.annotations.NotNull;

public class NuclearDetonationTrigger extends SimpleCriterionTrigger<NuclearDetonationTrigger.Instance> {
    public static final ResourceLocation ID = new ResourceLocation(MainShupapium.MODID, "nuclear_explosion_trigger");

    @Override
    protected NuclearDetonationTrigger.@NotNull Instance createInstance(@NotNull JsonObject pJson, @NotNull ContextAwarePredicate pPredicate, @NotNull DeserializationContext pDeserializationContext) {
        String type = GsonHelper.getAsString(pJson, "type");
        return new Instance(pPredicate, type);
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer pPlayer, String type) {
        this.trigger(pPlayer, instance -> instance.matches(type));
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        private final String type;
        public Instance(ContextAwarePredicate playerPredicate, String type) {
            super(ID, playerPredicate);
            this.type = type;
        }

        public boolean matches(String type) {
            return this.type.equals(type);
        }
    }
}
