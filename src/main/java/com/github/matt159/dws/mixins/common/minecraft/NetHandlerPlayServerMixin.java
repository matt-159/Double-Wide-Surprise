package com.github.matt159.dws.mixins.common.minecraft;

import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;

@Mixin(NetHandlerPlayServer.class)
public abstract class NetHandlerPlayServerMixin {

    @ModifyConstant(method = "processCreativeInventoryAction",
                    constant = @Constant(intValue = 36),
                    require = 1)
    private int modifyHardcodedInventorySize(int constant) {
        return 63;
    }

//    @Redirect(  method = "processHeldItemChange",
//                at = @At(   value = "INVOKE",
//                            target = "Lnet/minecraft/network/play/client/C09PacketHeldItemChange;func_149614_c()I",
//                            ordinal = 1),
//                require = 1)
//    private int redirectGetHotbarSlotIndex(C09PacketHeldItemChange instance) {
//
//        return Arrays.binarySearch(InventoryDWS.HOTBAR_SLOTS, instance.func_149614_c());
//    }
}
