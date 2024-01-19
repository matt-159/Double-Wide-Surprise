package com.github.matt159.dws.mixin.mixins.common.backpack;

import com.github.matt159.dws.util.Constants;
import de.eydamos.backpack.factory.FactoryPersonalSlot;
import de.eydamos.backpack.inventory.container.ContainerAdvanced;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import net.minecraft.inventory.Slot;

@Mixin(FactoryPersonalSlot.class)
public abstract class FactoryPersonalSlotMixin {
    @Redirect(method = "getContainer(Lde/eydamos/backpack/saves/PlayerSave;[Lnet/minecraft/inventory/IInventory;Lnet/minecraft/entity/player/EntityPlayer;)Lde/eydamos/backpack/inventory/container/ContainerAdvanced;",
              at = @At(value = "INVOKE",
                           target = "Lde/eydamos/backpack/inventory/container/ContainerAdvanced;setWidth(I)V"),
              remap = false,
              require = 1)
    private void redirectSetWidth(ContainerAdvanced instance, int value) {
        instance.setWidth(Constants.GENERAL_DWS_GUI_WIDTH);
    }

    @Redirect(method = "getContainer(Lde/eydamos/backpack/saves/PlayerSave;[Lnet/minecraft/inventory/IInventory;Lnet/minecraft/entity/player/EntityPlayer;)Lde/eydamos/backpack/inventory/container/ContainerAdvanced;",
              at = @At(value = "INVOKE",
                       target = "Lde/eydamos/backpack/inventory/container/ContainerAdvanced;addSlot(Lnet/minecraft/inventory/Slot;)V",
                       ordinal = 0),
              remap = false,
              require = 1)
    private void centerPersonalSlot(ContainerAdvanced instance, Slot slot) {
        slot.xDisplayPosition = 161;
        instance.addSlot(slot);
    }

    @ModifyConstant(method = "getContainer(Lde/eydamos/backpack/saves/PlayerSave;[Lnet/minecraft/inventory/IInventory;Lnet/minecraft/entity/player/EntityPlayer;)Lde/eydamos/backpack/inventory/container/ContainerAdvanced;",
                    slice = @Slice(from = @At(value = "INVOKE",
                                              target = "Lde/eydamos/backpack/inventory/container/ContainerAdvanced;addBoundary(Lde/eydamos/backpack/inventory/container/Boundaries;)V",
                                              ordinal = 2),
                                   to = @At(value = "INVOKE",
                                            target = "Lde/eydamos/backpack/inventory/container/ContainerAdvanced;addBoundary(Lde/eydamos/backpack/inventory/container/Boundaries;)V",
                                            ordinal = 5)),
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 4)
    private int modifyPlayerInventoryWidth(int constant) {
        return 18;
    }
}
