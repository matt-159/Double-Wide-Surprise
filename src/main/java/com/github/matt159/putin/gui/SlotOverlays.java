package com.github.matt159.putin.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Slot;
import org.lwjgl.opengl.GL11;
import travellersgear.client.gui.GuiButtonMoveableElement;
import travellersgear.client.handlers.CustomizeableGuiHandler;
import travellersgear.common.inventory.SlotNull;

public class SlotOverlays {
    public enum Hints {
        AMULET(16, 176),
        RING(32, 176),
        BAUBLE_BELT(48, 176),
        CLOAK(16, 192),
        PAULDRON(32, 192),
        VAMBRACE(48, 192),
        TITLE(64, 192),
        MASK(16, 208),
        TINKERS_BELT(32, 208),
        GLOVE(48, 208),
        KNAPSACK(64, 208),
        RED_CANISTER(16, 224),
        YELLOW_CANISTER(32, 224),
        GREEN_CANISTER(48, 224);

        private int x, y;

        public int getX() { return x; }

        public int getY() { return y; }

        Hints(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
