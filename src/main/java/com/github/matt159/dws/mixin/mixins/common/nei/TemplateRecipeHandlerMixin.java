package com.github.matt159.dws.mixin.mixins.common.nei;

import codechicken.nei.recipe.TemplateRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(TemplateRecipeHandler.class)
public abstract class TemplateRecipeHandlerMixin {
    @ModifyConstant(method = "drawBackground",
                    constant = @Constant(intValue = 5),
                    remap = false,
                    require = 1)
    private int modifyBackgroundXOffset(int constant) {
        return constant + 81;
    }
}
