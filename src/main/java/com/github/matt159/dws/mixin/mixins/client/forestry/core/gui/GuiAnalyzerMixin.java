package com.github.matt159.dws.mixin.mixins.client.forestry.core.gui;

import com.github.matt159.dws.util.Constants;
import forestry.core.gui.GuiAnalyzer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiAnalyzer.class)
public abstract class GuiAnalyzerMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 95),
                    require = 1)
    private int modifyTankXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 64),
                    require = 1)
    private int modifyProgressBarXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
