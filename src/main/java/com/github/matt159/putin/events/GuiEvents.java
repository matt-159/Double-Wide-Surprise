package com.github.matt159.putin.events;

import com.github.matt159.putin.gui.GuiShiftInventoryButton;
import com.github.matt159.putin.gui.GuiShiftInventoryButton.Facing;
import com.github.matt159.putin.gui.PutinGui;
import com.github.matt159.putin.inventory.InventoryPutin;
import com.github.matt159.putin.network.PacketHandler;
import com.github.matt159.putin.network.PutinInventorySwapPacket;
import com.github.matt159.putin.util.PutinUtil;
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
        if (event.gui instanceof GuiContainer && !(event.gui instanceof PutinGui)) {
            GuiContainer gc = (GuiContainer) event.gui;

            int firstPutinSlotIndex = PutinUtil.getFirstPlayerSlotIndex(gc.inventorySlots);

            Slot topLeftSlot = gc.inventorySlots.getSlot(firstPutinSlotIndex);
            Slot topRightSlot = gc.inventorySlots.getSlot(firstPutinSlotIndex + 8);

            int guiLeft = (gc.width - gc.xSize) / 2;
            int guiTop = (gc.height - gc.ySize) / 2;

            event.buttonList.add(new GuiShiftInventoryButton(GuiShiftInventoryButton.ID,
                    guiLeft + topRightSlot.xDisplayPosition, guiTop + topRightSlot.yDisplayPosition - 14, 18, 12,
                    "Shift Inventory Right", GuiShiftInventoryButton.Facing.Right));
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void guiPostAction(GuiScreenEvent.ActionPerformedEvent.Post event) {
        if (event.gui instanceof GuiContainer) {
            if (event.button.id == GuiShiftInventoryButton.ID) {
                GuiShiftInventoryButton button = (GuiShiftInventoryButton) event.button;

                button.setFacing(button.getFacing() == Facing.Left ? Facing.Right : Facing.Left);

                PacketHandler.INSTANCE.sendToServer(new PutinInventorySwapPacket(event.gui.mc.thePlayer));
            }
        }
    }
}
