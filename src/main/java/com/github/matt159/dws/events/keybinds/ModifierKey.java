package com.github.matt159.dws.events.keybinds;

import com.github.matt159.dws.Tags;
import org.lwjgl.input.Keyboard;

import net.minecraft.util.StatCollector;

public class ModifierKey extends KeyHandler {
    private static final String UNLOCALIZED_NAME = "keybind." + Tags.MODID + ".modifier";

    public ModifierKey() {
        super(StatCollector.translateToLocal(UNLOCALIZED_NAME + ".name"),
              Keyboard.KEY_LCONTROL,
              Tags.MODNAME,
              new ModifierCallback());
    }

    private static class ModifierCallback implements KeyHandler.CallBack {
        @Override
        public void onPress() {

        }
    }
}
