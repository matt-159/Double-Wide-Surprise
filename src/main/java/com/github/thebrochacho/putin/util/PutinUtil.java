package com.github.thebrochacho.putin.util;

import com.github.thebrochacho.putin.inventory.ContainerPutin;
import com.github.thebrochacho.putin.inventory.InventoryPutin;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public static void shiftMainInventory(EntityPlayer player) {
        if (player.inventoryContainer instanceof ContainerPutin) {
            InventoryPutin inventory = (InventoryPutin) player.inventory;

            int size = inventory.mainInventory.length;
            List<ItemStack> items = new ArrayList<>(size);
            //Right Half
            Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 36, 63));

            //Left Half
            Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 9, 36));

            //Right Hotbar
            Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 63, 72));

            //Left Hotbar
            Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 0, 9));

            ItemStack[] itemStacks = items.toArray(new ItemStack[0]);

            for (int i = 0; i < size; ++i) {
                //i + 9 gets past the armor and crafting slots
                player.inventoryContainer.putStackInSlot(i + 9, itemStacks[i]);
            }
        }
    }

    public static int getFirstPlayerSlotIndex(Container container) {
        for (int i = 0; i < container.inventorySlots.size(); ++i) {
            if (container.getSlot(i).inventory instanceof InventoryPutin) {
                return i;
            }
        }
        return -1;
    }

    public static void addPutinSlotsToContainer(Container container, InventoryPlayer inventoryPlayer) {
        int row, col;
        //main inventory
        for (row = 0; row < 3; ++row) {
            for (col = 0; col < 18; ++col) {
                PutinUtil.addSlotToContainer(container, new Slot(inventoryPlayer, col + row * 18 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        //left half of hotbar
        for (row = 0; row < 9; ++row) {
            PutinUtil.addSlotToContainer(container, new Slot(inventoryPlayer, row, 8 + row * 18, 142));
        }

        //right half of hotbar
        for (row = 0; row < 9; ++row) {
            PutinUtil.addSlotToContainer(container, new Slot(inventoryPlayer, row + 63, 8 + (row + 9) * 18, 142));
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
