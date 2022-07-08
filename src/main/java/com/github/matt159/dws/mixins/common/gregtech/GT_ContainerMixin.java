package com.github.matt159.dws.mixins.common.gregtech;

import gregtech.api.gui.GT_Container;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GT_Container.class)
public abstract class GT_ContainerMixin extends Container {
    @ModifyConstant(method = "bindPlayerInventory",
                    constant = {
                        @Constant(intValue = 9, ordinal = 0),
                        @Constant(intValue = 9, ordinal = 3)
                    },
                    remap = false,
                    require = 1)
    private int modifyInventoryWidth(int constant) {
        return 18;
    }
}
