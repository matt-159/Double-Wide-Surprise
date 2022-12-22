package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiCondenser;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiCondenser.class)
public abstract class GuiCondenserMixin {
    @ModifyConstant(method = "initGui",
                    constant = { @Constant(intValue = 120),
                                 @Constant(intValue = 128) },
                    require = 1)
    private int modifyButtonXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 178),
                    require = 1)
    private int modifyProgressBarUVXOffset(int constant) {
        return 340;
    }
}
