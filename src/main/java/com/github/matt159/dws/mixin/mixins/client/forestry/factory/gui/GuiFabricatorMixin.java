package com.github.matt159.dws.mixin.mixins.client.forestry.factory.gui;

import com.github.matt159.dws.util.Constants;
import forestry.factory.gui.GuiFabricator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiFabricator.class)
public abstract class GuiFabricatorMixin {
    @ModifyConstant(method = { "<init>",
                               "drawGuiContainerBackgroundLayer" },
                    constant = { @Constant(intValue = 26),
                                 @Constant(intValue = 52,
                                           ordinal = 4),
                                 @Constant(intValue = 55) },
                    require = 3)
    private int modifyXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = { @Constant(intValue = 192),
                                 @Constant(intValue = 196) },
                    require = 2)
    private int modifyTextureUVXOffset(int constant) {
        return constant + 162;
    }
}
