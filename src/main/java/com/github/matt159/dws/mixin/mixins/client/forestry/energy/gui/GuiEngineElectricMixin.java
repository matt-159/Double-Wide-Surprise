package com.github.matt159.dws.mixin.mixins.client.forestry.energy.gui;

import forestry.energy.gui.GuiEngineElectric;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiEngineElectric.class)
public abstract class GuiEngineElectricMixin {
    @ModifyConstant(method = { "<init>",
                               "drawGuiContainerBackgroundLayer" },
                    constant = { @Constant(intValue = 30),
                                 @Constant(intValue = 74) },
                    require = 2)
    private int modifyXOffsets(int constant) {
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
