package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import net.minecraft.inventory.ContainerFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerFurnace.class)
public abstract class ContainerFurnace_TransferStackInSlotMixin {
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
