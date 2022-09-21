package com.github.thebrochacho.dws.mixin.mixins.client.gregtech;

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
        return constant + 81;
    }
}
