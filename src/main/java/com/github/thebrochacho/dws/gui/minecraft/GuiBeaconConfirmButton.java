package com.github.thebrochacho.dws.gui.minecraft;

import net.minecraft.client.gui.inventory.GuiBeacon;

public class GuiBeaconConfirmButton extends GuiBeacon.ConfirmButton {

    public GuiBeaconConfirmButton(GuiBeacon guiBeacon, int id, int xPosition, int yPosition) {
        guiBeacon.super(id, xPosition, yPosition);
    }
}
