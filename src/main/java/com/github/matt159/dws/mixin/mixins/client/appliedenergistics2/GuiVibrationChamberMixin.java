package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiVibrationChamber;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiVibrationChamber.class)
public abstract class GuiVibrationChamberMixin {
    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyProgressBarUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }

    @ModifyConstant(method = "drawFG",
                    constant = @Constant(intValue = 176),
                    remap = false,
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 99),
                    require = 1)
    private int modifyProgressBarXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = { "drawFG",
                               "drawBG" },
                    constant = { @Constant(intValue = 81),
                                 @Constant(intValue = 99) },
                    remap = false,
                    require = 2)
    private int modifyFuelBurnTimeXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
