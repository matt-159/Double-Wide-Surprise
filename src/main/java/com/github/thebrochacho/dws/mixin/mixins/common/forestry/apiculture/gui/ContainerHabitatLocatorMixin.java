package com.github.thebrochacho.dws.mixin.mixins.common.forestry.apiculture.gui;

import forestry.apiculture.gui.ContainerHabitatLocator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerHabitatLocator.class)
public abstract class ContainerHabitatLocatorMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 152),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
