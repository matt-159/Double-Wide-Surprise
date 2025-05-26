package com.github.matt159.dws.mixin.mixins.client.railcraft;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.Constants;
import mods.railcraft.client.gui.GuiBoilerSolid;
import mods.railcraft.client.gui.GuiTools;
import mods.railcraft.client.gui.TileGui;
import mods.railcraft.common.blocks.RailcraftTileEntity;
import mods.railcraft.common.gui.containers.RailcraftContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.gui.FontRenderer;

@Mixin(GuiBoilerSolid.class)
public abstract class GuiBoilerSolidMixin extends TileGui implements IDWSGui {
    public GuiBoilerSolidMixin(RailcraftTileEntity tile, RailcraftContainer container, String texture) {
        super(tile, container, texture);
    }

    @Redirect(method = "drawGuiContainerForegroundLayer",
              at = @At(value = "INVOKE",
                       target = "Lmods/railcraft/client/gui/GuiTools;drawCenteredString(Lnet/minecraft/client/gui/FontRenderer;Ljava/lang/String;I)V",
                       remap = false),
              require = 1)
    private void redirectDrawCenteredString(FontRenderer fr, String s, int y) {
        GuiTools.drawCenteredString(fr, s, y, this.xSize);
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 62),
                    require = 1)
    private int modifyProgressBarXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyTextureUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }
}
