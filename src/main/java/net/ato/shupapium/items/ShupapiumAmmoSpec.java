package net.ato.shupapium.items;

import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.utils.acballistics.ShupapiumAmmoBallistics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public record ShupapiumAmmoSpec(
        ResourceLocation properties,
        List<Supplier<? extends EntityType<?>>> variants,
        boolean affectedByGravity,
        boolean requiresPropellant,
        @Nullable Supplier<? extends Item> spentItem,
        @Nullable ShupapiumMuzzleEffect muzzle,
        ShupapiumAmmoBallistics ballistics
) {
    public static Builder builder() {return new Builder();}

    public static final class Builder {
        private ResourceLocation properties;
        private final List<Supplier<? extends EntityType<?>>> variants = new ArrayList<>();
        private boolean gravity = false;
        private boolean propellant = false;
        private Supplier<? extends Item> spentItem;
        private ShupapiumMuzzleEffect muzzle;
        private ShupapiumAmmoBallistics ballistics = ShupapiumAmmoBallistics.DEFAULT;

        public Builder properties(String name) {
            this.properties = MainShupapium.resource("munition_properties/projectiles/" + name);
            return this;
        }

        @SafeVarargs
        public final Builder variants(Supplier<? extends EntityType<?>>... types) {
            this.variants.addAll(List.of(types));
            return this;
        }

        public Builder gravity(boolean v) {this.gravity = v; return this;}
        public Builder propellant(boolean v) {this.propellant = v; return this;}
        public Builder spentItem(Supplier<? extends Item> s) {this.spentItem = s; return this;}
        public Builder muzzle(ShupapiumMuzzleEffect e) {this.muzzle = e; return this;}
        public Builder ballistics(ShupapiumAmmoBallistics b) {this.ballistics = b; return this;}

        public ShupapiumAmmoSpec build() {
            Objects.requireNonNull(properties, "properties");
            if (variants.isEmpty()) throw new IllegalStateException("Ammo needs at least one variant");
            return new ShupapiumAmmoSpec(properties, List.copyOf(variants), gravity, propellant, spentItem, muzzle, ballistics);
        }
    }
}
