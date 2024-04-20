package com.github.matt159.dws.mixin.mixins.common.thaumcraft;

import com.github.matt159.dws.interfaces.IDWSContainer;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.container.ContainerFocusPouch;

import net.minecraft.inventory.Container;

@Mixin(ContainerFocusPouch.class)
public abstract class ContainerFocusPouchMixin extends Container implements IDWSContainer {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 37),
                    require = 1,
                    remap = false)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "bindPlayerInventory",
                    constant = @Constant(intValue = 9),
                    require = 4,
                    remap = false)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
