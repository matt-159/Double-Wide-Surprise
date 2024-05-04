package com.github.matt159.dws.mixin.mixins.common.thaumcraft;

import com.github.matt159.dws.interfaces.IDWSContainer;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.container.ContainerThaumatorium;

import net.minecraft.inventory.Container;

@Mixin(ContainerThaumatorium.class)
public abstract class ContainerThaumatoriumMixin extends Container implements IDWSContainer {
    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyInventorySize(int constant) {
        return 18;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 48),
                    require = 1)
    private int modifyInputSlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = { @Constant(intValue = 28),
                                 @Constant(intValue = 37) },
                    require = 7)
    private int modifyInventoryBounds(int constant) {
        return constant + 27;
    }
}
