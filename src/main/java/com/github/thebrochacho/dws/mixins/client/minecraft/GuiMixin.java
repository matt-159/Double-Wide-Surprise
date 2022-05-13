package com.github.thebrochacho.dws.mixins.client.minecraft;

import com.github.thebrochacho.dws.interfaces.minecraft.IGuiMixin;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Gui.class)
public class GuiMixin implements IGuiMixin {
    @Shadow(aliases = {"field_73735_i"})
    protected float zLevel;

    @Override
    public float getZLevel() {
        return zLevel;
    }
}
