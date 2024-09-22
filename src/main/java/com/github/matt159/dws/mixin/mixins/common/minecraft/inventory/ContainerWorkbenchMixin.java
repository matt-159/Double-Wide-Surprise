package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import com.github.matt159.dws.util.Constants;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerWorkbench.class)
public abstract class ContainerWorkbenchMixin extends Container {

    @ModifyConstant(method = "<init>",
                    constant = {    @Constant(intValue = 30),
                                    @Constant(intValue = 124)   },
                    require = 2)
    private int modifyXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }


    //here because NEI slides in a method with asm
    @SuppressWarnings("all")
    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 36))
    private int modifyNEIsASMBullshit(int constant) {
        return constant + 27;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 37),
                    require = 4)
    private int modifyHotbarStartIndex(int constant) {
        return 64;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 46),
                    require = 4)
    private int modifyHotbarEndIndex(int constant) {
        return 82;
    }
}
