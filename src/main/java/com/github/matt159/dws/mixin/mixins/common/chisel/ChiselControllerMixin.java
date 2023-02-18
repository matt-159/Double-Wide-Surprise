package com.github.matt159.dws.mixin.mixins.common.chisel;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import team.chisel.item.chisel.ChiselController;

@Mixin(ChiselController.class)
public abstract class ChiselControllerMixin {
    @ModifyConstant(method = "onInteract",
                    constant = @Constant(intValue = 8),
                    remap = false,
                    require = 1)
    private int modifySlotRange(int constant) {
        return 18;
    }
}
