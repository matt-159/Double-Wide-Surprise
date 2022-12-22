package com.github.matt159.dws.mixin.mixins.client.forestry.factory.nei;

import forestry.factory.recipes.nei.NEIHandlerCarpenter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NEIHandlerCarpenter.CachedCarpenterRecipe.class)
public abstract class CachedCarpenterRecipeMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 176),
                    remap = false,
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return 338;
    }
}
