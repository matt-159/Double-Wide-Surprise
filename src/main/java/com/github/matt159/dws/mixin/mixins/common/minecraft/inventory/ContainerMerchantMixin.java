package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import com.github.matt159.dws.util.Constants;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerMerchant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerMerchant.class)
public abstract class ContainerMerchantMixin extends Container {

    @ModifyConstant(method = "<init>",
                    constant = {    @Constant(intValue = 36),
                                    @Constant(intValue = 62),
                                    @Constant(intValue = 120)   },
                    require = 3)
    private int modifyXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 30),
                    require = 4)
    private int modifyHotbarStartIndex(int constant) {
        return 57;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 39),
                    require = 4)
    private int modifyHotbarEndIndex(int constant) {
        return 75;
    }
}
