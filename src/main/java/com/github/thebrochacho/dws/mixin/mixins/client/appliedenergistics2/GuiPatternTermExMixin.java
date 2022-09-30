package com.github.thebrochacho.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiPatternTermEx;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiPatternTermEx.class)
public abstract class GuiPatternTermExMixin {
    @ModifyConstant(method = "initGui",
                    constant = { @Constant(intValue = 87),
                                 @Constant(intValue = 97),
                                 @Constant(intValue = 147) },
                    remap = false,
                    require = 1)
    private int modifyGuiButtonXOffset(int constant) {
        return constant + 81;
    }
}
