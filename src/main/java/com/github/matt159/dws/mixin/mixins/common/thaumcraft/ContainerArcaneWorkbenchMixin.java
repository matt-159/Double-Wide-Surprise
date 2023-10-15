package com.github.matt159.dws.mixin.mixins.common.thaumcraft;

import com.github.matt159.dws.interfaces.IDWSContainer;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;
import thaumcraft.common.container.ContainerArcaneWorkbench;

import net.minecraft.inventory.Container;

@Mixin(ContainerArcaneWorkbench.class)
public abstract class ContainerArcaneWorkbenchMixin extends Container implements IDWSContainer {
    @ModifyConstant(method = "<init>",
                    constant = {
                        @Constant(intValue = 160),
                        @Constant(intValue = 40, ordinal = 0),
                    },
                    require = 3)
    private int modifySlotXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "<init>",
                    slice = @Slice(from = @At(value = "INVOKE",
                                              target = "Lthaumcraft/common/container/ContainerArcaneWorkbench;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                                              ordinal = 1),
                                   to = @At("TAIL")),
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
