package com.github.matt159.dws.mixin.mixins.common.forestry.apiculture.gui;

import com.github.matt159.dws.util.Constants;
import forestry.apiculture.gui.ContainerBeeHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerBeeHelper.class)
public abstract class ContainerBeeHelperMixin {
    @ModifyConstant(method = "addSlots",
                    constant = { @Constant(intValue = 29),
                                 @Constant(intValue = 66),
                                 @Constant(intValue = 95),
                                 @Constant(intValue = 116),
                                 @Constant(intValue = 137) },
                    remap = false,
                    require = 1)
    private static int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
