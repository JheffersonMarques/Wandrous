package com.hakimen.wandrous.common.events;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.config.ServerConfig;
import com.klikli_dev.modonomicon.registry.ItemRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import static com.klikli_dev.modonomicon.registry.DataComponentRegistry.BOOK_ID;

@EventBusSubscriber
public class PlayerJoinEvent {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {

        if(!event.getEntity().getTags().contains("HasWandrousBook") && ServerConfig.SHOULD_GIVE_GUIDE_BOOK.get()){

            ItemStack stack = ItemRegistry.MODONOMICON.get().getDefaultInstance();

            stack.set(BOOK_ID, ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "wandrous"));

            event.getEntity().addTag("HasWandrousBook");
            event.getEntity().getInventory().add(stack);
        }
    }

}
