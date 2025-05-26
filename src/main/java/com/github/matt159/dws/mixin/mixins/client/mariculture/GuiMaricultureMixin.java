package com.github.matt159.dws.mixin.mixins.client.mariculture;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.Constants;
import mariculture.core.gui.ContainerMariculture;
import mariculture.core.gui.GuiMariculture;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMariculture.class)
public abstract class GuiMaricultureMixin extends GuiContainer implements IDWSGui {
    public GuiMaricultureMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>(Lmariculture/core/gui/ContainerMariculture;Ljava/lang/String;I)V",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void injectNewXSize(ContainerMariculture container, String texture, int yOffset, CallbackInfo ci) {
        this.xSize = Constants.GENERAL_DWS_GUI_WIDTH + 25;
    }
}
