package net.ato.shupapium.utils;

import com.simibubi.create.api.contraption.ContraptionType;
import com.simibubi.create.content.contraptions.AssemblyException;
import com.simibubi.create.content.contraptions.StructureTransform;
import net.ato.shupapium.MainShupapium;
import net.ato.shupapium.ShupapiumContraptionsTypes;
import net.ato.shupapium.blockentities.ShupapiumACBreechBlockEntity;
import net.ato.shupapium.blocks.ShupapiumACBarrelBlock;
import net.ato.shupapium.blocks.ShupapiumACBreechBlock;
import net.ato.shupapium.blocks.ShupapiumACRecoilSpringBlock;
import net.ato.shupapium.items.ShupapiumAmmoContainerItem;
import net.ato.shupapium.items.ShupapiumAmmoItem;
import net.ato.shupapium.utils.actypes.ShupapiumACParts;
import net.ato.shupapium.utils.actypes.ShupapiumACProfile;
import net.ato.shupapium.utils.actypes.ShupapiumACProfileHandler;
import net.mcreator.crustychunks.procedures.MuzzleFlashProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Main;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import rbasamoyai.createbigcannons.cannon_control.ControlPitchContraption;
import rbasamoyai.createbigcannons.cannon_control.cannon_mount.CannonMountBlockEntity;
import rbasamoyai.createbigcannons.cannon_control.cannon_types.CBCCannonContraptionTypes;
import rbasamoyai.createbigcannons.cannon_control.cannon_types.ICannonContraptionType;
import rbasamoyai.createbigcannons.cannon_control.contraption.MountedAutocannonContraption;
import rbasamoyai.createbigcannons.cannon_control.contraption.PitchOrientedContraptionEntity;
import rbasamoyai.createbigcannons.cannon_control.fixed_cannon_mount.FixedCannonMountBlockEntity;
import rbasamoyai.createbigcannons.cannons.ItemCannonBehavior;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.IAutocannonBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.MovesWithAutocannonRecoilSpring;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterialProperties;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringBlockEntity;
import rbasamoyai.createbigcannons.config.CBCConfigs;
import rbasamoyai.createbigcannons.effects.particles.plumes.AutocannonPlumeParticleData;
import rbasamoyai.createbigcannons.index.CBCAutocannonMaterials;
import rbasamoyai.createbigcannons.index.CBCEntityTypes;
import rbasamoyai.createbigcannons.multiloader.NetworkPlatform;
import rbasamoyai.createbigcannons.munitions.autocannon.config.AutocannonProjectilePropertiesComponent;
import rbasamoyai.createbigcannons.munitions.autocannon.config.InertAutocannonProjectileProperties;
import rbasamoyai.createbigcannons.network.ClientboundAnimateCannonContraptionPacket;
import rbasamoyai.createbigcannons.utils.CBCUtils;
import rbasamoyai.ritchiesprojectilelib.RitchiesProjectileLib;

import java.util.*;

import static rbasamoyai.createbigcannons.cannons.big_cannons.BigCannonBlock.writeAndSyncSingleBlockData;

public class MountedShupapiumACContraption extends MountedAutocannonContraption {
    private ShupapiumACProfile profile;
    private AutocannonMaterial cannonMaterial;
    private final Set<BlockPos> recoilSpringPositions = new LinkedHashSet<>();
    private boolean isHandle = false;

    @Override
    public boolean assemble(Level level, BlockPos pos) throws AssemblyException {
        boolean assemblyTest = originalAssembly(level, pos);
        if (!assemblyTest) return false;

        this.validateShupapiumAssembly(level);

        return true;
    }

    private boolean originalAssembly(Level level, BlockPos pos) throws AssemblyException {
        if (!this.collectCannonBlocks(level, pos)) return false;
        this.bounds = this.createBoundsFromExtensionLengths();
        return !this.blocks.isEmpty();
    }

