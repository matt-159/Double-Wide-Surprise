package com.github.matt159.dws.mixins.client.gregtech;

import com.github.matt159.dws.interfaces.IDWSGui;
import gregtech.api.gui.GT_GUIContainerMetaTile_Machine;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GT_GUIContainerMetaTile_Machine.class)
public abstract class GT_GUIContainerMetaTile_MachineMixin implements IDWSGui {
}
