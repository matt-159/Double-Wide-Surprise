package com.github.matt159.dws.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
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

    public static void shiftMainInventory(EntityPlayer player) {
        InventoryPlayer inventory = player.inventory;

        int size = inventory.mainInventory.length;
        List<ItemStack> items = new ArrayList<>(size);

        for (int i = 1; i <= 4; ++i) {
            //Using a modulus here because the hotbar slots occur at the end of the inventorySlot list
            int offset = (i * 18) % 72;

            Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, offset + 9, offset + 18));
            Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, offset, offset + 9));
        }

        ItemStack[] itemStacks = items.toArray(new ItemStack[0]);

        for (int i = 0; i < size; ++i) {
            //i + 9 gets past the armor and crafting slots
            player.inventoryContainer.putStackInSlot(i + 9, itemStacks[i]);
        }
    }
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
                DWSUtil.addSlotToContainer(container, new Slot(inventoryPlayer, col + (row + 1) * 18, 8 + col * 18, yOffset1 + row * 18));
            }
        }

        for (col = 0; col < 18; ++col) {
            DWSUtil.addSlotToContainer(container, new Slot(inventoryPlayer, col, 8 + col * 18 , yOffset2));
        }
    }

    @SuppressWarnings("unchecked")
    public static Slot addSlotToContainer(Container container, Slot slot) {
        slot.slotNumber = container.inventorySlots.size();
        container.inventorySlots.add(slot);
        container.inventoryItemStacks.add(null);
        return slot;
    }
}
