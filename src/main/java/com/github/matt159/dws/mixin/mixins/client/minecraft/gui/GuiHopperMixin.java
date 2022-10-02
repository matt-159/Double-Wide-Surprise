package com.github.matt159.dws.mixin.mixins.client.minecraft.gui;

import com.github.matt159.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.GuiHopper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiHopper.class)
public class GuiHopperMixin implements IDWSGui {
}
