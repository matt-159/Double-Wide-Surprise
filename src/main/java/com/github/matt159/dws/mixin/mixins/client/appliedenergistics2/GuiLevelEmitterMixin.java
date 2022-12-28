package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiLevelEmitter;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiLevelEmitter.class)
public abstract class GuiLevelEmitterMixin {
    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 24),
                    require = 1)
    private int modifyTextboxXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyArg(method = "addButtons",
               at = @At(value = "INVOKE",
                        target = "Lnet/minecraft/client/gui/GuiButton;<init>(IIIIILjava/lang/String;)V"),
               index = 1,
               remap = false,
               require = 8)
    private int modifyButtonXOffset(int x) {
        return x + Constants.GENERAL_X_OFFSET;
    }
}
