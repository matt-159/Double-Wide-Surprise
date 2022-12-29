package com.github.matt159.dws.mixin.mixins.common.endercore;

import com.enderio.core.common.transform.EnderCoreMethods;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EnderCoreMethods.class)
public abstract class EnderCoreMethodsMixin {
    @ModifyConstant(method = "transferStackInSlot",
            constant = @Constant(intValue = 30),
            remap = false,
            require = 4)
    private static int modifyHotbarStartIndex(int constant) {
        return 57;
    }

    @ModifyConstant(method = "transferStackInSlot",
            constant = @Constant(intValue = 39),
            remap = false,
            require = 4)
    private static int modifyHotbarEndIndex(int constant) {
        return 75;
    }
}
