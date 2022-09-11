package com.github.thebrochacho.dws.mixin.mixins.client.minecraft.gui;

import com.github.thebrochacho.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.inventory.GuiDispenser;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiDispenser.class)
public abstract class GuiDispenserMixin implements IDWSGui {
}
