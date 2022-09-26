package com.github.thebrochacho.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.AEBaseGui;
import com.github.thebrochacho.dws.interfaces.IDWSGui;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AEBaseGui.class)
public abstract class AEBaseGuiMixin implements IDWSGui {
}
