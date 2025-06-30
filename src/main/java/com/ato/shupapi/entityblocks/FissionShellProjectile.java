package com.ato.shupapi.entityblocks;

import com.ato.shupapi.MainShupapium;
import com.ato.shupapi.ShupapiumBlocks;
import com.github.alexmodguy.alexscaves.client.particle.ACParticleRegistry;
import com.github.alexmodguy.alexscaves.server.block.blockentity.NuclearSirenBlockEntity;
import com.github.alexmodguy.alexscaves.server.block.poi.ACPOIRegistry;
import com.google.common.base.Predicates;
import net.mcreator.crustychunks.procedures.FissionExplosionProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.index.CBCMunitionPropertiesHandlers;
import rbasamoyai.createbigcannons.munitions.ShellExplosion;
import rbasamoyai.createbigcannons.munitions.big_cannon.FuzedBigCannonProjectile;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonCommonShellProperties;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonFuzePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.big_cannon.config.BigCannonProjectilePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.BallisticPropertiesComponent;
import rbasamoyai.createbigcannons.munitions.config.components.EntityDamagePropertiesComponent;

import java.util.stream.Stream;

public class FissionShellProjectile extends FuzedBigCannonProjectile {
    public FissionShellProjectile(EntityType<? extends FuzedBigCannonProjectile> type, Level level) {
        super(type, level);
    }

    private static final EntityDataAccessor<Boolean> ACTIVATED = SynchedEntityData.defineId(FissionShellProjectile.class, EntityDataSerializers.BOOLEAN);

    @Override
    protected @NotNull BigCannonFuzePropertiesComponent getFuzeProperties() {
        return this.getAllProperties().fuze();
    }

    @Override
    protected void detonate(Position position) {
        FissionExplosionProcedure.execute(this.level(), position.x(), position.y(), position.z());
        this.discard();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ACTIVATED, false);
    }

    private boolean isActivated() {
        return this.entityData.get(ACTIVATED);
    }

    private void setActivated(boolean activated) {
        this.entityData.set(ACTIVATED, activated);
    }

    @Override
    public BlockState getRenderedBlockState() {
        return ShupapiumBlocks.FISSION_SHELL.getDefaultState().setValue(BlockStateProperties.FACING, Direction.NORTH);
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

    private Stream<BlockPos> getNearbySirens(ServerLevel world, int range) {
        PoiManager poiManager = world.getPoiManager();
        return poiManager.findAll(poiTypeHolder -> {
            assert ACPOIRegistry.NUCLEAR_SIREN.getKey() != null;
            return poiTypeHolder.is(ACPOIRegistry.NUCLEAR_SIREN.getKey());
        }, Predicates.alwaysTrue(), this.blockPosition(), range, PoiManager.Occupancy.ANY);
    }

    @Override
    protected @NotNull BigCannonProjectilePropertiesComponent getBigCannonProjectileProperties() {
        return this.getAllProperties().bigCannonProperties();
    }

    @Override
    public @NotNull EntityDamagePropertiesComponent getDamageProperties() {
        return this.getAllProperties().damage();
    }

    @Override
    protected @NotNull BallisticPropertiesComponent getBallisticProperties() {
        return this.getAllProperties().ballistics();
    }

    protected BigCannonCommonShellProperties getAllProperties() {
        return CBCMunitionPropertiesHandlers.COMMON_SHELL_BIG_CANNON_PROJECTILE.getPropertiesOf(this);
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
}