    private boolean collectCannonBlocks(Level level, BlockPos pos) throws AssemblyException {
        BlockState startState = level.getBlockState(pos);

        if (!(startState.getBlock() instanceof AutocannonBlock startCannon)) {
            return false;
        }
        if (!startCannon.isComplete(startState)) {
            throw hasIncompleteCannonBlocks(pos);
        }

        AutocannonMaterial material = startCannon.getAutocannonMaterial();
        boolean isStartBreech = startCannon.isBreechMechanism(startState);

        List<StructureTemplate.StructureBlockInfo> cannonBlocks = new ArrayList<>();
        cannonBlocks.add(new StructureTemplate.StructureBlockInfo(pos, startState, this.getBlockEntityNBT(level, pos)));

        int cannonLength = 1;

        Direction cannonFacing = startCannon.getFacing(startState);

        Direction positive = Direction.get(Direction.AxisDirection.POSITIVE, cannonFacing.getAxis());
        Direction negative = positive.getOpposite();

        BlockPos start = pos;
        BlockState nextState = level.getBlockState(pos.relative(positive));
        boolean positiveBreech = false;

        while (nextState.getBlock() instanceof AutocannonBlock cBlock && this.isConnectedToCannon(level, nextState, start.relative(positive), positive, material)) {
            start = start.relative(positive);
            if (!cBlock.isComplete(nextState)) throw hasIncompleteCannonBlocks(start);
            cannonBlocks.add(new StructureTemplate.StructureBlockInfo(start, nextState, this.getBlockEntityNBT(level, start)));
            this.frontExtensionLength++;
            cannonLength++;
            positiveBreech = cBlock.isBreechMechanism(nextState);
            if (positiveBreech && isStartBreech) {
                throw invalidCannon();
            }
            if (positiveBreech && cBlock.getFacing(nextState) != negative)
                throw incorrectBreechDirection(start);
            nextState = level.getBlockState(start.relative(positive));
            if (cannonLength > getMaxCannonLength()) throw cannonTooLarge();
            if (positiveBreech) break;
        }
        BlockPos positiveEndPos = positiveBreech ? start : start.relative(negative);

        start = pos;
        nextState = level.getBlockState(pos.relative(negative));

        boolean negativeBreech = false;
        while (nextState.getBlock() instanceof AutocannonBlock cBlock && this.isConnectedToCannon(level, nextState, start.relative(negative), negative, material)) {
            start = start.relative(negative);
            if (!cBlock.isComplete(nextState)) throw hasIncompleteCannonBlocks(start);
            cannonBlocks.add(new StructureTemplate.StructureBlockInfo(start, nextState, this.getBlockEntityNBT(level, start)));
            this.backExtensionLength++;
            cannonLength++;
            negativeBreech = cBlock.isBreechMechanism(nextState);
            if (negativeBreech && isStartBreech) {
                throw invalidCannon();
            }
            if (negativeBreech && cBlock.getFacing(nextState) != positive)
                throw incorrectBreechDirection(start);
            nextState = level.getBlockState(start.relative(negative));
            if (cannonLength > getMaxCannonLength()) throw cannonTooLarge();
            if (negativeBreech) break;
        }
        BlockPos negativeEndPos = negativeBreech ? start : start.relative(positive);
        MainShupapium.LOGGER.info("La lista de bloques es la siguiente: {}", cannonBlocks);

        if (cannonLength < 1 || positiveBreech && negativeBreech) {
            throw invalidCannon();
        }

        this.startPos = !positiveBreech && !negativeBreech ? pos : (negativeBreech ? negativeEndPos : positiveEndPos);
        BlockState breechState = level.getBlockState(this.startPos);
        if (!(breechState.getBlock() instanceof ShupapiumACBreechBlock)) {
            MainShupapium.LOGGER.info("breech positivo: {} breech negativo: {}", positiveBreech, negativeBreech);
            cannonBlocks.forEach(info -> MainShupapium.LOGGER.info("{} at {}", info.state().getBlock(), info.pos()));
            throw invalidCannon();
        }
        this.initialOrientation = breechState.getValue(BlockStateProperties.FACING);

        this.anchor = pos;

        this.startPos = this.startPos.subtract(pos);

        for (StructureTemplate.StructureBlockInfo blockInfo : cannonBlocks) {
            BlockPos localPos = blockInfo.pos().subtract(pos);
            StructureTemplate.StructureBlockInfo localBlockInfo = new StructureTemplate.StructureBlockInfo(localPos, blockInfo.state(), blockInfo.nbt());
            this.blocks.put(localPos, localBlockInfo);

            if (blockInfo.nbt() == null) continue;
            BlockEntity be = BlockEntity.loadStatic(localPos, blockInfo.state(), blockInfo.nbt());
            this.presentBlockEntities.put(localPos, be);
            if (blockInfo.state().getBlock() instanceof ShupapiumACRecoilSpringBlock)
                this.recoilSpringPositions.add(localPos);
        }

        StructureTemplate.StructureBlockInfo startInfo = this.blocks.get(this.startPos);
        if (startInfo == null || !(startInfo.state().getBlock() instanceof ShupapiumACBreechBlock))
            throw noAutocannonBreech();
        this.isHandle = startInfo.state().hasProperty(ShupapiumACBreechBlock.HANDLE) && startInfo.state().getValue(ShupapiumACBreechBlock.HANDLE);
        if (this.isHandle) {
            this.getSeats().add(this.startPos.immutable());
        }

        StructureTemplate.StructureBlockInfo possibleSpring = this.blocks.get(this.startPos.relative(this.initialOrientation));
        if (possibleSpring != null
                && possibleSpring.state().getBlock() instanceof ShupapiumACRecoilSpringBlock springBlock
                && springBlock.getFacing(possibleSpring.state()) == this.initialOrientation) {
            BlockPos mainRecoilSpringPos = this.startPos.relative(this.initialOrientation).immutable();
            if (this.presentBlockEntities.get(mainRecoilSpringPos) instanceof AutocannonRecoilSpringBlockEntity springBE) {
                for (int i = 1; i < cannonLength; ++i) {
                    BlockPos pos1 = this.startPos.relative(this.initialOrientation, i);
                    StructureTemplate.StructureBlockInfo blockInfo = this.blocks.get(pos1);
                    if (blockInfo == null || !(blockInfo.state().getBlock() instanceof MovesWithAutocannonRecoilSpring springed))
                        continue;
                    springBE.toAnimate.put(pos1.subtract(mainRecoilSpringPos), springed.getMovingState(blockInfo.state()));
                    this.blocks.put(pos1, new StructureTemplate.StructureBlockInfo(pos1, springed.getStationaryState(blockInfo.state()), blockInfo.nbt()));
                }
                CompoundTag newTag = springBE.saveWithFullMetadata();
                newTag.remove("x");
                newTag.remove("y");
                newTag.remove("z");
                this.blocks.put(mainRecoilSpringPos, new StructureTemplate.StructureBlockInfo(mainRecoilSpringPos, possibleSpring.state(), newTag));
            }
        }

        this.cannonMaterial = material;

        return true;
    }

