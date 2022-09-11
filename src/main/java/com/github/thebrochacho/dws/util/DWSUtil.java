package com.github.thebrochacho.dws.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class DWSUtil {
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

    //allow for drawing of NxM textures
    public static void drawTexturedRect(@Nonnull ResourceLocation textureResourceLocation,
                                        int x,
                                        int y,
                                        int width,
                                        int height,
                                        float zLevel) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(textureResourceLocation);
        drawTexturedRect(x, y, width, height, zLevel);
    }

    public static void drawTexturedRect(int xPos,
                                        int yPos,
                                        int width,
                                        int height,
                                        float zLevel) {
        int xPosMax = xPos + width;
        int yPosMax = yPos + height;

        Tessellator.instance.startDrawingQuads();
        Tessellator.instance.addVertexWithUV(xPos, yPosMax, zLevel, 0F, 1F);
        Tessellator.instance.addVertexWithUV(xPosMax, yPosMax, zLevel, 0F, 1F);
        Tessellator.instance.addVertexWithUV(xPosMax, yPos, zLevel, 0F, 1F);
        Tessellator.instance.addVertexWithUV(xPos, yPos, zLevel, 0F, 1F);
        Tessellator.instance.draw();
    }

//    public static void shiftMainInventory(EntityPlayer player) {
//        if (player.inventoryContainer instanceof ContainerDWS) {
//            InventoryDWS inventory = (InventoryDWS) player.inventory;
//
//            int size = inventory.mainInventory.length;
//            List<ItemStack> items = new ArrayList<>(size);
//            //Right Half
//            Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 36, 63));
//
//            //Left Half
//            Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 9, 36));
//
//            //Right Hotbar
//            Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 63, 72));
//
//            //Left Hotbar
//            Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 0, 9));
//
//            ItemStack[] itemStacks = items.toArray(new ItemStack[0]);
//
//            for (int i = 0; i < size; ++i) {
//                //i + 9 gets past the armor and crafting slots
//                player.inventoryContainer.putStackInSlot(i + 9, itemStacks[i]);
//            }
//        }
//    }
//
//    public static int getFirstPlayerSlotIndex(Container container) {
//        for (int i = 0; i < container.inventorySlots.size(); ++i) {
//            if (container.getSlot(i).inventory instanceof InventoryDWS) {
//                return i;
//            }
//        }
//        return -1;
//    }

    public static void addDWSSlotsToContainer(Container container, IInventory inventoryPlayer) {
        addDWSSlotsToContainer(container, inventoryPlayer, 8, 84, 142);
    }

    public static void addDWSSlotsToContainer(Container container, IInventory inventoryPlayer, int xOffset, int yOffset1, int yOffset2) {

        int row, col;
        //main inventory
        for (row = 0; row < 3; ++row) {
            for (col = 0; col < 18; ++col) {
                DWSUtil.addSlotToContainer(container, new Slot(inventoryPlayer, col + row * 18 + 9, xOffset + col * 18, yOffset1 + row * 18));
            }
        }

        //left half of hotbar
        for (col = 0; col < 9; ++col) {
            DWSUtil.addSlotToContainer(container, new Slot(inventoryPlayer, col, xOffset + col * 18, yOffset2));
        }

        //right half of hotbar
        for (col = 0; col < 9; ++col) {
            DWSUtil.addSlotToContainer(container, new Slot(inventoryPlayer, col + 63, xOffset + (col+ 9) * 18 , yOffset2));
        }
    }

    @SuppressWarnings("unchecked")
    public static Slot addSlotToContainer(Container container, Slot slot) {
        slot.slotNumber = container.inventorySlots.size();
        container.inventorySlots.add(slot);
        container.inventoryItemStacks.add((Object)null);
        return slot;
    }
}
