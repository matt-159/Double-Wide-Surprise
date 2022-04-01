package com.github.thebrochacho.putin.mixins.common.minecraft;

import com.github.thebrochacho.putin.inventory.slots.minecraft.SlotIngredient;
import com.github.thebrochacho.putin.inventory.slots.minecraft.SlotPotion;
import com.github.thebrochacho.putin.util.PutinUtil;
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
            at = @At(value = "RETURN"),
            require = 1)
    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, TileEntityBrewingStand tileEntityBrewingStand, CallbackInfo ci) {
        ((ContainerBrewingStand) (Object) (this)).inventorySlots.clear();
        this.addSlotToContainer(new SlotPotion(inventoryPlayer.player, tileEntityBrewingStand, 0, 137, 46));
        this.addSlotToContainer(new SlotPotion(inventoryPlayer.player, tileEntityBrewingStand, 1, 160, 53));
        this.addSlotToContainer(new SlotPotion(inventoryPlayer.player, tileEntityBrewingStand, 2, 183, 46));
        this.theSlot = this.addSlotToContainer(new SlotIngredient((ContainerBrewingStand) (Object) (this), tileEntityBrewingStand, 3, 160, 17));

        PutinUtil.addPutinSlotsToContainer((ContainerBrewingStand) (Object) (this), inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
