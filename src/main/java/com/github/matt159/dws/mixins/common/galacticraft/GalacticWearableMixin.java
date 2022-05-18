package com.github.matt159.dws.mixins.common.galacticraft;

import com.github.matt159.dws.interfaces.galacticraft.IGalacticWearable;
import micdoodle8.mods.galacticraft.core.items.*;
import micdoodle8.mods.galacticraft.planets.asteroids.items.ItemThermalPadding;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = {ItemOxygenGear.class,
                ItemOxygenMask.class,
                ItemOxygenTank.class,
                ItemParaChute.class,
                ItemThermalPadding.class,
                ItemBasic.class})
@Implements(@Interface(iface = IGalacticWearable.class, prefix = "pu$"))
public abstract class GalacticWearableMixin {
    //Pls work
}
