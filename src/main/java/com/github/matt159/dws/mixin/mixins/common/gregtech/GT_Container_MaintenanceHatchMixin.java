package com.github.matt159.dws.mixin.mixins.common.gregtech;

import com.github.matt159.dws.util.Constants;
import gregtech.api.gui.GT_Container_MaintenanceHatch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_MaintenanceHatch.class)
public abstract class GT_Container_MaintenanceHatchMixin {
    @ModifyConstant(method = "addSlots",
                    constant = @Constant(intValue = 80),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
