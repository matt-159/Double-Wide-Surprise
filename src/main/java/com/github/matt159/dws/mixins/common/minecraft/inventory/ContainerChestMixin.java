package com.github.matt159.dws.mixins.common.minecraft.inventory;

import com.github.matt159.dws.util.DWSUtil;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerChest.class)
public abstract class ContainerChestMixin extends Container {

    @ModifyConstant(method = "<init>",
                    constant = @Constant(   intValue = 8,
                                            ordinal = 0),
                    require = 1)
    private int modifyChestSlotsXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "<init>",
                    slice = @Slice( from =  @At(value = "INVOKE",
                                                target = "Lnet/minecraft/inventory/ContainerChest;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                                                ordinal = 0,
                                                shift = At.Shift.AFTER),
                                    to =    @At("RETURN")),
                    constant = @Constant(intValue = 9),
                    require = 3)
    private int modifyPlayerInventorySlotCount(int constant) {
        return 18;
    }
}
