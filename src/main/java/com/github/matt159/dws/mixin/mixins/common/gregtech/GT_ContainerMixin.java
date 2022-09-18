package com.github.matt159.dws.mixin.mixins.common.gregtech;

import gregtech.api.gui.GT_Container;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container.class)
public abstract class GT_ContainerMixin extends Container {
    @ModifyConstant(method = {  "bindPlayerInventory",
                                "getAllSlotCount",
                                "transferStackInSlot"   },
                    constant = {    @Constant(intValue = 9),
                                    @Constant(intValue = 36)    },
                    remap = false,
                    require = 6)
    private int modifyPlayerInventorySize(int constant) {
        return constant * 2;
    }
}
