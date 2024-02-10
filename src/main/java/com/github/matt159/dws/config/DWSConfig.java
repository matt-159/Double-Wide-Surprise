package com.github.matt159.dws.config;

import com.falsepattern.lib.config.Config;
import com.falsepattern.lib.config.ConfigurationManager;
import com.github.matt159.dws.Tags;

public class DWSConfig {
    @Config(modid = Tags.MODID)
    public static class General {
        @Config.Comment("Show debug slot number and index")
        @Config.DefaultBoolean(false)
        @Config.LangKey("dws.config.showSlotInfo.name")
        public static boolean showDebugSlotInfo;

        @Config.Comment("Timer measured in game ticks")
        @Config.DefaultInt(4)
        @Config.RangeInt(min = 1, max = 10)
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