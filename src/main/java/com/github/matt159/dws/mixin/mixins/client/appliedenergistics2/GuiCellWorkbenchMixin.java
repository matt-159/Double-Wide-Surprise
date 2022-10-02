package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiCellWorkbench;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiCellWorkbench.class)
public abstract class GuiCellWorkbenchMixin {
    @ModifyConstant(method = "drawBG",
            constant =  {   @Constant(intValue = 177),
                            @Constant(intValue = 178)   },
            remap = false,
            require = 23)
    private int modifyGuiXSize(int constant) {
        return constant + 162;
    }
}
