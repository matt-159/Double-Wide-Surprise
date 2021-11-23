package com.github.thebrochacho.putin.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.client.event.GuiOpenEvent;

public class PlayerGuiEvent {

    @SubscribeEvent
    public void onPlayerOpenGUI(GuiOpenEvent event) {
        if (event.gui instanceof GuiInventory) {
            System.out.println("send nudes");
        }
    }
}
