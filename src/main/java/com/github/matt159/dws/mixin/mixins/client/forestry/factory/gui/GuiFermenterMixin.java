package com.github.matt159.dws.mixin.mixins.client.forestry.factory.gui;

import com.github.matt159.dws.util.Constants;
import forestry.factory.gui.GuiFermenter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiFermenter.class)
public abstract class GuiFermenterMixin {
    @ModifyConstant(method = { "<init>",
                               "drawGuiContainerBackgroundLayer" },
                    constant = { @Constant(intValue = 35),
                                 @Constant(intValue = 74),
                                 @Constant(intValue = 98),
                                 @Constant(intValue = 125) },
                    require = 4)
    private int modifyXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
            constant = @Constant(intValue = 176),
            require = 2)
    private int modifyTextureUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }
}
