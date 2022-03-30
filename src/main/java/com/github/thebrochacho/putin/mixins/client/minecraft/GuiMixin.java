package com.github.thebrochacho.putin.mixins.client.minecraft;

import com.github.thebrochacho.putin.interfaces.minecraft.IGuiMixin;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Gui.class)
public class GuiMixin implements IGuiMixin {
    @Shadow protected float zLevel;

    @Override
    public float getZLevel() {
        return zLevel;
    }
}
