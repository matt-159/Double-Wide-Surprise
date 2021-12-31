package com.github.matt159.putin.gui;

import com.github.matt159.putin.Config;
import com.github.matt159.putin.Tags;
import com.github.matt159.putin.gui.SlotOverlays.Hints;
import com.github.matt159.putin.inventory.ContainerPutin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class PutinGui extends GuiInventory {

    private static final ResourceLocation PUTIN_TEXTURE = new ResourceLocation(Tags.MODID, "textures/putinv.png");
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

        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, GUI_WIDTH, GUI_HEIGHT);

        if (Config.isBaublesLoaded) {
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.BAUBLES_SLOT_START + 0), Hints.AMULET);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.BAUBLES_SLOT_START + 1), Hints.RING);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.BAUBLES_SLOT_START + 2), Hints.RING);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.BAUBLES_SLOT_START + 3), Hints.BAUBLE_BELT);
        }

        if (Config.isTinkersLoaded) {
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.TINKERS_SLOT_START + 0), Hints.MASK);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.TINKERS_SLOT_START + 1), Hints.GLOVE);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.TINKERS_SLOT_START + 2), Hints.TINKERS_BELT);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.TINKERS_SLOT_START + 3), Hints.KNAPSACK);

            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.TINKERS_SLOT_START + 4), Hints.RED_CANISTER);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.TINKERS_SLOT_START + 5), Hints.YELLOW_CANISTER);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.TINKERS_SLOT_START + 6), Hints.GREEN_CANISTER);
        }

        if (Config.isTravellersGearLoaded) {
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.TG_SLOT_START + 0), Hints.CLOAK);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.TG_SLOT_START + 1), Hints.PAULDRON);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.TG_SLOT_START + 2), Hints.VAMBRACE);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.TG_SLOT_START + 3), Hints.TITLE);
        }

        if (Config.isGalacticraftLoaded) {
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.GC_SLOT_START + 0), Hints.THERMAL_HELMET);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.GC_SLOT_START + 1), Hints.THERMAL_CHEST);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.GC_SLOT_START + 2), Hints.THERMAL_PANTS);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.GC_SLOT_START + 3), Hints.THERMAL_BOOTS);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.GC_SLOT_START + 4), Hints.PARACHUTE);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.GC_SLOT_START + 5), Hints.OXYGEN_MASK);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.GC_SLOT_START + 6), Hints.OXYGEN_TANK);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.GC_SLOT_START + 7), Hints.FREQUENCY_MODULE);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.GC_SLOT_START + 8), Hints.OXYGEN_GEAR);
            this.drawSlotAndOverlay(this.inventorySlots.getSlot(ContainerPutin.GC_SLOT_START + 9), Hints.OXYGEN_TANK);
        }

        for (Pair<Integer, Integer> nullSlotXY : ContainerPutin.nullSlots) {
            this.drawTexturedModalRect(guiLeft + nullSlotXY.getLeft() - 1, guiTop + nullSlotXY.getRight() - 1, 96, 208, 18, 18);
        }

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
            this.fontRendererObj.drawString(Integer.toString(i), slot.xDisplayPosition, slot.yDisplayPosition, 4210752);
        }
    }

    private void drawSlotAndOverlay(Slot slot, Hints hint) {
        GL11.glColor3f(1, 1, 1);
        Minecraft.getMinecraft().getTextureManager().bindTexture(PUTIN_TEXTURE);
        GL11.glEnable(3042);

        int x = this.guiLeft + slot.xDisplayPosition - 1;
        int y = this.guiTop + slot.yDisplayPosition - 1;

        //draw empty slot
        this.drawTexturedModalRect(x, y, 96,176, 18,18);

        if(!slot.getHasStack())
        {
            this.drawTexturedModalRect(x + 1, y + 1, hint.getX(), hint.getY(), 16,16);
        }
    }

    //Need to override because this function in Gui.java only allows for 256x256 textures at max
    @Override
    public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height)
    {
        float f = 0.001953125F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((x + 0),    (y + height),   this.zLevel,(u + 0) * f,    (v + height) * f);
        tessellator.addVertexWithUV((x + width),(y + height),   this.zLevel,(u + width) * f,(v + height) * f);
        tessellator.addVertexWithUV((x + width),(y + 0),        this.zLevel,(u + width) * f,(v + 0) * f);
        tessellator.addVertexWithUV((x + 0),    (y + 0),        this.zLevel,(u + 0) * f,    (v + 0) * f);
        tessellator.draw();
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
