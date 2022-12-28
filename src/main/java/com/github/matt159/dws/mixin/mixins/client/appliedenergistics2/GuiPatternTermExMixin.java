package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiPatternTermEx;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiPatternTermEx.class)
public abstract class GuiPatternTermExMixin {
    @ModifyConstant(method = "initGui",
                    constant = { @Constant(intValue = 87),
                                 @Constant(intValue = 97),
                                 @Constant(intValue = 147) },
                    require = 1)
    private int modifyGuiButtonXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
