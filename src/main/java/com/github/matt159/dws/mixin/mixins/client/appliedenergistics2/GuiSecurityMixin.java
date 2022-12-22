package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiSecurity;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GuiSecurity.class)
public abstract class GuiSecurityMixin {
    @ModifyArg(method = "initGui",
               at = @At(value = "INVOKE",
                        target = "Lappeng/client/gui/widgets/GuiToggleButton;<init>(IIIILjava/lang/String;Ljava/lang/String;)V"),
               index = 0,
               require = 5)
    private int modifyGuiButtonXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
