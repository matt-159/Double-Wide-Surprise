package com.github.matt159.dws.mixin.mixins.client.forestry.factory.nei;

import forestry.factory.recipes.nei.NEIHandlerSqueezer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NEIHandlerSqueezer.CachedSqueezerRecipe.class)
public abstract class CachedSqueezerRecipeMixin {
    @ModifyConstant(method = "setTankFluid(Ljava/util/Collection;)V",
                    constant = @Constant(intValue = 176),
                    remap = false,
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return 338;
    }
}
