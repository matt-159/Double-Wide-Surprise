package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.github.matt159.dws.interfaces.dws.IAddsBaubleSlots;
import com.github.matt159.dws.util.ModCompat;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin extends Container  {

    private boolean didMerge = false;

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

    @Inject(method = "transferStackInSlot",
            at = { @At(value = "INVOKE",
                       target = "Lnet/minecraft/inventory/ContainerPlayer;mergeItemStack(Lnet/minecraft/item/ItemStack;IIZ)Z",
                       ordinal = 4),
                   @At(value = "INVOKE",
                       target = "Lnet/minecraft/inventory/ContainerPlayer;mergeItemStack(Lnet/minecraft/item/ItemStack;IIZ)Z",
                       ordinal = 5) },
            cancellable = true,
            require = 2)
    private void injectAccessoryShiftClickAttempt(EntityPlayer player, int slotNumber, CallbackInfoReturnable<ItemStack> cir) {
        this.didMerge = false;
        ItemStack itemstack1 = ((Slot) this.inventorySlots.get(slotNumber)).getStack();
        ItemStack itemstack = itemstack1.copy();

        int startIndex;
        int endIndex = -1;

        if (ModCompat.isBaublesPresent() && itemstack.getItem() instanceof IBauble) {
            IBauble bauble = (IBauble) itemstack.getItem();
            BaubleType type = bauble.getBaubleType(itemstack);

            startIndex = ((IAddsBaubleSlots) this).getBaublesSlotStart();

            switch (type) {
                case RING:
                    startIndex += 1;
                    endIndex = startIndex + 2;
                    break;
                case BELT:
                    startIndex += 3;
                case AMULET:
                    endIndex = startIndex + 1;
                    break;
            }

            if (bauble.canEquip(itemstack, player) && emptySlotInRange(startIndex, endIndex)) {
                if (!mergeItemStack(itemstack1, startIndex, endIndex, false)) {
                    cir.setReturnValue(null);
                    cir.cancel();
                }
                didMerge = true;
            }
        } else if (ModCompat.isTinkersConstructPresent()) {

        } else if (ModCompat.isTravellersGearPresent()) {

        } else if (ModCompat.isGalacticraftPresent()) {

        }
    }

    @ModifyExpressionValue(method = "transferStackInSlot",
                           at = { @At(value = "INVOKE",
                                      target = "Lnet/minecraft/inventory/ContainerPlayer;mergeItemStack(Lnet/minecraft/item/ItemStack;IIZ)Z",
                                      ordinal = 4),
                                  @At(value = "INVOKE",
                                      target = "Lnet/minecraft/inventory/ContainerPlayer;mergeItemStack(Lnet/minecraft/item/ItemStack;IIZ)Z",
                                      ordinal = 5) },
                           require = 2)
    private boolean skipMergeCheck(boolean original) {
        return this.didMerge || original;
    }

    private boolean emptySlotInRange(int startIndex, int endIndex) {
        boolean ret = false;

        for (; startIndex < endIndex; startIndex++) {
            ret = ret || !((Slot) this.inventorySlots.get(startIndex)).getHasStack();
        }

        return ret;
    }
}
