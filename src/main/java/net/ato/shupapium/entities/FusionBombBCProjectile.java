package net.ato.shupapium.entities;

import com.github.alexmodguy.alexscaves.client.particle.ACParticleRegistry;
import com.github.alexmodguy.alexscaves.server.block.blockentity.NuclearSirenBlockEntity;
import com.github.alexmodguy.alexscaves.server.block.poi.ACPOIRegistry;
import com.google.common.base.Predicates;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.wariumce.procedures.FusionExplosionProcedure;
import net.ato.shupapium.ShupapiumBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.fml.ModList;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

import java.util.stream.Stream;

public class FusionBombBCProjectile extends AbstractShupapiumBCProjectile {
    private static final EntityDataAccessor<Boolean> ACTIVATED = SynchedEntityData.defineId(FusionBombBCProjectile.class, EntityDataSerializers.BOOLEAN);
    public FusionBombBCProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.FUSION_BOMB_SHELL_BLOCK;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ACTIVATED, false);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setActivated(tag.getBoolean("activated"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("activated", isActivated());
    }

    @Override
    protected void onTickRotate() {
        super.onTickRotate();
        if (ModList.get().isLoaded("alexscaves")) {
            if (this.level() instanceof ServerLevel server && tickCount % 10 == 0) {
                getNearbySirens(server, 256).forEach(this::activateSiren);
            }
            boolean b = random.nextFloat() < 0.5F;
            if (this.level().isClientSide && isActivated() && b) {
                Vec3 center = this.getEyePosition();
                this.level().addParticle(ACParticleRegistry.PROTON.get(), center.x, center.y, center.z, center.x, center.y, center.z);
            }
        }
    }

    @Override
    protected void detonate(Position position) {
        FusionExplosionProcedure.execute(this.level(), position.x(), position.y(), position.z());
    }

    private boolean isActivated() {
        return this.entityData.get(ACTIVATED);
    }

    private void setActivated(boolean activated) {
        this.entityData.set(ACTIVATED, activated);
    }

    private void activateSiren(BlockPos pos) {
        if(level().getBlockEntity(pos) instanceof NuclearSirenBlockEntity nuclearSirenBlock){
            nuclearSirenBlock.setNearestNuclearBomb(this);
        }
    }

    private Stream<BlockPos> getNearbySirens(ServerLevel world, int range) {
        PoiManager poiManager = world.getPoiManager();
        return poiManager.findAll(poiTypeHolder -> poiTypeHolder.is(ACPOIRegistry.NUCLEAR_SIREN.getKey()), Predicates.alwaysTrue(), this.blockPosition(), range, PoiManager.Occupancy.ANY);
    }
}
