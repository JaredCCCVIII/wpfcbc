package net.ato.shupapium.cannons;

import com.simibubi.create.content.contraptions.AssemblyException;
import com.simibubi.create.content.contraptions.StructureTransform;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import rbasamoyai.createbigcannons.cannon_control.ControlPitchContraption;
import rbasamoyai.createbigcannons.cannon_control.contraption.MountedAutocannonContraption;
import rbasamoyai.createbigcannons.cannon_control.contraption.PitchOrientedContraptionEntity;
import rbasamoyai.createbigcannons.cannon_control.fixed_cannon_mount.FixedCannonMountBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBarrelBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.AutocannonBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.IAutocannonBlockEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.MovesWithAutocannonRecoilSpring;
import rbasamoyai.createbigcannons.cannons.autocannon.breech.AutocannonBreechBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.material.AutocannonMaterial;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringBlock;
import rbasamoyai.createbigcannons.cannons.autocannon.recoil_spring.AutocannonRecoilSpringBlockEntity;

import java.util.*;

public class MountedShupapiumACContraption extends MountedAutocannonContraption {
    private AutocannonMaterial cannonMaterial;
    private final Set<BlockPos> recoilSpringPositions = new LinkedHashSet<>();
    private boolean isHandle = false;

    @Override
    public boolean assemble(Level level, BlockPos pos) throws AssemblyException {
        boolean assemblyTest = originalAssembly(level, pos);
        if (!assemblyTest) return false;

        //this.validateShupapiumAssembly(level);

        return true;
    }

    private boolean originalAssembly(Level level, BlockPos pos) throws AssemblyException {
        if (!this.collectCannonBlocks(level, pos)) return false;
        this.bounds = this.createBoundsFromExtensionLengths();
        return !this.blocks.isEmpty();
    }

    private boolean collectCannonBlocks(Level level, BlockPos pos) throws AssemblyException {
        BlockState startState = level.getBlockState(pos);
        if (!(startState.getBlock() instanceof AutocannonBlock startCannon)) return false;
        if (!startCannon.isComplete(startState)) throw hasIncompleteCannonBlocks(pos);

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
            if (positiveBreech && isStartBreech) throw invalidCannon();
            if (positiveBreech && cBlock.getFacing(nextState) != negative) throw incorrectBreechDirection(start);
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
            if (negativeBreech && isStartBreech) throw invalidCannon();
            if (negativeBreech && cBlock.getFacing(nextState) != positive) throw incorrectBreechDirection(start);
            nextState = level.getBlockState(start.relative(negative));
            if (cannonLength > getMaxCannonLength()) throw cannonTooLarge();
            if (negativeBreech) break;
        }

        BlockPos negativeEndPos = negativeBreech ? start : start.relative(positive);
        if (cannonLength < 2 || positiveBreech && negativeBreech) throw invalidCannon();

        this.startPos = !positiveBreech && !negativeBreech ? pos : negativeBreech ? negativeEndPos : positiveEndPos;
        BlockState breechState = level.getBlockState(this.startPos);
        if (!(breechState.getBlock() instanceof AutocannonBreechBlock)) throw invalidCannon();
        this.initialOrientation = breechState.getValue(BlockStateProperties.FACING);
        this.anchor = pos;
        this.startPos = this.startPos.subtract(pos);

        for (StructureTemplate.StructureBlockInfo blockInfo : cannonBlocks) {
            BlockPos localPos = blockInfo.pos().subtract(pos);
            StructureTemplate.StructureBlockInfo localBlockInfo = new StructureTemplate.StructureBlockInfo(localPos, blockInfo.state(), blockInfo.nbt());
            this.blocks.put(localPos, localBlockInfo);

            if (blockInfo.nbt() == null) continue;
            BlockEntity be = BlockEntity.loadStatic(localPos, blockInfo.state(), blockInfo.nbt(), level.registryAccess());
            this.presentBlockEntities.put(localPos, be);
            if (blockInfo.state().getBlock() instanceof AutocannonRecoilSpringBlock) {
                this.recoilSpringPositions.add(localPos);
            }
        }

