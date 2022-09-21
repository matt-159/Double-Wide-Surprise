package com.github.thebrochacho.dws.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
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
}
