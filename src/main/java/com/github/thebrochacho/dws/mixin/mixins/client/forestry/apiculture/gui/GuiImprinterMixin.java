package com.github.thebrochacho.dws.mixin.mixins.client.forestry.apiculture.gui;

import forestry.apiculture.gui.GuiImprinter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiImprinter.class)
public abstract class GuiImprinterMixin {
    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 138),
                    require = 1)
    private int modifyTextXOffset(int constant) {
        return constant + 162;
    }

    @ModifyConstant(method = { "drawGuiContainerBackgroundLayer",
                               "drawBeeSpeciesIcon" },
                    constant = { @Constant(intValue = 12),
                                 @Constant(intValue = 32,
                                           ordinal = 1),
                                 @Constant(intValue = 32,
                                           ordinal = 2)},
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "getHabitatSlotAtPosition",
                    constant = @Constant(intValue = 12),
                    remap = false,
                    require = 1)
    private static int modifyBoundsXOffset(int constant) {
        return constant + 81;
    }
}
