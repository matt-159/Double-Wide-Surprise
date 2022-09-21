package com.github.matt159.dws.mixin.mixins.common.nei;

import codechicken.nei.recipe.BrewingRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BrewingRecipeHandler.class)
public abstract class BrewingRecipeHandlerMixin {
    @ModifyConstant(method = "loadTransferRects",
                    constant = {
                        @Constant(intValue = 58),
                        @Constant(intValue = 68),
                        @Constant(intValue = 92)
                    },
                    remap = false,
                    require = 1)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }
}
