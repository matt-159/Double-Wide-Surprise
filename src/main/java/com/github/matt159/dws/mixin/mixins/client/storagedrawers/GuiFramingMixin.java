package com.github.matt159.dws.mixin.mixins.client.storagedrawers;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityFramingTable;
import com.jaquadro.minecraft.storagedrawers.client.gui.GuiFraming;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiFraming.class)
public abstract class GuiFramingMixin extends GuiContainer implements IDWSGui {
    public GuiFramingMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void injectNewXSize(InventoryPlayer inventory, TileEntityFramingTable tileEntity, CallbackInfo ci) {
        this.xSize = 338;
    }
}
