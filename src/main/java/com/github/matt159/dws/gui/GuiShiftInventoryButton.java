package com.github.matt159.dws.gui;

import com.github.matt159.dws.util.DWSUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;

public class GuiShiftInventoryButton extends GuiButton {

    public static final int ID = 78846;

    public enum Facing {
        Left,
        Right;
    }

    private Facing facing;
    private ItemStack items[];

    public GuiShiftInventoryButton(int id, int xPos, int yPos, int width, int height, String displayString, Facing facing) {
        super(id, xPos, yPos, width, height, displayString);
        this.facing = facing;
    }

    @Override
    public void drawButton(Minecraft mc, int x, int y) {
        if (this.visible) {
            FontRenderer fontRenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(DWSGui.DWS_TEXTURE);

            DWSUtil.drawTexturedModalRect(this.xPosition, this.yPosition, 128, 176 + facing.ordinal() * 16, 18, 12, 10);
        }
    }

    public Facing getFacing() {
        return this.facing;
    }

    public void setFacing(Facing facing) {
        this.facing = facing;
    }
}
