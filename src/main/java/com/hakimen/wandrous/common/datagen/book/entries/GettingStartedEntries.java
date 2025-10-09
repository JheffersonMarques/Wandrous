package com.hakimen.wandrous.common.datagen.book.entries;

import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.Items;

public class GettingStartedEntries extends EntryProvider {
    public GettingStartedEntries(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {
        this.page("page1", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context.pageText())
        );

        this.pageTitle("Getting Started");
        this.pageText("""
                Welcome to Wandrous\\
                Wandrous is a mod inspired on the casting mechanics of Noita\\
                \\
                To get started with creating and using the very flexible spell system you will need a Wand!\\
                \\
                You can get wands by finding a Dungeon or a Ancient City, you can get a Dungeon Explorer Map from a Cartographer.
                """);

    }

    @Override
    protected String entryName() {
        return "Getting Started";
    }

    @Override
    protected String entryDescription() {
        return "The basics";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return Pair.of(0, 1);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(Items.BOOK);
    }

    @Override
    protected String entryId() {
        return "getting_started";
    }
}
