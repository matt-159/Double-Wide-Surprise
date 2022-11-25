package com.github.thebrochacho.dws.mixin.mixins.client.forestry.factory;

import forestry.factory.gui.GuiCentrifuge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiCentrifuge.class)
public abstract class GuiCentrifugeMixin {
    @ModifyConstant(method = { "<init>",
                               "drawGuiContainerBackgroundLayer" },
                    constant = { @Constant(intValue = 58),
                                 @Constant(intValue = 71) },
                    require = 2)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
            constant = @Constant(intValue = 176),
            require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return 338;
    }
}
