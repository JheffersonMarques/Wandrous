package com.hakimen.wandrous.common.datagen;

import com.hakimen.wandrous.Wandrous;
import com.hakimen.wandrous.common.data.Glyph;
import com.hakimen.wandrous.common.datagen.recipes.ArcaneInscriberRecipeBuilder;
import com.hakimen.wandrous.common.item.InscribedLensItem;
import com.hakimen.wandrous.common.registers.GlyphRegister;
import com.hakimen.wandrous.common.registers.ItemRegister;
import com.klikli_dev.modonomicon.registry.ItemRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.neoforged.neoforge.common.Tags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.klikli_dev.modonomicon.registry.DataComponentRegistry.BOOK_ID;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {
    public RecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        arcaneInscriberRecipes(pRecipeOutput);
        craftingTableRecipes(pRecipeOutput);

        ItemStack stack = ItemRegistry.MODONOMICON.get().getDefaultInstance();
        stack.set(BOOK_ID, ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "wandrous"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, stack)
                .requires(Items.BOOK)
                .requires(ItemRegister.TEALESTITE_SHARD.get())
                .unlockedBy(getHasName(ItemRegister.TEALESTITE_SHARD.get()), has(ItemRegister.TEALESTITE_SHARD.get()))
                .save(pRecipeOutput, ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, "guide_book"));
    }

    public void craftingTableRecipes(RecipeOutput pRecipeOutput) {

        ItemStack stack = ItemRegister.INSCRIBED_LENS.get().getDefaultInstance();

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.BIND.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.CHAIN)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, location("bind_lens"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.CONTROL.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.AMETHYST_SHARD)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, location("control_lens"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.DESTINY.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.ENDER_EYE)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, location("destiny_lens"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.FOCUS.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")

                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.GLOWSTONE_DUST)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, location("focus_lens"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.GUIDANCE.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.REDSTONE)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, location("guidance_lens"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.KNOWLEDGE.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.BOOK)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, location("knowledge_lens"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.MIND.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.GOLD_NUGGET)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, location("mind_lens"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.NEW.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.PAPER)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, location("new_lens"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.POWER.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.GUNPOWDER)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, location("power_lens"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.TINKER.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.REPEATER)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, location("tinker_lens"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.VITALITY.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.GOLDEN_CARROT)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, location("vitality_lens"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, InscribedLensItem.makeGlyphStack(stack.copy(), GlyphRegister.WEAVE.get()))
                .pattern(" - ")
                .pattern("+x+")
                .pattern(" - ")
                .define('+', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Items.STRING)
                .define('x', Tags.Items.GLASS_PANES)
                .unlockedBy(getHasName(Items.GLASS_PANE), has(Tags.Items.GLASS_PANES))
                .save(pRecipeOutput, location("weave_lens"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.ARCANE_INSCRIBER.get())
                .pattern("+-+")
                .pattern("+++")
                .define('-', ItemRegister.TEALESTITE_SHARD::get)
                .define('+', Tags.Items.INGOTS_IRON)
                .unlockedBy(getHasName(ItemRegister.TEALESTITE_SHARD.get()), has(ItemRegister.TEALESTITE_SHARD.get()))
                .save(pRecipeOutput, location("arcane_inscriber"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.GLYPH_PROJECTOR.get())
                .pattern("+-+")
                .pattern("+y+")
                .pattern("+,+")
                .define('y', ItemRegister.TEALESTITE_CLUSTER::get)
                .define(',', ItemRegister.TEALESTITE_SHARD::get)
                .define('-', Tags.Items.GLASS_PANES)
                .define('+', Tags.Items.INGOTS_IRON)
                .unlockedBy(getHasName(ItemRegister.TEALESTITE_SHARD.get()), has(ItemRegister.TEALESTITE_SHARD.get()))
                .save(pRecipeOutput, location("glyph_projector"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.TEALESTITE_RECHARGE_CRYSTAL.get())
                .pattern(" + ")
                .pattern("+y+")
                .pattern(" + ")
                .define('y', ItemRegister.TEALESTITE_SHARD::get)
                .define('+', Tags.Items.NUGGETS_GOLD)
                .unlockedBy(getHasName(ItemRegister.TEALESTITE_SHARD.get()), has(ItemRegister.TEALESTITE_SHARD.get()))
                .save(pRecipeOutput, location("recharge_crystal"));

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.TEALESTITE_GREATER_RECHARGE_CRYSTAL.get())
                .pattern("x+ ")
                .pattern("+y+")
                .pattern(" +x")
                .define('y', ItemRegister.TEALESTITE_RECHARGE_CRYSTAL::get)
                .define('x', ItemRegister.TEALESTITE_SHARD::get)
                .define('+', Tags.Items.NUGGETS_GOLD)
                .unlockedBy(getHasName(ItemRegister.TEALESTITE_SHARD.get()), has(ItemRegister.TEALESTITE_SHARD.get()))
                .save(pRecipeOutput, location("greater_recharge_crystal"));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ItemRegister.ARCANE_DISPENSER.get())
                .pattern("+++")
                .pattern("+y+")
                .pattern("|,|")
                .define('y', Items.REDSTONE)
                .define(',', Items.BOW)
                .define('|', ItemRegister.TEALESTITE_SHARD::get)
                .define('+', Tags.Items.COBBLESTONES)
                .unlockedBy(getHasName(ItemRegister.TEALESTITE_SHARD.get()), has(ItemRegister.TEALESTITE_SHARD.get()))
                .save(pRecipeOutput, location("arcane_dispenser"));

        //CHERT STUFF
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ItemRegister.MOSSY_CHERT_BRICKS.get())
                .requires(ItemRegister.CHERT_BRICKS.get())
                .requires(Items.VINE)
                .unlockedBy(getHasName(ItemRegister.CHERT_BRICKS.get()), has(ItemRegister.CHERT_BRICKS.get()))
                .unlockedBy(getHasName(Items.VINE), has(Items.VINE))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ItemRegister.MOSSY_CHERT_TILES.get())
                .requires(ItemRegister.CHERT_TILES.get())
                .requires(Items.VINE)
                .unlockedBy(getHasName(ItemRegister.CHERT_TILES.get()), has(ItemRegister.CHERT_TILES.get()))
                .unlockedBy(getHasName(Items.VINE), has(Items.VINE))
                .save(pRecipeOutput);

        stairBuilder(ItemRegister.CHERT_STAIRS.get(), Ingredient.of(ItemRegister.CHERT.get()))
                .unlockedBy(getHasName(ItemRegister.CHERT.get()), has(ItemRegister.CHERT.get()))
                .save(pRecipeOutput);

        stairBuilder(ItemRegister.POLISHED_CHERT_STAIRS.get(), Ingredient.of(ItemRegister.POLISHED_CHERT.get()))
                .unlockedBy(getHasName(ItemRegister.POLISHED_CHERT.get()), has(ItemRegister.POLISHED_CHERT.get()))
                .save(pRecipeOutput);

        stairBuilder(ItemRegister.CHERT_TILES_STAIRS.get(), Ingredient.of(ItemRegister.CHERT_TILES.get()))
                .unlockedBy(getHasName(ItemRegister.CHERT_TILES.get()), has(ItemRegister.CHERT_TILES.get()))
                .save(pRecipeOutput);

        stairBuilder(ItemRegister.CHERT_BRICKS_STAIRS.get(), Ingredient.of(ItemRegister.CHERT_BRICKS.get()))
                .unlockedBy(getHasName(ItemRegister.CHERT_BRICKS.get()), has(ItemRegister.CHERT_BRICKS.get()))
                .save(pRecipeOutput);

        stairBuilder(ItemRegister.MOSSY_CHERT_BRICKS_STAIRS.get(), Ingredient.of(ItemRegister.MOSSY_CHERT_BRICKS.get()))
                .unlockedBy(getHasName(ItemRegister.MOSSY_CHERT_BRICKS.get()), has(ItemRegister.MOSSY_CHERT_BRICKS.get()))
                .save(pRecipeOutput);

        stairBuilder(ItemRegister.MOSSY_CHERT_TILES_STAIRS.get(), Ingredient.of(ItemRegister.MOSSY_CHERT_TILES.get()))
                .unlockedBy(getHasName(ItemRegister.MOSSY_CHERT_TILES.get()), has(ItemRegister.MOSSY_CHERT_TILES.get()))
                .save(pRecipeOutput);


        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_SLAB.get(), ItemRegister.CHERT.get());
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.POLISHED_CHERT_SLAB.get(), ItemRegister.POLISHED_CHERT.get());
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_TILES_SLAB.get(), ItemRegister.CHERT_TILES.get());
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_BRICKS_SLAB.get(), ItemRegister.CHERT_BRICKS.get());
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.MOSSY_CHERT_BRICKS_SLAB.get(), ItemRegister.MOSSY_CHERT_BRICKS.get());
        slab(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.MOSSY_CHERT_TILES_SLAB.get(), ItemRegister.MOSSY_CHERT_TILES.get());

        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_WALL.get(), ItemRegister.CHERT.get());
        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_BRICKS_WALL.get(), ItemRegister.CHERT_BRICKS.get());
        wall(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.MOSSY_CHERT_BRICKS_WALL.get(), ItemRegister.MOSSY_CHERT_BRICKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHISELED_CHERT_BRICKS.get())
                .pattern("x")
                .pattern("x")
                .define('x', ItemRegister.CHERT_BRICKS_SLAB::get)
                .unlockedBy(getHasName(ItemRegister.CHERT_BRICKS_SLAB.get()), has(ItemRegister.CHERT_BRICKS_SLAB.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_PILLAR.get())
                .pattern("x")
                .pattern("x")
                .define('x', ItemRegister.CHERT_TILES_SLAB::get)
                .unlockedBy(getHasName(ItemRegister.CHERT_TILES_SLAB.get()), has(ItemRegister.CHERT_TILES_SLAB.get()))
                .save(pRecipeOutput);

        simpleCookingRecipe(pRecipeOutput, "smelting", RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, 100, ItemRegister.CHERT_BRICKS.get(), ItemRegister.CRACKED_CHERT_BRICKS.get(), 0.1f);

        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_SLAB.get(), ItemRegister.CHERT.get(), 2);
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_STAIRS.get(), ItemRegister.CHERT.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_WALL.get(), ItemRegister.CHERT.get());

        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.POLISHED_CHERT.get(), ItemRegister.CHERT.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.POLISHED_CHERT_SLAB.get(), ItemRegister.CHERT.get(), 2);
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.POLISHED_CHERT_STAIRS.get(), ItemRegister.CHERT.get());

        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_BRICKS.get(), ItemRegister.CHERT.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHISELED_CHERT_BRICKS.get(), ItemRegister.CHERT.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_BRICKS_SLAB.get(), ItemRegister.CHERT.get(), 2);
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_BRICKS_STAIRS.get(), ItemRegister.CHERT.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_BRICKS_WALL.get(), ItemRegister.CHERT.get());

        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_TILES.get(), ItemRegister.CHERT.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_TILES_SLAB.get(), ItemRegister.CHERT.get(), 2);
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_TILES_STAIRS.get(), ItemRegister.CHERT.get());

        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.POLISHED_CHERT_SLAB.get(), ItemRegister.POLISHED_CHERT.get(), 2);
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.POLISHED_CHERT_STAIRS.get(), ItemRegister.POLISHED_CHERT.get());

        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_TILES_SLAB.get(), ItemRegister.CHERT_TILES.get(), 2);
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_TILES_STAIRS.get(), ItemRegister.CHERT_TILES.get());

        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHISELED_CHERT_BRICKS.get(), ItemRegister.CHERT_BRICKS.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_BRICKS_SLAB.get(), ItemRegister.CHERT_BRICKS.get(), 2);
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_BRICKS_STAIRS.get(), ItemRegister.CHERT_BRICKS.get());
        stonecutterResultFromBase(pRecipeOutput, RecipeCategory.BUILDING_BLOCKS, ItemRegister.CHERT_BRICKS_WALL.get(), ItemRegister.CHERT_BRICKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegister.TEALESTITE_BLOCK.get())
                .pattern("xx")
                .pattern("xx")
                .define('x', ItemRegister.TEALESTITE_SHARD::get)
                .unlockedBy(getHasName(ItemRegister.TEALESTITE_SHARD.get()), has(ItemRegister.TEALESTITE_SHARD.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ItemRegister.POLISHED_CHERT.get(), 4)
                .pattern("xx")
                .pattern("xx")
                .define('x', ItemRegister.CHERT::get)
                .unlockedBy(getHasName(ItemRegister.CHERT.get()), has(ItemRegister.CHERT.get()))
                .save(pRecipeOutput);
    }

    static List<Glyph> TRIGGER = List.of(
            GlyphRegister.TINKER.get(),
            GlyphRegister.CONTROL.get()
    );

    static List<Glyph> TIMER = List.of(
            GlyphRegister.DESTINY.get(),
            GlyphRegister.FOCUS.get()
    );

    private void projectiles(RecipeOutput pRecipeOutput) {
        inscriberRecipe(pRecipeOutput, "glimmering_bolt", List.of(
                        GlyphRegister.NEW.get(),
                        GlyphRegister.CONTROL.get()
                ),
                Ingredient.of(Items.AMETHYST_SHARD),
                ItemRegister.GLIMMERING_BOLT_SPELL.get().getDefaultInstance(),
                100,
                1);


        inscriberRecipe(pRecipeOutput, "trigger_glimmering_bolt",
                TRIGGER,
                Ingredient.of(ItemRegister.GLIMMERING_BOLT_SPELL.get()),
                ItemRegister.TRIGGER_GLIMMERING_BOLT_SPELL.get().getDefaultInstance(),
                100,
                1);

        inscriberRecipe(pRecipeOutput, "timer_glimmering_bolt",
                TIMER,
                Ingredient.of(ItemRegister.GLIMMERING_BOLT_SPELL.get()),
                ItemRegister.TIMER_GLIMMERING_BOLT_SPELL.get().getDefaultInstance(),
                100,
                1);

        inscriberRecipe(pRecipeOutput, "fireball", List.of(
                GlyphRegister.WEAVE.get(),
                GlyphRegister.POWER.get(),
                GlyphRegister.POWER.get(),
                GlyphRegister.POWER.get(),
                GlyphRegister.POWER.get(),
                GlyphRegister.FOCUS.get(),
                GlyphRegister.CONTROL.get(),
                GlyphRegister.CONTROL.get()
        ), Ingredient.of(Items.FIRE_CHARGE), ItemRegister.FIREBALL_SPELL.get().getDefaultInstance(), 100, 2);

        inscriberRecipe(pRecipeOutput, "trigger_fireball",
                TRIGGER,
                Ingredient.of(ItemRegister.FIREBALL_SPELL.get()),
                ItemRegister.TRIGGER_FIREBALL_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "timer_fireball",
                TIMER,
                Ingredient.of(ItemRegister.FIREBALL_SPELL.get()),
                ItemRegister.TIMER_FIREBALL_SPELL.get().getDefaultInstance(),
                100,
                2);


        inscriberRecipe(pRecipeOutput, "bomb",
                List.of(
                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.GUNPOWDER),
                ItemRegister.BOMB_SPELL.get().getDefaultInstance(),
                100,
                3);

        inscriberRecipe(pRecipeOutput, "black_hole",
                List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.ENDER_EYE),
                ItemRegister.BLACK_HOLE_SPELL.get().getDefaultInstance(),
                100,
                4
        );

        inscriberRecipe(pRecipeOutput, "timer_black_hole",
                TIMER,
                Ingredient.of(ItemRegister.BLACK_HOLE_SPELL.get()),
                ItemRegister.TIMER_BLACK_HOLE_SPELL.get().getDefaultInstance(),
                100,
                4);


        inscriberRecipe(pRecipeOutput, "sonic_boom",
                List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.VITALITY.get()
                ),
                Ingredient.of(Items.ECHO_SHARD),
                ItemRegister.SONIC_BOOM_SPELL.get().getDefaultInstance(),
                100,
                4);


        inscriberRecipe(pRecipeOutput, "trigger_sonic_boom",
                TRIGGER,
                Ingredient.of(ItemRegister.SONIC_BOOM_SPELL.get()),
                ItemRegister.TRIGGER_SONIC_BOOM_SPELL.get().getDefaultInstance(),
                100,
                4);

        inscriberRecipe(pRecipeOutput, "timer_sonic_boom",
                TIMER,
                Ingredient.of(ItemRegister.SONIC_BOOM_SPELL.get()),
                ItemRegister.TIMER_SONIC_BOOM_SPELL.get().getDefaultInstance(),
                100,
                4);

        inscriberRecipe(pRecipeOutput, "chain_shot",
                List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(ItemRegister.TEALESTITE_SHARD.get()),
                ItemRegister.CHAIN_SHOT_SPELL.get().getDefaultInstance(),
                100,
                3);

        inscriberRecipe(pRecipeOutput, "trigger_chain_shot",
                TRIGGER,
                Ingredient.of(ItemRegister.CHAIN_SHOT_SPELL.get()),
                ItemRegister.TRIGGER_CHAIN_SHOT_SPELL.get().getDefaultInstance(),
                100,
                3);

        inscriberRecipe(pRecipeOutput, "timer_chain_shot",
                TIMER,
                Ingredient.of(ItemRegister.CHAIN_SHOT_SPELL.get()),
                ItemRegister.TIMER_CHAIN_SHOT_SPELL.get().getDefaultInstance(),
                100,
                3);
    }

    private void staticProjectiles(RecipeOutput pRecipeOutput) {
        inscriberRecipe(pRecipeOutput, "explosion", List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.TNT),
                ItemRegister.EXPLOSION_SPELL.get().getDefaultInstance(),
                100,
                1);

        inscriberRecipe(pRecipeOutput, "major_explosion", List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.TNT),
                ItemRegister.MAJOR_EXPLOSION_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "gust", List.of(
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.WIND_CHARGE),
                ItemRegister.GUST_SPELL.get().getDefaultInstance(),
                100,
                2);


        inscriberRecipe(pRecipeOutput, "lighting_bolt", List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.LIGHTNING_ROD),
                ItemRegister.SUMMON_LIGHTING_BOLT_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "plasma_beam", List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),

                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),

                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),

                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),

                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.REDSTONE_BLOCK),
                ItemRegister.PLASMA_BEAM.get().getDefaultInstance(),
                100,
                4);

        inscriberRecipe(pRecipeOutput, "tnt_minecart", List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.TNT_MINECART),
                ItemRegister.SUMMON_TNT_MINECART_SPELL.get().getDefaultInstance(),
                100,
                2);


        inscriberRecipe(pRecipeOutput, "chain_prison", List.of(
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.CHAIN),
                ItemRegister.CHAIN_PRISON_SPELL.get().getDefaultInstance(),
                100,
                3);

        inscriberRecipe(pRecipeOutput, "conjure_webs", List.of(
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.SPIDER_EYE),
                ItemRegister.CONJURE_WEBS_SPELL.get().getDefaultInstance(),
                100,
                2);


        inscriberRecipe(pRecipeOutput, "conjure_light", List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.GLOWSTONE_DUST),
                ItemRegister.CONJURE_LIGHT_SPELL.get().getDefaultInstance(),
                100,
                1);

        inscriberRecipe(pRecipeOutput, "conjure_block", List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Tags.Items.GLASS_BLOCKS),
                ItemRegister.CONJURE_BLOCK_SPELL.get().getDefaultInstance(),
                100,
                1);


        inscriberRecipe(pRecipeOutput, "collect", List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.HOPPER),
                ItemRegister.COLLECT_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "smelt", List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.FURNACE),
                ItemRegister.SMELT_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "teleport", List.of(
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.ENDER_PEARL),
                ItemRegister.TELEPORT_SPELL.get().getDefaultInstance(),
                100,
                2);


        inscriberRecipe(pRecipeOutput, "swap_teleport", List.of(
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.ENDER_PEARL),
                ItemRegister.SWAP_TELEPORT_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "homebringer_teleport", List.of(
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.NEW.get()
                ),
                Ingredient.of(Items.ENDER_PEARL),
                ItemRegister.HOME_BRINGER_TELEPORT_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "drill", List.of(
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.IRON_PICKAXE),
                ItemRegister.DRILL_SPELL.get().getDefaultInstance(),
                100,
                1);

        inscriberRecipe(pRecipeOutput, "chainsaw", List.of(
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.IRON_AXE),
                ItemRegister.CHAINSAW_SPELL.get().getDefaultInstance(),
                100,
                1);

        inscriberRecipe(pRecipeOutput, "giga_drill", List.of(
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.DIAMOND_PICKAXE),
                ItemRegister.GIGA_DRILL_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "glyph_of_trigger", List.of(
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.GUIDANCE.get(),

                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.SCULK_SENSOR),
                ItemRegister.GLYPH_OF_TRIGGERING.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "summon_bees", List.of(
                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get(),

                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.HONEY_BLOCK),
                ItemRegister.SUMMON_BEE_SWARM_SPELL.get().getDefaultInstance(),
                100,
                2);

        inscriberRecipe(pRecipeOutput, "summon_wolfs", List.of(
                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get(),
                        GlyphRegister.NEW.get(),

                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.BONE),
                ItemRegister.SUMMON_WOLF_PACK_SPELL.get().getDefaultInstance(),
                100,
                2);
    }

    private void movers(RecipeOutput pRecipeOutput) {
        inscriberRecipe(pRecipeOutput, "homing", List.of(
                GlyphRegister.GUIDANCE.get(),
                GlyphRegister.GUIDANCE.get(),
                GlyphRegister.WEAVE.get(),
                GlyphRegister.WEAVE.get(),
                GlyphRegister.FOCUS.get(),
                GlyphRegister.FOCUS.get(),
                GlyphRegister.CONTROL.get(),
                GlyphRegister.CONTROL.get()
        ), Ingredient.of(Items.ENDER_EYE), ItemRegister.HOMING_SPELL.get().getDefaultInstance(), 100, 2);

        inscriberRecipe(pRecipeOutput, "boomerang", List.of(
                GlyphRegister.GUIDANCE.get(),
                GlyphRegister.GUIDANCE.get(),
                GlyphRegister.WEAVE.get(),
                GlyphRegister.WEAVE.get(),
                GlyphRegister.WEAVE.get(),
                GlyphRegister.WEAVE.get(),
                GlyphRegister.TINKER.get(),
                GlyphRegister.DESTINY.get()
        ), Ingredient.of(Items.SLIME_BALL), ItemRegister.BOOMERANG_SPELL.get().getDefaultInstance(), 100, 2);

        inscriberRecipe(pRecipeOutput, "guide", List.of(
                GlyphRegister.GUIDANCE.get(),
                GlyphRegister.GUIDANCE.get(),
                GlyphRegister.GUIDANCE.get(),
                GlyphRegister.GUIDANCE.get(),

                GlyphRegister.WEAVE.get(),
                GlyphRegister.WEAVE.get(),
                GlyphRegister.TINKER.get(),
                GlyphRegister.DESTINY.get()
        ), Ingredient.of(Items.LEAD), ItemRegister.GUIDE_SPELL.get().getDefaultInstance(), 100, 2);

    }

    public void modifiers(RecipeOutput pRecipeOutput) {
        inscriberRecipe(pRecipeOutput,
                "small_delay_cast",
                List.of(
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.DESTINY.get()
                ),
                Ingredient.of(Items.CLOCK),
                ItemRegister.SMALL_DELAY_CAST_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "medium_delay_cast",
                List.of(
                        GlyphRegister.BIND.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.DESTINY.get()
                ),
                Ingredient.of(ItemRegister.SMALL_DELAY_CAST_SPELL.get()),
                ItemRegister.MEDIUM_DELAY_CAST_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "big_delay_cast",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.DESTINY.get()
                ),
                Ingredient.of(ItemRegister.MEDIUM_DELAY_CAST_SPELL.get()),
                ItemRegister.BIG_DELAY_CAST_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "freezing_charge",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.PACKED_ICE),
                ItemRegister.FREEZING_CHARGE_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "igneous_charge",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.MAGMA_BLOCK),
                ItemRegister.IGNEOUS_CHARGE_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "poison_charge",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.POISONOUS_POTATO),
                ItemRegister.POISON_CHARGE_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "crumbling_charge",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.GUNPOWDER),
                ItemRegister.CRUMBLING_CHARGE_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "accelerate",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),

                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.SUGAR),
                ItemRegister.ACCELERATE_SPELL.get().getDefaultInstance(),
                100,
                3
        );
        inscriberRecipe(pRecipeOutput,
                "hexagon_cast",
                List.of(
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),

                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),

                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.HONEYCOMB),
                ItemRegister.HEXAGON_CAST_SPELL.get().getDefaultInstance(),
                100,
                3
        );

        inscriberRecipe(pRecipeOutput,
                "double_cast",
                List.of(
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),

                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.CHAIN),
                ItemRegister.DOUBLE_CAST_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "triple_cast",
                List.of(
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(ItemRegister.DOUBLE_CAST_SPELL.get()),
                ItemRegister.TRIPLE_CAST_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "double_split_cast",
                List.of(
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),

                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(ItemRegister.DOUBLE_CAST_SPELL.get()),
                ItemRegister.DOUBLE_SPLIT_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "triple_split_cast",
                List.of(
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),

                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(ItemRegister.DOUBLE_SPLIT_SPELL.get()),
                ItemRegister.TRIPLE_SPLIT_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "quad_split_cast",
                List.of(
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),

                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.MIND.get(),

                        GlyphRegister.TINKER.get(),
                        GlyphRegister.CONTROL.get()
                ),
                Ingredient.of(ItemRegister.TRIPLE_SPLIT_SPELL.get()),
                ItemRegister.QUAD_SPLIT_SPELL.get().getDefaultInstance(),
                100,
                3
        );

        inscriberRecipe(pRecipeOutput,
                "spread_cast",
                List.of(
                        GlyphRegister.BIND.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.SCULK_VEIN),
                ItemRegister.SPREAD_CAST_SPELL.get().getDefaultInstance(),
                100,
                1
        );

        inscriberRecipe(pRecipeOutput,
                "add_mana",
                List.of(
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),

                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),

                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),

                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get()

                ),
                Ingredient.of(Items.HEART_OF_THE_SEA),
                ItemRegister.ADD_MANA_SPELL.get().getDefaultInstance(),
                100,
                4
        );

        inscriberRecipe(pRecipeOutput,
                "damage_plus",
                List.of(
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),

                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),

                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),

                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get()

                ),
                Ingredient.of(Items.BLAZE_POWDER),
                ItemRegister.DAMAGE_PLUS_SPELL.get().getDefaultInstance(),
                100,
                4
        );

        inscriberRecipe(pRecipeOutput,
                "critical_plus",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),

                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),

                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),

                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(ItemRegister.DAMAGE_PLUS_SPELL.get()),
                ItemRegister.CRITICAL_PLUS_SPELL.get().getDefaultInstance(),
                100,
                4
        );

        inscriberRecipe(pRecipeOutput,
                "light_shot",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),

                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.CONTROL.get()
                ),
                Ingredient.of(Items.FEATHER),
                ItemRegister.LIGHT_SHOT_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "heavy_shot",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),

                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.CONTROL.get()
                ),
                Ingredient.of(Items.IRON_BLOCK),
                ItemRegister.HEAVY_SHOT_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "increase_lifetime",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),

                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.CONTROL.get()
                ),
                Ingredient.of(Items.CLOCK),
                ItemRegister.INCREASE_LIFETIME_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "decrease_lifetime",
                List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),

                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get()
                ),
                Ingredient.of(Items.CLOCK),
                ItemRegister.DECREASE_LIFETIME_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "increase_range",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),

                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.CONTROL.get()
                ),
                Ingredient.of(Items.PISTON),
                ItemRegister.INCREASE_RANGE_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "decrease_range",
                List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),

                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get()
                ),
                Ingredient.of(Items.PISTON),
                ItemRegister.DECREASE_RANGE_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "increase_spread",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),

                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.CONTROL.get()
                ),
                Ingredient.of(Items.SCULK_VEIN),
                ItemRegister.INCREASE_SPREAD_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "decrease_spread",
                List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),

                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get()
                ),
                Ingredient.of(Items.SCULK_VEIN),
                ItemRegister.DECREASE_SPREAD_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "heavy_spread",
                List.of(
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get(),
                        GlyphRegister.POWER.get()
                ),
                Ingredient.of(ItemRegister.INCREASE_SPREAD_SPELL.get()),
                ItemRegister.HEAVY_SPREAD_SPELL.get().getDefaultInstance(),
                100,
                1
        );

        inscriberRecipe(pRecipeOutput,
                "decrease_recharge_time",
                List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),

                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),

                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.MIND.get(),

                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get()
                ),
                Ingredient.of(Items.CLOCK),
                ItemRegister.DECREASE_RECHARGE_TIME_SPELL.get().getDefaultInstance(),
                100,
                4
        );

        inscriberRecipe(pRecipeOutput,
                "health_to_power",
                List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.DESTINY.get(),

                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.VITALITY.get(),

                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.ROTTEN_FLESH),
                ItemRegister.HEALTH_TO_POWER_SPELL.get().getDefaultInstance(),
                100,
                3
        );

        inscriberRecipe(pRecipeOutput,
                "friends_to_power",
                List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.DESTINY.get(),

                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.VITALITY.get(),
                        GlyphRegister.VITALITY.get(),

                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.BONE),
                ItemRegister.FRIENDS_TO_POWER_SPELL.get().getDefaultInstance(),
                100,
                3
        );

        inscriberRecipe(pRecipeOutput,
                "piercing",
                List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.DESTINY.get(),

                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),

                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.ARROW),
                ItemRegister.PIERCING_SPELL.get().getDefaultInstance(),
                100,
                3
        );

        inscriberRecipe(pRecipeOutput,
                "teleport_cast",
                List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.DESTINY.get(),

                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.MIND.get()
                ),
                Ingredient.of(Items.CHORUS_FRUIT),
                ItemRegister.TELEPORT_CAST_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "line_cast",
                List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.DESTINY.get(),

                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get(),
                        GlyphRegister.BIND.get()
                ),
                Ingredient.of(ItemRegister.TELEPORT_CAST_SPELL.get()),
                ItemRegister.LINE_CAST_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "long_distance_cast",
                List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.DESTINY.get(),

                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(ItemRegister.TELEPORT_CAST_SPELL.get()),
                ItemRegister.LONG_DISTANCE_CAST_SPELL.get().getDefaultInstance(),
                100,
                2
        );

        inscriberRecipe(pRecipeOutput,
                "faltering",
                List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.DESTINY.get(),
                        GlyphRegister.DESTINY.get(),

                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.KNOWLEDGE.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.MIND.get(),

                        GlyphRegister.POWER.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),
                        GlyphRegister.CONTROL.get(),

                        GlyphRegister.POWER.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get(),
                        GlyphRegister.GUIDANCE.get()
                ),
                Ingredient.of(Items.TOTEM_OF_UNDYING),
                ItemRegister.FALTERING_SPELL.get().getDefaultInstance(),
                100,
                4
        );

    }

    private void raycasts(RecipeOutput pRecipeOutput) {
        inscriberRecipe(pRecipeOutput,
                "igneous_gaze",
                List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),

                        GlyphRegister.MIND.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.FLINT_AND_STEEL),
                ItemRegister.IGNEOUS_GAZE_SPELL.get().getDefaultInstance(),
                100,
                2
        );
        inscriberRecipe(pRecipeOutput,
                "freezing_gaze",
                List.of(
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.WEAVE.get(),
                        GlyphRegister.FOCUS.get(),
                        GlyphRegister.FOCUS.get(),

                        GlyphRegister.MIND.get(),
                        GlyphRegister.MIND.get(),
                        GlyphRegister.TINKER.get(),
                        GlyphRegister.TINKER.get()
                ),
                Ingredient.of(Items.SNOWBALL),
                ItemRegister.FREEZING_GAZE_SPELL.get().getDefaultInstance(),
                100,
                2
        );
    }

    private void arcaneInscriberRecipes(RecipeOutput pRecipeOutput) {
        projectiles(pRecipeOutput);
        staticProjectiles(pRecipeOutput);
        modifiers(pRecipeOutput);
        movers(pRecipeOutput);
        raycasts(pRecipeOutput);



    }


    public static void inscriberRecipe(RecipeOutput out, String name, List<Glyph> glyphs, Ingredient onSlate, ItemStack output, int ticks, int tier) {
        ArcaneInscriberRecipeBuilder builder = new ArcaneInscriberRecipeBuilder()
                .setGlyphs(glyphs)
                .setOnSlate(onSlate)
                .setTier(tier)
                .setTicks(ticks)
                .setOutput(output);

        builder.save(out, location(name));
    }

    private static ResourceLocation location(String name) {
        return ResourceLocation.fromNamespaceAndPath(Wandrous.MODID, name);
    }
}
