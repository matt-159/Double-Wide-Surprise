package com.github.thebrochacho.dws.mixin.mixins.common.minecraft.inventory;

import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerChest.class)
public abstract class ContainerChestMixin extends Container {

    @Shadow private int numRows;

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void addSlotsToContainer(IInventory inventoryPlayer, IInventory inventoryChest, CallbackInfo ci) {
        this.inventorySlots.clear();

        int var3 = (numRows - 4) * 18;

        int row;
        int col;
        for(row = 0; row < this.numRows; ++row) {
            for(col = 0; col < 9; ++col) {
                this.addSlotToContainer(new Slot(inventoryChest, col + row * 9, 89 + col * 18, 18 + row * 18));
            }
        }

        DWSUtil.addDWSSlotsToContainer(this, inventoryPlayer, 8, 103 + var3, 161 + var3);
    }
}
