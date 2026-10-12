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

        // Config
        MainShupapium.REGISTRATE.addRawLang(MainShupapium.MOD_ID + ".configuration.maxFireRate", "Max Default Fire Rate");
        MainShupapium.REGISTRATE.addRawLang(MainShupapium.MOD_ID + ".configuration.maxFireRate.tooltip", "Maximum cap AC Profile");
        MainShupapium.REGISTRATE.addRawLang(MainShupapium.MOD_ID + ".configuration.acHeat", "Mod's AC Barrels Heat Up");
        MainShupapium.REGISTRATE.addRawLang(MainShupapium.MOD_ID + ".configuration.acHeat.tooltip", "Do mod's barrels heat up?");

        // CBC related
        MainShupapium.REGISTRATE.addRawLang(MainShupapium.MOD_ID + ".gui.autocannon_ammo_container.propellant_slot", "Propellents");
        MainShupapium.REGISTRATE.addLang("block", ShupapiumBlocks.SHUPAPIUM_AMMO_CONTAINER.getId(), "tooltip.main_ammo", "x%1$s %2$s");
        MainShupapium.REGISTRATE.addLang("block", ShupapiumBlocks.SHUPAPIUM_AMMO_CONTAINER.getId(), "tooltip.propellants", "Propellants: x%1$s %2$s");
        MainShupapium.REGISTRATE.addLang("exception", MainShupapium.resource("cannon_mount"), "invalid_barrel", "Invalid barrel detected.");
        MainShupapium.REGISTRATE.addLang("exception", MainShupapium.resource("cannon_mount"), "invalid_attachment", "Invalid attachment detected.");
        MainShupapium.REGISTRATE.addLang("exception", MainShupapium.resource("cannon_mount"), "invalid_breech", "Invalid breech detected.");
        MainShupapium.REGISTRATE.addLang("exception", MainShupapium.resource("cannon_mount"), "invalid_compatibility", "Invalid cannon compatibility detected.");
        MainShupapium.REGISTRATE.addLang("exception", MainShupapium.resource("cannon_mount"), "invalid_ammo_type", "Invalid ammo type detected.");
        MainShupapium.REGISTRATE.addLang("exception", MainShupapium.resource("cannon_mount"), "duplicated_attachments", "Duplicated attachments detected.");
        MainShupapium.REGISTRATE.addLang("exception", MainShupapium.resource("cannon_mount"), "misplaced_attachment", "Misplaced attachment detected.");
        MainShupapium.REGISTRATE.addLang("exception", MainShupapium.resource("cannon_mount"), "mixed_barrels", "Mixed barrel detected.");
        MainShupapium.REGISTRATE.addLang("block", MainShupapium.resource("cannon_carriage"), "hotbar.fireRate", "Rate of fire: %1$s RPM");

        // AC Ammo
        tooltip(ShupapiumItems.MEDIUM_BULLET_AMMO_ITEM)
                .header("Show info")
                .summary("Tracers included!")
                .conditionAndBehavior("Projectile Info:", "Medium bullet projectile, now in a turret-like version.");
        tooltip(ShupapiumItems.LARGE_BULLET_AMMO_ITEM)
                .header("Show info")
                .summary("Two tracers included!")
                .conditionAndBehavior("Projectile Info:", "Large bullet projectile, effective on non-vehicle entities.");
        tooltip(ShupapiumItems.EXTRA_LARGE_BULLET_AMMO_ITEM)
                .header("Show info")
                .summary("Sharp tracer included!")
                .conditionAndBehavior("Projectile Info:", "Extra large bullet projectile, more penetrant.");
        tooltip(ShupapiumItems.HUGE_BULLET_AMMO_ITEM)
                .header("Show info")
                .summary("It's really a small shell projectile...")
                .conditionAndBehavior("Projectile Info:", "The very first iteration of shells, effective on vehicles.");
        tooltip(ShupapiumItems.HUGE_HE_BULLET_AMMO_ITEM)
                .header("Show info")
                .summary("A highly explosive small shell projectile...")
                .conditionAndBehavior("Projectile Info:", "The very first iteration of shells, now with explosives included.");

        // Shells
        tooltip(ShupapiumBlocks.SMOKE_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("Common Smoke Projectile.")
                .conditionAndBehavior("On Detonation", "Drops a smoke cloud on impact.");
        tooltip(ShupapiumBlocks.BARREL_SHELL_BLOCK)
                .header("Show info")
                .summary("Throwable barrel...")
                .conditionAndBehavior("On Detonation", "Its internal flammable fuel explodes.");
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

        tooltip(ShupapiumBlocks.JOKE_BOMB_SHELL_BLOCK)
                .header("Show info")
                .summary("CHISTE!")
                .conditionAndBehavior("On Detonation", "Converts targets into chistosos.");
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
