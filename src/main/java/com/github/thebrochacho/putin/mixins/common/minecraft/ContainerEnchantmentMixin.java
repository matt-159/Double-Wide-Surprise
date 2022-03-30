package com.github.thebrochacho.putin.mixins.common.minecraft;

import com.github.thebrochacho.putin.inventory.slots.minecraft.SlotEnchantment;
import com.github.thebrochacho.putin.util.PutinUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerEnchantment.class)
public class ContainerEnchantmentMixin extends Container {

    @Shadow public IInventory tableInventory;

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, World p_i1811_2_, int p_i1811_3_, int p_i1811_4_, int p_i1811_5_, CallbackInfo ci) {
        ((ContainerEnchantment) (Object) (this)).inventorySlots.clear();
        this.addSlotToContainer(new SlotEnchantment(tableInventory, 0, 106, 47));

        PutinUtil.addPutinSlotsToContainer((ContainerEnchantment) (Object) (this), inventoryPlayer);
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }
}
