package com.github.matt159.dws.mixin.mixins.common.galacticraft;

import com.github.matt159.dws.interfaces.galacticraft.IGalacticWearable;
import micdoodle8.mods.galacticraft.core.items.ItemBasic;
import micdoodle8.mods.galacticraft.core.items.ItemOxygenGear;
import micdoodle8.mods.galacticraft.core.items.ItemOxygenMask;
import micdoodle8.mods.galacticraft.core.items.ItemOxygenTank;
import micdoodle8.mods.galacticraft.core.items.ItemParaChute;
import micdoodle8.mods.galacticraft.planets.asteroids.items.ItemThermalPadding;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { ItemOxygenGear.class,
                 ItemOxygenMask.class,
                 ItemOxygenTank.class,
                 ItemParaChute.class,
                 ItemThermalPadding.class,
                 ItemBasic.class })
public abstract class GalacticWearableMixin implements IGalacticWearable {
    //Pls work
}
