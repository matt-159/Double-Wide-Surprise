package com.github.matt159.dws.mixin.mixins.client.storagedrawers;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.Constants;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawers;
import com.jaquadro.minecraft.storagedrawers.client.gui.GuiDrawers;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiDrawers.class)
public abstract class GuiDrawersMixin extends GuiContainer implements IDWSGui {
    public GuiDrawersMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void injectNewXSize(InventoryPlayer inventory, TileEntityDrawers tileEntity, CallbackInfo ci) {
        this.xSize = 338;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 176),
                    require = 2)
    private int modifyTextureUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }
}
