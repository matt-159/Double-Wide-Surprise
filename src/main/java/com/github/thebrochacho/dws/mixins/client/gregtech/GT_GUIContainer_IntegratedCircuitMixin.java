package com.github.thebrochacho.dws.mixins.client.gregtech;

import gregtech.common.gui.GT_Container_IntegratedCircuit;
import gregtech.common.gui.GT_GUIContainer_IntegratedCircuit;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GT_GUIContainer_IntegratedCircuit.class)
public abstract class GT_GUIContainer_IntegratedCircuitMixin extends GuiContainer {

    public GT_GUIContainer_IntegratedCircuitMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void injectSmallerGUISize(GT_Container_IntegratedCircuit container, CallbackInfo ci) {
        this.xSize = 176;
    }
}
