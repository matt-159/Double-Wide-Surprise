package com.github.matt159.putin.gui;

import com.github.matt159.putin.util.PutinUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GuiShiftInventoryButton extends GuiButton {

    public static final int ID = 78846;

    public enum Facing {
        Left,
        Right;
    }

    private Facing facing;
    private ItemStack items[];

    public GuiShiftInventoryButton(int id, int xPos, int yPos, int width, int height, String displayString, Facing facing, ItemStack items[]) {
        super(id, xPos, yPos, width, height, displayString);
        this.facing = facing;
        this.items = Arrays.copyOf(items, items.length);
    }

    @Override
    public void drawButton(Minecraft mc, int x, int y) {
        if (this.visible) {
            FontRenderer fontRenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(PutinGui.PUTIN_TEXTURE);

            PutinUtil.drawTexturedModalRect(this.xPosition, this.yPosition, 128, 176 + facing.ordinal() * 16, 18, 12, 10);
        }
    }

    public Facing getFacing() {
        return this.facing;
    }

    public void setFacing(Facing facing) {
        this.facing = facing;
    }

    public ItemStack[] getItemsForFacing(Facing facing) {
        if (facing == Facing.Right) {
            return Arrays.copyOfRange(items, 36, 72);
        } else {
            List<ItemStack> result = new ArrayList<>(36);
            Collections.addAll(result, Arrays.copyOfRange(items, 9, 36));
            Collections.addAll(result, Arrays.copyOfRange(items, 0, 9));
            return result.toArray(new ItemStack[0]);
        }
    }
}
