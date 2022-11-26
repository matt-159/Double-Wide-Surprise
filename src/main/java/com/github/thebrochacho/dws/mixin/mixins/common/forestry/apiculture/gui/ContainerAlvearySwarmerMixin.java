package com.github.thebrochacho.dws.mixin.mixins.common.forestry.apiculture.gui;

import forestry.apiculture.gui.ContainerAlvearySwarmer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerAlvearySwarmer.class)
public abstract class ContainerAlvearySwarmerMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 58),
                                 @Constant(intValue = 79),
                                 @Constant(intValue = 100) },
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
