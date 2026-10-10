package net.ato.shupapium.blockentities;

import com.simibubi.create.api.schematic.nbt.PartialSafeNBT;
import net.ato.shupapium.ShupapiumBlocks;
import net.ato.shupapium.ShupapiumDataComponents;
import net.ato.shupapium.blocks.ShupapiumAmmoContainerBlock;
import net.ato.shupapium.client.gui.ShupapiumAmmoContainerMenu;
import net.ato.shupapium.utils.IShupapiumAmmoContainerContainer;
import net.ato.shupapium.utils.ShupapiumAmmoContainerInterface;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.PatchedDataComponentMap;
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
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rbasamoyai.createbigcannons.remix.CBCHasIItemHandlerBlockEntity;
import rbasamoyai.createbigcannons.utils.CBCUtils;

import java.util.List;

public class ShupapiumAmmoContainerBlockEntity extends BlockEntity implements IShupapiumAmmoContainerContainer, MenuProvider, Nameable, CBCHasIItemHandlerBlockEntity, PartialSafeNBT {
    private IItemHandler inventory;
    private ItemStack ammoStack = ItemStack.EMPTY;
    private ItemStack propellantStack = ItemStack.EMPTY;
    @Nullable private Component name = null;

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

    public ShupapiumAmmoContainerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("Ammo", this.ammoStack.saveOptional(registries));
        tag.put("Propellants", this.propellantStack.saveOptional(registries));
        if (this.name != null)
            tag.putString("CustomName", Component.Serializer.toJson(this.name, registries));
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        this.ammoStack = ItemStack.parseOptional(registries, tag.getCompound("Ammo"));
        this.propellantStack = ItemStack.parseOptional(registries, tag.getCompound("Propellants"));
        if (tag.contains("CustomName", Tag.TAG_STRING))
            this.name = parseCustomNameSafe(tag.getString("CustomName"), registries);
    }

    @Override public ItemStack getMainAmmoStack() { return this.ammoStack; }

    @Override public ItemStack getPropellantStack() { return this.propellantStack; }

    public void setMainAmmoDirect(ItemStack stack) { this.ammoStack = stack == null ? ItemStack.EMPTY : stack; }

    public void setPropellantsDirect(ItemStack stack) { this.propellantStack = stack == null ? ItemStack.EMPTY : stack; }

    public boolean canDropInCreative() {
        return !this.getMainAmmoStack().isEmpty() || !this.getPropellantStack().isEmpty();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void applyImplicitComponents(DataComponentInput componentInput) {
        this.ammoStack = componentInput.getOrDefault(ShupapiumDataComponents.AMMO, ItemContainerContents.EMPTY).copyOne();
        this.propellantStack = componentInput.getOrDefault(ShupapiumDataComponents.PROPELLANTS, ItemContainerContents.EMPTY).copyOne();
        this.name = componentInput.getOrDefault(DataComponents.CUSTOM_NAME, null);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        components.set(ShupapiumDataComponents.AMMO, ItemContainerContents.fromItems(List.of(this.ammoStack)));
        components.set(ShupapiumDataComponents.PROPELLANTS, ItemContainerContents.fromItems(List.of(this.propellantStack)));
        components.set(DataComponents.CUSTOM_NAME, this.name);
    }

    @Nullable
    @Override
    public Component getCustomName() { return this.name; }

    public void setCustomName(@Nullable Component name) { this.name = name; }

    protected Component getDefaultName() {
        return Component.translatable(this.isCreativeContainer()
                ? ShupapiumBlocks.CREATIVE_SHUPAPIUM_AMMO_CONTAINER.get().getDescriptionId()
                : ShupapiumBlocks.SHUPAPIUM_AMMO_CONTAINER.get().getDescriptionId());
    }

    @Override
    public @NotNull Component getName() {
        Component custom = this.getCustomName();
        return custom == null ? this.getDefaultName() : custom;
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
            this.setMainAmmoDirect(ItemStack.EMPTY);
        } else if (slot == PROPELLANT_SLOT) {
            this.setPropellantsDirect(ItemStack.EMPTY);
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
        return player.canInteractWithBlock(this.getBlockPos(), 4);
    }

    @Override
    public void clearContent() {
        this.setMainAmmoDirect(ItemStack.EMPTY);
        this.setPropellantsDirect(ItemStack.EMPTY);
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

    @Override
    public IItemHandler getItemHandler(Direction side) {
        if (this.isCreativeContainer())
            return null;
        return this.inventory == null ? this.inventory = new ShupapiumAmmoContainerInterface(this) : this.inventory;
    }

    @Override
    public void writeSafe(CompoundTag tag, HolderLookup.Provider registries) {
        PatchedDataComponentMap copy = new PatchedDataComponentMap(DataComponentMap.EMPTY);
        if (this.components().has(DataComponents.CUSTOM_NAME))
            copy.set(DataComponents.CUSTOM_NAME, this.components().get(DataComponents.CUSTOM_NAME));
        this.saveAdditional(tag, registries);
        CBCUtils.saveComponentsToStructureTag(tag, copy, registries);
    }

    @Override
    public boolean canPlaceItem(int index, @NotNull ItemStack stack) {
        return !this.isCreativeContainer() && IShupapiumAmmoContainerContainer.super.canPlaceItem(index, stack);
    }
}
