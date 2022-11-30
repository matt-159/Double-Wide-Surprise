package com.github.thebrochacho.dws.mixin.mixins.client.forestry.apiculture.gui;

import forestry.apiculture.gui.GuiBeealyzer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiBeealyzer.class)
public abstract class GuiBeealyzerMixin {
    @ModifyConstant(method = "/drawAnalytics/",
                    constant = { @Constant(intValue = 8),
                                 @Constant(intValue = 12),
                                 @Constant(intValue = 90),
                                 @Constant(intValue = 155) },
                    remap = false,
                    require = 1)
    private int modifyColumnXOffsets(int constant) {
        return constant + 46;
    }
}
