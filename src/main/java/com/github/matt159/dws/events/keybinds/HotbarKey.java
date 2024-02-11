package com.github.matt159.dws.events.keybinds;

import com.github.matt159.dws.config.DWSConfig;
import lombok.RequiredArgsConstructor;
import lombok.val;

import net.minecraft.client.Minecraft;

public class HotbarKey extends KeyHandler {
    public HotbarKey(String description, int keyCode, String category, int index) {
        super(description, keyCode, category, new HotbarKeyhandlerCallback(index));
    }

    @RequiredArgsConstructor
    private static class HotbarKeyhandlerCallback implements KeyHandler.CallBack {
        private final int slotNumber;
        private long timeOfLastPress = 0;

        @Override
        public void onPress() {
            val player = Minecraft.getMinecraft().thePlayer;

            val now = System.nanoTime();
            val threshold = DWSConfig.General.hotbarTimerLength * 1_000_000;

            if (now - this.timeOfLastPress <= threshold) {
                player.inventory.currentItem = this.slotNumber + 9;

                this.timeOfLastPress = 0;
            } else {
                player.inventory.currentItem = this.slotNumber;

                this.timeOfLastPress = now;
            }
        }
    }
}
