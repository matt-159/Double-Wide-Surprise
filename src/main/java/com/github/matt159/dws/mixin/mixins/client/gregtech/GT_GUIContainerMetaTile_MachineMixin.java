package com.github.matt159.dws.mixin.mixins.client.gregtech;

import com.github.matt159.dws.interfaces.IDWSGui;
import gregtech.api.gui.GT_GUIContainer;
import gregtech.api.gui.GT_GUIContainerMetaTile_Machine;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.inventory.Container;

@Mixin(GT_GUIContainerMetaTile_Machine.class)
public abstract class GT_GUIContainerMetaTile_MachineMixin extends GT_GUIContainer implements IDWSGui {
    public GT_GUIContainerMetaTile_MachineMixin(Container aContainer, String aGUIbackground) {
        super(aContainer, aGUIbackground);
    }
}
