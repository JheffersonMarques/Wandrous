package com.hakimen.wandrous.client.tooltip;

import com.ibm.icu.impl.Pair;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class    SpellTooltipRenderer implements ClientTooltipComponent {

    private final SpellTooltipComponent comp;

    public SpellTooltipRenderer(SpellTooltipComponent comp) {
        this.comp = comp;
    }

    @Override
    public int getHeight() {
        return 22;
    }

    @Override
    public int getWidth(Font font) {
        return comp.spells.size() * 18 + (comp.overflow ? 8 + font.width(". . ." ) : 0);
    }


    @Override
    public void renderImage(Font pFont, int pX, int pY, GuiGraphics gfx) {
        for (ItemStack spellItem : this.comp.spells()) {
            if (!spellItem.isEmpty()) {
                PoseStack pose = gfx.pose();
                pose.pushPose();
                pose.scale(1f, 1f, 1);
                gfx.renderFakeItem(spellItem, pX + 18 * this.comp.spells().indexOf(spellItem) + 1, pY + 3);
                pose.popPose();
            }
        }
    }

    @Override
    public void renderText(Font pFont, int pMouseX, int pMouseY, Matrix4f pMatrix, MultiBufferSource.BufferSource pBufferSource) {
        if(comp.overflow()) {
            pFont.drawInBatch(Component.literal(". . .").getString(), 4 + pMouseX + 18 * this.comp.spells().size() + 1, pMouseY + 10, 0xAABBCC, true, pMatrix,pBufferSource, Font.DisplayMode.NORMAL,0, 0xf000f0);
        }
    }

    public record SpellTooltipComponent(List<ItemStack> spells, boolean overflow) implements TooltipComponent {}


}
