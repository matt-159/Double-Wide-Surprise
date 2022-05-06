package com.github.matt159.putin.gui;

import com.github.matt159.putin.Tags;
import com.github.matt159.putin.gui.SlotOverlays.Hints;
import com.github.matt159.putin.interfaces.IPutinGui;
import com.github.matt159.putin.util.PutinUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class PutinGui extends GuiInventory implements IPutinGui {

    public static final ResourceLocation PUTIN_TEXTURE = new ResourceLocation(Tags.MODID, "textures/putinv.png");
    private static final int GUI_WIDTH = 338;
    private static final int GUI_HEIGHT = 166;

    public PutinGui(EntityPlayer p_i1094_1_) {
        super(p_i1094_1_);
        this.xSize = GUI_WIDTH;
        this.ySize = GUI_HEIGHT;
    }

    @Override
    public void initGui() {
        super.initGui();
        this.guiLeft = (this.width-this.xSize)/2;
        this.guiTop = (this.height-this.ySize)/2;

        this.buttonList.clear();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int mouseX, int mouseZ)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(PUTIN_TEXTURE);

        PutinUtil.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, GUI_WIDTH, GUI_HEIGHT, this.zLevel);

        drawPlayerModel(    this.guiLeft + 51,
                            this.guiTop + 75,
                            30,
                            (float)(this.guiLeft + 51) - mouseX,
                            (float)(this.guiTop + 75 - 50) - mouseZ,
                            this.mc.thePlayer);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        for (int i = 0; i < this.inventorySlots.inventorySlots.size(); ++i) {
            Slot slot = (Slot)this.inventorySlots.inventorySlots.get(i);
//            String value = Integer.toString(i);
            String value = Integer.toString(slot.getSlotIndex());
            this.fontRendererObj.drawString(value, slot.xDisplayPosition, slot.yDisplayPosition, 4210752);
        }
    }

    private void drawSlotAndOverlay(Slot slot, Hints hint) {
        GL11.glColor3f(1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(PUTIN_TEXTURE);
        GL11.glEnable(3042);

        int x = this.guiLeft + slot.xDisplayPosition - 1;
        int y = this.guiTop + slot.yDisplayPosition - 1;

        //draw empty slot
        PutinUtil.drawTexturedModalRect(x, y, 96,176, 18,18, this.zLevel);

        if(!slot.getHasStack())
        {
            PutinUtil.drawTexturedModalRect(x + 1, y + 1, hint.getX(), hint.getY(), 16,16, this.zLevel);
        }
    }

    public static void drawPlayerModel(int x, int y, int scale, float mouseX, float mouseZ, EntityLivingBase playerdrawn)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, 50.0F);
        GL11.glScalef((float) (-scale), (float) scale, (float) scale);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = playerdrawn.renderYawOffset;
        float f3 = playerdrawn.rotationYaw;
        float f4 = playerdrawn.rotationPitch;
        mouseZ -= 19;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float) Math.atan((double) (mouseZ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
        playerdrawn.renderYawOffset = (float) Math.atan((double) (mouseX / 40.0F)) * 20.0F;
        playerdrawn.rotationYaw = (float) Math.atan((double) (mouseX / 40.0F)) * 40.0F;
        playerdrawn.rotationPitch = -((float) Math.atan((double) (mouseZ / 40.0F))) * 20.0F;
        playerdrawn.rotationYawHead = playerdrawn.rotationYaw;
        GL11.glTranslatef(0.0F, playerdrawn.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.renderEntityWithPosYaw(playerdrawn, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        playerdrawn.renderYawOffset = f2;
        playerdrawn.rotationYaw = f3;
        playerdrawn.rotationPitch = f4;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}
