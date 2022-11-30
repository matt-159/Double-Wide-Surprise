package com.github.thebrochacho.dws.mixin.mixins.client.forestry.apiculture.gui;

import forestry.apiculture.gui.GuiAlveary;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiAlveary.class)
public abstract class GuiAlvearyMixin {
    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 20),
                    require = 1)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawHealthMeter",
                    constant = @Constant(intValue = 176),
                    remap = false,
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return 338;
    }
}
