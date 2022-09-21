package com.github.matt159.dws.mixin.mixins.common.nei;

import codechicken.nei.recipe.ShapedRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ShapedRecipeHandler.class)
public abstract class ShapedRecipeHandlerMixin {
    @ModifyConstant(method = "loadTransferRects",
                    constant = @Constant(intValue = 84),
                    remap = false,
                    require = 1)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }
}
