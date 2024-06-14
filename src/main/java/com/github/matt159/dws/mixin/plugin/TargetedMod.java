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
    BAUBLES              ("Baubles"              , false , startsWith("baubles-").and(contains("expanded").negate())),
    BAUBLESEXPANDED      ("Baubles Expanded"     , false , startsWith("baublesexpanded")),
    CHISEL               ("Chisel"               , false , startsWith("chisel")),
    ENDERCORE            ("EnderCore"            , false , startsWith("endercore")),
    FORESTRY             ("Forestry"             , false , startsWith("forestry")),
    FORGEBACKPACKS       ("Backpack"             , false , startsWith("backpack")),
    CODECHICKENLIB       ("CodeChickenLib"       , false , startsWith("codechicken")),
    GALACTICRAFT         ("Galacticraft"         , false , startsWith("galacticraft")),
    GARDENCORE           ("GardenCore"           , false , startsWith("gardenstuff")),
    GREGTECH             ("GregTech"             , false , startsWith("gregtech").or(startsWith("gt5u"))),
    INDUSTRIALCRAFT2     ("IndustrialCraft 2"    , false , startsWith("industrialcraft-2")),
    INVENTORYTWEAKS      ("InventoryTweaks"      , false , startsWith("inventorytweaks")),
    IRONCHEST            ("IronChest"            , false , startsWith("ironchest")),
    MARICULTURE          ("Mariculture"          , false , startsWith("mariculture")),
    NATURA               ("Natura"               , false , startsWith("natura-")),
    NOTENOUGHITEMS       ("NotEnoughItems"       , false , startsWith("notenoughitems")),
    STORAGEDRAWERS       ("StorageDrawers"       , false , startsWith("storagedrawers-")),
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
