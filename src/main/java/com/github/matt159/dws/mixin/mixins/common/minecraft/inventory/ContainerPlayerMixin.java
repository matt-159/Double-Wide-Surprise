package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import com.github.matt159.dws.util.SlotLayoutManager;
import lombok.val;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin extends Container  {
    @ModifyConstant(method = "<init>",
                    constant = {    @Constant(intValue = 88),
                                    @Constant(intValue = 144)   },
                    require = 1)
    private int modifyCraftingSlotsXOffset(int constant) {
        return constant + 162;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyColumnCount(int constant) {
        return 18;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 36),
                    require = 4)
    private int modifyHotbarSlotStart(int constant) {
        return 63;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 45),
                    require = 6)
    private int modifyHotbarSlotEnd(int constant) {
        return 81;
    }

    @Redirect(method = "transferStackInSlot",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/inventory/ContainerPlayer;mergeItemStack(Lnet/minecraft/item/ItemStack;IIZ)Z",
                       ordinal = 4),
              require = 1)
    private boolean tryMergeFromMainIntoAccessorySlots(ContainerPlayer instance,
                                               ItemStack itemStack,
                                               int start,
                                               int end,
                                               boolean scanReverse) {
        boolean didMerge = this.dws$mergeIntoAccessorySlots(itemStack);

        return didMerge || this.mergeItemStack(itemStack, start, end, scanReverse);
    }

    @Redirect(method = "transferStackInSlot",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/inventory/ContainerPlayer;mergeItemStack(Lnet/minecraft/item/ItemStack;IIZ)Z",
                    ordinal = 5),
            require = 1)
    private boolean tryMergeFromHotbarIntoAccessorySlots(ContainerPlayer instance,
                                                         ItemStack itemStack,
                                                         int start,
                                                         int end,
                                                         boolean scanReverse) {
        boolean didMerge = this.dws$mergeIntoAccessorySlots(itemStack);

        return didMerge || this.mergeItemStack(itemStack, start, end, scanReverse);
    }

    @Unique
    private boolean dws$mergeIntoAccessorySlots(ItemStack itemStack) {
        val startIndex = SlotLayoutManager.getFirstAccessorySlot();
        val endIndex = this.inventorySlots.size();

        for (int index = startIndex; index < endIndex; index++) {
            val slot = (Slot) this.inventorySlots.get(index);

            if (slot.isItemValid(itemStack)) {
                if (mergeItemStack(itemStack, index, index + 1, false)) {
                    return true;
                }
            }
        }
        return false;
    }
}
