package com.github.matt159.dws.mixin.mixins.client.minecraft.gui;

import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "net.minecraft.client.gui.GuiMerchant.MerchantButton")
public abstract class MerchantButtonMixin {
    @ModifyConstant(method = "drawButton",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifySize(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }
}
