package com.github.matt159.dws.mixin.mixins.client.thaumcraft;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.client.gui.GuiResearchTable;
import thaumcraft.common.tiles.TileResearchTable;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

@Mixin(GuiResearchTable.class)
public abstract class GuiResearchTableMixin extends GuiContainer implements IDWSGui {
    public GuiResearchTableMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void injectNewXSize(EntityPlayer player, TileResearchTable e, CallbackInfo ci) {
        this.xSize = Constants.GENERAL_DWS_GUI_WIDTH;
    }
}
