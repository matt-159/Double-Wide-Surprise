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
    AGRICRAFT            ("Agricraft"            , false , startsWith("agricraft-1.7.10")),
    APPLIEDENERGISTICS2  ("AppliedEnergistics2"  , false , startsWith("appliedenergistics2").or(contains("ae2"))),
    BARTWORKS            ("Bartworks"            , false , startsWith("bartworks")),
    BAUBLES              ("Baubles"              , true  , startsWith("baubles-").and(contains("expanded").negate())),
    BAUBLESEXPANDED      ("Baubles Expanded"     , true  , startsWith("baublesexpanded")),
    CHISEL               ("Chisel"               , true  , startsWith("chisel")),
    ENDERCORE            ("EnderCore"            , true  , startsWith("endercore")),
    FORESTRY             ("Forestry"             , false , startsWith("forestry")),
    CODECHICKENLIB       ("CodeChickenLib"       , true  , startsWith("codechicken")),
    GALACTICRAFT         ("Galacticraft"         , false , startsWith("galacticraft")),
    GARDENCORE           ("GardenCore"           , false , startsWith("gardenstuff")),
    GREGTECH             ("GregTech"             , false , startsWith("gregtech").or(startsWith("gt5u"))),
    INDUSTRIALCRAFT2     ("IndustrialCraft 2"    , false , startsWith("industrialcraft-2")),
    INVENTORYTWEAKS      ("InventoryTweaks"      , true  , startsWith("inventorytweaks")),
    IRONCHEST            ("IronChest"            , true  , startsWith("ironchest")),
    MARICULTURE          ("Mariculture"          , false , startsWith("mariculture")),
    NATURA               ("Natura"               , false , startsWith("natura-")),
    NOTENOUGHITEMS       ("NotEnoughItems"       , true  , startsWith("notenoughitems")),
    STORAGEDRAWERS       ("StorageDrawers"       , false , startsWith("storagedrawers-1.7.10")),
    RAILCRAFT            ("Railcraft"            , false , startsWith("railcraft-")),
    TECTECH              ("TecTech"              , false , startsWith("tectech").or(startsWith("tec-tech"))),
    THAUMCRAFT           ("Thaumcraft"           , false , startsWith("thaumcraft-")),
    TINKERSCONSTRUCT     ("TConstruct"           , false , startsWith("tconstruct").or(startsWith("tinkersconstruct"))),
    TRAVELLERSGEAR       ("TravellersGear"       , false , startsWith("traveller")),
    ;

    private final String modName;
    private final boolean loadInDevelopment;
    private final Predicate<String> condition;
}
