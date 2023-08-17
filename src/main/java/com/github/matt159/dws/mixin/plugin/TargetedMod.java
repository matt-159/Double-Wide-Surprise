package com.github.matt159.dws.mixin.plugin;

import com.falsepattern.lib.mixin.ITargetedMod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

import static com.falsepattern.lib.mixin.ITargetedMod.PredicateHelpers.contains;
import static com.falsepattern.lib.mixin.ITargetedMod.PredicateHelpers.startsWith;

/**
 * List of targeted mods used for mixing loading logic.
 */
@Getter
@RequiredArgsConstructor
public enum TargetedMod implements ITargetedMod {
    APPLIEDENERGISTICS2  ("AppliedEnergistics2"  , true  , startsWith("appliedenergistics2").or(contains("ae2"))),
    BARTWORKS            ("Bartworks"            , true  , startsWith("bartworks")),
    BAUBLES              ("Baubles"              , true  , startsWith("baubles-")),
    CHISEL               ("Chisel"               , true  , startsWith("chisel")),
    ENDERCORE            ("EnderCore"            , true  , startsWith("endercore")),
    FORESTRY             ("Forestry"             , true  , startsWith("forestry")),
    CODECHICKENLIB       ("CodeChickenLib"       , true  , startsWith("codechicken")),
    GALACTICRAFT         ("Galacticraft"         , false , startsWith("galacticraft")),
    GARDENCORE           ("GardenCore"           , true  , startsWith("gardenstuff")),
    GREGTECH             ("GregTech"             , true  , startsWith("gregtech").or(startsWith("gt5u"))),
    INVENTORYTWEAKS      ("InventoryTweaks"      , true  , startsWith("inventorytweaks")),
    IRONCHEST            ("IronChest"            , true  , startsWith("ironchest")),
    NOTENOUGHITEMS       ("NotEnoughItems"       , true  , startsWith("notenoughitems")),
    STORAGEDRAWERS       ("StorageDrawers"       , true  , startsWith("storagedrawers-1.7.10")),
    TECTECH              ("TecTech"              , true  , startsWith("tectech").or(startsWith("tec-tech"))),
    TINKERSCONSTRUCT     ("TConstruct"           , true  , startsWith("tconstruct").or(startsWith("tinkersconstruct"))),
    TRAVELLERSGEAR       ("TravellersGear"       , true  , startsWith("traveller")),
    ;

    private final String modName;
    private final boolean loadInDevelopment;
    private final Predicate<String> condition;
}
