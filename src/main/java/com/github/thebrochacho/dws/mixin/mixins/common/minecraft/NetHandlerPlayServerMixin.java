package com.github.thebrochacho.dws.mixin.mixins.common.minecraft;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.client.C0DPacketCloseWindow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayServer.class)
public abstract class NetHandlerPlayServerMixin {

    @Shadow
    public EntityPlayerMP playerEntity;

    @ModifyConstant(method = "processCreativeInventoryAction",
                    constant = @Constant(intValue = 36),
                    require = 1)
    private int modifyHardcodedInventorySize(int constant) {
        // 9 (crafting + armor slots) + 54 (3x18 main inventory)
        return 9 + 54;
    }

    @Inject(method = "processCloseWindow",
            at = @At("RETURN"),
            require = 1)
    private void injectUpdatePlayerInventory(C0DPacketCloseWindow packet, CallbackInfo ci) {
        this.playerEntity.sendContainerToPlayer(this.playerEntity.openContainer);
    }
}
