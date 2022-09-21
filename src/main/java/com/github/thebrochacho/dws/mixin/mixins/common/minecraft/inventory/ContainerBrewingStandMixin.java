package com.github.thebrochacho.dws.mixin.mixins.common.minecraft.inventory;

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
        return constant + 81;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
