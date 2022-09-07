package com.github.matt159.dws.mixins.client.minecraft.gui;

import com.github.matt159.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.inventory.GuiChest;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiChest.class)
public abstract class GuiChestMixin implements IDWSGui {
}
