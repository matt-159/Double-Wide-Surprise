package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import com.github.matt159.dws.util.Constants;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerEnchantment.class)
public abstract class ContainerEnchantmentMixin extends Container {

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 25),
                    require = 1)
    private int modifyXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
