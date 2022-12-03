package com.github.matt159.dws.mixin.mixins.client.dws.baubles;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.interfaces.dws.IAddsBaubleSlots;
import com.github.matt159.dws.util.ModCompat;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiInventory.class)
public abstract class GuiInventoryMixin extends InventoryEffectRenderer implements IDWSGui {
    public GuiInventoryMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void injectSyncBaublesStackList(EntityPlayer player, CallbackInfo ci) {
        if (ModCompat.isBaublesPresent()) {
            InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);

            ((IAddsBaubleSlots) (player.inventoryContainer)).setBaublesAccessories(baubles);
        }
    }
}
