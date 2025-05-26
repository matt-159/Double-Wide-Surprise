package com.github.matt159.dws.events.keybinds;

import com.github.matt159.dws.Tags;
import org.lwjgl.input.Keyboard;

import net.minecraft.util.StatCollector;

public class ModifierKey extends KeyHandler {
    private static final String UNLOCALIZED_NAME = "dws.keybind.modifier.name";

    public ModifierKey() {
        super(StatCollector.translateToLocal(UNLOCALIZED_NAME),
              Keyboard.KEY_LCONTROL,
              Tags.MOD_NAME,
              new ModifierCallback());
    }

    private static class ModifierCallback implements KeyHandler.CallBack {
        @Override
        public void onPress() {

        }
    }
}
