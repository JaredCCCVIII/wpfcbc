package net.ato.shupapium.cannons;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.schematics.requirement.ItemRequirement;
import net.ato.shupapium.ShupapiumConfig;
import net.ato.shupapium.items.ShupapiumAmmoContainerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtUtils;
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
import java.util.HashMap;
import java.util.Map;

public class ShupapiumACBreechBlockEntity extends AutocannonBreechBlockEntity {
    private IItemHandler inventory;
    private final int maxFireRate = ShupapiumConfig.MAX_SAC_FIRE_RATE.get();
    private int fireRate = 7;
    private float firingCooldown;
    private int animateTicks = 5;
    @Nullable
    DyeColor seat = null;
    public static final float RECOIL_TRAVEL = 0.25F;
    public final Map<BlockPos, BlockState> toAnimate = new HashMap<>();
    public ShupapiumACBreechBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public IItemHandler createItemHandler() {
        return this.inventory == null ? this.inventory = new ShupapiumACBreechInterface(this) : this.inventory;
    }

    @Override
    public void setMagazine(ItemStack stack) {
        super.setMagazine(stack);
    }

    @Override
    public void tick() {
        super.tick();
        this.allTick();
    }

    @Override
    public void tickFromContraption(Level level, PitchOrientedContraptionEntity poce, BlockPos localPos) {
        super.tickFromContraption(level, poce, localPos);
        this.allTick();
        if (level.isClientSide && poce.getContraption().getBlockEntityClientSide(localPos) instanceof ShupapiumACBreechBlockEntity cbe) {
            cbe.allTick();
        }
    }

    private void allTick() {
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
        super.setSeatColor(color);
    }

    @Nullable
    public DyeColor getSeatColor() { return this.seat; }

    @Override
    protected void read(CompoundTag tag, HolderLookup.Provider registry, boolean clientPacket) {
        super.read(tag, registry, clientPacket);
        this.fireRate = tag.getInt("FiringRate");
        this.firingCooldown = tag.getFloat("Cooldown");
        this.animateTicks = tag.getInt("AnimateTicks");
        this.seat = DyeColor.byName(tag.getString("Seat"), null);

        this.toAnimate.clear();
        ListTag renderedList = tag.getList("RenderedBlocks", Tag.TAG_COMPOUND);
        for (int i = 0; i < renderedList.size(); ++i) {
            CompoundTag block = renderedList.getCompound(i);
            this.toAnimate.put(NbtUtils.readBlockPos(block, "Pos").get(), NbtUtils.readBlockState(this.blockHolderGetter(), block.getCompound("Block")));
        }
    }

    @Override
    protected void write(CompoundTag tag, HolderLookup.Provider registry, boolean clientPacket) {
        super.write(tag, registry, clientPacket);
        tag.putInt("FiringRate", this.fireRate);
        tag.putFloat("Cooldown", this.firingCooldown);
        tag.putInt("AnimateTicks", this.animateTicks);
        if (this.seat != null) tag.putString("Seat", this.seat.getSerializedName());

        ListTag renderedList = new ListTag();
        for (Map.Entry<BlockPos, BlockState> entry : this.toAnimate.entrySet()) {
            CompoundTag block = new CompoundTag();
            block.put("Pos", NbtUtils.writeBlockPos(entry.getKey()));
            block.put("Block", NbtUtils.writeBlockState(entry.getValue()));
            renderedList.add(block);
        }
        tag.put("RenderedBlocks", renderedList);
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
    public ItemStack extractNextInput() {
        Deque<ItemStack> input = this.getInputBuffer();
        if (!input.isEmpty()) return input.poll();

        ItemStack magazine = this.getMagazine();
        if (magazine.isEmpty()) return ItemStack.EMPTY;
        if (ShupapiumAmmoContainerItem.getTotalAmmoCount(magazine) == 0) return ItemStack.EMPTY;

        boolean wasFilled = ShupapiumAmmoContainerItem.isFilled(magazine);
        ItemStack polled = ShupapiumAmmoContainerItem.pollItemFromContainer(magazine);
        if (wasFilled != ShupapiumAmmoContainerItem.isFilled(magazine)) this.setMagazine(magazine);
        return polled;
    }
}
