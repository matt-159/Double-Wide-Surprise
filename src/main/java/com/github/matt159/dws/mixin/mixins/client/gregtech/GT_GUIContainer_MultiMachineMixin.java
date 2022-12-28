package com.github.matt159.dws.mixin.mixins.client.gregtech;

import com.github.matt159.dws.util.Constants;
import gregtech.api.gui.GT_GUIContainer_MultiMachine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_GUIContainer_MultiMachine.class)
public abstract class GT_GUIContainer_MultiMachineMixin {
    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 10),
                    require = 1)
    private int modifyDrawStringXPos(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
