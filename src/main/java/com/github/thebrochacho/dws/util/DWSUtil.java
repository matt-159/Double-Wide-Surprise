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

    public enum Reorganization {
        Do, Undo
    }

    //TODO figure out why there is a client desync issue
    public static void ReorganizeInventoryForFallbackSupport(EntityPlayer player, Reorganization operation) {
        if (player.worldObj.isRemote) {
            return;
        }

        InventoryPlayer inventory = new InventoryPlayer(player);
        inventory.copyInventory(player.inventory);

        int size = inventory.mainInventory.length;
        List<ItemStack> items = new ArrayList<>(size);

        /*  This grid represents the player item slots. This is actually a 1-D array, but is presented here as 2-D.
         *  The number inside each cell represents the corresponding index in the Itemstack array that the slots map onto.
         *  Indices 0-18 appear last because hotbar slots are added last.
         *  ┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐
         *  │ 18 │ 19 │ 20 │ 21 │ 22 │ 23 │ 24 │ 25 │ 26 │ 27 │ 28 │ 29 │ 30 │ 31 │ 32 │ 33 │ 34 │ 35 │
         *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
         *  │ 36 │ 37 │ 38 │ 39 │ 40 │ 41 │ 42 │ 43 │ 44 │ 45 │ 46 │ 47 │ 48 │ 49 │ 50 │ 51 │ 52 │ 53 │
         *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
         *  │ 54 │ 55 │ 56 │ 57 │ 58 │ 59 │ 60 │ 61 │ 62 │ 63 │ 64 │ 65 │ 66 │ 67 │ 68 │ 69 │ 70 │ 71 │
         *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
         *  │ 0  │ 1  │ 2  │ 3  │ 4  │ 5  │ 6  │ 7  │ 8  │ 9  │ 10 │ 11 │ 12 │ 13 │ 14 │ 15 │ 16 │ 17 │
         *  └────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘
         *
         *  When opening an inventory not supported by this mod, items are selected from the player inventory in the
         *  following manner:
         *  ┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐
         *  │ R3 │ R3 │ R3 │ R3 │ R3 │ R3 │ R3 │ R3 │ R3 │ R4 │ R4 │ R4 │ R4 │ R4 │ R4 │ R4 │ R4 │ R4 │
         *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
         *  │    │    │    │    │    │    │    │    │    │    │    │    │    │    │    │    │    │    │
         *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
         *  │    │    │    │    │    │    │    │    │    │    │    │    │    │    │    │    │    │    │
         *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
         *  │ R1 │ R1 │ R1 │ R1 │ R1 │ R1 │ R1 │ R1 │ R1 │ R2 │ R2 │ R2 │ R2 │ R2 │ R2 │ R2 │ R2 │ R2 │
         *  └────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘
         *  Where Rx indicates which row that item gets placed into.
         */

        switch (operation) {
            case Do:
                /*
                *  ┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐
                *  │ 18 │ 19 │ 20 │ 21 │ 22 │ 23 │ 24 │ 25 │ 26 │ 27 │ 28 │ 29 │ 30 │ 31 │ 32 │ 33 │ 34 │ 35 │
                *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
                *  │ 36 │ 37 │ 38 │ 39 │ 40 │ 41 │ 42 │ 43 │ 44 │ 45 │ 46 │ 47 │ 48 │ 49 │ 50 │ 51 │ 52 │ 53 │
                *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
                *  │ 54 │ 55 │ 56 │ 57 │ 58 │ 59 │ 60 │ 61 │ 62 │ 63 │ 64 │ 65 │ 66 │ 67 │ 68 │ 69 │ 70 │ 71 │
                *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
                *  │  0 │  1 │  2 │  3 │  4 │  5 │  6 │  7 │  8 │  9 │ 10 │ 11 │ 12 │ 13 │ 14 │ 15 │ 16 │ 17 │
                *  └────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘
                *                                               ↓
                *  ┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐
                *  │ 36 │ 37 │ 38 │ 39 │ 40 │ 41 │ 42 │ 43 │ 44 │ 54 │ 55 │ 56 │ 57 │ 58 │ 59 │ 60 │ 61 │ 62 │
                *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
                *  │  9 │ 10 │ 11 │ 12 │ 13 │ 14 │ 15 │ 16 │ 17 │ 27 │ 28 │ 29 │ 30 │ 31 │ 32 │ 33 │ 34 │ 35 │
                *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
                *  │ 45 │ 46 │ 47 │ 48 │ 49 │ 50 │ 51 │ 52 │ 53 │ 63 │ 64 │ 65 │ 66 │ 67 │ 68 │ 69 │ 70 │ 71 │
                *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
                *  │  0 │  1 │  2 │  3 │  4 │  5 │  6 │  7 │  8 │ 18 │ 19 │ 20 │ 21 │ 22 │ 23 │ 24 │ 25 │ 26 │
                *  └────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘
                */

                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 36, 45));
                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 54, 63));

                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 9, 18));
                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 27, 36));
                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 45, 54));
                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 63, 72));

                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 0, 9));
                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 18, 27));

                break;
            case Undo:
                /*
                 *  ┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐
                 *  │ 36 │ 37 │ 38 │ 39 │ 40 │ 41 │ 42 │ 43 │ 44 │ 54 │ 55 │ 56 │ 57 │ 58 │ 59 │ 60 │ 61 │ 62 │
                 *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
                 *  │  9 │ 10 │ 11 │ 12 │ 13 │ 14 │ 15 │ 16 │ 17 │ 27 │ 28 │ 29 │ 30 │ 31 │ 32 │ 33 │ 34 │ 35 │
                 *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
                 *  │ 45 │ 46 │ 47 │ 48 │ 49 │ 50 │ 51 │ 52 │ 53 │ 63 │ 64 │ 65 │ 66 │ 67 │ 68 │ 69 │ 70 │ 71 │
                 *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
                 *  │  0 │  1 │  2 │  3 │  4 │  5 │  6 │  7 │  8 │ 18 │ 19 │ 20 │ 21 │ 22 │ 23 │ 24 │ 25 │ 26 │
                 *  └────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘
                 *                                               ↓
                 *  ┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐
                 *  │ 18 │ 19 │ 20 │ 21 │ 22 │ 23 │ 24 │ 25 │ 26 │ 27 │ 28 │ 29 │ 30 │ 31 │ 32 │ 33 │ 34 │ 35 │
                 *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
                 *  │ 36 │ 37 │ 38 │ 39 │ 40 │ 41 │ 42 │ 43 │ 44 │ 45 │ 46 │ 47 │ 48 │ 49 │ 50 │ 51 │ 52 │ 53 │
                 *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
                 *  │ 54 │ 55 │ 56 │ 57 │ 58 │ 59 │ 60 │ 61 │ 62 │ 63 │ 64 │ 65 │ 66 │ 67 │ 68 │ 69 │ 70 │ 71 │
                 *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
                 *  │  0 │  1 │  2 │  3 │  4 │  5 │  6 │  7 │  8 │  9 │ 10 │ 11 │ 12 │ 13 │ 14 │ 15 │ 16 │ 17 │
                 *  └────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘
                 */

                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 9, 18));
                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 45, 54));

                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 18, 27));
                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 54, 63));

                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 27, 36));
                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 63, 72));

                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 0, 9));
                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 36, 45));

                break;
        }

        ItemStack[] itemStacks = items.toArray(new ItemStack[0]);

        for (int i = 0; i < size; ++i) {
            //i + 9 gets past the armor and crafting slots
            player.inventoryContainer.putStackInSlot(i + 9, itemStacks[i]);
        }
        player.inventoryContainer.detectAndSendChanges();
    }

    public static void shiftMainInventory(EntityPlayer player, boolean needsFallbackSupport) {
        InventoryPlayer inventory = new InventoryPlayer(player);
        inventory.copyInventory(player.inventory);

        int size = inventory.mainInventory.length;
        List<ItemStack> items = new ArrayList<>(size);

        if (needsFallbackSupport) {
            /*
             *  ┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐
             *  │ 36 │ 37 │ 38 │ 39 │ 40 │ 41 │ 42 │ 43 │ 44 │ 54 │ 55 │ 56 │ 57 │ 58 │ 59 │ 60 │ 61 │ 62 │
             *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
             *  │  9 │ 10 │ 11 │ 12 │ 13 │ 14 │ 15 │ 16 │ 17 │ 27 │ 28 │ 29 │ 30 │ 31 │ 32 │ 33 │ 34 │ 35 │
             *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
             *  │ 45 │ 46 │ 47 │ 48 │ 49 │ 50 │ 51 │ 52 │ 53 │ 63 │ 64 │ 65 │ 66 │ 67 │ 68 │ 69 │ 70 │ 71 │
             *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
             *  │  0 │  1 │  2 │  3 │  4 │  5 │  6 │  7 │  8 │ 18 │ 19 │ 20 │ 21 │ 22 │ 23 │ 24 │ 25 │ 26 │
             *  └────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘
             *                                               ↓
             *  ┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐
             *  │ 45 │ 46 │ 47 │ 48 │ 49 │ 50 │ 51 │ 52 │ 53 │ 63 │ 64 │ 65 │ 66 │ 67 │ 68 │ 69 │ 70 │ 71 │
             *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
             *  │  0 │  1 │  2 │  3 │  4 │  5 │  6 │  7 │  8 │ 18 │ 19 │ 20 │ 21 │ 22 │ 23 │ 24 │ 25 │ 26 │
             *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
             *  │ 36 │ 37 │ 38 │ 39 │ 40 │ 41 │ 42 │ 43 │ 44 │ 54 │ 55 │ 56 │ 57 │ 58 │ 59 │ 60 │ 61 │ 62 │
             *  ├────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┼────┤
             *  │  9 │ 10 │ 11 │ 12 │ 13 │ 14 │ 15 │ 16 │ 17 │ 27 │ 28 │ 29 │ 30 │ 31 │ 32 │ 33 │ 34 │ 35 │
             *  └────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘
             */

            Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 54, 72));

            Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 0, 36));

            Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, 36, 54));
        } else {
            for (int i = 1; i <= 4; ++i) {
                //Using a modulus here because the hotbar slots occur at the end of the inventorySlot list
                int offset = (i * 18) % 72;

                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, offset + 9, offset + 18));
                Collections.addAll(items, Arrays.copyOfRange(inventory.mainInventory, offset, offset + 9));
            }
        }

        ItemStack[] itemStacks = items.toArray(new ItemStack[0]);

        for (int i = 0; i < size; ++i) {
            //i + 9 gets past the armor and crafting slots
            player.inventoryContainer.putStackInSlot(i + 9, itemStacks[i]);
        }
        player.inventoryContainer.detectAndSendChanges();
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
