package com.github.thebrochacho.dws.gui.minecraft;

import com.github.thebrochacho.dws.Tags;
import com.github.thebrochacho.dws.inventory.InventoryDWS;
import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;

public class DWSHotbar extends GuiIngameForge {

    private static final ResourceLocation WIDGITS = new ResourceLocation(Tags.MODID, "textures/minecraft/gui/widgets.png");

    public DWSHotbar() {
        super(Minecraft.getMinecraft());
    }

    public void renderDWSHotbar(float partialTicks, ScaledResolution resolution) {

        int width = resolution.getScaledWidth();
        int height = resolution.getScaledHeight();

        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.renderEngine.bindTexture(WIDGITS);

        InventoryPlayer inv = this.mc.thePlayer.inventory;
        DWSUtil.drawTexturedModalRect(width / 2 - 181, height - 22, 0, 0, 362, 22, this.zLevel);

        int index = Arrays.binarySearch(InventoryDWS.HOTBAR_SLOTS, this.mc.thePlayer.inventory.currentItem);

        DWSUtil.drawTexturedModalRect(width / 2 - 181 - 1 + index * 20, height - 22 - 1, 0, 22, 24, 22, this.zLevel);

        GL11.glDisable(3042);
        GL11.glEnable(32826);
        RenderHelper.enableGUIStandardItemLighting();

        for(int i = 0; i < 18; ++i) {
            int x = width / 2 - 180 + i * 20 + 2;
            int z = height - 16 - 3;
            this.renderInventorySlot(InventoryDWS.HOTBAR_SLOTS[i], x, z, partialTicks);
        }

        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826);
    }
}
