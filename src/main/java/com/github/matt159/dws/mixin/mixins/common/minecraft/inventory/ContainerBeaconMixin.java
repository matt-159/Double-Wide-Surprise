package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerBeacon.class)
public abstract class ContainerBeaconMixin extends Container {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 136),
                    require = 1)
    private int modifyBeaconSlotXDisplayPosition(int constant) {
        return constant + 54;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 36),
                    require = 1)
    private int modifyXOffset(int constant) {
        return 8;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
