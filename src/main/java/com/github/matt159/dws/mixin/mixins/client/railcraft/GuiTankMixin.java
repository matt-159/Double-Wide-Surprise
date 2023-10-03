package com.github.matt159.dws.mixin.mixins.client.railcraft;

import com.github.matt159.dws.interfaces.IDWSGui;
import mods.railcraft.client.gui.GuiTank;
import mods.railcraft.client.gui.TileGui;
import mods.railcraft.common.blocks.RailcraftTileEntity;
import mods.railcraft.common.gui.containers.RailcraftContainer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiTank.class)
public abstract class GuiTankMixin extends TileGui implements IDWSGui {
    public GuiTankMixin(RailcraftTileEntity tile, RailcraftContainer container, String texture) {
        super(tile, container, texture);
    }


}
