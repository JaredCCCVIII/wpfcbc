package net.ato.shupapium.utils.acprofiles;

import net.ato.shupapium.cannons.ShupapiumACBarrelBlock;
import net.ato.shupapium.cannons.ShupapiumACBreechBlock;
import net.ato.shupapium.utils.actypes.ShupapiumACParts;
import net.ato.shupapium.utils.actypes.ShupapiumACProfile;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class BasicShupapiumACProfile implements ShupapiumACProfile {
    private final ResourceLocation id;
    private final Supplier<? extends ShupapiumACBarrelBlock> barrel;
    private final Supplier<? extends ShupapiumACBreechBlock> breech;
    private final List<Supplier<? extends ShupapiumACBarrelBlock>> alternateBarrels;
    private final Supplier<Set<Block>> compatibleAttachments;
    private final Supplier<SoundEvent> fireSound;
    private final @Nullable BiConsumer<Level, Vec3> customFireSound;
    private final List<ParticleOptions> muzzleParticles;
    private final int fireRate;
    private final int projectilesPerShot;
    private final float baseSpeed;
    private final float baseSpread;
    private final List<Supplier<? extends Item>> ammoTypes;

    private BasicShupapiumACProfile(Builder b) {
        this.id = b.id;
        this.barrel = b.barrel;
        this.breech = b.breech;
        this.alternateBarrels = List.copyOf(b.alternateBarrels);
        this.compatibleAttachments = b.compatibleAttachments;
        this.fireSound = b.fireSound;
        this.customFireSound = b.customFireSound;
        this.muzzleParticles = List.copyOf(b.muzzleParticles);
        this.fireRate = b.fireRate;
        this.projectilesPerShot = b.projectilesPerShot;
        this.baseSpeed = b.baseSpeed;
        this.baseSpread = b.baseSpread;
        this.ammoTypes = List.copyOf(b.ammoTypes);
    }

    @Override
    public ResourceLocation getProfileId() {
        return id;
    }

    @Override
    public ShupapiumACParts parts() {
        return new ShupapiumACParts(barrel.get(), breech.get());
    }

    @Override
    public List<ShupapiumACParts> allParts() {
        List<ShupapiumACParts> list = new ArrayList<>();
        list.add(parts());
        for (Supplier<? extends ShupapiumACBarrelBlock> alt : alternateBarrels) {
            list.add(new ShupapiumACParts(alt.get(), breech.get()));
        }
        return list;
    }

    @Override
    public Set<Block> getCompatibleAttachments() {
        return compatibleAttachments.get();
    }

    @Override
    public SoundEvent getFireSound() {
        return fireSound.get();
    }

    @Override
    public List<ParticleOptions> getMuzzleParticles() {
        return muzzleParticles;
    }

    @Override
    public int getCannonFireRate() {
        return fireRate;
    }

    @Override
    public int getProjectilePerShot() {
        return projectilesPerShot;
    }

    @Override
    public float getCannonBaseSpeed() {
        return baseSpeed;
    }

    @Override
    public float getCanonBaseSpread() {
        return baseSpread;
    }

    @Override
    public List<Item> getAmmoTypes() {
        return ammoTypes.stream().<Item>map(Supplier::get).toList();
    }

    @Override
    public void cannonSoundEvent(Level level, Vec3 pos, float volumeMultiplier, @Nullable SoundEvent override) {
        if (override == null && customFireSound != null) {
            customFireSound.accept(level, pos);
            return;
        }
        ShupapiumACProfile.super.cannonSoundEvent(level, pos, volumeMultiplier, override);
    }

    public static Builder builder(ResourceLocation id) {return new Builder(id);}

    public static final class Builder {
        private final ResourceLocation id;
        private Supplier<? extends ShupapiumACBarrelBlock> barrel;
        private Supplier<? extends ShupapiumACBreechBlock> breech;
        private final List<Supplier<? extends ShupapiumACBarrelBlock>> alternateBarrels = new ArrayList<>();
        private Supplier<Set<Block>> compatibleAttachments = Set::of;
        private Supplier<SoundEvent> fireSound;
        private @Nullable BiConsumer<Level, Vec3> customFireSound;
        private final List<ParticleOptions> muzzleParticles = new ArrayList<>();
        private int fireRate = 600;
        private int projectilesPerShot = 1;
        private float baseSpeed = 0F;
        private float baseSpread = 0F;
        private final List<Supplier<? extends Item>> ammoTypes = new ArrayList<>();

        private Builder(ResourceLocation id) {this.id = id;}

        public Builder parts(Supplier<? extends ShupapiumACBarrelBlock> barrel, Supplier<? extends ShupapiumACBreechBlock> breech) {
            this.barrel = barrel;
            this.breech = breech;
            return this;
        }

        @SafeVarargs
        public final Builder alternateBarrels(Supplier<? extends ShupapiumACBarrelBlock>... barrels) {this.alternateBarrels.addAll(List.of(barrels)); return this;}
        public Builder attachments(Supplier<Set<Block>> whitelist) {this.compatibleAttachments = whitelist; return this;}
        public Builder fireSound(Supplier<SoundEvent> s) {this.fireSound = s; return this;}
        public Builder customFireSound(BiConsumer<Level, Vec3> r) {this.customFireSound = r; return this;}
        public Builder muzzleParticle(ParticleOptions p) {this.muzzleParticles.add(p); return this;}
        public Builder fireRate(int rpm) {this.fireRate = rpm; return this;}
        public Builder projectilesPerShot(int n) {this.projectilesPerShot = n; return this;}
        public Builder cannonSpeed(float v) {this.baseSpeed = v; return this;}
        public Builder cannonSpread(float v) {this.baseSpread = v; return this;}
        @SafeVarargs
        public final Builder ammo(Supplier<? extends Item>... items) {this.ammoTypes.addAll(List.of(items)); return this;}

        public BasicShupapiumACProfile build() {
            Objects.requireNonNull(barrel, "barrel");
            Objects.requireNonNull(breech, "breech");
            Objects.requireNonNull(fireSound, "fireSound");
            return new BasicShupapiumACProfile(this);
        }
    }
}
