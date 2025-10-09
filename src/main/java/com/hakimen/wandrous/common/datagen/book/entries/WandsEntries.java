package com.hakimen.wandrous.common.datagen.book.entries;

import com.hakimen.wandrous.common.registers.ItemRegister;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookSpotlightPageModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.BookTextPageModel;
import com.mojang.datafixers.util.Pair;

public class WandsEntries extends EntryProvider {
    public WandsEntries(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {
        this.page("page1", () -> BookSpotlightPageModel.create()
                .withItem(ItemRegister.WAND.get())
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );


        this.pageTitle("Wands");
        this.pageText("""
                Wands are the main way to cast spells, their visuals are randomly generated, along with their names and status
                """);

        this.page("page2", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context().pageText())
        );


        this.pageTitle("Wands");
        this.pageText("""
                They have a set of _status_ that define their behaviour:   \s
                
                * Cast Delay: Controls the cooldown between each spell cast on the sequence
                * Recharge Speed: Controls the cooldown between each wand complete cycle (a reload of sorts)
                * Max Mana: The total mana this wand has
                * Mana: Current mana this wand has (also displayed as a bar on the item while recharging)
                * Mana Charge Speed: The amount of mana that the wand regenerates per second
                * Capacity: The amount of slots for spells the wand has
                """);

    }

    @Override
    protected String entryName() {
        return "Wands";
    }

    @Override
    protected String entryDescription() {
        return "The means of spell casting";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return Pair.of(0, 0);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(ItemRegister.WAND.get());
    }

    @Override
    protected String entryId() {
        return "wands";
    }
}