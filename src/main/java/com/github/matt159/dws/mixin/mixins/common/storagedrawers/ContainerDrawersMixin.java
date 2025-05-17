package com.github.matt159.dws.mixin.mixins.common.storagedrawers;

import com.github.matt159.dws.interfaces.IDWSContainer;
import com.github.matt159.dws.util.Constants;
import com.jaquadro.minecraft.storagedrawers.inventory.ContainerDrawers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

@Mixin(ContainerDrawers.class)
public abstract class ContainerDrawersMixin extends Container implements IDWSContainer {
    @Redirect(method = "<init>",
              slice = @Slice(from = @At(value = "HEAD"),
                             to = @At(value = "INVOKE",
                                      target = "Ljava/util/ArrayList;<init>()V",
                                      ordinal = 2)),
              at = @At(value = "INVOKE",
                       target = "Lcom/jaquadro/minecraft/storagedrawers/inventory/ContainerDrawers;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;"),
              require = 2)
    private Slot addXOffset(ContainerDrawers instance, Slot slot) {
        slot.xDisplayPosition += Constants.GENERAL_X_OFFSET;

        return this.addSlotToContainer(slot);
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }

    @Override
    public void putStackInSlot(int index, ItemStack itemStack) {
        if (index < this.inventorySlots.size()) {
            super.putStackInSlot(index, itemStack);
        }
    }
}
