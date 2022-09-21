package com.github.thebrochacho.dws.util;

import baubles.common.Baubles;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import tconstruct.TConstruct;
import travellersgear.TravellersGear;

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

    public static boolean isTravellersGearPresent() {
        return areModsPresent(TravellersGear.MODID);
    }

    public static boolean isGalacticraftPresent() {
        return areModsPresent(Constants.MOD_ID_CORE);
    }

    public static boolean areModsPresent(String... mods) {
        for (String mod : mods)
            if (!isModLoaded(mod))
                return false;
        return true;
    }
}
