package com.github.matt159.dws;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class Config {

    private static class Defaults {
        public static final String greeting = "Hello World";

        public static final boolean showDebugSlotInfo = false;
    }

    private static class Categories {
        public static final String general = "general";
    }
    
    public static String greeting = Defaults.greeting;

    public static boolean showDebugSlotInfo = Defaults.showDebugSlotInfo;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);
        configuration.load();
        
        Property greetingProperty = configuration.get(Categories.general,
                                                      "greeting",
                                                      Defaults.greeting,
                                                      "How shall I greet?");
        greeting = greetingProperty.getString();

        Property showDebugSlotInfoProperty = configuration.get(Categories.general,
                                                               "showDebugSlotInfo",
                                                               Defaults.showDebugSlotInfo,
                                                               "Render Slot Number and Slot Index inside each slot.");
        showDebugSlotInfo = showDebugSlotInfoProperty.getBoolean();

        if(configuration.hasChanged()) {
            configuration.save();
        }
    }
}