        StructureTemplate.StructureBlockInfo startInfo = this.blocks.get(this.startPos);
        if (startInfo == null || !(startInfo.state().getBlock() instanceof AutocannonBreechBlock)) throw noAutocannonBreech();
        this.isHandle = startInfo.state().hasProperty(AutocannonBreechBlock.HANDLE) && startInfo.state().getValue(AutocannonBreechBlock.HANDLE);
        if (this.isHandle) {
            this.getSeats().add(this.startPos.immutable());
        }

        StructureTemplate.StructureBlockInfo possibleSpring = this.blocks.get(this.startPos.relative(this.initialOrientation));
        if (possibleSpring != null && possibleSpring.state().getBlock() instanceof AutocannonRecoilSpringBlock springBlock && springBlock.getFacing(possibleSpring.state()) == this.initialOrientation) {
            BlockPos mainRecoilSpringPos = this.startPos.relative(this.initialOrientation).immutable();
            if (this.presentBlockEntities.get(mainRecoilSpringPos) instanceof AutocannonRecoilSpringBlockEntity springBE) {
                for (int i = 1; i < cannonLength; ++i) {
                    BlockPos pos1 = this.startPos.relative(this.initialOrientation, i);
                    StructureTemplate.StructureBlockInfo blockInfo = this.blocks.get(pos1);
                    if (blockInfo == null || !(blockInfo.state().getBlock() instanceof MovesWithAutocannonRecoilSpring springed)) continue;
                    springBE.toAnimate.put(pos1.subtract(mainRecoilSpringPos), springed.getMovingState(blockInfo.state()));
                    this.blocks.put(pos1, new StructureTemplate.StructureBlockInfo(pos1, springed.getStationaryState(blockInfo.state()), blockInfo.nbt()));
                }
                CompoundTag newTag = springBE.saveWithFullMetadata(level.registryAccess());
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
            boolean modified = true;

            if (newState.hasProperty(AutocannonBarrelBlock.ASSEMBLED) && newState.getValue(AutocannonBarrelBlock.ASSEMBLED)) {
                newState = newState.setValue(AutocannonBarrelBlock.ASSEMBLED, false);
                modified = true;
            }

            CompoundTag infoNBT = info.nbt();
            if (infoNBT != null) {
                if (infoNBT.contains("AnimateTicks")) {
                    infoNBT.remove("AnimateTicks");
                    modified = true;
                }
                if (infoNBT.contains("RenderedBlocks")) {
                    infoNBT.remove("RenderedBlocks");
                    modified = true;
                }
            }

            if (modified) modifiedBlocks.put(info.pos(), new StructureTemplate.StructureBlockInfo(info.pos(), newState, infoNBT));
        }
        this.blocks.putAll(modifiedBlocks);
        super.addBlocksToWorld(world, transform);
    }

    @Override
    public void fireShot(ServerLevel level, PitchOrientedContraptionEntity entity) {
        super.fireShot(level, entity);
    }

    @Override
    public void animate() {
        super.animate();
    }

    @Override
    public void tick(Level level, PitchOrientedContraptionEntity entity) {
        super.tick(level, entity);
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
        if (this.entity instanceof PitchOrientedContraptionEntity poce && poce.getController() instanceof FixedCannonMountBlockEntity) return false;
        return this.isHandle && entity instanceof Player;
    }

    @Override
    public boolean canBeFiredOnController(ControlPitchContraption control) {
        return !this.isHandle && this.entity.getVehicle() != control;
    }

    @Override
    public void onRedstoneUpdate(ServerLevel level, PitchOrientedContraptionEntity entity, boolean togglePower, int firePower, ControlPitchContraption controller) {
        super.onRedstoneUpdate(level, entity, togglePower, firePower, controller);
    }


}
