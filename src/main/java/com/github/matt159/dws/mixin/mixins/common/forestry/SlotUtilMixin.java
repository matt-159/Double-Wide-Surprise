package com.github.matt159.dws.mixin.mixins.common.forestry;

import forestry.core.utils.SlotUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(SlotUtil.class)
public abstract class SlotUtilMixin {
    @ModifyConstant(method = { "isInPlayerInventory",
                               "isInPlayerHotbar",
                               "shiftToHotbar",
                               "shiftToPlayerInventory",
                               "shiftToPlayerInventoryNoHotbar",
                               "shiftToMachineInventory*" },
                    constant = { @Constant(intValue = 9),
                                 @Constant(intValue = 27),
                                 @Constant(intValue = 36) },
                    remap = false,
                    require = 10)
    private static int modifyPlayerInventoryConstants(int constant) {
        return constant * 2;
    }
}
