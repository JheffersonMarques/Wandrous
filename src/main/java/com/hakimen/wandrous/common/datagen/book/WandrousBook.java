package com.hakimen.wandrous.common.datagen.book;

import com.hakimen.wandrous.common.datagen.book.categories.WandrousCategory;
import com.klikli_dev.modonomicon.api.datagen.SingleBookSubProvider;
import com.klikli_dev.modonomicon.api.datagen.book.BookModel;
import com.klikli_dev.modonomicon.book.BookDisplayMode;

import java.util.function.BiConsumer;

public class WandrousBook extends SingleBookSubProvider {

    public WandrousBook(BiConsumer<String, String> defaultLang) {
        super("wandrous", com.hakimen.wandrous.Wandrous.MODID, defaultLang);
    }

    @Override
    protected void registerDefaultMacros() {

    }

    @Override
    protected void generateCategories() {
        this.add(new WandrousCategory(this).generate());
    }

    @Override
    protected BookModel additionalSetup(BookModel book) {
       return super.additionalSetup(book.withBookTextOffsetX(5).withBookTextOffsetWidth(-5).withDisplayMode(BookDisplayMode.INDEX));
    }

    @Override
    protected String bookName() {
        return "Wandrous";
    }

    @Override
    protected String bookTooltip() {
        return "Noita-like spell casting";
    }

}
