package com.github.thebrochacho.dws.mixin.plugin;

import com.falsepattern.lib.mixin.ITargetedMod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

import static com.falsepattern.lib.mixin.ITargetedMod.PredicateHelpers.startsWith;

/**
 * List of targeted mods used for mixing loading logic.
 */
@Getter
@RequiredArgsConstructor
public enum TargetedMod implements ITargetedMod {
    GALACTICRAFT      ("Galacticraft"    , false, startsWith("galacticraft")),
    TRAVELLERSGEAR    ("TravellersGear"  , true, startsWith("traveller")),
    IRONCHEST         ("IronChest"       , true, startsWith("ironchest")),
    BAUBLES           ("Baubles"         , true, startsWith("baubles")),
    TINKERSCONSTRUCT  ("TConstruct"      , true, startsWith("tconstruct")),
    CODECHICKENLIB    ("CodeChickenLib"  , true, startsWith("codechicken")),
    NOTENOUGHITEMS    ("NotEnoughItems"  , true, startsWith("notenoughitems")),
    GREGTECH          ("GregTech"        , true, startsWith("gregtech").or(startsWith("gt5u"))),
    BARTWORKS         ("Bartworks"       , true, startsWith("bartworks")),
    TECTECH           ("TecTech"         , true, startsWith("tectech").or(startsWith("tec-tech")));


    private final String modName;
    private final boolean loadInDevelopment;
    private final Predicate<String> condition;
}
