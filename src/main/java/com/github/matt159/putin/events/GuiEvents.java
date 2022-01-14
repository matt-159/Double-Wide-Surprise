package com.github.matt159.putin.events;

import com.github.matt159.putin.gui.GuiShiftInventoryButton;
import com.github.matt159.putin.gui.GuiShiftInventoryButton.Facing;
import com.github.matt159.putin.gui.PutinGui;
import com.github.matt159.putin.inventory.InventoryPutin;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;

public class GuiEvents {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.gui instanceof GuiContainer && !(event.gui instanceof PutinGui)) {
            GuiContainer gc = (GuiContainer) event.gui;

            int firstPlayerSlotIndex = getFirstPlayerSlotIndex(gc);

            Slot topLeftSlot = gc.inventorySlots.getSlot(firstPlayerSlotIndex);
            Slot topRightSlot = gc.inventorySlots.getSlot(firstPlayerSlotIndex + 8);

            int guiLeft = (gc.width - gc.xSize) / 2;
            int guiTop = (gc.height - gc.ySize) / 2;

            event.buttonList.add(new GuiShiftInventoryButton(GuiShiftInventoryButton.ID,
                    guiLeft + topRightSlot.xDisplayPosition, guiTop + topRightSlot.yDisplayPosition - 14, 18, 12,
                    "Shift Inventory Right", GuiShiftInventoryButton.Facing.Right,
                    ((InventoryPutin) topLeftSlot.inventory).mainInventory));
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiPostAction(GuiScreenEvent.ActionPerformedEvent.Post event) {
        if (event.gui instanceof GuiContainer) {
            switch (event.button.id) {
                case GuiShiftInventoryButton.ID:
                    GuiContainer gc = (GuiContainer) event.gui;
                    GuiShiftInventoryButton button = (GuiShiftInventoryButton) event.button;

                    int index = getFirstPlayerSlotIndex(gc);
                    InventoryPutin inventory = (InventoryPutin) gc.inventorySlots.getSlot(index).inventory;

                    ItemStack items[] = button.getItemsForFacing(button.getFacing());
                    for (int i = 0; i < 36; ++i) {
                        int slotIndex = index + i;
                        ItemStack itemstack = items[i];
                        gc.inventorySlots.putStackInSlot(slotIndex, itemstack);
                    }

                    button.setFacing(button.getFacing() == Facing.Left ? Facing.Right : Facing.Left);
                    break;
                default:
                    break;
            }
        }
    }

    private int getFirstPlayerSlotIndex(GuiContainer gc) {
        for (int i = 0; i < gc.inventorySlots.inventorySlots.size(); ++i) {
            if (gc.inventorySlots.getSlot(i).inventory instanceof InventoryPutin) {
                return i;
            }
        }
        return -1;
    }
}
