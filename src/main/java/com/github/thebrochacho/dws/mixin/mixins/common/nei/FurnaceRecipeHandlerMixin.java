package com.github.thebrochacho.dws.mixin.mixins.common.nei;

import codechicken.nei.recipe.FurnaceRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FurnaceRecipeHandler.class)
public class FurnaceRecipeHandlerMixin {
    @ModifyConstant(method = "loadTransferRects",
                    constant = {
                        @Constant(intValue = 50),
                        @Constant(intValue = 74)
                    },
                    remap = false,
                    require = 1)
    private int modifyTransferRectXOffset(int constant) {
        return constant + 81;
    }
}
