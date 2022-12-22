package com.github.matt159.dws.mixin.mixins.client.forestry.energy.gui;

import com.github.matt159.dws.util.Constants;
import forestry.energy.gui.GuiEngineBiogas;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiEngineBiogas.class)
public abstract class GuiEngineBiogasMixin {
    @ModifyConstant(method = { "<init>",
                               "drawGuiContainerBackgroundLayer" },
                    constant = { @Constant(intValue = 30),
                                 @Constant(intValue = 53),
                                 @Constant(intValue = 89),
                                 @Constant(intValue = 107) },
                    require = 1)
    private int modifyXOffsets(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }
}
