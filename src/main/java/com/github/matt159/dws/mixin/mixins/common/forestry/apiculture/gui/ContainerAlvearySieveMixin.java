package com.github.matt159.dws.mixin.mixins.common.forestry.apiculture.gui;

import com.github.matt159.dws.util.Constants;
import forestry.apiculture.gui.ContainerAlvearySieve;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerAlvearySieve.class)
public abstract class ContainerAlvearySieveMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 43),
                                 @Constant(intValue = 73),
                                 @Constant(intValue = 94),
                                 @Constant(intValue = 115) },
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
