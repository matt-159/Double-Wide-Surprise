package com.github.matt159.dws.mixins.client.minecraft.gui;

import com.github.matt159.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.inventory.GuiDispenser;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiDispenser.class)
public abstract class GuiDispenserMixin implements IDWSGui {
}
