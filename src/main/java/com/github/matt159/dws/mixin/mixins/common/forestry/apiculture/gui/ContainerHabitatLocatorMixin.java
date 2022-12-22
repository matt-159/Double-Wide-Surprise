package com.github.matt159.dws.mixin.mixins.common.forestry.apiculture.gui;

import com.github.matt159.dws.util.Constants;
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
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
