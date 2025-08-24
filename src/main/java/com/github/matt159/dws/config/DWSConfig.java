package com.github.matt159.dws.config;

import com.falsepattern.lib.config.Config;
import com.falsepattern.lib.config.ConfigurationManager;
import com.github.matt159.dws.Tags;
import lombok.val;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DWSConfig {
    public static Map<String, Long> SLOT_GROUPINGS;

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

    @Config(modid = Tags.MOD_ID,
            category = "slots")
    public static class Slots {
        @Config.DefaultBoolean(value = false)
        @Config.Comment("Ignore modded accessory slots and use Mix 'n' Match bauble slots instead")
        @Config.LangKey("dws.config.slotOverride.name")
        public static boolean slotOverride;

        @Config.ListMaxLength(20)
        @Config.DefaultStringList({"amulet", "ring", "ring", "belt"})
        @Config.Comment({"Item groupings for Mix 'n' Match slots",
                         "Frequency of values determines how many of corresponding bauble type can be worn",
                         "\"amulet\", \"ring\", \"ring\", \"belt\" -> 1 amulet, 2 rings, 1 belt"})
        @Config.LangKey("dws.config.slotGroupings.name")
        public static String[] slotGroupings;

        static {
            ConfigurationManager.selfInit();
        }

        public static void poke() {

        }
    }

    public static void poke() {
        General.poke();
        Slots.poke();

        DWSConfig.SLOT_GROUPINGS = Arrays.stream(DWSConfig.Slots.slotGroupings)
                                         .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));;
    }
}