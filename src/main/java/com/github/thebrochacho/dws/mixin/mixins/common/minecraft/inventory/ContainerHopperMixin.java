package com.github.thebrochacho.dws.mixin.mixins.common.minecraft.inventory;

import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerHopper.class)
public abstract class ContainerHopperMixin extends Container {

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 44),
                    require = 1)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
