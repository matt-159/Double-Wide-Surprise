package com.github.matt159.dws.config;

import com.falsepattern.lib.config.Config;
import com.falsepattern.lib.config.ConfigurationManager;
import com.github.matt159.dws.Tags;

public class DWSConfig {
    @Config(modid = Tags.MOD_ID)
    public static class General {
        @Config.Comment("Show debug slot number and index")
        @Config.DefaultBoolean(false)
        @Config.LangKey("dws.config.showSlotInfo.name")
        public static boolean showDebugSlotInfo;

        @Config.Comment("Timer measured in milliseconds")
        @Config.DefaultInt(400)
        @Config.RangeInt(min = 50, max = 1000)
        @Config.LangKey("dws.config.hotbarTimer.name")
        public static int hotbarTimerLength;

        static {
            ConfigurationManager.selfInit();
        }

        public static void poke() {

        }
    }

    public static void poke() {
        General.poke();
    }
}