    private boolean isConnectedToCannon(LevelAccessor level, BlockState state, BlockPos pos, Direction connection, AutocannonMaterial material) {
        AutocannonBlock cBlock = (AutocannonBlock) state.getBlock();
        if (cBlock.getAutocannonMaterialInLevel(level, state, pos) != material) return false;
        return level.getBlockEntity(pos) instanceof IAutocannonBlockEntity cbe
                && level.getBlockEntity(pos.relative(connection.getOpposite())) instanceof IAutocannonBlockEntity cbe1
                && cbe.cannonBehavior().isConnectedTo(connection.getOpposite())
                && cbe1.cannonBehavior().isConnectedTo(connection);
    }

    @Override
    public void addBlocksToWorld(Level world, StructureTransform transform) {
        Map<BlockPos, StructureTemplate.StructureBlockInfo> modifiedBlocks = new HashMap<>();
        for (Map.Entry<BlockPos, StructureTemplate.StructureBlockInfo> entry : this.blocks.entrySet()) {
            StructureTemplate.StructureBlockInfo info = entry.getValue();
            BlockState newState = info.state();

            if (newState.hasProperty(ShupapiumACBarrelBlock.ASSEMBLED) && newState.getValue(ShupapiumACBarrelBlock.ASSEMBLED)) {
                newState = newState.setValue(ShupapiumACBarrelBlock.ASSEMBLED, false);
            }

            CompoundTag infoNbt = info.nbt();
            if (infoNbt != null) {
                if (infoNbt.contains("AnimateTicks")) {
                    infoNbt.remove("AnimateTicks");
                }
                if (infoNbt.contains("RenderedBlocks")) {
                    infoNbt.remove("RenderedBlocks");
                }
            }

            modifiedBlocks.put(info.pos(), new StructureTemplate.StructureBlockInfo(info.pos(), newState, infoNbt));
        }
        this.blocks.putAll(modifiedBlocks);
        super.addBlocksToWorld(world, transform);
    }

