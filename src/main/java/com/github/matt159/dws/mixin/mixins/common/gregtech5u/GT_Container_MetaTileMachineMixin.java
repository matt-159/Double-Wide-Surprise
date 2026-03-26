package com.github.matt159.dws.mixin.mixins.common.gregtech5u;

import com.github.matt159.dws.util.Constants;
import gregtech.api.gui.GT_Container;
import gregtech.api.gui.GT_ContainerMetaTile_Machine;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

@Mixin(GT_ContainerMetaTile_Machine.class)
public abstract class GT_Container_MetaTileMachineMixin extends GT_Container {
    public GT_Container_MetaTileMachineMixin(InventoryPlayer aPlayerInventory, IGregTechTileEntity aTileEntityInventory) {
        super(aPlayerInventory, aTileEntityInventory);
    }

    @Redirect(method = "addCircuitSlot",
              at = @At(value = "INVOKE",
                       target = "Lgregtech/api/gui/GT_ContainerMetaTile_Machine;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;"),
              require = 1)
    private Slot addCircuitSlotXOffset(GT_ContainerMetaTile_Machine instance, Slot slot) {
        slot.xDisplayPosition += Constants.GENERAL_X_OFFSET;

        return this.addSlotToContainer(slot);
    }
}
