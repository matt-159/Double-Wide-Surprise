package com.github.matt159.dws.util;

import appeng.client.gui.implementations.GuiGrinder;
import appeng.client.gui.implementations.GuiInscriber;
import forestry.factory.gui.*;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiFurnace;

import java.util.HashSet;

public final class ListOfGUIsWithTransferRects {
    private static final HashSet<Class<? extends GuiContainer>> set = new HashSet<>();

    public static boolean contains(Class<? extends GuiContainer> clazz) {
        return set.contains(clazz);
    }

    public static boolean addClassToList(Class<? extends GuiContainer> clazz) {
        return set.add(clazz);
    }

    static {
        //region Minecraft
        addClassToList(GuiCrafting.class);
        addClassToList(GuiBrewingStand.class);
        addClassToList(GuiFurnace.class);
        //endregion
        //region AE2
        addClassToList(GuiGrinder.class);
        addClassToList(GuiInscriber.class);
        //endregion
        //region Forestry
        addClassToList(GuiBottler.class);
        addClassToList(GuiCarpenter.class);
        addClassToList(GuiCentrifuge.class);
        addClassToList(GuiFabricator.class);
        addClassToList(GuiFermenter.class);
        addClassToList(GuiMoistener.class);
        addClassToList(GuiSqueezer.class);
        addClassToList(GuiStill.class);
        //endregion
        //region Gregtech
        //Gregtech's NEI Handler overrides handleToolTip in TemplateRecipeHandler so a list of classes isn't needed here
        //endregion
    }
}
