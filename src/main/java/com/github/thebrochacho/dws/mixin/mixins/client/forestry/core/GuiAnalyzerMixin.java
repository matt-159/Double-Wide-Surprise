package com.github.thebrochacho.dws.mixin.mixins.client.forestry.core;

import forestry.core.gui.GuiAnalyzer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiAnalyzer.class)
public abstract class GuiAnalyzerMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 95),
                    remap = false,
                    require = 1)
    private int modifyTankXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 64),
                    remap = false,
                    require = 1)
    private int modifyProgressBarXOffset(int constant) {
        return constant + 81;
    }
}
