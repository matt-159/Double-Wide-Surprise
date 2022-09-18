package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import com.github.matt159.dws.inventory.slots.minecraft.SlotHorseArmor;
import com.github.matt159.dws.inventory.slots.minecraft.SlotSaddle;
import com.github.matt159.dws.util.DWSUtil;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerHorseInventory.class)
public abstract class ContainerHorseInventoryMixin extends Container {
    @Shadow
    private EntityHorse theHorse;

    @Redirect(  method = "<init>",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/inventory/ContainerHorseInventory;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                            ordinal = 0),
                require = 1)
    private Slot redirectAddSlotSaddle(ContainerHorseInventory instance, Slot slot) {
        return this.addSlotToContainer(new SlotSaddle(slot.inventory, 0, 8 + 81, 18));
    }

    @Redirect(  method = "<init>",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/inventory/ContainerHorseInventory;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                            ordinal = 1),
                require = 1)
    private Slot redirectAddSlotHorseArmor(ContainerHorseInventory instance, Slot slot) {
        return this.addSlotToContainer(new SlotHorseArmor(this.theHorse, slot.inventory, 1, 8 + 81, 36));
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 80),
                    require = 1)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
