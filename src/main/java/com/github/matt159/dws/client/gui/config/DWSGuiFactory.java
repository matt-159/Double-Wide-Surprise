package com.github.matt159.dws.client.gui.config;

import com.falsepattern.lib.config.SimpleGuiFactory;

import net.minecraft.client.gui.GuiScreen;

public class DWSGuiFactory implements SimpleGuiFactory {
    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return DWSGuiConfig.class;
    }
}
