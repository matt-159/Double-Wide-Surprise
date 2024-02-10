package com.github.matt159.dws.events.keybinds;

import net.minecraft.client.settings.KeyBinding;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public abstract class KeyHandler extends KeyBinding {
    protected final CallBack callBack;

    public KeyHandler(String description, int keyCode, String category, CallBack callback) {
        super(description, keyCode, category);
        this.callBack = callback;
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (this.isPressed()) {
            callBack.onPress();
        }
    }

    @Override
    public boolean isPressed() {
        this.callBack.tick();

        return super.isPressed();
    }

    public interface CallBack {
        void onPress();

        void tick();
    }
}
