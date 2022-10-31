package com.github.thebrochacho.dws.mixin.mixins.common.tectech;

import com.github.technus.tectech.nei.TT_NEI_ResearchHandler;
import com.github.technus.tectech.nei.TT_NEI_ScannerHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(value = {
    TT_NEI_ScannerHandler.GT_RectHandler.class,
    TT_NEI_ResearchHandler.GT_RectHandler.class
})
public abstract class GT_RectHandlerMixin {
    @ModifyConstant(method = {
                        "handleTooltip",
                        "transferRect"
                    },
                    constant = @Constant(intValue = 65),
                    remap = false,
                    require = 1)
    private int modifyBasicMachineRecipeRectangleXOffset(int constant) {
        return constant + 81;
    }

    @ModifyArgs(method = {
                    "handleTooltip",
                    "transferRect"
                },
                at = @At(   value = "INVOKE",
                        target = "Ljava/awt/Rectangle;<init>(IIII)V",
                        ordinal = 1),
                remap = false,
                require = 1)
    private void modifyFusionReactorRecipeRectangle(Args args) {
        args.setAll(239, -7, 17, 17);
    }
}