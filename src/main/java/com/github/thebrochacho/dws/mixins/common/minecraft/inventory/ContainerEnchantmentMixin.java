package com.github.thebrochacho.dws.mixins.common.minecraft.inventory;

import com.github.thebrochacho.dws.inventory.slots.minecraft.SlotEnchantment;
import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerEnchantment.class)
public abstract class ContainerEnchantmentMixin extends Container {

    @Shadow public IInventory tableInventory;

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, World world, int x, int y, int z, CallbackInfo ci) {
        this.inventorySlots.clear();
        this.addSlotToContainer(new SlotEnchantment(tableInventory, 0, 106, 47));

        DWSUtil.addDWSSlotsToContainer(this, inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
