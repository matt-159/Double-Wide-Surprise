package com.github.thebrochacho.dws.mixin.mixins.client.forestry.energy.gui;

import forestry.energy.gui.GuiGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiGenerator.class)
public abstract class GuiGeneratorMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 49),
                    require = 1)
    private int modifyTankWidgetXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 108),
                    require = 1)
    private int modifyProgressBarXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return 338;
    }
}
