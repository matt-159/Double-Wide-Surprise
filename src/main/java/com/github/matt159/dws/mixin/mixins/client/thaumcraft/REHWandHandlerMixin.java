package com.github.matt159.dws.mixin.mixins.client.thaumcraft;

import baubles.api.BaublesApi;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import lombok.val;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.client.lib.REHWandHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

@Mixin(REHWandHandler.class)
public abstract class REHWandHandlerMixin {
    private static int baublesInventorySize = -1;

    @WrapOperation(method = "handleFociRadial",
                   at = @At(value = "INVOKE",
                            target = "Lbaubles/api/BaublesApi;getBaubles(Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/inventory/IInventory;"),
                   require = 1,
                   remap = false)
    private IInventory wrapBaublesInventory(EntityPlayer player, Operation<IInventory> original) {
        val inventory = original.call(player);

        if (baublesInventorySize == -1) {
            baublesInventorySize = inventory.getSizeInventory();
        }

        return inventory;
    }

    @ModifyConstant(method = "handleFociRadial",
                    constant = @Constant(intValue = 4),
                    require = 1,
                    remap = false)
    private int modifyBaublesRangeCheck(int constant) {
        return baublesInventorySize;
    }

    @ModifyConstant(method = "handleFociRadial",
                    constant = @Constant(intValue = 36),
                    require = 1,
                    remap = false)
    private int modifyInventoryRangeCheck(int constant) {
        return 72;
    }
}
