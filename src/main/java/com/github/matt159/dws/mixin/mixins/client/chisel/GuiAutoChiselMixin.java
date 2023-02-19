package com.github.matt159.dws.mixin.mixins.client.chisel;

import com.github.matt159.dws.interfaces.IDWSGui;
import org.spongepowered.asm.mixin.Mixin;
import team.chisel.client.gui.GuiAutoChisel;

@Mixin(GuiAutoChisel.class)
public abstract class GuiAutoChiselMixin implements IDWSGui {

}
