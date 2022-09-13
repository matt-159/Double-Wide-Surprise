package com.github.matt159.dws.mixin.mixins.client.nei;

import codechicken.lib.inventory.InventoryRange;
import codechicken.lib.inventory.InventoryUtils;
import codechicken.nei.NEIClientUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NEIClientUtils.class)
public abstract class NEIClientUtilsMixin {

    @Inject(method = "canItemFitInInventory",
            at = @At("HEAD"),
            cancellable = true,
            remap = false,
            require = 1)
    @SideOnly(Side.CLIENT)
    private static void canItemFitInInventory(EntityPlayer player, ItemStack itemstack, CallbackInfoReturnable<Boolean> ci) {
        ci.setReturnValue(InventoryUtils.getInsertibleQuantity(new InventoryRange(player.inventory, 0, player.inventory.getSizeInventory()), itemstack) > 0);
    }
}