    @Override
    public void fireShot(ServerLevel level, PitchOrientedContraptionEntity entity) {
        if (this.profile == null) return;
        if (this.startPos == null
                || this.cannonMaterial == null
                || !(this.presentBlockEntities.get(this.startPos) instanceof ShupapiumACBreechBlockEntity breech)
                || !breech.canFire()) return;

        ItemStack foundProjectile = breech.extractNextInput();
        if (!(foundProjectile.getItem() instanceof ShupapiumAmmoItem round)) return;

        ControlPitchContraption controller = entity.getController();

        Vec3 ejectPos = entity.toGlobalVector(Vec3.atCenterOf(breech.getBlockPos().relative(this.isHandle ? Direction.DOWN : this.initialOrientation.getOpposite())), 0);
        Vec3 centerPos = entity.toGlobalVector(Vec3.atCenterOf(BlockPos.ZERO), 0);
        ItemStack ejectStack = round.getSpentItem(foundProjectile);
        if (!ejectStack.isEmpty()) {
            //ItemStack output = breech.insertOutput(ejectStack);
            //if (!output.isEmpty()) {
            ItemEntity ejectEntity = new ItemEntity(level, ejectPos.x, ejectPos.y, ejectPos.z, ejectStack);
            Vec3 eject = ejectPos.subtract(centerPos).normalize();
            ejectEntity.setDeltaMovement(eject.scale(this.isHandle ? 0.1 : 0.5));
            ejectEntity.setPickUpDelay(20);
            level.addFreshEntity(ejectEntity);
            //}
        }

        InertAutocannonProjectileProperties projectileProperties = round.getMainProperties(foundProjectile);
        AutocannonMaterialProperties properties = this.cannonMaterial.properties();
        AutocannonProjectilePropertiesComponent roundProperties = round.getAutocannonProperties(foundProjectile);

        boolean canFail = !CBCConfigs.server().failure.disableAllFailure.get();

        float speed = this.profile.getProjectileBaseSpeed();
        float spread = this.profile.getProjectileSpread();
        boolean canSquib = roundProperties == null || roundProperties.canSquib();
        canSquib &= canFail;

        BlockPos currentPos = this.startPos.relative(this.initialOrientation);
        int barrelTravelled = 0;
        boolean squib = false;

        while (this.presentBlockEntities.get(currentPos) instanceof IAutocannonBlockEntity autocannon) {
            ItemCannonBehavior behavior = autocannon.cannonBehavior();

            if (behavior.canLoadItem(foundProjectile)) {
                ++barrelTravelled;
                if (barrelTravelled <= properties.maxSpeedIncreases())
                    speed += properties.speedIncreasePerBarrel();
                spread -= properties.spreadReductionPerBarrel();
                spread = Math.max(spread, 0);
                if (canSquib && barrelTravelled > properties.maxBarrelLength()) {
                    StructureTemplate.StructureBlockInfo oldInfo = this.blocks.get(currentPos);
                    if (oldInfo == null) return;
                    behavior.tryLoadingItem(foundProjectile);
                    CompoundTag tag = this.presentBlockEntities.get(currentPos).saveWithFullMetadata();
                    tag.remove("x");
                    tag.remove("y");
                    tag.remove("z");
                    StructureTemplate.StructureBlockInfo squibInfo = new StructureTemplate.StructureBlockInfo(currentPos, oldInfo.state(), tag);
                    this.blocks.put(currentPos, squibInfo);
                    Vec3 squibPos = entity.toGlobalVector(Vec3.atCenterOf(currentPos), 0);
                    level.playSound(null, squibPos.x, squibPos.y, squibPos.z, oldInfo.state().getSoundType().getBreakSound(), SoundSource.BLOCKS, 10.0f, 0.0f);
                    squib = true;
                    break;
                }
                currentPos = currentPos.relative(this.initialOrientation);
            } else {
                behavior.removeItem();
                if (canFail) {
                    Vec3 failurePoint = entity.toGlobalVector(Vec3.atCenterOf(currentPos), 0);
                    level.explode(null, failurePoint.x, failurePoint.y, failurePoint.z, 2, Level.ExplosionInteraction.NONE);
                    for (int i = 0; i < 10; ++i) {
                        BlockPos pos = currentPos.relative(this.initialOrientation, i);
                        this.blocks.remove(pos);
                    }
                    if (controller != null) controller.disassemble();
                    return;
                }
            }
        }
        breech.handleFiring();
        if (squib) return;

        for (BlockPos pos : this.recoilSpringPositions) {
            if (this.presentBlockEntities.get(pos) instanceof AutocannonRecoilSpringBlockEntity spring)
                spring.handleFiring();
        }
        NetworkPlatform.sendToClientTracking(new ClientboundAnimateCannonContraptionPacket(entity), entity);

        Vec3 spawnPos = entity.toGlobalVector(Vec3.atCenterOf(currentPos.relative(this.initialOrientation)), 0);
        Vec3 vec1 = spawnPos.subtract(centerPos).normalize();
        spawnPos = spawnPos.subtract(vec1.scale(1.5));
        Vec3 particlePos = spawnPos;

        float recoilMagnitude = properties.baseRecoil();

        List<AbstractArrow> projectiles = round.getAutocannonProjectile(foundProjectile, level);
        assert projectiles != null;
        for (int i = 0; i < this.profile.getProjectilePerShot(); i++) {
            AbstractArrow projectile = projectiles.get(Mth.nextInt(level.random, 0, projectiles.size() - 1));
            if (projectile != null) {
                spread = spread + (i * 0.15F);
                projectile.setPos(spawnPos.x + Mth.nextFloat(level.random, -this.profile.getProjectileSpread(), this.profile.getProjectileSpread()), spawnPos.y + Mth.nextFloat(level.random, -this.profile.getProjectileSpread(), this.profile.getProjectileSpread()), spawnPos.z + Mth.nextFloat(level.random, -this.profile.getProjectileSpread(), this.profile.getProjectileSpread()));
                projectile.addTag("shupapiumProjectile");
                projectile.setOwner(entity.getControllingPassenger());
                projectile.setBaseDamage(projectileProperties.damage().entityDamage() / 10);
                projectile.setKnockback((int) projectileProperties.damage().knockback());
                projectile.setSilent(true);
                projectile.setNoGravity(!round.projectileAffectedByWorldsGravity());
                ProjectileManager.track(projectile, level, properties.projectileLifetime());
                //projectile.setLifetime(properties.projectileLifetime());
                projectile.shoot(vec1.x + Mth.nextFloat(level.random, -this.profile.getProjectileSpread(), this.profile.getProjectileSpread()) , vec1.y + Mth.nextFloat(level.random, -this.profile.getProjectileSpread(), this.profile.getProjectileSpread()), vec1.z + Mth.nextFloat(level.random, -this.profile.getProjectileSpread(), this.profile.getProjectileSpread()), speed, spread);
                projectile.xRotO = projectile.getXRot();
                projectile.yRotO = projectile.getYRot();

                //projectile.addUntouchableEntity(entity, 1);
                //Entity vehicle = entity.getVehicle();
                //if (vehicle != null && CBCEntityTypes.CANNON_CARRIAGE.is(vehicle))
                //projectile.addUntouchableEntity(vehicle, 1);

                level.addFreshEntity(projectile);
                if (roundProperties != null) recoilMagnitude += (float) roundProperties.addedRecoil();
                if (CBCConfigs.server().munitions.projectilesCanChunkload.get()) {
                    ChunkPos cpos1 = new ChunkPos(projectile.blockPosition());
                    RitchiesProjectileLib.queueForceLoad(level, cpos1.x, cpos1.z);
                }
            }
        }

        recoilMagnitude *= CBCConfigs.server().cannons.autocannonRecoilScale.getF();
        if (controller != null) controller.onRecoil(vec1.scale(-recoilMagnitude), entity);

        Vec3 particleVel = vec1.scale(1.25);
        for (ServerPlayer player : level.players()) {
            if (entity.getControllingPassenger() == player) continue;
            level.sendParticles(player, new AutocannonPlumeParticleData(1f), true, particlePos.x, particlePos.y, particlePos.z, 0, particleVel.x, particleVel.y, particleVel.z, 1.0f);
        }

        round.customProjectileMuzzle(foundProjectile, level, particlePos, particleVel);

        this.profile.cannonSoundEvent(level, spawnPos);

        this.profile.cannonMuzzleEvent(level, particlePos, particleVel);
    }

