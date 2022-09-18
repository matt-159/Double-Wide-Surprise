package com.github.matt159.dws.mixin.mixins.common.gregtech;

import codechicken.lib.gui.GuiDraw;
import com.github.matt159.dws.util.DWSUtil;
import gregtech.nei.GT_NEI_DefaultHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GT_NEI_DefaultHandler.class)
public class GT_NEI_DefaultHandlerMixin {
    @Redirect(  method = "drawBackground",
                at = @At(   value = "INVOKE",
                            target = "Lcodechicken/lib/gui/GuiDraw;drawTexturedModalRect(IIIIII)V"),
                remap = false,
                require = 1)
    private void redirectDrawCall(int x, int y, int tx, int ty, int w, int h) {
        int xOffset = 3;
        GuiDraw.drawTexturedModalRect(x + xOffset, y, tx + 81 + xOffset, ty, w - 2 * xOffset, h);
    }


}
