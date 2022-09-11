package com.github.matt159.dws.mixin.mixins.client.baubles;

import baubles.client.gui.GuiEvents;
import com.github.matt159.dws.interfaces.IDWSGui;
import net.minecraftforge.client.event.GuiScreenEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiEvents.class)
public abstract class GuiEventsMixin {

    private boolean useDWSXSize = false;

    @Inject(method = "guiPostInit",
            at = @At("HEAD"),
            remap = false,
            require = 1)
    private void decideXSize(GuiScreenEvent.InitGuiEvent.Post event, CallbackInfo ci) {
        useDWSXSize = event.gui instanceof IDWSGui;
    }

    @ModifyConstant(method = "guiPostInit",
                    constant = @Constant(intValue = 176),
                    remap = false,
                    require = 1)
    private int modifyXSize(int value) {
        return useDWSXSize ? 338 : 176;
    }

    // lmao. budget asm.
    @Redirect(  method = "guiPostInit",
                at = @At(   value = "INVOKE",
                            target = "Lbaubles/client/gui/GuiEvents;isNeiHidden()Z"),
                remap = false,
                require = 1)
    private boolean redirectIsHidden(GuiEvents instance) {
        return false;
    }
}

