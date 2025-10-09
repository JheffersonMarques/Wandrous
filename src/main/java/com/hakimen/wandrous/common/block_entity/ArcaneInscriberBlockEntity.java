package com.hakimen.wandrous.common.block_entity;

import com.hakimen.wandrous.common.recipe.ArcaneInscriberRecipeInput;
import com.hakimen.wandrous.common.recipe.ArcaneInscribingRecipe;
import com.hakimen.wandrous.common.registers.BlockEntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ArcaneInscriberBlockEntity extends BlockEntity implements BlockEntityTicker<ArcaneInscriberBlockEntity> {

    List<BlockPos> offsets = List.of(
            new BlockPos(0, -1,-3),
            new BlockPos(-3, -1,0),
            new BlockPos(3, -1, 0),
            new BlockPos(0, -1, 3),

            new BlockPos(-2, -1, -2),
            new BlockPos(-2, -1,  2),
            new BlockPos(2, -1,   2),
            new BlockPos(2, -1,  -2),

            new BlockPos(0, -1, -6),
            new BlockPos(-6, -1, 0),
            new BlockPos(6, -1,  0),
            new BlockPos(0, -1,  6),

            new BlockPos(-4, -1, -4),
            new BlockPos(-4, -1,  4),
            new BlockPos(4, -1,   4),
            new BlockPos(4, -1,  -4)
    );

    ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    };

    int progress = 0;
    int maxTicks = 0;

    public ArcaneInscriberBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegister.ARCANE_INSCRIBER.get(), pPos, pBlockState);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        inventory.deserializeNBT(pRegistries, pTag.getCompound("Inventory"));
        progress = pTag.getInt("Progress");
        maxTicks = pTag.getInt("MaxTicks");
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, BlockEntity::saveWithFullMetadata);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return this.saveWithFullMetadata(pRegistries);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.put("Inventory", inventory.serializeNBT(pRegistries));
        pTag.putInt("Progress", progress);
        pTag.putInt("MaxTicks", maxTicks);
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    public void clientTick(Level pLevel, BlockPos pPos, BlockState pState, ArcaneInscriberBlockEntity pBlockEntity) {

    }

    @Override
    public void tick(Level pLevel, BlockPos pPos, BlockState pState, ArcaneInscriberBlockEntity pBlockEntity) {
        AtomicInteger totalCount = new AtomicInteger();
        SimpleContainer simpleContainer = new SimpleContainer(16);

        for (BlockPos offset : offsets) {
            BlockPos pos = pPos.offset(offset);
            pLevel.getBlockEntity(pos, BlockEntityRegister.GLYPH_PROJECTOR_ENTITY.get()).ifPresent(
                    glyphProjectorBlockEntity -> {
                        totalCount.getAndIncrement();
                        simpleContainer.addItem(glyphProjectorBlockEntity.getInventory().getStackInSlot(0));
                    }
            );
        }

        int calculatedTier = totalCount.get() / 4;


        ArcaneInscriberRecipeInput input = new ArcaneInscriberRecipeInput(simpleContainer, calculatedTier, getInventory().getStackInSlot(0));

        Optional<RecipeHolder<ArcaneInscribingRecipe>> match = pLevel.getRecipeManager()
                .getRecipeFor(ArcaneInscribingRecipe.Type.INSTANCE, input, pLevel);

        if (isCrafting() && pBlockEntity.getProgress() <= 1) {
            pLevel.playSound(null, pPos, SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.BLOCKS, 1f, 1f);
        }

        if (match.isPresent()) {
            ArcaneInscribingRecipe recipe = match.get().value();
            if(this.maxTicks != recipe.getTicksNeeded()){
                this.maxTicks = recipe.getTicksNeeded();
            }
            if (progress < recipe.getTicksNeeded()) {
                progress++;
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
            if (progress == recipe.getTicksNeeded()) {
                getInventory().extractItem(0, 1, false);
                ItemStack returnFromCraft = match.get().value().assemble(input, pLevel.registryAccess());

                for (BlockPos offset : offsets) {
                    BlockPos pos = pPos.offset(offset);
                    pLevel.getBlockEntity(pos, BlockEntityRegister.GLYPH_PROJECTOR_ENTITY.get()).ifPresent(
                            glyphProjectorBlockEntity -> {
                                ItemStack stackInSlot = glyphProjectorBlockEntity.getInventory().getStackInSlot(0);
                                if (stackInSlot.isDamageableItem()
                                    && stackInSlot.getDamageValue() != stackInSlot.getMaxDamage()) {
                                    stackInSlot.setDamageValue(stackInSlot.getDamageValue() + 1);
                                } else if (stackInSlot.getDamageValue() >= stackInSlot.getMaxDamage() - 1) {
                                    glyphProjectorBlockEntity.getInventory().extractItem(0, 1, false);
                                    glyphProjectorBlockEntity.setChanged();
                                }
                            }
                    );
                }

                getInventory().insertItem(0, returnFromCraft, false);
                progress = 0;
                setChanged();
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        } else if (progress > 0) {
            progress = Math.max(progress - 1, 0);
            maxTicks = 0;
            setChanged();
            level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        loadAdditional(tag, lookupProvider);
    }

    public Integer getProgress() {
        return progress;
    }

    public boolean isCrafting() {
        return progress > 0;
    }

    public int getMaxTicks() {
        return maxTicks;
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if(level != null){
            level.setBlockAndUpdate(this.getBlockPos(), this.getBlockState());
        }
    }

    public List<BlockPos> getOffsets() {
        return offsets;
    }
}