    @Override
    public void animate() {
        super.animate();
        if (this.presentBlockEntities.get(this.startPos) instanceof ShupapiumACBreechBlockEntity breech)
            breech.handleFiring();
        for (BlockPos pos : this.recoilSpringPositions) {
            if (this.presentBlockEntities.get(pos) instanceof AutocannonRecoilSpringBlockEntity spring)
                spring.handleFiring();
        }
        if (this.getBlockEntityClientSide(this.startPos) instanceof ShupapiumACBreechBlockEntity breech)
            breech.handleFiring();
        for (BlockPos pos : this.recoilSpringPositions) {
            if (this.getBlockEntityClientSide(pos) instanceof AutocannonRecoilSpringBlockEntity spring)
                spring.handleFiring();
        }
    }

    @Override
    public void tick(Level level, PitchOrientedContraptionEntity entity) {
        super.tick(level, entity);

        if (this.profile == null && !level.isClientSide()) {
            try {
                ShupapiumACParts parts = getParts();
                this.profile = ShupapiumACProfileHandler.getProfile(parts);
            } catch (AssemblyException ignored) {}
        }

        Entity controller = entity.getControllingPassenger();
        if (this.canBeTurnedByPassenger(controller)) {
            Direction dir = entity.getInitialOrientation();
            boolean flag = (dir.getAxisDirection() == Direction.AxisDirection.POSITIVE) == (dir.getAxis() == Direction.Axis.X);
            assert controller != null;
            if (flag) {
                entity.pitch = -controller.xRotO;
            } else {
                entity.pitch = controller.xRotO;
            }
            entity.yaw = Mth.wrapDegrees(controller.yRotO);
            controller.setYBodyRot(controller.getYRot());
            assert entity.getVehicle() != null;
            if (CBCEntityTypes.CANNON_CARRIAGE.is(entity.getVehicle())) {
                entity.getVehicle().onPassengerTurned(entity);
            } else if (entity.getController() instanceof CannonMountBlockEntity) {
                entity.setXRot(entity.pitch);
                entity.setYRot(entity.yaw);
            }
        }

        assert entity.getVehicle() != null;
        if (CBCEntityTypes.CANNON_CARRIAGE.is(entity.getVehicle())) {
            controller = entity.getVehicle().getControllingPassenger();
        }
        if (!level.isClientSide && controller instanceof Player player) {
            String key = "";
            ControlPitchContraption controllerBlock = entity.getController();
            if (controllerBlock != null) {
                ResourceLocation loc = controllerBlock.getTypeId();
                if (loc != null) key = "." + loc.getNamespace() + "." + loc.getPath();
            }
            player.displayClientMessage(Component.translatable("block." + MainShupapium.MODID + ".cannon_carriage.hotbar.fireRate",
                    this.getReferencedFireRate()), true);
        }

        if (level instanceof ServerLevel slevel && this.canBeFiredOnController(entity.getController()))
            this.fireShot(slevel, entity);

        for (Map.Entry<BlockPos, BlockEntity> entry : this.presentBlockEntities.entrySet()) {
            if (entry.getValue() instanceof IAutocannonBlockEntity autocannon)
                autocannon.tickFromContraption(level, entity, entry.getKey());
        }
    }

