package com.github.matt159.dws.util;

import baubles.common.Baubles;
import cpw.mods.fml.common.ModContainer;
import micdoodle8.mods.galacticraft.core.Constants;
import tconstruct.TConstruct;
import travellersgear.TravellersGear;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static cpw.mods.fml.common.Loader.isModLoaded;

public final class ModCompat {
    enum CompatibleMods {
        VANILLA("minecraft"),
        AE2("appliedenergistics2"),
        AE2_CORE("appliedenergistics2-core"),
        CHISEL("chisel"),
        FORESTRY("Forestry"),
        GREGTECH("gregtech"),
        IRONCHEST("IronChest"),
        NEI("NotEnoughItems"),
        STORAGEDRAWERS("StorageDrawers"),
        GARDENCORE("GardenCore"),
        ;

        private final String modID;

        CompatibleMods(String modID) {
            this.modID = modID;
        }

        String modID() {
            return this.modID;
        }
    }

    private static List<String> modIDs = null;

    public static List<String> getModIDs() {
        if (modIDs == null) {
            modIDs = Arrays.stream(CompatibleMods.values())
                           .map(CompatibleMods::modID)
                           .collect(Collectors.toList());
        }
        return modIDs;
    }

    public static boolean hasDWSCompat(ModContainer mc) {
        return getModIDs().contains(mc.getModId());
    }

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
