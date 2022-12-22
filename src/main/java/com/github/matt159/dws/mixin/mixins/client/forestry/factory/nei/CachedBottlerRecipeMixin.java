package com.github.matt159.dws.mixin.mixins.client.forestry.factory.nei;

import com.github.matt159.dws.util.Constants;
import forestry.factory.recipes.nei.NEIHandlerBottler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NEIHandlerBottler.CachedBottlerRecipe.class)
public class CachedBottlerRecipeMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 176),
                    remap = false,
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }
}
