package com.github.thebrochacho.dws.mixin.mixins.client.minecraft;

import net.minecraft.client.network.NetHandlerPlayClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NetHandlerPlayClient.class)
public abstract class NetHandlerPlayClientMixin {
    @ModifyConstant(method = "handleSetSlot",
                    constant = @Constant(intValue = 36),
                    require = 1)
    private int modifyHotBarSlotRangeStart(int constant) {
        return 63;
    }

    @ModifyConstant(method = "handleSetSlot",
            constant = @Constant(intValue = 45),
            require = 1)
    private int modifyHotBarSlotRangeEnd(int constant) {
        return 81;
    }
}
