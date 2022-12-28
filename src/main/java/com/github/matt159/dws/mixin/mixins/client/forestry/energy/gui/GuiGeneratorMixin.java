package com.github.matt159.dws.mixin.mixins.client.forestry.energy.gui;

import com.github.matt159.dws.util.Constants;
import forestry.energy.gui.GuiGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiGenerator.class)
public abstract class GuiGeneratorMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 49),
                    require = 1)
    private int modifyTankWidgetXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 108),
                    require = 1)
    private int modifyProgressBarXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }
}
