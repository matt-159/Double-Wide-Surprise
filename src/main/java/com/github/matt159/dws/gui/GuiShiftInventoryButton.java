package com.github.matt159.dws.gui;

import com.github.matt159.dws.Tags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiShiftInventoryButton extends GuiButton {

    private static final ResourceLocation SPRITE_SHEET = new ResourceLocation(Tags.MODID, "textures/spritesheet.png");

    public static final int ID = 78846;

    public enum Facing {
        Left,
        Right;
    }

    private Facing facing;
    private ItemStack items[];

    public GuiShiftInventoryButton(int id, int xPos, int yPos, int width, int height, String displayString, Facing facing) {
        super(id, xPos, yPos, width, height, displayString);
        this.zLevel = 10;
        this.facing = facing;
    }

    @Override
    public void drawButton(Minecraft mc, int x, int y) {
        if (this.visible) {
            FontRenderer fontRenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(SPRITE_SHEET);

//            DWSUtil.drawTexturedModalRect(this.xPosition, this.yPosition, 82 + facing.ordinal() * 18, 0, 18, 12, this.zLevel);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 164 + facing.ordinal() * 36, 0, 36, 24);
        }
    }

    public Facing getFacing() {
        return this.facing;
    }

    public void setFacing(Facing facing) {
        this.facing = facing;
    }
}
