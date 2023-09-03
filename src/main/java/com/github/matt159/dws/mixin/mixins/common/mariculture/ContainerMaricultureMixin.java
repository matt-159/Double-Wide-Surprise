package com.github.matt159.dws.mixin.mixins.common.mariculture;

import com.github.matt159.dws.interfaces.IDWSContainer;
import mariculture.core.gui.ContainerMariculture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ContainerMariculture.class)
public abstract class ContainerMaricultureMixin implements IDWSContainer {
    @ModifyConstant(method = "bindPlayerInventory(Lnet/minecraft/entity/player/InventoryPlayer;I)V",
                    constant = @Constant(intValue = 9),
                    require = 4,
                    remap = false)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }

    @ModifyConstant(method = "addUpgradeSlots",
                    constant = @Constant(intValue = 179),
                    require = 1,
                    remap = false)
    private int modifyUpgradeSlotsXOffset(int constant) {
        return constant + 161;
    }

    @ModifyConstant(method = "addPowerSlot",
                    constant = @Constant(intValue = 8),
                    require = 1,
                    remap = false)
    private int modifyPowerSlotXOffset(int constant) {
        return constant + 161;
    }
}
