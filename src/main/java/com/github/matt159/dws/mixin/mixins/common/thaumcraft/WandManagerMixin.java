package com.github.matt159.dws.mixin.mixins.common.thaumcraft;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import lombok.val;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.common.items.wands.WandManager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

@Mixin(WandManager.class)
public abstract class WandManagerMixin {
    private static int baublesInventorySize = -1;

    @WrapOperation(method = { "changeFocus",
                              "fetchFocusFromPouch",
                              "addFocusToPouch" },
                   at = @At(value = "INVOKE",
                            target = "Lbaubles/api/BaublesApi;getBaubles(Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/inventory/IInventory;"),
                   require = 3,
                   remap = false)
    private static IInventory wrapBaublesInventory(EntityPlayer player, Operation<IInventory> original) {
        val baublesInventory = original.call(player);

        if (baublesInventorySize == -1) {
            baublesInventorySize = baublesInventory.getSizeInventory();
        }

        return baublesInventory;
    }

    @ModifyConstant(method = { "changeFocus",
                               "fetchFocusFromPouch",
                               "addFocusToPouch" },
                    constant = @Constant(intValue = 4),
                    require = 6,
                    remap = false)
    private static int modifyBaublesRangeCheck(int constant) {
        return baublesInventorySize;
    }

    @ModifyConstant(method = "changeFocus",
                    constant = @Constant(intValue = 36),
                    require = 1,
                    remap = false)
    private static int modifyInventoryRangeCheck(int constant) {
        return 72;
    }
}
