package com.github.matt159.dws.mixin.mixins.client.forestry.factory.gui;

import forestry.factory.gui.GuiWorktable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiWorktable.class)
public abstract class GuiWorktableMixin {
    @ModifyConstant(method = "<init>",
                    constant = { @Constant(intValue = 66),
                                 @Constant(intValue = 110) },
                    require = 2)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "addButtons",
                    constant = { @Constant(intValue = 76),
                                 @Constant(intValue = 85) },
                    remap = false,
                    require = 2)
    private int modifyButtonXOffset(int constant) {
        return constant + 81;
    }
}
