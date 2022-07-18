package com.github.matt159.dws.mixins.common.minecraft.inventory;

import com.github.matt159.dws.util.DWSUtil;
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
public abstract class ContainerFurnaceMixin extends Container {

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, TileEntityFurnace tileEntityFurnace, CallbackInfo ci) {
        ((ContainerFurnace) (Object) (this)).inventorySlots.clear();

        this.addSlotToContainer(new Slot(tileEntityFurnace, 0, 137, 17));
        this.addSlotToContainer(new Slot(tileEntityFurnace, 1, 137, 53));
        this.addSlotToContainer(new SlotFurnace(inventoryPlayer.player, tileEntityFurnace, 2, 197, 35));

        DWSUtil.addDWSSlotsToContainer((ContainerFurnace) (Object) (this), inventoryPlayer);
    }
}
