package com.github.matt159.putin.mixins;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiContainer.class)
public class GuiContainerMixin {

    protected static final ResourceLocation field_147001_a = new ResourceLocation("textures/putinv.png");
}