    @Override
    public BlockPos getSeatPos(Entity entity) {
        return entity == this.entity.getControllingPassenger() ? this.startPos.relative(this.initialOrientation.getOpposite()) : super.getSeatPos(entity);
    }

    @Override
    public boolean canBeTurnedByController(ControlPitchContraption control) {
        return !this.isHandle;
    }

    @Override
    public boolean canBeTurnedByPassenger(Entity entity) {
        if (this.entity instanceof PitchOrientedContraptionEntity poce && poce.getController() instanceof FixedCannonMountBlockEntity)
            return false;
        return this.isHandle && entity instanceof Player;
    }

    @Override
    public boolean canBeFiredOnController(ControlPitchContraption control) {
        return !this.isHandle && this.entity.getVehicle() != control;
    }

    @Override
    public void onRedstoneUpdate(ServerLevel level, PitchOrientedContraptionEntity entity, boolean togglePower, int firePower, ControlPitchContraption controller) {
        if (this.presentBlockEntities.get(this.startPos) instanceof ShupapiumACBreechBlockEntity breech) {
            breech.setFireRate(firePower == 0 ? 0 : this.profile.getCannonFireRate());
            writeAndSyncSingleBlockData(breech, this.blocks.get(this.startPos), entity, this);
        }
    }

    public void trySettingFireRateCarriage(int fireRateAdjustment) {
        if (this.presentBlockEntities.get(this.startPos) instanceof ShupapiumACBreechBlockEntity breech) {
            breech.setFireRate(this.profile.getCannonFireRate());
            writeAndSyncSingleBlockData(breech, this.blocks.get(this.startPos), entity, this);
        }
    }

