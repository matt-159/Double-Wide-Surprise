package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import com.github.matt159.dws.util.Constants;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBrewingStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerBrewingStand.class)
public abstract class ContainerBrewingStandMixin extends Container {

    @ModifyConstant(method = "<init>",
                    constant = {    @Constant(intValue = 56),
                                    @Constant(intValue = 79),
                                    @Constant(intValue = 102)   },
                    require = 4)
    private int modifyBrewingSlotsXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
