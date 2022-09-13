package com.github.matt159.dws.mixin.mixins.client.gregtech;

import gregtech.common.gui.GT_ContainerVolumetricFlask;
import gregtech.common.gui.GT_GUIContainerVolumetricFlask;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GT_GUIContainerVolumetricFlask.class)
public abstract class GT_GUIContainerVolumetricFlaskMixin extends GuiContainer {

    public GT_GUIContainerVolumetricFlaskMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void injectSmallGuiWidth(GT_ContainerVolumetricFlask container, CallbackInfo ci) {
        this.xSize = 176;
    }
}
