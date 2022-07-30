package com.github.matt159.dws.mixins.common.tectech;

import com.github.technus.tectech.nei.TT_NEI_ResearchHandler;
import com.github.technus.tectech.nei.TT_NEI_ScannerHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = {
        TT_NEI_ResearchHandler.class,
        TT_NEI_ScannerHandler.class
})
public abstract class TecTech_NEI_HandlerMixin {
    @ModifyConstant(method = "<init>",
            constant = @Constant(intValue = 65),
            remap = false,
            require = 1)
    private int modifyXPos(int constant) {
        return constant + 81;
    }
}
