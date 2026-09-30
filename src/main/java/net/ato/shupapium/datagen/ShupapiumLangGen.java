package net.ato.shupapium.datagen;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumBlocks;
import net.ato.shupapium.ShupapiumEntities;
import net.ato.shupapium.ShupapiumItems;
import net.minecraft.resources.ResourceLocation;

import java.util.Objects;

public class ShupapiumLangGen {
    private static final String DUMMY_RAGDOLL_SPAWN_EGG_NAME = Objects.requireNonNull(ShupapiumItems.DUMMY_RAGDOLL_SPAWN_EGG.getKey()).location().getPath();
    private static final String HEAVY_DUMMY_RAGDOLL_SPAWN_EGG_NAME = Objects.requireNonNull(ShupapiumItems.HEAVY_DUMMY_RAGDOLL_SPAWN_EGG.getKey()).location().getPath();
    private static final String DUMMY_RAGDOLL_NAME = ShupapiumEntities.DUMMY_RAGDOLL_ENTITY.getKey().location().getPath();
    private static final String HEAVY_DUMMY_RAGDOLL_NAME = ShupapiumEntities.HEAVY_DUMMY_RAGDOLL_ENTITY.getKey().location().getPath();
    public static void prepare() {
        // Misc
        MainShupapium.REGISTRATE.addRawLang("item." + MainShupapium.MOD_ID + "." + DUMMY_RAGDOLL_SPAWN_EGG_NAME, "Dummy Ragdoll Spawn Egg");
        MainShupapium.REGISTRATE.addRawLang("item." + MainShupapium.MOD_ID + "." + HEAVY_DUMMY_RAGDOLL_SPAWN_EGG_NAME, "Heavy Dummy Ragdoll Spawn Egg");
        MainShupapium.REGISTRATE.addRawLang("entity." + MainShupapium.MOD_ID + "." + DUMMY_RAGDOLL_NAME, "Dummy Ragdoll");
        MainShupapium.REGISTRATE.addRawLang("entity." + MainShupapium.MOD_ID + "." + HEAVY_DUMMY_RAGDOLL_NAME, "Heavy Dummy Ragdoll");
        MainShupapium.REGISTRATE.addRawLang("subtitle." + MainShupapium.MOD_ID + "." + DUMMY_RAGDOLL_NAME + ".ambient", "Test Dummy murmurs");
        MainShupapium.REGISTRATE.addRawLang("subtitle." + MainShupapium.MOD_ID + "." + DUMMY_RAGDOLL_NAME + ".hurt", "Test Dummy in pain");
        MainShupapium.REGISTRATE.addRawLang("subtitle." + MainShupapium.MOD_ID + "." + DUMMY_RAGDOLL_NAME + ".death", "Test Dummy dying");
        MainShupapium.REGISTRATE.addRawLang("subtitle." + MainShupapium.MOD_ID + "." + HEAVY_DUMMY_RAGDOLL_NAME + ".ambient", "Test Dummy murmurs");
        MainShupapium.REGISTRATE.addRawLang("subtitle." + MainShupapium.MOD_ID + "." + HEAVY_DUMMY_RAGDOLL_NAME + ".hurt", "Test Dummy in pain");
        MainShupapium.REGISTRATE.addRawLang("subtitle." + MainShupapium.MOD_ID + "." + HEAVY_DUMMY_RAGDOLL_NAME + ".death", "Test Dummy dying");
        MainShupapium.REGISTRATE.addRawLang("effect." + MainShupapium.MOD_ID + ".joke_effect", "Chistosada");

        // Shells
        tooltip(ShupapiumBlocks.SMOKE_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("Common Smoke Projectile.")
                .conditionAndBehavior("On Detonation", "Drops a smoke cloud on impact.");
        tooltip(ShupapiumBlocks.BARREL_SHELL_BLOCK)
                .header("Show info")
                .summary("Throwable barrel...")
                .conditionAndBehavior("On Detonation", "Explodes on impact.");
        tooltip(ShupapiumBlocks.TOXIC_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("Smells like rotten eggs.")
                .conditionAndBehavior("On Detonation", "Dissipates a toxic smoke upon impact, stunning the near living entities.");
        tooltip(ShupapiumBlocks.SMALL_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("Stronger than a common autocannon projectile.")
                .conditionAndBehavior("On Detonation", "Destroys a small area and drops debris.");
        tooltip(ShupapiumBlocks.SMALL_BOMB_CLUSTER_SHELL_BLOCK)
                .header("Show info")
                .summary("Cluster little brother.")
                .conditionAndBehavior("On Firing", "Drops multiple small bombs in a small area.");
        tooltip(ShupapiumBlocks.MEDIUM_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("A good upgrade from the artillery ammo.")
                .conditionAndBehavior("On Detonation", "Destroys a medium area and drop debris.");
        tooltip(ShupapiumBlocks.HEAVY_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("Try to upgrade it!")
                .conditionAndBehavior("On Detonation", "The first level of ordinance destroys a large area and drops debris.");
        tooltip(ShupapiumBlocks.FIRE_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("Forged with cursed fire...")
                .conditionAndBehavior("On Detonation", "The red ordinance destroys a medium area and drops fire around.");
        tooltip(ShupapiumBlocks.SUPER_HEAVY_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("Try to upgrade it again.")
                .conditionAndBehavior("On Detonation", "The second level of ordinance destroys a big area and drops debris.");
        tooltip(ShupapiumBlocks.BLOCK_BUSTER_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("Not so strong as the fission one.")
                .conditionAndBehavior("On Detonation", "The last level of ordinance destroys a grand area and drops debris.");
        tooltip(ShupapiumBlocks.KINETIC_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("Sharp head!")
                .conditionAndBehavior("On Detonation", "The kinetic ordinance make a small hole when impact and after a few while explodes, dropping debris around the impact zone.");
        tooltip(ShupapiumBlocks.IR_SEEKER_SHELL_BLOCK)
                .header("Show info")
                .summary("What was that?")
                .conditionAndBehavior("On Firing", "Follows a heat source and explodes in contact.");
        tooltip(ShupapiumBlocks.CLUSTER_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("They are spamming us with shells!")
                .conditionAndBehavior("On Firing", "The cluster ordinance drops explosive pieces to deal various damage near the impact zone.");
        tooltip(ShupapiumBlocks.FISSION_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("Not so strong as the fusion one.")
                .conditionAndBehavior("On Detonation", "Unleashes a powerful nuclear blast and creates a mushroom of radioactive gas.");
        tooltip(ShupapiumBlocks.FUSION_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("I'm become death, the destroyers of worlds.")
                .conditionAndBehavior("On Detonation", "Unleashes a powerful nuclear blast and evaporates everything.");
    }

    private static class TooltipBuilder {
        private final ResourceLocation loc;
        private final String type;
        private int cbCount = 1;
        private int caCount = 1;
        public TooltipBuilder(ItemProviderEntry<?, ?> provider, boolean item) {
            this.loc = provider.getId();
            this.type = item ? "item" : "block";
        }

        public TooltipBuilder header(String enUS) {
            MainShupapium.REGISTRATE.addLang(this.type, this.loc, "tooltip", enUS);
            return this;
        }

        public TooltipBuilder summary(String enUS) {
            MainShupapium.REGISTRATE.addLang(this.type, this.loc, "tooltip.summary", enUS);
            return this;
        }

        public TooltipBuilder conditionAndBehavior(String enUSCondition, String enUSBehaviour) {
            MainShupapium.REGISTRATE.addLang(this.type, this.loc, String.format("tooltip.condition%d", this.cbCount), enUSCondition);
            MainShupapium.REGISTRATE.addLang(this.type, this.loc, String.format("tooltip.behaviour%d", this.cbCount), enUSBehaviour);
            this.cbCount++;
            return this;
        }

        public TooltipBuilder controlAndAction(String enUSControl, String enUSAction) {
            MainShupapium.REGISTRATE.addLang(this.type, this.loc, String.format("tooltip.control%d", this.caCount), enUSControl);
            MainShupapium.REGISTRATE.addLang(this.type, this.loc, String.format("tooltip.action%d", this.caCount), enUSAction);
            this.caCount++;
            return this;
        }
    }

    private static TooltipBuilder tooltip(BlockEntry<?> provider) {
        return new TooltipBuilder(provider, false);
    }

    private static TooltipBuilder tooltip(ItemEntry<?> provider) {
        return new TooltipBuilder(provider, true);
    }
}
