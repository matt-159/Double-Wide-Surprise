package com.github.matt159.dws.mixin.mixins.client.minecraft;

import com.github.matt159.dws.events.keybinds.HotbarKeyhandler;
import lombok.val;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

@Mixin(GameSettings.class)
public abstract class GameSettingsMixin {
    @Redirect(method = { "<init>()V",
                         "<init>(Lnet/minecraft/client/Minecraft;Ljava/io/File;)V" },
              at = @At(value = "FIELD",
                       target = "Lnet/minecraft/client/settings/GameSettings;keyBindsHotbar:[Lnet/minecraft/client/settings/KeyBinding;",
                       opcode = Opcodes.GETFIELD),
              require = 2)
    private KeyBinding[] redirectHotbarKeybind(GameSettings instance) {
        val hotbarKeybinds = instance.keyBindsHotbar;
        val hotbarKeyhandlers = new KeyBinding[hotbarKeybinds.length];

        for (int index = 0; index < hotbarKeybinds.length; index++) {
            val hotbarKeybind = hotbarKeybinds[index];

            val hotbarKeyhandler = new HotbarKeyhandler(hotbarKeybind.getKeyDescription(),
                                                        hotbarKeybind.getKeyCode(),
                                                        hotbarKeybind.getKeyCategory(),
                                                        index);

            hotbarKeyhandlers[index] = hotbarKeyhandler;
        }

        instance.keyBindsHotbar = hotbarKeyhandlers;

        return instance.keyBindsHotbar;
    }
}