    public int getReferencedFireRate() {
        return this.presentBlockEntities.get(this.startPos) instanceof ShupapiumACBreechBlockEntity breech ? breech.getActualFireRate() : 0;
    }

    @Override
    public float getWeightForStress() {
        return this.cannonMaterial == null ? this.blocks.size() : this.blocks.size() * this.cannonMaterial.properties().weight();
    }

    @Override
    public Vec3 getInteractionVec(PitchOrientedContraptionEntity poce) {
        return poce.toGlobalVector(Vec3.atCenterOf(this.startPos), 0);
    }

    @Override
    public ICannonContraptionType getCannonType() {
        return this.isHandle ? CBCCannonContraptionTypes.HANDLE_AUTOCANNON : CBCCannonContraptionTypes.AUTOCANNON;
    }

    @Override
    public CompoundTag writeNBT(boolean clientData) {
        CompoundTag tag = super.writeNBT(clientData);
        if (this.profile != null) {
            tag.putString("ACProfile", this.profile.getProfileId().toString());
        }
        tag.putString("AutocannonMaterial", this.cannonMaterial == null ? CBCAutocannonMaterials.CAST_IRON.name().toString() : this.cannonMaterial.name().toString());
        if (this.startPos != null) tag.put("StartPos", NbtUtils.writeBlockPos(this.startPos));
        if (!this.recoilSpringPositions.isEmpty()) {
            ListTag positionsTag = new ListTag();
            for (BlockPos pos : this.recoilSpringPositions)
                positionsTag.add(NbtUtils.writeBlockPos(pos));
            tag.put("RecoilSpringPositions", positionsTag);
        }
        tag.putBoolean("IsHandle", this.isHandle);
        return tag;
    }

