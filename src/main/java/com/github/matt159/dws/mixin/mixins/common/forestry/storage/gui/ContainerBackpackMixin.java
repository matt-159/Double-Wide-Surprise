package com.github.matt159.dws.mixin.mixins.common.forestry.storage.gui;

import com.github.matt159.dws.util.Constants;
import forestry.storage.gui.ContainerBackpack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ContainerBackpack.class)
public abstract class ContainerBackpackMixin {
    @ModifyArg(method = "<init>",
               at = @At(value = "INVOKE",
                        target = "Lforestry/core/gui/slots/SlotFilteredInventory;<init>(Lnet/minecraft/inventory/IInventory;III)V"),
               index = 2)
    private int modifySlotXOffset(int slotXPos) {
        return slotXPos + Constants.GENERAL_X_OFFSET;
    }
}
