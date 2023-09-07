package com.github.matt159.dws.inventory.slots;

import lombok.Getter;

@Getter
public enum SlotType {
    /* Tinkers Construct */
    TINKERS_MASK(0, 0),
    TINKERS_GLOVE(0, 1),
    TINKERS_BELT(0, 2),
    TINKERS_KNAPSACK(0, 3),
    TINKERS_HEART_RED(1, 0),
    TINKERS_HEART_YELLOW(1, 1),
    TINKERS_HEART_GREEN(1, 2),

    /* Baubles */
    BAUBLE_AMULET(2, 0),
    BAUBLE_RING(2, 1),
    BAUBLE_BELT(2, 2),

    /* Traveller's Gear */
    TRAVEL_CLOAK(3, 0),
    TRAVEL_PAULDRON(3, 1),
    TRAVEL_VAMBRACE(3, 2),
    TRAVEL_TITLE(3, 3),

    /* Galacticraft */
    GC_THERMAL_HELM(6, 0),
    GC_THERMAL_CHEST(6, 1),
    GC_THERMAL_LEGS(6, 2),
    GC_THERMAL_BOOTS(6, 3),
    GC_FREQUENCY_MODULE(4, 0),
    GC_OXYGEN_MASK(5, 0),
    GC_OXYGEN_GEAR(5, 1),
    GC_OXYGEN_TANK(4, 1),
    GC_PARACHUTE(5, 2),
    ;

    /**
     * X and Y location in the sprite grid within dws:textures/minecraft/gui/container/inventory.png
     */
    private final int x, y;

    SlotType(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
