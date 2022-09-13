package com.github.thebrochacho.dws.util;

import baubles.common.Baubles;
import tconstruct.TConstruct;

import static cpw.mods.fml.common.Loader.isModLoaded;

public final class ModCompat {
    private ModCompat() {
        throw new IllegalArgumentException();
    }

    public static boolean isBaublesPresent() {
        return areModsPresent(Baubles.MODID);
    }

    public static boolean isTinkersConstructPresent() {
        return areModsPresent(TConstruct.modID);
    }

    public static boolean areModsPresent(String... mods) {
        for (String mod : mods)
            if (!isModLoaded(mod))
                return false;
        return true;
    }
}
