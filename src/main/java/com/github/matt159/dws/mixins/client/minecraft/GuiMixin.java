package com.github.matt159.dws.mixins.client.minecraft;

import com.github.matt159.dws.interfaces.minecraft.IGuiMixin;
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
