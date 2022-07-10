package com.github.matt159.dws.mixins.client.gregtech;

import com.github.matt159.dws.Tags;
import com.github.matt159.dws.interfaces.IDWSGui;
import gregtech.api.gui.GT_GUIContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.stream.Collectors;

@Mixin(GT_GUIContainer.class)
public abstract class GT_GUIContainerMixin extends GuiContainer implements IDWSGui {

    @Shadow(remap = false)
    public ResourceLocation mGUIbackground;

    @Shadow(remap = false)
    public String mGUIbackgroundPath;

    private static String PATH = "textures/gregtech/";

    public GT_GUIContainerMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            remap = false,
            require = 1)
    private void injectXSize(Container aContainer, String aGUIbackground, CallbackInfo ci) {
        this.xSize = 338;
    }

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            remap = false,
            require = 1)
    private void injectUpdatedResourceLocation(Container aContainer, String aGUIbackground, CallbackInfo ci) {
        this.mGUIbackgroundPath = PATH + Arrays.stream(aGUIbackground.split("/"))
                .skip(1)
                .collect(Collectors.joining("/"));

        this.mGUIbackground = new ResourceLocation(Tags.MODID, this.mGUIbackgroundPath);
    }
}
