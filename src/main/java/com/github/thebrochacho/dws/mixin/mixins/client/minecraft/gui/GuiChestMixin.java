package com.github.thebrochacho.dws.mixin.mixins.client.minecraft.gui;

import com.github.thebrochacho.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.inventory.GuiChest;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiChest.class)
public abstract class GuiChestMixin implements IDWSGui {
}
