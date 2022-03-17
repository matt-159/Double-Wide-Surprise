package com.github.matt159.putin.mixins.common.minecraft;

import com.github.matt159.putin.inventory.SlotIngredient;
import com.github.matt159.putin.inventory.SlotPotion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntityBrewingStand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerBrewingStand.class)
public class ContainerBrewingStandMixin extends Container {

    @Mutable
    @Shadow @Final private Slot theSlot;

    @Inject(method = "<init>",
            at = @At(   value = "RETURN",
                        remap = false),
            require = 1)
    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, TileEntityBrewingStand p_i1805_2_, CallbackInfo ci) {
        ((ContainerBrewingStand) (Object) (this)).inventorySlots.clear();
        this.addSlotToContainer(new SlotPotion(inventoryPlayer.player, p_i1805_2_, 0, 137, 46));
        this.addSlotToContainer(new SlotPotion(inventoryPlayer.player, p_i1805_2_, 1, 160, 53));
        this.addSlotToContainer(new SlotPotion(inventoryPlayer.player, p_i1805_2_, 2, 183, 46));
        this.theSlot = this.addSlotToContainer(new SlotIngredient((ContainerBrewingStand) (Object) (this), p_i1805_2_, 3, 160, 17));

        //main inventory
        int row, col;
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
