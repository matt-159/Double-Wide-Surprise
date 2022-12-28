package com.github.matt159.dws.mixin.mixins.common.bartworks;

import com.github.bartimaeusnek.bartworks.neiHandler.BW_NEI_BioLabHandler;
import com.github.bartimaeusnek.bartworks.neiHandler.BW_NEI_BioVatHandler;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin( value = {
        BW_NEI_BioVatHandler.class,
        BW_NEI_BioLabHandler.class
})
public abstract class BW_NEI_HandlerMixin {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 65),
                    remap = false,
                    require = 1)
    private int modifyXPos(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
