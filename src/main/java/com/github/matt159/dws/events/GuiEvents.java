package com.github.matt159.dws.events;

import com.github.matt159.dws.gui.GuiShiftInventoryButton;
import com.github.matt159.dws.gui.GuiShiftInventoryButton.Facing;
import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.network.DWSInventorySwapPacket;
import com.github.matt159.dws.network.PacketHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.client.event.GuiScreenEvent;

public class GuiEvents {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void guiPostInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.gui instanceof GuiContainer && !(event.gui instanceof IDWSGui)) {
            GuiContainer gc = (GuiContainer) event.gui;

            int firstDWSSlotIndex = -1; //DWSUtil.getFirstPlayerSlotIndex(gc.inventorySlots);

            if (firstDWSSlotIndex != -1) {
                Slot topLeftSlot = gc.inventorySlots.getSlot(firstDWSSlotIndex);
                Slot topRightSlot = gc.inventorySlots.getSlot(firstDWSSlotIndex + 4);

                int guiLeft = (gc.width - gc.xSize) / 2;
                int guiTop = (gc.height - gc.ySize) / 2;

                event.buttonList.add(new GuiShiftInventoryButton(GuiShiftInventoryButton.ID,
                        guiLeft + topRightSlot.xDisplayPosition, guiTop + topRightSlot.yDisplayPosition - 7, 18, 12,
                        "Shift Inventory Right", GuiShiftInventoryButton.Facing.Right));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiPostAction(GuiScreenEvent.ActionPerformedEvent.Post event) {
        if (event.gui instanceof GuiContainer) {
            if (event.button.id == GuiShiftInventoryButton.ID) {
                GuiShiftInventoryButton button = (GuiShiftInventoryButton) event.button;

                button.setFacing(button.getFacing() == Facing.Left ? Facing.Right : Facing.Left);

                PacketHandler.INSTANCE.sendToServer(new DWSInventorySwapPacket(true));
            }
        }
    }
}
