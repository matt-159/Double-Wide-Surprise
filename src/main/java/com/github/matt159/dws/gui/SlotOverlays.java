package com.github.matt159.dws.gui;

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
        GREEN_CANISTER(48, 224),
        THERMAL_HELMET(16, 240),
        THERMAL_CHEST(32, 240),
        THERMAL_PANTS(48, 240),
        THERMAL_BOOTS(64, 240),
        FREQUENCY_MODULE(16, 256),
        PARACHUTE(32, 256),
        OXYGEN_MASK(48, 256),
        OXYGEN_GEAR(64, 256),
        OXYGEN_TANK(80, 256);

        private int x, y;

        public int getX() { return x; }

        public int getY() { return y; }

        Hints(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
