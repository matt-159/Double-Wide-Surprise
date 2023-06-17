package com.github.matt159.dws.mixin.mixins.common.nei;

import codechicken.lib.inventory.InventoryRange;
import codechicken.lib.inventory.InventoryUtils;
import codechicken.nei.NEIServerUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NEIServerUtils.class)
public abstract class NEIServerUtilsMixin {
    @ModifyConstant(method = "cycleCreativeInv",
                    constant = {    @Constant(  intValue = 9,
                                                slice = "one"),
                                    @Constant(  intValue = 9,
                                                slice = "two"),
                                    @Constant(  intValue = 9,
                                                slice = "three")  },
                    slice = {   @Slice( from = @At("HEAD"),
                                        to = @At(   value = "CONSTANT",
                                                    args = "intValue=9",
                                                    ordinal = 0),
                                        id = "one"),
                                @Slice( from = @At( value = "CONSTANT",
                                                    args = "intValue=9",
                                                    ordinal = 2),
                                        to = @At( value = "CONSTANT",
                                                    args = "intValue=9",
                                                    ordinal = 6),
                                        id = "two"),
                                @Slice( from = @At( value = "CONSTANT",
                                                    args = "intValue=9",
                                                    ordinal = 8),
                                        to = @At("TAIL"),
                                        id = "three")   },
                    remap = false,
                    require = 1)
    private static int modifyPlayerInventorySize(int constant) {
        return 18;
    }

    @Inject(method = "canItemFitInInventory",
            at = @At("HEAD"),
            cancellable = true,
            remap = false,
            require = 1)
    @SideOnly(Side.SERVER)
    private static void canItemFitInInventory(EntityPlayer player, ItemStack itemstack, CallbackInfoReturnable<Boolean> ci) {
        ci.setReturnValue(InventoryUtils.getInsertibleQuantity(new InventoryRange(player.inventory, 0, player.inventory.getSizeInventory()), itemstack) > 0);
    }
}
