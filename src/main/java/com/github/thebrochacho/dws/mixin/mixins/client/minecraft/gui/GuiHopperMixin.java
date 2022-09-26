package com.github.thebrochacho.dws.mixin.mixins.client.minecraft.gui;

import com.github.thebrochacho.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.GuiHopper;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiHopper.class)
public class GuiHopperMixin implements IDWSGui {
}
