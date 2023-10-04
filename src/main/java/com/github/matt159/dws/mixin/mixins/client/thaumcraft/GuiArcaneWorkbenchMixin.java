package com.github.matt159.dws.mixin.mixins.client.thaumcraft;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.client.gui.GuiArcaneWorkbench;
import thaumcraft.common.tiles.TileArcaneWorkbench;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

@Mixin(GuiArcaneWorkbench.class)
public abstract class GuiArcaneWorkbenchMixin extends GuiContainer implements IDWSGui {
    public GuiArcaneWorkbenchMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void injectNewXSize(InventoryPlayer inventoryPlayer, TileArcaneWorkbench tile, CallbackInfo ci) {
        this.xSize = 354;
    }

    @ModifyArg(method = "drawGuiContainerBackgroundLayer",
               at = @At(value = "INVOKE",
                        target = "Lthaumcraft/client/lib/UtilsFX;drawTag(IILthaumcraft/api/aspects/Aspect;FIDIFZ)V"),
               index = 0)
    private int modifyAspectXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
