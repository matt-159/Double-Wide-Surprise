package com.github.matt159.dws.mixin.mixins.client.forestry.arboriculture.gui;

import forestry.arboriculture.gui.GuiTreealyzer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiTreealyzer.class)
public class GuiTreealyzerMixin {
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
