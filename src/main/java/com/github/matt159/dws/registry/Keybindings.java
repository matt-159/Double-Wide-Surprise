package com.github.matt159.dws.registry;

import com.github.matt159.dws.events.keybinds.KeyHandler;
import com.github.matt159.dws.events.keybinds.ModifierKey;
import lombok.experimental.UtilityClass;

import cpw.mods.fml.client.registry.ClientRegistry;

@UtilityClass
public class Keybindings {
    public static KeyHandler ModifierKey;

    public static void register() {
        ClientRegistry.registerKeyBinding(ModifierKey = new ModifierKey());
    }
}
