package com.github.thebrochacho.dws.mixin.mixins.common.minecraft;

import net.minecraft.network.NetHandlerPlayServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(NetHandlerPlayServer.class)
public abstract class NetHandlerPlayServerMixin {

    @ModifyConstant(method = "processCreativeInventoryAction",
                    constant = @Constant(intValue = 36),
                    require = 1)
    private int modifyHardcodedInventorySize(int constant) {
        // 9 (crafting + armor slots) + 54 (3x18 main inventory)
        return 9 + 54;
    }
}
