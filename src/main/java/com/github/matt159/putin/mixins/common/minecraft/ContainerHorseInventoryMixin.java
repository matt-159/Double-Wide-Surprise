package com.github.matt159.putin.mixins.common.minecraft;

import com.github.matt159.putin.inventory.slots.minecraft.SlotHorseArmor;
import com.github.matt159.putin.inventory.slots.minecraft.SlotSaddle;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerHorseInventory.class)
public class ContainerHorseInventoryMixin extends Container {

    @Inject(method = "<init>",
            at = @At(   value = "RETURN",
                        remap = false),
            require = 1)
    private void addSlotsToContainer(IInventory inventoryPlayer, IInventory inventoryHorse, EntityHorse horse, CallbackInfo ci) {
        ((ContainerHorseInventory) (Object) this).inventorySlots.clear();

        this.addSlotToContainer(new SlotSaddle(inventoryHorse, 0, 89, 18));
        this.addSlotToContainer(new SlotHorseArmor(horse, inventoryHorse, 1, 89, 36));

        int var4 = 3;
        int row, col;

        if (horse.isChested()) {
            for(row = 0; row < var4; ++row) {
                for(col = 0; col < 5; ++col) {
                    this.addSlotToContainer(new Slot(inventoryHorse, 2 + col + row * 5, 161 + col * 18, 18 + row * 18));
                }
            }
        }

        //main inventory
        for (row = 0; row < 3; ++row) {
            for (col = 0; col < 18; ++col) {
                this.addSlotToContainer(new Slot(inventoryPlayer, col + row * 18 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        //left half of hotbar
        for (row = 0; row < 9; ++row) {
            this.addSlotToContainer(new Slot(inventoryPlayer, row, 8 + row * 18, 142));
        }

        //right half of hotbar
        for (row = 0; row < 9; ++row) {
            this.addSlotToContainer(new Slot(inventoryPlayer, row + 63, 8 + (row + 9) * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
