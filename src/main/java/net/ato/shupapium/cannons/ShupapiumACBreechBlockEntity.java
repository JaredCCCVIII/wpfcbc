package net.ato.shupapium.cannons;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.schematics.requirement.ItemRequirement;
import net.ato.shupapium.ShupapiumConfig;
import net.ato.shupapium.items.ShupapiumAmmoContainerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import rbasamoyai.createbigcannons.cannon_control.contraption.PitchOrientedContraptionEntity;
import rbasamoyai.createbigcannons.cannons.autocannon.breech.AutocannonBreechBlockEntity;

import javax.annotation.Nullable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static rbasamoyai.createbigcannons.cannons.big_cannons.BigCannonBlock.writeAndSyncSingleBlockData;

public class ShupapiumACBreechBlockEntity extends AutocannonBreechBlockEntity {
    private IItemHandler inventory;
    private final int maxFireRate = ShupapiumConfig.MAX_SAC_FIRE_RATE.get();
    private int fireRate = 7;
    private float firingCooldown;
    private int animateTicks = 5;
    @Nullable
    DyeColor seat = null;
    private boolean updateInstance = true;
    private final Deque<ItemStack> inputBuffer = new LinkedList<>();
    private ItemStack outputBuffer = ItemStack.EMPTY;
    private ItemStack magazine = ItemStack.EMPTY;
    public ShupapiumACBreechBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public IItemHandler createItemHandler() {
        return this.inventory == null ? this.inventory = new ShupapiumACBreechInterface(this) : this.inventory;
    }

    @Override
    public Deque<ItemStack> getInputBuffer() {
        return this.inputBuffer;
    }

    @Override
    public ItemStack getOutputBuffer() {
        return this.outputBuffer;
    }

    @Override
    public void setOutputBuffer(ItemStack stack) {
        this.outputBuffer = stack;
    }

    @Override
    public void setMagazine(ItemStack stack) {
        this.magazine = stack;
        this.updateInstance = true;
    }

    @Override
    public ItemStack getMagazine() {
        return this.magazine;
    }

    @Override
    public void tick() {
        super.tick();
        this.allTick(this.getLevel());
    }

    @Override
    public void tickFromContraption(Level level, PitchOrientedContraptionEntity poce, BlockPos localPos) {
        super.tickFromContraption(level, poce, localPos);
        this.allTick(level);
        if (!level.isClientSide && this.updateInstance) {
            this.updateInstance = false;
            Contraption contraption = poce.getContraption();
            writeAndSyncSingleBlockData(this, contraption.getBlocks().get(localPos), poce, contraption);
        }
        if (level.isClientSide && poce.getContraption().getBlockEntityClientSide(localPos) instanceof ShupapiumACBreechBlockEntity cbe) {
            cbe.allTick(level);
        }
    }

    private void allTick(Level level) {
        if (this.fireRate < 0 || this.fireRate > maxFireRate) this.fireRate = 0;
        if (this.firingCooldown < 0) this.firingCooldown = 0;
        if (this.firingCooldown > 0) this.firingCooldown--;

        if (this.animateTicks < 5) ++this.animateTicks;
        if (this.animateTicks < 0) this.animateTicks = 0;
    }

    @Override
    public void setFireRate(int power) {
        this.fireRate = Mth.clamp(power, 0, maxFireRate);
    }

    @Override
    public int getFireRate() {
        return this.fireRate;
    }

    @Override
    public int getActualFireRate() {
        if (this.fireRate < 1 || this.fireRate > this.maxFireRate) return 0;
        return this.fireRate;
    }

    @Override
    public boolean canFire() {
        return this.getFireRate() > 0 && this.firingCooldown <= 0;
    }

    @Override
    public void handleFiring() {
        if (this.fireRate > 0 && this.fireRate <= maxFireRate) {
            this.firingCooldown = (float) 1200 / this.fireRate;
            this.animateTicks = 0;
        }
    }

    @Override
    public float getAnimateOffset(float partialTicks) {
        float t = ((float) this.animateTicks + partialTicks) * 1.2f;
        if (t <= 0 || t >= 4.8f) return 0;
        float f = t < 1 ? t : (4.8f - t) / 3.8f;
        return Mth.sin(f * Mth.HALF_PI);
    }

    @Override
    public void incrementAnimationTicks() {
        ++this.animateTicks;
    }

    @Override
    public int getAnimationTicks() {
        return this.animateTicks;
    }

