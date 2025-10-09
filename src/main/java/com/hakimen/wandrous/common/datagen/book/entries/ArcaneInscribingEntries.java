package com.hakimen.wandrous.common.datagen.book.entries;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.registers.ItemRegister;
import com.klikli_dev.modonomicon.api.datagen.CategoryProviderBase;
import com.klikli_dev.modonomicon.api.datagen.EntryProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import com.klikli_dev.modonomicon.api.datagen.book.page.*;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;

public class ArcaneInscribingEntries extends EntryProvider {
    public ArcaneInscribingEntries(CategoryProviderBase parent) {
        super(parent);
    }

    @Override
    protected void generatePages() {
        this.page("page0", () -> BookTextPageModel.create()
                .withTitle(this.context().pageTitle())
                .withText(this.context.pageText())
        );

        this.pageTitle("Arcane Inscribing");
        this.pageText("""
                Arcane Inscribing is the process which allows you to make spells
                You can create almost all spells using this method
                
                To start arcane inscribing you need an Arcane Inscriber, some Arcane Projectors and the Inscribed Lenses
                
                
                There are 4 Tiers of inscribing, with each tier needing more glyph projectors than the last
                """);

        this.page("page_0_empty", BookEmptyPageModel::create);

        this.page("page1", () -> BookSpotlightPageModel.create()
                .withItem(ItemRegister.ARCANE_INSCRIBER.get())
                .withTitle(this.context().pageTitle())
                .withText(this.context.pageText())
        );

        this.pageTitle("Arcane Inscriber");
        this.pageText("""
                This is the arcane inscriber and allows you to create your spells, it holds the catalyst for making the spells
                """);

        this.page("page1_crafting", () -> BookCraftingRecipePageModel.create()
                .withRecipeId1("wandrous:arcane_inscriber")
                .withText(this.context.pageText())
        );

        this.pageText("""
                And you can craft it like so.
                """);

        this.page("page2", () -> BookSpotlightPageModel.create()
                .withItem(ItemRegister.ARCANE_INSCRIBER.get())
                .withTitle(this.context().pageTitle())
                .withText(this.context.pageText())
        );

        this.pageTitle("Glyph Projectors");
        this.pageText("""
                This is the glyph projector, it holds the lenses which imbue the catalyst with the properties to become a spell.
                """);

        this.page("page2_crafting", () -> BookCraftingRecipePageModel.create()
                .withRecipeId1("wandrous:glyph_projector")
                .withText(this.context.pageText())
        );

        this.pageText("""
                And you can craft it like so.
                """);


        this.page("page3", () -> BookMultiblockPageModel.create()
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "tier_1_inscribing"))
                .withText(this.context.pageText())
        );

        this.pageText("""
                Tier 1 Multiblock Structure
                """);

        this.page("page4", () -> BookMultiblockPageModel.create()
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "tier_2_inscribing"))
                .withText(this.context.pageText())
        );

        this.pageText("""
                Tier 2 Multiblock Structure
                """);

        this.page("page5", () -> BookMultiblockPageModel.create()
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "tier_3_inscribing"))
                .withText(this.context.pageText())
        );

        this.pageText("""
                Tier 3 Multiblock Structure
                """);

        this.page("page6", () -> BookMultiblockPageModel.create()
                .withMultiblockId(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "tier_4_inscribing"))
                .withText(this.context.pageText())
        );

        this.pageText("""
                Tier 4 Multiblock Structure
                """);
    }

    @Override
    protected String entryName() {
        return "Arcane Inscribing";
    }

    @Override
    protected String entryDescription() {
        return "How to craft spells";
    }

    @Override
    protected Pair<Integer, Integer> entryBackground() {
        return Pair.of(0, 1);
    }

    @Override
    protected BookIconModel entryIcon() {
        return BookIconModel.create(ItemRegister.ARCANE_INSCRIBER.get());
    }

    @Override
    protected String entryId() {
        return "inscribing";
    }
}
