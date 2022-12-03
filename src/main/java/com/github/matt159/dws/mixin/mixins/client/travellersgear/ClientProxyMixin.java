package com.github.matt159.dws.mixin.mixins.client.travellersgear;

import net.minecraftforge.client.event.GuiScreenEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import travellersgear.client.ClientProxy;

@Mixin(ClientProxy.class)
public abstract class ClientProxyMixin {

    @Inject(method = "guiPostInit",
            at = @At("HEAD"),
            cancellable = true,
            remap = false,
            require = 1)
    private void injectGuiPostInitEarlyReturn(GuiScreenEvent.InitGuiEvent.Post event, CallbackInfo ci) {
        ci.cancel();
    }

    @Inject(method = "guiDrawScreen",
            at = @At("HEAD"),
            cancellable = true,
            remap = false,
            require = 1)
    private void injectGuiDrawScreenEarlyReturn(GuiScreenEvent.DrawScreenEvent.Post event, CallbackInfo ci) {
        ci.cancel();
    }
    /*@ModifyConstant(method = "guiPostInit",
                    constant = @Constant(intValue = 176),
                    remap = false,
                    require = 1)
    private int modifyXSize(int constant) {
        return 338;
    }

    // Lmao. Budget ASM
    @Redirect(  method = "guiPostInit",
                at = @At(   value = "INVOKE",
                            target = "Ltravellersgear/common/util/ModCompatability;isNeiHidden()Z"),
                remap = false,
                require = 1)
    private boolean redirectIsHidden() {
        return false;
    }*/
}
