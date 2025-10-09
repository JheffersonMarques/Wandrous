package com.hakimen.wandrous.common.datagen.book.categories;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.datagen.book.entries.ArcaneInscribingEntries;
import com.hakimen.wandrous.common.datagen.book.entries.GettingStartedEntries;
import com.hakimen.wandrous.common.datagen.book.entries.WandsEntries;
import com.hakimen.wandrous.common.registers.ItemRegister;
import com.klikli_dev.modonomicon.api.datagen.CategoryProvider;
import com.klikli_dev.modonomicon.api.datagen.ModonomiconProviderBase;
import com.klikli_dev.modonomicon.api.datagen.book.BookCategoryModel;
import com.klikli_dev.modonomicon.api.datagen.book.BookIconModel;
import net.minecraft.resources.ResourceLocation;

public class WandrousCategory extends CategoryProvider {

    public WandrousCategory(ModonomiconProviderBase parent) {
        super(parent);
    }

    @Override
    protected String[] generateEntryMap() {
        return  new String[]{
                "_____________________",
                "__a_b_c______________",
                "_____________________",
        };
    }

    @Override
    protected void generateEntries() {
        var started = this.add(new GettingStartedEntries(this).generate('a'));

        var wands = this.add(new WandsEntries(this).generate('b'));
        var arcaneInscribing = this.add(new ArcaneInscribingEntries(this).generate('c'));
    }

    @Override
    public BookCategoryModel generate() {
        return super.generate().withBackground(ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "textures/book/img"));
    }

    @Override
    protected String categoryName() {
        return "Wandrous";
    }

    @Override
    protected BookIconModel categoryIcon() {
        return BookIconModel.create(ItemRegister.WAND.get());
    }

    @Override
    public String categoryId() {
        return "getting_started";
    }
}
