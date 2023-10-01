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
    AGRICRAFT            ("Agricraft"            , true  , startsWith("agricraft-1.7.10")),
    APPLIEDENERGISTICS2  ("AppliedEnergistics2"  , false , startsWith("appliedenergistics2").or(contains("ae2"))),
    BARTWORKS            ("Bartworks"            , false , startsWith("bartworks")),
    BAUBLES              ("Baubles"              , true  , startsWith("baubles-").and(contains("expanded").negate())),
    BAUBLESEXPANDED      ("Baubles Expanded"     , true  , startsWith("baublesexpanded")),
    CHISEL               ("Chisel"               , true  , startsWith("chisel")),
    ENDERCORE            ("EnderCore"            , true  , startsWith("endercore")),
    FORESTRY             ("Forestry"             , true  , startsWith("forestry")),
    CODECHICKENLIB       ("CodeChickenLib"       , true  , startsWith("codechicken")),
    GALACTICRAFT         ("Galacticraft"         , false , startsWith("galacticraft")),
    GARDENCORE           ("GardenCore"           , true  , startsWith("gardenstuff")),
    GREGTECH             ("GregTech"             , false , startsWith("gregtech").or(startsWith("gt5u"))),
    INDUSTRIALCRAFT2     ("IndustrialCraft 2"    , false , startsWith("industrialcraft-2")),
    INVENTORYTWEAKS      ("InventoryTweaks"      , true  , startsWith("inventorytweaks")),
    IRONCHEST            ("IronChest"            , true  , startsWith("ironchest")),
    MARICULTURE          ("Mariculture"          , true  , startsWith("mariculture")),
    NATURA               ("Natura"               , true  , startsWith("natura-")),
    NOTENOUGHITEMS       ("NotEnoughItems"       , true  , startsWith("notenoughitems")),
    STORAGEDRAWERS       ("StorageDrawers"       , true  , startsWith("storagedrawers-1.7.10")),
    RAILCRAFT            ("Railcraft"            , false , startsWith("railcraft-")),
    TECTECH              ("TecTech"              , false , startsWith("tectech").or(startsWith("tec-tech"))),
    TINKERSCONSTRUCT     ("TConstruct"           , false , startsWith("tconstruct").or(startsWith("tinkersconstruct"))),
    TRAVELLERSGEAR       ("TravellersGear"       , false , startsWith("traveller")),
    ;

    private final String modName;
    private final boolean loadInDevelopment;
    private final Predicate<String> condition;
}
