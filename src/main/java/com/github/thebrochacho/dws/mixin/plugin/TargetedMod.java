package com.github.thebrochacho.dws.mixin.plugin;

import com.falsepattern.lib.mixin.ITargetedMod;
import lombok.*;

import java.util.function.Predicate;

import static com.falsepattern.lib.mixin.ITargetedMod.PredicateHelpers.startsWith;

/**
 * List of targeted mods used for mixing loading logic.
 */
@Getter
@RequiredArgsConstructor
public enum TargetedMod implements ITargetedMod {
    GALACTICRAFT("Galacticraft", false, startsWith("galacticraft")),
    TRAVELLERSGEAR("TravellersGear", false, startsWith("traveller")),
    IRONCHEST("IronChest", false, startsWith("ironchest")),
    BAUBLES("Baubles", false, startsWith("baubles")),
    TINKERSCONSTRUCT("TConstruct", false, startsWith("construct")),
    CODECHICKENLIB("CodeChickenLib", false, startsWith("codechicken")),
    NOTENOUGHITEMS("NotEnoughItems", false, startsWith("notenoughitems")),
    GREGTECH("GregTech", false, startsWith("gregtech").or(startsWith("gt5u"))),
    BARTWORKS("Bartworks", false, startsWith("bartworks")),
    TECTECH("TecTech", false, startsWith("tectech").or(startsWith("tec-tech")));

    private final String modName;
    private final boolean loadInDevelopment;
    private final Predicate<String> condition;
}
