package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiIOPort;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiIOPort.class)
public abstract class GuiIOPortMixin {
    @ModifyConstant(method = "drawBG",
                    constant = {    @Constant(intValue = 66),
                                    @Constant(intValue = 94),   },
                    remap = false,
                    require = 2)
    private int modifyItemHintXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
