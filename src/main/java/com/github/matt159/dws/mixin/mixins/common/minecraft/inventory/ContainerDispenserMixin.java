package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import com.github.matt159.dws.util.Constants;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerDispenser;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerDispenser.class)
public abstract class ContainerDispenserMixin extends Container {

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 62),
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

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 45),
                    require = 1)
    private int modifyHotbarStartIndex(int constant) {
        return 81;
    }
}
