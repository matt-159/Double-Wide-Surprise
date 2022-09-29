package com.github.thebrochacho.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiLevelEmitter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GuiLevelEmitter.class)
public abstract class GuiLevelEmitterMixin {
    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 24),
                    remap = false,
                    require = 1)
    private int modifyTextboxXOffset(int constant) {
        return constant + 81;
    }

    @ModifyArg(method = "addButtons",
               at = @At(value = "INVOKE",
                        target = "Lnet/minecraft/client/gui/GuiButton;<init>(IIIIILjava/lang/String;)V"),
               index = 1,
               remap = false,
               require = 8)
    private int modifyButtonXOffset(int x) {
        return x + 81;
    }
}
