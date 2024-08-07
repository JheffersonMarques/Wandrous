package com.hakimen.wandrous.common.item;

import com.hakimen.wandrous.common.item.component.WandDataComponent;
import com.hakimen.wandrous.common.registers.DataComponentsRegister;
import com.hakimen.wandrous.common.registers.SoundRegister;
import com.hakimen.wandrous.common.utils.ChargesUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.Optional;
import java.util.Random;

import static com.hakimen.wandrous.common.item.component.WandDataComponent.DEFAULT_STAT;

public class RechargeItem extends Item {

    public enum RechargeTier {
        SHARD,
        CRYSTAL,
        GREATER_CRYSTAL
    };

    RechargeTier tier;

    public RechargeItem(int uses) {
        super(new Item.Properties().durability(uses));
        tier = RechargeTier.SHARD;
    }

    public RechargeItem(Properties props) {
        super(props);
        tier = RechargeTier.SHARD;
    }

    public RechargeItem(int uses, RechargeTier tier) {
        this(uses);
        this.tier = tier;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack pStack, LivingEntity p_344979_) {
        return switch (tier){   //TODO make these config values
            case SHARD -> 80;
            case CRYSTAL -> 60;
            case GREATER_CRYSTAL -> 40;
        };
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);
        if(getDamage(itemstack) != getMaxDamage(itemstack) || tier.equals(RechargeTier.SHARD)){
            pPlayer.startUsingItem(pUsedHand);
        } else {
            pPlayer.displayClientMessage(Component.literal("Out of charges"), true);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if(pLivingEntity instanceof Player player) {
            player.getInventory().items.stream().filter(stack -> stack.getItem() instanceof WandItem)
                    .forEach(stack -> {
                        Optional<ItemStackHandler> handler = Optional.ofNullable((ItemStackHandler)stack.getCapability(Capabilities.ItemHandler.ITEM));

                        handler.ifPresent(itemHandler -> {
                            itemHandler.deserializeNBT(pLevel.registryAccess(), stack.get(DataComponentsRegister.WAND_COMPONENT.get()).getInventory());
                            for (int i = 0; i < itemHandler.getSlots(); i++) {
                                ItemStack spellStack = itemHandler.getStackInSlot(i);
                                if(spellStack.getItem() instanceof SpellEffectItem && ChargesUtils.hasCharge(spellStack) && ChargesUtils.hasSpentCharges(spellStack)) {
                                    ChargesUtils.regainAllCharges(spellStack);
                                }
                            }
                            stack.update(DataComponentsRegister.WAND_COMPONENT.get(), DEFAULT_STAT, wandStat -> new WandDataComponent.WandStatBuilder(wandStat).
                                    setInventory(itemHandler.serializeNBT(pLevel.registryAccess())).build());
                        });
                    });

            player.getInventory().items.stream().filter(stack ->  stack.getItem() instanceof SpellEffectItem && ChargesUtils.hasCharge(stack) && ChargesUtils.hasSpentCharges(stack))
                    .forEach(ChargesUtils::regainAllCharges);

            if(tier.equals(RechargeTier.SHARD)){
                pStack.shrink(1);
                pLevel.broadcastEntityEvent(pLivingEntity, (byte) (player.getUsedItemHand().equals(InteractionHand.MAIN_HAND) ? 47 : 48));
            }else{
                pLevel.playSound(player, player.getOnPos(), SoundRegister.RECHARGE.get(), SoundSource.PLAYERS, 1f, (switch (tier) {
                    case SHARD -> 1.25f;
                    case CRYSTAL -> 1f;
                    case GREATER_CRYSTAL -> 0.75f;
                }) + new Random().nextFloat(-0.15f,0.15f));
                pStack.setDamageValue(pStack.getDamageValue() + 1);
            }
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }

    @Override
    public SoundEvent getBreakingSound() {
        return SoundRegister.RECHARGE.get();
    }
}
