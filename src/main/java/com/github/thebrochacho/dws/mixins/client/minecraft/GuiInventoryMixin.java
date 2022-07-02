package com.github.thebrochacho.dws.mixins.client.minecraft;

import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiInventory.class)
public abstract class GuiInventoryMixin extends InventoryEffectRenderer {

    public GuiInventoryMixin(EntityPlayer player) {
        super(player.inventoryContainer);
        this.allowUserInput = true;
    }


}
