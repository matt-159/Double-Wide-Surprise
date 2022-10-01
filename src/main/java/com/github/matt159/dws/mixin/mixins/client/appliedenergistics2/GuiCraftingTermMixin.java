package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.implementations.GuiCraftingTerm;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiCraftingTerm.class)
public abstract class GuiCraftingTermMixin {
    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 92),
                    require = 1)
    private int modifyButtonXOffset(int constant) {
        return constant + 81;
    }
}
