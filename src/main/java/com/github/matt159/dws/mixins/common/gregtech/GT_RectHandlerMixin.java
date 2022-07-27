package com.github.matt159.dws.mixins.common.gregtech;

import gregtech.nei.GT_NEI_DefaultHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(GT_NEI_DefaultHandler.GT_RectHandler.class)
public abstract class GT_RectHandlerMixin {
    @ModifyArgs(method = {
                    "handleTooltip",
                    "transferRect"
                },
                at = @At(   value = "INVOKE",
                            target = "Ljava/awt/Rectangle;<init>(IIII)V",
                            ordinal = 0),
                remap = false,
                require = 1)
    private void modifyBasicMachineRecipeRectangle(Args args) {
        args.setAll(146, 13, 36, 18);
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

    @ModifyArgs(method = {
                    "handleTooltip",
                    "transferRect"
                },
                at = @At(   value = "INVOKE",
                        target = "Ljava/awt/Rectangle;<init>(IIII)V",
                        ordinal = 2),
                remap = false,
                require = 1)
    private void modifyBBBRecipeRectangle(Args args) {
        args.setAll(127, 13, 34, 18);
    }
}
