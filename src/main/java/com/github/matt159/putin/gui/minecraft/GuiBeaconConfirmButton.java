package com.github.matt159.putin.gui.minecraft;

import net.minecraft.client.gui.inventory.GuiBeacon;

public class GuiBeaconConfirmButton extends GuiBeacon.ConfirmButton {

    public GuiBeaconConfirmButton(GuiBeacon guiBeacon, int id, int xPosition, int yPosition) {
        guiBeacon.super(id, xPosition, yPosition);
    }
}
