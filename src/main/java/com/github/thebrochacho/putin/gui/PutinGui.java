package com.github.thebrochacho.putin.gui;

import com.github.thebrochacho.putin.Tags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class PutinGui extends GuiInventory {

    private static final ResourceLocation PUTIN_TEXTURE = new ResourceLocation(Tags.MODID, "textures/putinv.png");

    public PutinGui(EntityPlayer p_i1094_1_) {
        super(p_i1094_1_);
        this.xSize = 338;
        this.ySize = 166;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(PUTIN_TEXTURE);
        int k = this.guiLeft;
        int l = this.guiTop;

        //PutInv.png is too big for drawTexturedModalRect()
        double uMax = (double)this.xSize / 512D;
        double vMax = (double)this.ySize / 512D;

        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(k, l + this.ySize, 0, 0, vMax);
        tessellator.addVertexWithUV(k + this.xSize, l + this.ySize, 0, uMax, vMax);
        tessellator.addVertexWithUV(k + this.xSize, l, 0, uMax, 0);
        tessellator.addVertexWithUV(k, l, 0,0, 0);
        tessellator.draw();

        func_147046_a(k + 51, l + 75, 30, (float)(k + 51) - this.xSize, (float)(l + 75 - 50) - this.ySize, this.mc.thePlayer);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {

    }
}
