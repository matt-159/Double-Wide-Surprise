package com.github.matt159.dws.mixin.mixins.client.gregtech;

import com.github.matt159.dws.util.Constants;
import gregtech.common.gui.GT_GUIContainer_PrimitiveBlastFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_GUIContainer_PrimitiveBlastFurnace.class)
public abstract class GT_GUIContainer_PrimitiveBlastFurnaceMixin {
    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 58),
                    require = 1)
    private int modifyTextureXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }
}