    @Override
    public void readNBT(Level level, CompoundTag tag, boolean clientData) {
        super.readNBT(level, tag, clientData);
        this.cannonMaterial = AutocannonMaterial.fromNameOrNull(CBCUtils.location(tag.getString("AutocannonMaterial")));
        if (this.cannonMaterial == null) this.cannonMaterial = CBCAutocannonMaterials.CAST_IRON;
        if (tag.contains("ACProfile")) {
            ResourceLocation id = ResourceLocation.parse(tag.getString("ACProfile"));
            this.profile = ShupapiumACProfileHandler.getProfile(id);
        } else {
            this.profile = null;
        }
        this.startPos = tag.contains("StartPos") ? NbtUtils.readBlockPos(tag.getCompound("StartPos")) : null;
        this.recoilSpringPositions.clear();
        if (tag.contains("RecoilSpringPositions")) {
            ListTag positionTags = tag.getList("RecoilSpringPositions", Tag.TAG_COMPOUND);
            int sz = positionTags.size();
            for (int i = 0; i < sz; ++i)
                this.recoilSpringPositions.add(NbtUtils.readBlockPos(positionTags.getCompound(i)));
        }
        this.isHandle = tag.getBoolean("IsHandle");
    }

    @Override
    public ContraptionType getType() {
        return ShupapiumContraptionsTypes.MOUNTED_SHUPAPIUM_AC.value();
    }

    @Override
    public ItemStack insertItemIntoCannon(ItemStack stack, boolean simulate) {
        return stack;
    }

    @Override
    public ItemStack extractItemFromCannon(boolean simulate) {
        return ItemStack.EMPTY;
    }

    private void validateShupapiumAssembly(Level level) throws AssemblyException {
        if (level.isClientSide) return;
        ShupapiumACParts detectedAssembly = getParts();
        ShupapiumACProfile profile = ShupapiumACProfileHandler.getProfile(detectedAssembly);
        if (profile == null) throw invalidSACCompatibilty();

        if (this.presentBlockEntities.get(this.startPos) instanceof ShupapiumACBreechBlockEntity breech) {
            Item mainAmmoItem = ShupapiumAmmoContainerItem.getMainAmmoStack(breech.getMagazine()).getItem();
            if (!(profile.getAmmoTypes().contains(mainAmmoItem))) {
                this.profile = null;
                throw invalidAmmoType();
            };
        }

        this.profile = profile;
    }

    private @NotNull ShupapiumACParts getParts() throws AssemblyException {
        Block barrelBlock = null;
        Block bodyBlock = null;
        Block breechBlock = null;
        for (StructureTemplate.StructureBlockInfo blockInfo : this.blocks.values()) {
            Block block = blockInfo.state().getBlock();
            if (block instanceof ShupapiumACBarrelBlock b) {
                barrelBlock = b;
            } else if (block instanceof ShupapiumACRecoilSpringBlock s) {
                bodyBlock = s;
            } else if (block instanceof ShupapiumACBreechBlock br) {
                breechBlock = br;
            }
        }
        if (barrelBlock == null) throw invalidBarrelBlock();
        if (bodyBlock == null) throw invalidChamberBlock();
        if (breechBlock == null) throw invalidBreechBlock();

        return new ShupapiumACParts(barrelBlock, bodyBlock, breechBlock);
    }

    public static AssemblyException invalidBarrelBlock() {
        return new AssemblyException(Component.translatable("exception.shupapium.cannon_mount.invalid_barrel"));
    }

    public static AssemblyException invalidChamberBlock() {
        return new AssemblyException(Component.translatable("exception.shupapium.cannon_mount.invalid_chamber"));
    }

    public static AssemblyException invalidBreechBlock() {
        return new AssemblyException(Component.translatable("exception.shupapium.cannon_mount.invalid_breech"));
    }

    public static AssemblyException invalidSACCompatibilty() {
        return new AssemblyException(Component.translatable("exception.shupapium.cannon_mount.invalid_compatibility"));
    }

    public static AssemblyException invalidAmmoType() {
        return new AssemblyException(Component.translatable("exception.shupapium.cannon_mount.invalid_ammo_type"));
    }
}
