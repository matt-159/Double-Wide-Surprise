package com.github.thebrochacho.dws.mixin.mixins.client.minecraft.gui;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(targets = "net.minecraft.client.gui.GuiMerchant.MerchantButton")
public abstract class MerchantButtonMixin {
    @ModifyConstant(method = "drawButton",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifySize(int constant) {
        return 338;
    }
}
