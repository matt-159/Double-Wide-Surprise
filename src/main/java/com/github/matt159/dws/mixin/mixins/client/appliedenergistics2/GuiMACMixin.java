package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiMAC;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiMAC.class)
public abstract class GuiMACMixin {
    @ModifyConstant(method = "initGui",
                    constant = { @Constant(intValue = 139),
                                 @Constant(intValue = 148) },
                    require = 2)
    private int modifyProgressBarXOffsets(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawBG",
            constant = @Constant(intValue = 148),
            remap = false,
            require = 1)
    private int modifyXOffsets(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
