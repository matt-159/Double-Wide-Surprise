package com.github.matt159.dws.mixin.mixins.common.backpack;

import com.github.matt159.dws.util.Constants;
import de.eydamos.backpack.factory.FactoryWorkbenchBackpack;
import de.eydamos.backpack.inventory.container.ContainerWorkbenchBackpack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(FactoryWorkbenchBackpack.class)
public abstract class FactoryWorkbenchBackpackMixin {
    @Redirect(method = "getContainer(Lde/eydamos/backpack/saves/BackpackSave;[Lnet/minecraft/inventory/IInventory;Lnet/minecraft/entity/player/EntityPlayer;)Lde/eydamos/backpack/inventory/container/ContainerAdvanced;",
              at = @At(value = "INVOKE",
                       target = "Lde/eydamos/backpack/inventory/container/ContainerWorkbenchBackpack;setWidth(I)V"),
              remap = false,
              require = 1)
    private void redirectSetWidth(ContainerWorkbenchBackpack instance, int i) {
        instance.setWidth(Constants.GENERAL_DWS_GUI_WIDTH);
    }

    @ModifyConstant(method = "getContainer(Lde/eydamos/backpack/saves/BackpackSave;[Lnet/minecraft/inventory/IInventory;Lnet/minecraft/entity/player/EntityPlayer;)Lde/eydamos/backpack/inventory/container/ContainerAdvanced;",
                    constant = { @Constant(intValue = 8,
                                           ordinal = 0),
                                 @Constant(intValue = 30) },
                    remap = false,
                    require = 2)
    private int modifyXSpacing(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "getContainer(Lde/eydamos/backpack/saves/BackpackSave;[Lnet/minecraft/inventory/IInventory;Lnet/minecraft/entity/player/EntityPlayer;)Lde/eydamos/backpack/inventory/container/ContainerAdvanced;",
                    slice = @Slice(from = @At(value = "INVOKE",
                                              target = "Lde/eydamos/backpack/inventory/container/ContainerWorkbenchBackpack;addBoundary(Lde/eydamos/backpack/inventory/container/Boundaries;)V",
                                              ordinal = 6),
                                   to = @At(value = "INVOKE",
                                            target = "Lde/eydamos/backpack/inventory/container/ContainerWorkbenchBackpack;addBoundary(Lde/eydamos/backpack/inventory/container/Boundaries;)V",
                                            ordinal = 9)),
                    constant = @Constant(intValue = 9),
                    remap = false,
                    require = 4)
    private int modifyPlayerInventoryWidth(int constant) {
        return 18;
    }
}
