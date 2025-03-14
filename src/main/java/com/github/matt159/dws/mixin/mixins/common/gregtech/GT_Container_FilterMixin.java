package com.github.matt159.dws.mixin.mixins.common.gregtech;

import com.github.matt159.dws.util.Constants;
import gregtech.api.gui.GT_Container;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.common.gui.GT_Container_Filter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

@Mixin(GT_Container_Filter.class)
public abstract class GT_Container_FilterMixin extends GT_Container {
    public GT_Container_FilterMixin(InventoryPlayer aPlayerInventory, IGregTechTileEntity aTileEntityInventory) {
        super(aPlayerInventory, aTileEntityInventory);
    }

    @Redirect(method = "addSlots",
              at = @At(value = "INVOKE",
                       target = "Lgregtech/common/gui/GT_Container_Filter;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;"),
              require = 23)
    private Slot applySlotXOffset(GT_Container_Filter instance, Slot slot) {
        slot.xDisplayPosition += Constants.GENERAL_X_OFFSET;

        return this.addSlotToContainer(slot);
    }
}
