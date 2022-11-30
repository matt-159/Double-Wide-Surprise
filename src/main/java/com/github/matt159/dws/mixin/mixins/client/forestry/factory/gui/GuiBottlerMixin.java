package com.github.matt159.dws.mixin.mixins.client.forestry.factory.gui;

import forestry.factory.gui.GuiBottler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiBottler.class)
public abstract class GuiBottlerMixin {
    @ModifyConstant(method = { "<init>",
                               "drawGuiContainerBackgroundLayer" },
                    constant = { @Constant(intValue = 53),
                                 @Constant(intValue = 80) },
                    require = 2)
    private int modifyXOffsets(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return 338;
    }
}