    @Override
    public void setSeatColor(@Nullable DyeColor color) {
        this.seat = color;
        this.updateInstance = true;
        super.setSeatColor(color);
    }

    @Nullable
    public DyeColor getSeatColor() { return this.seat; }

    @Override
    protected void read(CompoundTag tag, HolderLookup.Provider registry, boolean clientPacket) {
        super.read(tag, registry, clientPacket);

        // Clear vanilla breech data
        super.getInputBuffer().clear();
        super.setOutputBuffer(ItemStack.EMPTY);
        super.setMagazine(ItemStack.EMPTY);

        this.fireRate = tag.getInt("FiringRate");
        this.firingCooldown = tag.getFloat("Cooldown");
        this.animateTicks = tag.getInt("AnimateTicks");
        this.outputBuffer = tag.contains("Output") ? ItemStack.parseOptional(registry, tag.getCompound("Output")) : ItemStack.EMPTY;
        this.seat = DyeColor.byName(tag.getString("Seat"), null);

        this.inputBuffer.clear();
        ListTag inputTag = tag.getList("Input", Tag.TAG_COMPOUND);
        for (int i = 0; i < inputTag.size(); ++i) {
            this.inputBuffer.add(ItemStack.parseOptional(registry, inputTag.getCompound(i)));
        }
        this.magazine = tag.contains("Magazine") ? ItemStack.parseOptional(registry, tag.getCompound("Magazine")) : ItemStack.EMPTY;

        if (!clientPacket) return;
        this.updateInstance = tag.contains("UpdateInstance");
    }

    @Override
    protected void write(CompoundTag tag, HolderLookup.Provider registry, boolean clientPacket) {
        super.write(tag, registry, clientPacket);
        tag.putInt("FiringRate", this.fireRate);
        tag.putFloat("Cooldown", this.firingCooldown);
        tag.putInt("AnimateTicks", this.animateTicks);
        if (this.outputBuffer != null) tag.put("Output", this.outputBuffer.saveOptional(registry));
        if (this.seat != null) tag.putString("Seat", this.seat.getSerializedName());

        if (!this.inputBuffer.isEmpty()) {
            tag.put("Input", this.inputBuffer.stream()
                    .map(s -> s.saveOptional(registry))
                    .collect(Collectors.toCollection(ListTag::new)));
        }
        if (this.magazine != null && !this.magazine.isEmpty()) tag.put("Magazine", this.magazine.save(registry));

        if (!clientPacket) return;
        if (this.updateInstance) tag.putBoolean("UpdateInstance", true);
    }

    @Override
    public void writeSafe(CompoundTag tag, HolderLookup.Provider registries) {
        super.writeSafe(tag, registries);
        tag.putInt("FiringRate", this.fireRate);
    }

    @Override
    public ItemRequirement getRequiredItems(BlockState state) {
        if (this.seat == null)
            return ItemRequirement.NONE;
        ItemStack drop = AllBlocks.SEATS.get(this.seat).asStack();
        return new ItemRequirement(ItemRequirement.ItemUseType.CONSUME, drop);
    }

    @Override
    public boolean isInputFull() {
        return this.inputBuffer.size() >= this.getQueueLimit() || !this.magazine.isEmpty();
    }

    @Override
    public boolean isOutputFull() {
        return !this.outputBuffer.isEmpty();
    }

    @Override
    public ItemStack insertOutput(ItemStack stack) {
        if (stack.isEmpty()) return ItemStack.EMPTY;
        if (this.isOutputFull()) return stack;
        this.outputBuffer = stack;
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack extractNextInput() {
        if (!this.inputBuffer.isEmpty()) return this.inputBuffer.poll();
        if (this.magazine.isEmpty()) return ItemStack.EMPTY;
        int totalCount = ShupapiumAmmoContainerItem.getTotalAmmoCount(this.magazine);
        if (totalCount == 0) return ItemStack.EMPTY;
        if (totalCount == 1) this.updateInstance = true;
        return ShupapiumAmmoContainerItem.pollItemFromContainer(this.magazine);
    }

    @Override
    public List<ItemStack> getDrops() {
        List<ItemStack> list = super.getDrops();
        for (ItemStack s : this.inputBuffer) {
            if (!s.isEmpty()) list.add(s.copy());
        }
        if (!this.outputBuffer.isEmpty()) list.add(this.outputBuffer.copy());
        if (!this.magazine.isEmpty()) list.add(this.magazine.copy());
        return list;
    }
}
