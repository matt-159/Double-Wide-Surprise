package com.github.matt159.dws.mixin.mixins.client.forestry.factory;

import forestry.factory.gui.GuiSqueezer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiSqueezer.class)
public abstract class GuiSqueezerMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 75),
                                 @Constant(intValue = 122) },
                    require = 2)
    private int modifyXOffsetInit(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawWidgets",
                    constant = @Constant(intValue = 75),
                    remap = false,
                    require = 1)
    private int modifyTextureXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawWidgets",
                    constant = @Constant(intValue = 176),
                    remap = false,
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return 338;
    }
}
