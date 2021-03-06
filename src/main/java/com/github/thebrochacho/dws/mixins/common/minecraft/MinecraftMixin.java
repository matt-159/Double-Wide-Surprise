package com.github.thebrochacho.dws.mixins.common.minecraft;

import com.github.thebrochacho.dws.inventory.InventoryDWS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Arrays;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow
    public EntityClientPlayerMP thePlayer;

    @ModifyConstant(method="func_147112_ai",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyHotbarOffset(int constant) {
        return 18;
    }

    @ModifyVariable(method = "func_147112_ai",
                    at = @At(   value = "LOAD",
                                ordinal = 0 ),
                    ordinal = 0,
                    require = 1)
    private int modifyJ(int value) {
        int index = Arrays.binarySearch(InventoryDWS.HOTBAR_SLOTS, this.thePlayer.inventory.currentItem);
        return this.thePlayer.inventoryContainer.inventorySlots.size() - 18 + index;
    }
}
