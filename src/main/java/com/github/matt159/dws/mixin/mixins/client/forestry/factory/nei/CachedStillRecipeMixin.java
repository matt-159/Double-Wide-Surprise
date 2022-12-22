package com.github.matt159.dws.mixin.mixins.client.forestry.factory.nei;

import forestry.factory.recipes.nei.NEIHandlerStill;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NEIHandlerStill.CachedStillRecipe.class)
public abstract class CachedStillRecipeMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 176),
                    remap = false,
                    require = 2)
    private int modifyTextureUVXOffset(int constant) {
        return 338;
    }
}
