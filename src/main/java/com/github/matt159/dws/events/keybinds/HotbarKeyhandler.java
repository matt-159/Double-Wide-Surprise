package com.github.matt159.dws.events.keybinds;

import com.github.matt159.dws.config.DWSConfig;
import lombok.RequiredArgsConstructor;
import lombok.val;

import net.minecraft.client.Minecraft;

public class HotbarKeyhandler extends KeyHandler {
    public HotbarKeyhandler(String description, int keyCode, String category, int index) {
        super(description, keyCode, category, new HotbarKeyhandlerCallback(index));
    }

    @RequiredArgsConstructor
    private static class HotbarKeyhandlerCallback implements KeyHandler.CallBack {
        private final int slotNumber;
        private int doubleTapTimer = 0;

        @Override
        public void onPress() {
            val player = Minecraft.getMinecraft().thePlayer;

            if (this.doubleTapTimer == 0) {
                this.doubleTapTimer = DWSConfig.General.hotbarTimerLength;

                player.inventory.currentItem = this.slotNumber;
            } else {
                this.doubleTapTimer = 0;

                player.inventory.currentItem = this.slotNumber + 9;
            }
        }

        @Override
        public void tick() {
            this.doubleTapTimer = Math.max(0, this.doubleTapTimer - 1);
        }
    }
}
