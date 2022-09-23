package com.github.matt159.dws.util;

import java.util.LinkedList;
import java.util.List;

public final class SlotLayoutManager {
    public enum Mods {
        Baubles(1),
        TinkersConstruct(2),
        TravellersGear(1),
        Galacticraft(3),
        ;

        private final int numColumns;
        Mods(int numColums) {
            this.numColumns = numColums;
        }

        public int getNumColumns() {
            return this.numColumns;
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
            loadedMods.add(Mods.Baubles);
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
