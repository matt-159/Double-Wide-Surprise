package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiInscriber;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiInscriber.class)
public abstract class GuiInscriberMixin {
    @ModifyConstant(method = { "<init>",
                               "drawBG" },
                    constant = { @Constant(intValue = 177),
                                 @Constant(intValue = 178),
                                 @Constant(intValue = 211),
                                 @Constant(intValue = 246) },
                    remap = false,
                    require = 7)
    private int modifyGuiXSize(int constant) {
        return constant + 162;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 135),
                    require = 2)
    private int modifyProgressBarXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawBG",
            constant = @Constant(intValue = 135),
            remap = false,
            require = 1)
    private int modifyProgressBarUVXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
