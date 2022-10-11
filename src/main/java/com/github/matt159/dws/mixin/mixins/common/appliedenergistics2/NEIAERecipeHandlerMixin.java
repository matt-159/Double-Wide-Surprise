package com.github.matt159.dws.mixin.mixins.common.appliedenergistics2;

import appeng.integration.modules.NEIHelpers.*;
import codechicken.nei.recipe.TemplateRecipeHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = { NEIAEShapedRecipeHandler.class,
                 NEIAEShapelessRecipeHandler.class,
                 NEIFacadeRecipeHandler.class,
                 NEIGrinderRecipeHandler.class,
                 NEIInscriberRecipeHandler.class })
public abstract class NEIAERecipeHandlerMixin extends TemplateRecipeHandler {
    @ModifyConstant(method = "loadTransferRects",
                    constant = @Constant(intValue = 84),
                    remap = false,
                    require = 1)
    private int modifyTransferRectXOffset(int constant) {
        return constant + 81;
    }
}
