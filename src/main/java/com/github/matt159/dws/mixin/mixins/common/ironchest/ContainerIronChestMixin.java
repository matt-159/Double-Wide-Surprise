package com.github.matt159.dws.mixin.mixins.common.ironchest;

import cpw.mods.ironchest.ContainerIronChest;
import cpw.mods.ironchest.IronChestType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerIronChest.class)
public abstract class ContainerIronChestMixin extends Container {
    private int xSize;

    @Inject(method = "layoutContainer",
            at = @At("HEAD"),
            remap = false,
            require = 1)
    private void injectLocalCapture(IInventory playerInventory, IInventory chestInventory, IronChestType type, int xSize, int ySize, CallbackInfo ci) {
        this.xSize = xSize;
    }

    @ModifyConstant(method = "layoutContainer",
                    constant = @Constant(intValue = 84),
                    remap = false,
                    require = 1)
    private int modifyDirtChestSlotXOffset(int constant) {
        return 161;
    }

    @Redirect(  method = "layoutContainer",
                at = @At(   value = "INVOKE",
                            target = "Lcpw/mods/ironchest/IronChestType;makeSlot(Lnet/minecraft/inventory/IInventory;III)Lnet/minecraft/inventory/Slot;",
                            ordinal = 1),
                remap = false,
                require = 1)
    private Slot redirectAddChestSlot(IronChestType instance, IInventory chestInventory, int index, int x, int y) {
        //adjust x and y offsets of chest slots
        x = x - 12 + getXOffset(instance);
        y += 1;

        return instance.makeSlot(chestInventory, index, x, y);
    }

//    @Redirect(  method = "layoutContainer",
//                at = @At(   value = "INVOKE",
//                            target = "Lcpw/mods/ironchest/ContainerIronChest;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;"),
//                slice = @Slice( from = @At( value = "INVOKE",
//                                            target = "Lcpw/mods/ironchest/ContainerIronChest;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
//                                            ordinal = 2,
//                                            shift = At.Shift.BEFORE),
//                                to = @At(   value = "INVOKE",
//                                            target = "Lcpw/mods/ironchest/ContainerIronChest;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
//                                            ordinal = 3,
//                                            shift = At.Shift.AFTER)),
//                remap = false,
//                require = 2)
//    private Slot redirectSlotConstructor(ContainerIronChest instance, Slot slot) {
//        int oldXOffset = (this.xSize - 162) / 2 + 1;
//
//        return this.addSlotToContainer(new Slot(slot.inventory, slot.getSlotIndex(), slot.xDisplayPosition - oldXOffset + 8, slot.yDisplayPosition + 1));
//    }

    @ModifyArg(method = "layoutContainer",
               at = @At(value = "INVOKE",
                        target = "Lcpw/mods/ironchest/ContainerIronChest;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;"/*,
                        ordinal = 2*/),
               slice = @Slice(from = @At(value = "INVOKE",
                                         target = "Lcpw/mods/ironchest/ContainerIronChest;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                                         ordinal = 2,
                                         shift = At.Shift.BEFORE),
                              to = @At(value = "INVOKE",
                                       target = "Lcpw/mods/ironchest/ContainerIronChest;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                                       ordinal = 3,
                                       shift = At.Shift.AFTER)),
//               remap = false,
               require = 2)
    private Slot modifySlotXOffset(Slot slot) {
        int oldXOffset = (this.xSize - 162) / 2 + 1;

        slot.xDisplayPosition = slot.xDisplayPosition - oldXOffset + 8;
        slot.yDisplayPosition = slot.yDisplayPosition + 1;

        return slot;
    }

    @ModifyConstant(method = "layoutContainer",
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
    private int getXOffset(IronChestType type) {
        switch (type.name()) {
            case "DIRTCHEST9000":
                return  161;
            case "COPPER":
            case "IRON":
            case "SILVER":
            case "STEEL":
            case "GOLD":
                return  89;
            case "DIAMOND":
            case "CRYSTAL":
            case "OBSIDIAN":
                return  62;
        }

        return -1;
    }
}
