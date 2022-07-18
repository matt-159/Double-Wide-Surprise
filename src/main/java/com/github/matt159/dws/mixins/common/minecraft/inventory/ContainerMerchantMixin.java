package com.github.matt159.dws.mixins.common.minecraft.inventory;

import com.github.matt159.dws.util.DWSUtil;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerMerchant.class)
public abstract class ContainerMerchantMixin extends Container {

    @Shadow private InventoryMerchant merchantInventory;

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void addSlotsToContainer(InventoryPlayer inventoryPlayer, IMerchant merchant, World world, CallbackInfo ci) {
        this.inventorySlots.clear();

        this.addSlotToContainer(new Slot(this.merchantInventory,0, 117, 53));
        this.addSlotToContainer(new Slot(this.merchantInventory, 1, 143, 53));
        this.addSlotToContainer(new SlotMerchantResult(inventoryPlayer.player, merchant, this.merchantInventory, 2, 201, 53));

        DWSUtil.addDWSSlotsToContainer(this, inventoryPlayer);
    }
}
