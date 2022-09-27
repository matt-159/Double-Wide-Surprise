package com.github.thebrochacho.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiCondenser;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiCondenser.class)
public abstract class GuiCondenserMixin {
    @ModifyConstant(method = "initGui",
                    constant = { @Constant(intValue = 120),
                                 @Constant(intValue = 128) },
                    remap = false,
                    require = 1)
    private int modifyButtonXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 178),
                    remap = false,
                    require = 1)
    private int modifyProgressBarUVXOffset(int constant) {
        return 340;
    }
}
