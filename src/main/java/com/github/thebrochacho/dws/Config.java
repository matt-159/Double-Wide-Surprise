package com.github.thebrochacho.dws;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class Config {

    public static boolean isBaublesLoaded;
    public static boolean isTravellersGearLoaded;
    public static boolean isTinkersLoaded;
    public static boolean isGalacticraftLoaded;

    private static class Defaults {
        public static final String greeting = "Hello World";
    }

    private static class Categories {
        public static final String general = "general";
    }
    
    public static String greeting = Defaults.greeting;

    public static void syncronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);
        configuration.load();
        
        Property greetingProperty = configuration.get(Categories.general, "greeting", Defaults.greeting, "How shall I greet?");
        greeting = greetingProperty.getString();

        if(configuration.hasChanged()) {
            configuration.save();
        }
    }
}