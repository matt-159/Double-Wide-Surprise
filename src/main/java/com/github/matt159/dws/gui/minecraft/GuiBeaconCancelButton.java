package com.github.matt159.dws.gui.minecraft;

import net.minecraft.client.gui.inventory.GuiBeacon;

public class GuiBeaconCancelButton extends GuiBeacon.CancelButton {

    public GuiBeaconCancelButton(GuiBeacon guiBeacon, int id, int xPosition, int yPosition) {
        guiBeacon.super(id, xPosition, yPosition);
    }
}