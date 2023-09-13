package com.github.matt159.dws.util;

import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

@UtilityClass
public final class SlotLayoutManager {
    public static int FIRST_GALACTICRAFT_SLOT = Integer.MAX_VALUE;
    public static int FIRST_BAUBLES_SLOT = Integer.MAX_VALUE;
    public static int FIRST_TINKERS_SLOT = Integer.MAX_VALUE;
    public static int FIRST_TRAVELLERS_GEAR_SLOT = Integer.MAX_VALUE;
    public static int FIRST_ACCESSORY_SLOT = Integer.MAX_VALUE;


    public static int getFirstAccessorySlot() {
        if (FIRST_ACCESSORY_SLOT == Integer.MAX_VALUE) {
            val list = Arrays.asList(FIRST_BAUBLES_SLOT,
                                     FIRST_GALACTICRAFT_SLOT,
                                     FIRST_TINKERS_SLOT,
                                     FIRST_TRAVELLERS_GEAR_SLOT);

            FIRST_ACCESSORY_SLOT = Collections.min(list);
        }

        return FIRST_ACCESSORY_SLOT;
    }

    @RequiredArgsConstructor
    public enum Mods {
        Baubles(() -> 1),
        BaublesExpanded(() -> 1 + ReflectedModSupport.BaublesExpandedSlots_slotsCurrentlyUsed() / 4),
        TinkersConstruct(() -> 2),
        TravellersGear(() -> 1),
        Galacticraft(() -> 3),
        ;

        private final Supplier<Integer> numColumns;

        public int getNumColumns() {
            return this.numColumns.get();
        }
    }

    // Location of right border the player render window
    private static final int BASE_XOFFSET = 80;
    private static final int SLOT_WIDTH = 18;

    public static int getXOffset(Mods mod) {
        int xOffset = BASE_XOFFSET;

        for (Mods loadedMod : getLoadedMods()) {
            if (mod == loadedMod)
                break;

            xOffset += SLOT_WIDTH * loadedMod.getNumColumns();
        }

        return xOffset;
    }

    private static Mods[] getLoadedMods() {
        List<Mods> loadedMods = new LinkedList<>();

        if (ModCompat.isBaublesPresent()) {
            if (ModCompat.isBaublesExpandedPresent()) {
                loadedMods.add(Mods.BaublesExpanded);
            } else {
                loadedMods.add(Mods.Baubles);
            }
        }

        if (ModCompat.isTinkersConstructPresent()) {
            loadedMods.add(Mods.TinkersConstruct);
        }

        if (ModCompat.isTravellersGearPresent()) {
            loadedMods.add(Mods.TravellersGear);
        }

        if (ModCompat.isGalacticraftPresent()) {
            loadedMods.add(Mods.Galacticraft);
        }

        return loadedMods.toArray(new Mods[0]);
    }
}
