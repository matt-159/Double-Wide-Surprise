package com.github.matt159.dws.mixin.mixins.client.thaumcraft;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.client.gui.GuiFocusPouch;

@Mixin(GuiFocusPouch.class)
public abstract class GuiFocusPouchMixin implements IDWSGui {
    @Redirect(method = "<init>",
              at = @At(value = "FIELD",
                       target = "Lthaumcraft/client/gui/GuiFocusPouch;xSize:I"),
              require = 1)
    private void redirectSetXSize(GuiFocusPouch instance, int value) {
        instance.xSize = Constants.GENERAL_DWS_GUI_WIDTH;
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 240),
                    require = 1)
    private int modifyXTextureUVXOffset(int constant) {
        return 496;
    }
}
