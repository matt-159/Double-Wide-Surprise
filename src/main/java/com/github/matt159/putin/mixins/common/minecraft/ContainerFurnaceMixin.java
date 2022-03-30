package com.github.matt159.putin.mixins.common.minecraft;

import com.github.matt159.putin.util.PutinUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.tileentity.TileEntityFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerFurnace.class)
public class ContainerFurnaceMixin extends Container {

    @Inject(method = "<init>",
            at = @At(   value = "RETURN",
                        remap = false),
            require = 1)
    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, TileEntityFurnace p_i1812_2_, CallbackInfo ci) {
        ((ContainerFurnace) (Object) (this)).inventorySlots.clear();

        this.addSlotToContainer(new Slot(p_i1812_2_, 0, 137, 17));
        this.addSlotToContainer(new Slot(p_i1812_2_, 1, 137, 53));
        this.addSlotToContainer(new SlotFurnace(inventoryPlayer.player, p_i1812_2_, 2, 197, 35));

        PutinUtil.addPutinSlotsToContainer((ContainerFurnace) (Object) (this), inventoryPlayer);
    }



    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
