package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.datagen.book.WandrousBook;
import com.klikli_dev.modonomicon.api.datagen.LanguageProviderCache;
import com.klikli_dev.modonomicon.api.datagen.NeoBookProvider;
import com.klikli_dev.modonomicon.datagen.EnUsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = Wandrous.MODID, bus = EventBusSubscriber.Bus.MOD)
public class DataGeneration {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        var enUsCache = new LanguageProviderCache("en_us");

        gen.addProvider(true, (DataProvider.Factory<BlockTagProvider>) (packOutput) -> new BlockTagProvider(packOutput, event.getLookupProvider(), existingFileHelper));
        gen.addProvider(true, (DataProvider.Factory<net.neoforged.neoforge.client.model.generators.BlockModelProvider>) (packOutput) -> new BlockModelProvider(packOutput, existingFileHelper));
        gen.addProvider(true, (DataProvider.Factory<net.neoforged.neoforge.client.model.generators.BlockStateProvider>) (packOutput) -> new BlockStateProvider(packOutput, existingFileHelper));
        gen.addProvider(true, (DataProvider.Factory<ItemModelProvider>) (packOutput) -> new ItemModelProvider(packOutput, existingFileHelper));

        gen.addProvider(true, (DataProvider.Factory<LootTableProvider>) pOutput -> LootProvider.create(pOutput, provider));
        gen.addProvider(true, (DataProvider.Factory<GlobalLootModProvider>) pOutput -> new GlobalLootModProvider(pOutput, provider));
        gen.addProvider(true, (DataProvider.Factory<LangProvider>) LangProvider::new);
        gen.addProvider(true, (DataProvider.Factory<RecipeProvider>) pOutput -> new RecipeProvider(pOutput, provider));

        gen.addProvider(true, NeoBookProvider.of(event, new WandrousBook(enUsCache)));
        gen.addProvider(event.includeClient(), new EnUsProvider(gen.getPackOutput(), enUsCache));
    }
}
