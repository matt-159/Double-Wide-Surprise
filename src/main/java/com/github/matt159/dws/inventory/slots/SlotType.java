package com.github.matt159.dws.inventory.slots;

import com.github.matt159.dws.Tags;
import lombok.Getter;
import net.minecraft.util.ResourceLocation;

@Getter
public enum SlotType {
    /* Tinkers Construct */
    TINKERS_MASK("mask"),
    TINKERS_GLOVE("glove"),
    TINKERS_BELT("tinkersBelt"),
    TINKERS_KNAPSACK("knapsack"),
    TINKERS_HEART_RED("redHeartCanister", 10),
    TINKERS_HEART_YELLOW("yellowHeartCanister", 10),
    TINKERS_HEART_GREEN("greenHeartCanister", 10),

    /* Baubles */
    BAUBLE_AMULET("amulet"),
    BAUBLE_RING("ring"),
    BAUBLE_BELT("baublesBelt"),

    /* Traveller's Gear */
    TRAVEL_CLOAK("cloak"),
    TRAVEL_PAULDRON("pauldron"),
    TRAVEL_VAMBRACE("vambrace"),
    TRAVEL_TITLE("titleScroll"),

    /* Galacticraft */
    GC_THERMAL_HELM("thermalHelmet"),
    GC_THERMAL_CHEST("thermalChest"),
    GC_THERMAL_LEGS("thermalPants"),
    GC_THERMAL_BOOTS("thermalBoots"),
    GC_FREQUENCY_MODULE("frequencyModule"),
    GC_OXYGEN_MASK("oxygenMask"),
    GC_OXYGEN_GEAR("oxygenGear"),
    GC_OXYGEN_TANK("oxygenTank"),
    GC_PARACHUTE("parachute"),
    ;

    private final ResourceLocation slotHintTexture;
    private final int stackSize;

    SlotType(String hintTexture) {
        this(hintTexture, 1);
    }

    SlotType(String hintTexture, int stackSize) {
        this.slotHintTexture = new ResourceLocation(Tags.MOD_ID, String.format("textures/gui/slotIcons/%s.png", hintTexture));
        this.stackSize = stackSize;
    }
}
