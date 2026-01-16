package net.ato.shupapium.blockentities;

import com.simibubi.create.api.schematic.nbt.PartialSafeNBT;
import net.ato.shupapium.ShupapiumBlocks;
import net.ato.shupapium.blocks.ShupapiumAmmoContainerBlock;
import net.ato.shupapium.client.gui.ShupapiumAmmoContainerMenu;
import net.ato.shupapium.utils.IShupapiumAmmoContainerContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ShupapiumAmmoContainerBlockEntity extends BlockEntity implements IShupapiumAmmoContainerContainer, MenuProvider, Nameable, PartialSafeNBT {
    private ItemStack ammo = ItemStack.EMPTY;
    private ItemStack propellants = ItemStack.EMPTY;
    private int currentIndex = 0;
    private Component name;
    private ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {
        @Override
        protected void onOpen(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
            ShupapiumAmmoContainerBlockEntity.this.playSound(SoundEvents.IRON_TRAPDOOR_OPEN);
            ShupapiumAmmoContainerBlockEntity.this.updateBlockState(state, true);
        }

        @Override
        protected void onClose(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state) {
            ShupapiumAmmoContainerBlockEntity.this.playSound(SoundEvents.IRON_TRAPDOOR_CLOSE);
            ShupapiumAmmoContainerBlockEntity.this.updateBlockState(state, false);
        }

        @Override
        protected void openerCountChanged(@NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState state, int count, int openCount) {
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            return player.containerMenu instanceof ShupapiumAmmoContainerMenu menu && menu.getContainer() == ShupapiumAmmoContainerBlockEntity.this;
        }
    };
    public ShupapiumAmmoContainerBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    @Override
    public ItemStack getMainAmmoStack() {
        return this.ammo == null ? ItemStack.EMPTY : this.ammo;
    }

    @Override
    public ItemStack getPropellantStack() {
        return this.propellants == null ? ItemStack.EMPTY : this.propellants;
    }

    public void setMainAmmoDirect(ItemStack stack) {
        this.ammo = stack == null ? ItemStack.EMPTY : stack;
    }

    public void setPropellantsDirect(ItemStack stack) {
        this.propellants = stack == null ? ItemStack.EMPTY : stack;
    }

    public boolean canDropInCreative() {
        return !this.getMainAmmoStack().isEmpty() || !this.getPropellantStack().isEmpty();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        super.saveAdditional(tag);
        if (this.ammo != null && !this.ammo.isEmpty()) tag.put("Ammo", this.ammo.save(new CompoundTag()));
        if (this.propellants != null && !this.propellants.isEmpty()) tag.put("Propellants", this.propellants.save(new CompoundTag()));
        if (this.name != null) tag.putString("CustomName", Component.Serializer.toJson(this.name));
        if (this.isCreativeContainer()) tag.putInt("CurrentIndex", this.currentIndex);
    }

    @Override
    public void load(@NotNull CompoundTag tag) {
        super.load(tag);
        this.ammo = tag.contains("Ammo") ? ItemStack.of(tag.getCompound("Ammo")) : ItemStack.EMPTY;
        this.propellants = tag.contains("Propellants") ? ItemStack.of(tag.getCompound("Propellants")) : ItemStack.EMPTY;
        this.name = tag.contains("CustomName", Tag.TAG_STRING) ? Component.Serializer.fromJson(tag.getString("CustomName")) : null;
        this.currentIndex = tag.contains("CurrentIndex", Tag.TAG_INT) ? tag.getInt("CurrentIndex") : 0;
    }

    @Override
    public void saveToItem(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (this.ammo != null && !this.ammo.isEmpty()) tag.put("Ammo", this.ammo.save(new CompoundTag()));
        if (this.propellants != null && !this.propellants.isEmpty()) tag.put("Propellants", this.propellants.save(new CompoundTag()));
        if (this.isCreativeContainer()) tag.putInt("CurrentIndex", this.currentIndex);
    }

    @Override
    public void writeSafe(CompoundTag tag) {
        super.saveAdditional(tag);
        if (this.name != null) tag.putString("CustomName", Component.Serializer.toJson(this.name));
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        return this.saveWithFullMetadata();
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }


    @Nullable
    @Override
    public Component getCustomName() {
        return this.name;
    }

    public void setCustomName(Component name) {
        this.name = name;
    }

    protected Component getDefaultName() {
        return Component.translatable(ShupapiumBlocks.SHUPAPIUM_AMMO_CONTAINER.get().getDescriptionId());
    }

    @Override
    public @NotNull Component getName() {
        return this.name == null ? this.getDefaultName() : this.name;
    }

    @Override
    public @NotNull Component getDisplayName() {
        return this.getName();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, @NotNull Inventory inventory, @NotNull Player player) {
        return ShupapiumAmmoContainerMenu.getServerMenuForBlockEntity(i, inventory, this, this.isCreativeContainer());
    }

    public boolean isCreativeContainer() {
        return ShupapiumBlocks.CREATIVE_SHUPAPIUM_AMMO_CONTAINER.has(this.getBlockState());
    }

    @Override
    public @NotNull ItemStack removeItem(int slot, int amount) {
        ItemStack ammo = this.getItem(slot);
        if (ammo.isEmpty()) return ItemStack.EMPTY;
        ItemStack split = ammo.split(amount);
        this.setItem(slot, ammo);
        this.setChanged();
        return split;
    }

    @Override
    public @NotNull ItemStack removeItemNoUpdate(int slot) {
        ItemStack ret = this.getItem(slot);
        if (slot == AMMO_SLOT) {
            this.ammo = ItemStack.EMPTY;
        } else if (slot == PROPELLANT_SLOT) {
            this.propellants = ItemStack.EMPTY;
        }
        return ret;
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack stack) {
        if (slot == AMMO_SLOT) {
            this.setMainAmmoDirect(stack);
        } else if (slot == PROPELLANT_SLOT) {
            this.setPropellantsDirect(stack);
        }
        this.setChanged();
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (this.level != null && this.openersCounter.getOpenerCount() > 0) {
            BlockState state = this.getBlockState();
            this.level.setBlock(this.getBlockPos(), state.setValue(ShupapiumAmmoContainerBlock.CONTAINER_STATE,
                    ShupapiumAmmoContainerBlock.State.getFromFilled(this.getTotalCount() > 0)), 3);
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return this.getBlockPos().closerThan(player.blockPosition(), 4);
    }

    @Override
    public void clearContent() {
        this.ammo = ItemStack.EMPTY;
        this.propellants = ItemStack.EMPTY;
    }

    @Override
    public void startOpen(@NotNull Player player) {
        if (!this.remove && !player.isSpectator()) {
            assert this.getLevel() != null;
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    @Override
    public void stopOpen(@NotNull Player player) {
        if (!this.remove && !player.isSpectator()) {
            assert this.getLevel() != null;
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void recheckOpen() {
        if (!this.remove) {
            assert this.getLevel() != null;
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    void updateBlockState(BlockState state, boolean open) {
        ShupapiumAmmoContainerBlock.State containerState = open ? ShupapiumAmmoContainerBlock.State.getFromFilled(this.getTotalCount() > 0)
                : ShupapiumAmmoContainerBlock.State.CLOSED;
        assert this.level != null;
        this.level.setBlock(this.getBlockPos(), state.setValue(ShupapiumAmmoContainerBlock.CONTAINER_STATE, containerState), 3);
    }

    void playSound(SoundEvent sound) {
        double x = (double) this.worldPosition.getX() + 0.5d;
        double y = (double) this.worldPosition.getY() + 0.5d;
        double z = (double) this.worldPosition.getZ() + 0.5d;
        assert this.level != null;
        this.level.playSound(null, x, y, z, sound, SoundSource.BLOCKS, 0.5F, this.level.getRandom().nextFloat() * 0.1F + 0.9F);
    }

    public ShupapiumAmmoContainerBlock.State getContainerState() {
        return this.getBlockState().getValue(ShupapiumAmmoContainerBlock.CONTAINER_STATE);
    }
}
