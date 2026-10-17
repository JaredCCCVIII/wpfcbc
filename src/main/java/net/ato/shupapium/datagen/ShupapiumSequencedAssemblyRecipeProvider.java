package net.ato.shupapium.datagen;

import com.simibubi.create.api.data.recipe.SequencedAssemblyRecipeGen;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.CommonMetal;
import com.wariumce.init.CrustyChunksModItems;
import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import rbasamoyai.createbigcannons.CBCTags;

import java.util.concurrent.CompletableFuture;
import java.util.function.UnaryOperator;

public class ShupapiumSequencedAssemblyRecipeProvider extends SequencedAssemblyRecipeGen {
    public ShupapiumSequencedAssemblyRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, MainShupapium.MOD_ID);
    }

    GeneratedRecipe

    MEDIUM_BULLET_AMMO = create("medium_bullet_ammo", b -> b.require(CrustyChunksModItems.BULLET.get())
            .transitionTo(CrustyChunksModItems.BULLET.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.MEDIUM_BULLET_AMMO_ITEM, 1)),
    LARGE_BULLET_AMMO = create("large_bullet_ammo", b -> b.require(CrustyChunksModItems.LARGE_BULLET.get())
            .transitionTo(CrustyChunksModItems.LARGE_BULLET.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.LARGE_BULLET_AMMO_ITEM, 1)),
    EXTRA_LARGE_AMMO = create("extra_large_ammo", b -> b.require(CrustyChunksModItems.EXTRA_LARGE_BULLET.get())
            .transitionTo(CrustyChunksModItems.EXTRA_LARGE_BULLET.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.EXTRA_LARGE_BULLET_AMMO_ITEM, 1)),
    HUGE_BULLET_AMMO = create("huge_bullet_ammo", b -> b.require(CrustyChunksModItems.HUGE_BULLET.get())
            .transitionTo(CrustyChunksModItems.HUGE_BULLET.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.HUGE_BULLET_AMMO_ITEM, 1)),
    HUGE_HE_BULLET_AMMO = create("huge_he_bullet_ammo", b -> b.require(CrustyChunksModItems.HUGE_HE_BULLET.get())
            .transitionTo(CrustyChunksModItems.HUGE_HE_BULLET.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.HUGE_HE_BULLET_AMMO_ITEM, 1)),
    SMALL_SHELL_AMMO = create("small_shell_ammo", b -> b.require(CrustyChunksModItems.SMALL_SHELL.get())
            .transitionTo(CrustyChunksModItems.SMALL_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.SMALL_SHELL_AMMO_ITEM, 1)),
    SMALL_AP_SHELL_AMMO = create("small_ap_shell_ammo", b -> b.require(CrustyChunksModItems.SMALL_AP_SHELL.get())
            .transitionTo(CrustyChunksModItems.SMALL_AP_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.SMALL_AP_SHELL_AMMO_ITEM, 1)),
    SMALL_FLAK_SHELL_AMMO = create("small_flak_shell_ammo", b -> b.require(CrustyChunksModItems.SMALL_FLAK_SHELL.get())
            .transitionTo(CrustyChunksModItems.SMALL_FLAK_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.SMALL_FLAK_SHELL_AMMO_ITEM, 1)),
    LARGE_SHELL_AMMO = create("large_shell_ammo", b -> b.require(CrustyChunksModItems.LARGE_SHELL.get())
            .transitionTo(CrustyChunksModItems.LARGE_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.LARGE_SHELL_AMMO_ITEM, 1)),
    LARGE_SOLID_SHELL_AMMO = create("large_solid_shell_ammo", b -> b.require(CrustyChunksModItems.SOLID_SHELL.get())
            .transitionTo(CrustyChunksModItems.SOLID_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.LARGE_SOLID_SHELL_AMMO_ITEM, 1)),
    LARGE_HEAT_SHELL_AMMO = create("large_heat_shell_ammo", b -> b.require(CrustyChunksModItems.HEAT_SHELL.get())
            .transitionTo(CrustyChunksModItems.HEAT_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.LARGE_HEAT_SHELL_AMMO_ITEM, 1)),
    LARGE_AP_SHELL_AMMO = create("large_ap_shell_ammo", b -> b.require(CrustyChunksModItems.AP_SHELL.get())
            .transitionTo(CrustyChunksModItems.AP_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.LARGE_AP_SHELL_AMMO_ITEM, 1)),
    LARGE_FLAK_SHELL_AMMO = create("large_flak_shell_ammo", b -> b.require(CrustyChunksModItems.FLAK_SHELL.get())
            .transitionTo(CrustyChunksModItems.FLAK_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.LARGE_FLAK_SHELL_AMMO_ITEM, 1)),
    LARGE_SMOKE_SHELL_AMMO = create("large_smoke_shell_ammo", b -> b.require(CrustyChunksModItems.SMOKE_SHELL.get())
            .transitionTo(CrustyChunksModItems.SMOKE_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.LARGE_SMOKE_SHELL_AMMO_ITEM, 1)),
    ARTILLERY_SHELL_AMMO = create("artillery_shell_ammo", b -> b.require(CrustyChunksModItems.ARTILLERY_SHELL.get())
            .transitionTo(CrustyChunksModItems.ARTILLERY_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.ARTILLERY_SHELL_AMMO_ITEM, 1)),
    ARTILLERY_SOLID_SHELL_AMMO = create("artillery_solid_shell_ammo", b -> b.require(CrustyChunksModItems.ARTILLERY_SOLID_SHELL.get())
            .transitionTo(CrustyChunksModItems.ARTILLERY_SOLID_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.ARTILLERY_SOLID_SHELL_AMMO_ITEM, 1)),
    ARTILLERY_GAS_SHELL_AMMO = create("artillery_gas_shell_ammo", b -> b.require(CrustyChunksModItems.GAS_ARTILLERY_SHELL.get())
            .transitionTo(CrustyChunksModItems.GAS_ARTILLERY_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.ARTILLERY_GAS_SHELL_AMMO_ITEM, 1)),
    ARTILLERY_FIRE_SHELL_AMMO = create("artillery_fire_shell_ammo", b -> b.require(CrustyChunksModItems.FIRE_ARTILLERY_SHELL.get())
            .transitionTo(CrustyChunksModItems.FIRE_ARTILLERY_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.ARTILLERY_FIRE_SHELL_AMMO_ITEM, 1)),
    MORTAR_SHELL_AMMO = create("mortar_shell_ammo", b -> b.require(CrustyChunksModItems.MORTAR_SHELL.get())
            .transitionTo(CrustyChunksModItems.MORTAR_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.MORTAR_SHELL_AMMO_ITEM, 1)),
    SMOKE_MORTAR_SHELL_AMMO = create("smoke_mortar_shell_ammo", b -> b.require(CrustyChunksModItems.SMOKE_MORTAR_SHELL.get())
            .transitionTo(CrustyChunksModItems.SMOKE_MORTAR_SHELL.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.SMOKE_MORTAR_SHELL_AMMO_ITEM, 1)),
    ARMOR_PEELER_ROCKET_AMMO = create("armor_peeler_rocket_ammo", b -> b.require(CrustyChunksModItems.ARMOR_PEELER_ROCKET.get())
            .transitionTo(CrustyChunksModItems.ARMOR_PEELER_ROCKET.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.ARMOR_PEELER_ROCKET_AMMO_ITEM, 1)),
    FIRE_SPEAR_ROCKET_AMMO = create("fire_spear_rocket_ammo", b -> b.require(CrustyChunksModItems.FIRE_SPEAR_ROCKET.get())
            .transitionTo(CrustyChunksModItems.FIRE_SPEAR_ROCKET.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.FIRE_SPEAR_ROCKET_AMMO_ITEM, 1)),
    SEEKER_SPEAR_ROCKET_AMMO = create("seeker_spear_rocket_ammo", b -> b.require(CrustyChunksModItems.SEEKER_SPEAR_ROCKET.get())
            .transitionTo(CrustyChunksModItems.SEEKER_SPEAR_ROCKET.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.SEEKER_SPEAR_ROCKET_AMMO_ITEM, 1)),
    RADAR_SPEAR_MISSILE_AMMO = create("radar_spear_missile_ammo", b -> b.require(CrustyChunksModItems.RADAR_SPEAR_MISSILE.get())
            .transitionTo(CrustyChunksModItems.RADAR_SPEAR_MISSILE.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.RADAR_SPEAR_MISSILE_AMMO_ITEM, 1)),
    STRIKE_SPEAR_MISSILE_AMMO = create("strike_spear_missile_ammo", b -> b.require(CrustyChunksModItems.STRIKE_SPEAR_MISSILE.get())
            .transitionTo(CrustyChunksModItems.STRIKE_SPEAR_MISSILE.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.STRIKE_SPEAR_MISSILE_AMMO_ITEM, 1)),
    FLARE_CHARGE_AMMO = create("flare_charge_ammo", b -> b.require(CrustyChunksModItems.FLARE_CHARGE.get())
            .transitionTo(CrustyChunksModItems.FLARE_CHARGE.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.FLARE_CHARGE_AMMO_ITEM, 1)),
    CHAFF_CHARGE_AMMO = create("chaff_charge_ammo", b -> b.require(CrustyChunksModItems.CHAFF_CHARGE.get())
            .transitionTo(CrustyChunksModItems.CHAFF_CHARGE.get())
            .loops(1)
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CommonMetal.COPPER.plates))
            .addStep(DeployerApplicationRecipe::new, rb -> rb.require(CBCTags.CBCItemTags.GUNPOWDER))
            .addOutput(ShupapiumItems.CHAFF_CHARGE_AMMO_ITEM, 1));

    protected GeneratedRecipe create(String name, UnaryOperator<SequencedAssemblyRecipeBuilder> transform) {
        GeneratedRecipe generatedRecipe =
                c -> transform.apply(new SequencedAssemblyRecipeBuilder(MainShupapium.resource(name)))
                        .build(c);
        this.all.add(generatedRecipe);
        return generatedRecipe;
    }
}
