package com.github.matt159.dws.mixin.mixins.client.minecraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.entity.player.InventoryPlayer;

import com.github.matt159.dws.registry.Keybindings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    // inventorySlots.size() can't be trusted to provide a constant number of slots
    // since accessory slots are added to the ContainerPlayer object
    @Redirect(  method = "func_147112_ai",
                at = @At(   value = "INVOKE",
                            target = "Ljava/util/List;size()I"),
                require = 1)
    private int redirectGetMainInventorySize(List instance) {
        return 81;
    }

    @ModifyConstant(method="func_147112_ai",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyHotbarOffset(int constant) {
        return 18;
    }

    @Redirect(method = "runTick",
              at = @At(value = "FIELD",
                       target = "Lnet/minecraft/entity/player/InventoryPlayer;currentItem:I"),
              require = 1)
    private void redirectSlotSelection(InventoryPlayer instance, int value) {
        if (Keybindings.ModifierKey.getIsKeyPressed()) {
            value += 9;
        }

        instance.currentItem = value;
    }
}