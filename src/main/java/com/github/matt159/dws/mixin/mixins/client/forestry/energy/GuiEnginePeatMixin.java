package com.github.matt159.dws.mixin.mixins.client.forestry.energy;

import forestry.energy.gui.GuiEnginePeat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiEnginePeat.class)
public abstract class GuiEnginePeatMixin {
    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 45),
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
