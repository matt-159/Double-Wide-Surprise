package com.github.matt159.dws.mixins.plugin;

import com.google.common.io.Files;

import java.nio.file.Path;

public enum TargetedMod {

    VANILLA("Minecraft", "", "", true),
    GALACTICRAFT("Galacticraft", "", "Galacticraft", true),
    TRAVELLERSGEAR("TravellersGear", "", "Traveller", true),
    IRONCHEST("IronChest", "", "ironchest", true),
    BAUBLES("Baubles", "", "Baubles", true),
    TINKERS("TConstruct", "", "construct", true),
    CODECHICKENLIB("CodeChickenLib", "", "CodeChicken", true),
    GREGTECH("GregTech", "", "gt5u", true),
    ;

    public final String modName;
    public final String jarNamePrefixLowercase;
    public final String jarNameContainsLowercase;

    public final boolean loadInDevelopment;

    TargetedMod(String modName, String jarNamePrefix, String jarNameContains, boolean loadInDevelopment) {
        this.modName = modName;
        this.jarNamePrefixLowercase = jarNamePrefix.toLowerCase();
        this.jarNameContainsLowercase = jarNameContains.toLowerCase();
        this.loadInDevelopment = loadInDevelopment;
    }

    @SuppressWarnings("UnstableApiUsage")
    public boolean isMatchingJar(Path path) {
        final String pathString = path.toString();
        final String nameLowerCase = Files.getNameWithoutExtension(pathString).toLowerCase();
        final String fileExtension = Files.getFileExtension(pathString);

        return "jar".equals(fileExtension) && nameLowerCase.startsWith(jarNamePrefixLowercase) && nameLowerCase.contains(jarNameContainsLowercase);
    }

    @Override
    public String toString() {
        return "TargetedMod{" +
                "modName='" + modName + '\'' +
                ", jarNamePrefixLowercase='" + jarNamePrefixLowercase + '\'' +
                '}';
    }
}
