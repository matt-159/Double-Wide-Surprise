package com.github.thebrochacho.putin.mixins.common.minecraft;

import com.github.thebrochacho.putin.interfaces.IContainerRepairMixin;
import com.github.thebrochacho.putin.inventory.SlotAnvil;
import com.github.thebrochacho.putin.util.PutinUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerRepair.class)
public class ContainerRepairMixin extends Container implements IContainerRepairMixin {

    @Shadow private IInventory outputSlot;
    @Shadow private IInventory inputSlots;

    @Shadow private int field_82861_i;
    @Shadow private int field_82858_j;
    @Shadow private int field_82859_k;

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, final World world, final int x, final int y, final int z, EntityPlayer player, CallbackInfo ci) {
        ((ContainerRepair) (Object) (this)).inventorySlots.clear();
        this.addSlotToContainer(new Slot(this.inputSlots, 0, 108, 47));
        this.addSlotToContainer(new Slot(this.inputSlots, 1, 157, 47));
        this.addSlotToContainer(new SlotAnvil(this.outputSlot, world, ((ContainerRepair) (Object) (this)), 2, 215, 47));

        PutinUtil.addPutinSlotsToContainer((Container) (Object) (this), inventoryPlayer);
    }

    public IInventory getInputSlots() {
        return inputSlots;
    }

    public IInventory getOutputSlot() {
        return outputSlot;
    }

    public int getXPos() {
        return field_82861_i;
    }

    public int getYPos() {
        return field_82858_j;
    }

    public int getZPos() {
        return field_82859_k;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
