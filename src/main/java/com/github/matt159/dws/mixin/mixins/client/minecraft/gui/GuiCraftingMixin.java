package com.github.matt159.dws.mixin.mixins.client.minecraft.gui;

import com.github.matt159.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.inventory.GuiCrafting;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiCrafting.class)
public abstract class GuiCraftingMixin implements IDWSGui {
}
