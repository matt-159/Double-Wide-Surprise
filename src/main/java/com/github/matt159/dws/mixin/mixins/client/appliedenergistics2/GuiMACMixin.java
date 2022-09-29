package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiMAC;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiMAC.class)
public abstract class GuiMACMixin {
    @ModifyConstant(method = { "initGui",
                               "drawBG" },
                    constant = { @Constant(intValue = 139),
                                 @Constant(intValue = 148) },
                    remap = false,
                    require = 3)
    private int modifyXOffsets(int constant) {
        return constant + 81;
    }
}
