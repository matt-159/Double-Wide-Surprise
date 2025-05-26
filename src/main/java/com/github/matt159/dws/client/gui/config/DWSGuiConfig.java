package com.github.matt159.dws.client.gui.config;

import com.falsepattern.lib.config.ConfigException;
import com.falsepattern.lib.config.SimpleGuiConfig;
import com.github.matt159.dws.Tags;
import com.github.matt159.dws.config.DWSConfig;

import net.minecraft.client.gui.GuiScreen;

public class DWSGuiConfig extends SimpleGuiConfig {
    public DWSGuiConfig(GuiScreen parent) throws ConfigException {
        super(parent, Tags.MOD_ID, Tags.MOD_NAME, DWSConfig.General.class);
    }
}
