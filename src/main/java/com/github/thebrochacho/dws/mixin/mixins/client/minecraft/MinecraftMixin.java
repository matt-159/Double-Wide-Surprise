package com.github.thebrochacho.dws.mixin.mixins.client.minecraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Shadow(aliases = "field_71439_g")
    public EntityClientPlayerMP thePlayer;

    @ModifyConstant(method="func_147112_ai",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyHotbarOffset(int constant) {
        return 18;
    }

//    @ModifyVariable(method = "func_147112_ai",
//                    at = @At(   value = "LOAD",
//                                ordinal = 0 ),
//                    ordinal = 0,
//                    require = 1)
//    private int modifyJ(int value) {
//        int index = Arrays.binarySearch(InventoryDWS.HOTBAR_SLOTS, this.thePlayer.inventory.currentItem);
//        return this.thePlayer.inventoryContainer.inventorySlots.size() - 18 + index;
//    }
}
