package com.github.matt159.dws.mixin.mixins.client.forestry.factory.gui;

import com.github.matt159.dws.util.Constants;
import forestry.factory.gui.GuiCarpenter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiCarpenter.class)
public abstract class GuiCarpenterMixin {
    @ModifyConstant(method = { "<init>",
                               "drawGuiContainerBackgroundLayer" },
                    constant = { @Constant(intValue = 98),
                                 @Constant(intValue = 150) },
                    require = 2)
    private int modifyXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }
}
