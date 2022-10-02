package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.AEBaseGui;
import com.github.matt159.dws.interfaces.IDWSGui;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AEBaseGui.class)
public abstract class AEBaseGuiMixin implements IDWSGui {
}
