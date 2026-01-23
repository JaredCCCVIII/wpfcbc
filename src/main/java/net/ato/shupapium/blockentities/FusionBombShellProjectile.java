package net.ato.shupapium.blockentities;

import com.github.alexmodguy.alexscaves.client.particle.ACParticleRegistry;
import com.github.alexmodguy.alexscaves.server.block.blockentity.NuclearSirenBlockEntity;
import com.github.alexmodguy.alexscaves.server.block.poi.ACPOIRegistry;
import com.google.common.base.Predicates;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.ato.shupapium.ShupapiumAdvancements;
import net.ato.shupapium.ShupapiumBlocks;
import net.mcreator.crustychunks.procedures.FusionBombHitProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;

import java.util.stream.Stream;

public class FusionBombShellProjectile extends AbstractShupapiumBCProjectile {
    private static final EntityDataAccessor<Boolean> ACTIVATED = SynchedEntityData.defineId(FusionBombShellProjectile.class, EntityDataSerializers.BOOLEAN);
    public FusionBombShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public BlockEntry<?> getBlock() {
        return ShupapiumBlocks.FUSION_BOMB_SHELL_BLOCK;
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.setOwner(this.level().getNearestPlayer(this, 28.0D));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ACTIVATED, false);
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

    private boolean isActivated() {
        return this.entityData.get(ACTIVATED);
    }

    private void setActivated(boolean activated) {
        this.entityData.set(ACTIVATED, activated);
    }

    private Stream<BlockPos> getNearbySirens(ServerLevel world, int range) {
        PoiManager poiManager = world.getPoiManager();
        return poiManager.findAll(poiTypeHolder -> {
            assert ACPOIRegistry.NUCLEAR_SIREN.getKey() != null;
            return poiTypeHolder.is(ACPOIRegistry.NUCLEAR_SIREN.getKey());
        }, Predicates.alwaysTrue(), this.blockPosition(), range, PoiManager.Occupancy.ANY);
    }

    @Override
    protected void onTickRotate() {
        super.onTickRotate();
        if (ModList.get().isLoaded("alexscaves")) {
            if (level() instanceof ServerLevel server && tickCount % 10 == 0) {
                getNearbySirens(server, 256).forEach(
                        pos -> {
                            if (server.getBlockEntity(pos) instanceof NuclearSirenBlockEntity siren)
                                siren.setNearestNuclearBomb(this);
                        });
            }
            boolean b = random.nextFloat() < 0.5f;
            if (level().isClientSide && isActivated() && b) {
                Vec3 center = this.getEyePosition();
                level().addParticle(ACParticleRegistry.PROTON.get(), center.x, center.y, center.z, center.x, center.y, center.z);
            }
        }
    }

    @Override
    protected void detonate(Position position) {
        FusionBombHitProcedure.execute(this.level(), this);
        if (!this.level().isClientSide && this.getOwner() instanceof ServerPlayer player) {
            ShupapiumAdvancements.NUCLEAR_DETONATION.trigger(player, "fusion");
        }
    }
}
