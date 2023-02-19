package com.github.matt159.dws.mixin.mixins.common.chisel;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import team.chisel.inventory.ContainerChisel;
import team.chisel.inventory.InventoryChiselSelection;

@Mixin(ContainerChisel.class)
public abstract class ContainerChiselMixin extends Container {
    @ModifyConstant(method = { "<init>",
                               "slotClick" },
                    constant = @Constant(intValue = 27),
                    remap = false,
                    require = 2)
    private int modifyMainInventoryRange(int constant) {
        return 54;
    }

    @Redirect(method = "<init>",
              at = @At(value = "INVOKE",
                       target = "Lteam/chisel/inventory/ContainerChisel;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                       ordinal = 0),
              require = 1)
    private Slot redirectAddChiselSelectionSlots(ContainerChisel instance, Slot slot) {
        int i = slot.getSlotIndex();
        slot.xDisplayPosition = 44 + (i % 15) * 18;
        slot.yDisplayPosition = 8 + (i / 15) * 18;

        return this.addSlotToContainer(slot);
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 24),
                    remap = false,
                    require = 2)
    private int modifyChiselSlotXYOffset(int constant) {
        return 16;
    }

    @Redirect(method = "<init>",
              at = @At(value = "INVOKE",
                       target = "Lteam/chisel/inventory/ContainerChisel;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                       ordinal = 2),
              require = 1)
    private Slot redirectAddPlayerMainInventorySlot(ContainerChisel instance, Slot slot) {
        int i = slot.getSlotIndex() - 9;

        return this.addSlotToContainer(new Slot(slot.inventory, i + 18, 8 + (i % 18) * 18, 84 + (i / 18) * 18));
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9,
                                         ordinal = 3),
                    remap = false,
                    require = 1)
    private int modifyHotbarRange(int constant) {
        return 18;
    }

    @Redirect(method = "<init>",
              at = @At(value = "INVOKE",
                       target = "Lteam/chisel/inventory/ContainerChisel;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                       ordinal = 3),
              require = 1)
    private Slot redirectAddPlayerHotbarSlot(ContainerChisel instance, Slot slot) {
        int i = slot.getSlotIndex();

        return this.addSlotToContainer(new Slot(slot.inventory, i, 8 + (i % 18) * 18, 142 + (i / 18) * 18));
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = InventoryChiselSelection.normalSlots + 1 + 36),
                    require = 1)
    private int modifyPlayerInventorySize(int constant) {
        return constant + 36;
    }
}