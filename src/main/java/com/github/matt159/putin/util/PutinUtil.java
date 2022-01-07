package com.github.matt159.putin.util;

import net.minecraft.client.renderer.Tessellator;

public class PutinUtil {
    //Need to override because this function in Gui.java only allows for 256x256 textures at max
    public static void drawTexturedModalRect(int x, int y, int u, int v, int width, int height, float zLevel)
    {
        float f = 0.001953125F; // (1 / 512)
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((x + 0),    (y + height),   zLevel,(u + 0) * f,    (v + height) * f);
        tessellator.addVertexWithUV((x + width),(y + height),   zLevel,(u + width) * f,(v + height) * f);
        tessellator.addVertexWithUV((x + width),(y + 0),        zLevel,(u + width) * f,(v + 0) * f);
        tessellator.addVertexWithUV((x + 0),    (y + 0),        zLevel,(u + 0) * f,    (v + 0) * f);
        tessellator.draw();
    }
}
