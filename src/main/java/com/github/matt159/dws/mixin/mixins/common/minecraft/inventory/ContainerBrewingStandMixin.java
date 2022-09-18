package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import com.github.matt159.dws.inventory.slots.minecraft.SlotIngredient;
import com.github.matt159.dws.inventory.slots.minecraft.SlotPotion;
import com.github.matt159.dws.util.DWSUtil;
import cpw.mods.fml.common.Mod;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntityBrewingStand;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerBrewingStand.class)
public abstract class ContainerBrewingStandMixin extends Container {

    @ModifyConstant(method = "<init>",
                    constant = {    @Constant(intValue = 56),
                                    @Constant(intValue = 79),
                                    @Constant(intValue = 102)   },
                    require = 4)
    private int modifyBrewingSlotsXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
