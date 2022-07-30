package com.github.thebrochacho.dws.mixins.client.minecraft;

import com.github.thebrochacho.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.inventory.GuiCrafting;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiCrafting.class)
public abstract class GuiCraftingMixin implements IDWSGui {
}
