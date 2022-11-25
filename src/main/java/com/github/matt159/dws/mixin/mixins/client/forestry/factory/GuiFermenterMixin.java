package com.github.matt159.dws.mixin.mixins.client.forestry.factory;

import forestry.factory.gui.GuiFermenter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiFermenter.class)
public abstract class GuiFermenterMixin {
    @ModifyConstant(method = { "<init>",
                               "drawGuiContainerBackgroundLayer" },
                    constant = { @Constant(intValue = 35),
                                 @Constant(intValue = 74),
                                 @Constant(intValue = 98),
                                 @Constant(intValue = 125) },
                    require = 4)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
            constant = @Constant(intValue = 176),
            require = 2)
    private int modifyTextureUVXOffset(int constant) {
        return 338;
    }
}
