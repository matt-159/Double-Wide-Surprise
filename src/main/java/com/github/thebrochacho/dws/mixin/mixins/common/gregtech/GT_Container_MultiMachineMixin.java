package com.github.thebrochacho.dws.mixin.mixins.common.gregtech;

import gregtech.api.gui.GT_Container_MultiMachine;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container_MultiMachine.class)
public abstract class GT_Container_MultiMachineMixin extends Container {
    @ModifyConstant(method = "addSlots",
                    constant = @Constant(intValue = 152),
                    remap = false,
                    require = 1)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }
}
