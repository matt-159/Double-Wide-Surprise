package com.github.matt159.dws.mixin.mixins.client.forestry;

import forestry.apiculture.gui.GuiAlveary;
import forestry.apiculture.gui.GuiAlvearyHygroregulator;
import forestry.apiculture.gui.GuiAlvearySieve;
import forestry.apiculture.gui.GuiAlvearySwarmer;
import forestry.apiculture.gui.GuiBeeHousing;
import forestry.apiculture.gui.GuiBeealyzer;
import forestry.apiculture.gui.GuiHabitatLocator;
import forestry.apiculture.gui.GuiImprinter;
import forestry.arboriculture.gui.GuiTreealyzer;
import forestry.core.circuits.GuiSolderingIron;
import forestry.core.gui.GuiAlyzer;
import forestry.core.gui.GuiAnalyzer;
import forestry.core.gui.GuiEscritoire;
import forestry.core.gui.GuiForestry;
import forestry.core.gui.GuiForestryTitled;
import forestry.core.gui.GuiNaturalistInventory;
import forestry.energy.gui.GuiEngine;
import forestry.energy.gui.GuiEngineBiogas;
import forestry.energy.gui.GuiEngineElectric;
import forestry.energy.gui.GuiEnginePeat;
import forestry.energy.gui.GuiGenerator;
import forestry.factory.gui.GuiBottler;
import forestry.factory.gui.GuiCarpenter;
import forestry.factory.gui.GuiCentrifuge;
import forestry.factory.gui.GuiFabricator;
import forestry.factory.gui.GuiFermenter;
import forestry.factory.gui.GuiMoistener;
import forestry.factory.gui.GuiRaintank;
import forestry.factory.gui.GuiSqueezer;
import forestry.factory.gui.GuiStill;
import forestry.factory.gui.GuiWorktable;
import forestry.farming.gui.GuiFarm;
import forestry.food.gui.GuiInfuser;
import forestry.lepidopterology.gui.GuiFlutterlyzer;
import forestry.mail.gui.GuiCatalogue;
import forestry.mail.gui.GuiLetter;
import forestry.mail.gui.GuiMailbox;
import forestry.mail.gui.GuiStampCollector;
import forestry.mail.gui.GuiTrader;
import forestry.storage.gui.GuiBackpack;
import forestry.storage.gui.GuiBackpackT2;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = { //region Apiculture
                 GuiAlveary.class,
                 GuiAlvearyHygroregulator.class,
                 GuiAlvearySieve.class,
                 GuiAlvearySwarmer.class,
                 GuiBeealyzer.class,
                 GuiBeeHousing.class,
                 GuiHabitatLocator.class,
                 GuiImprinter.class,
                 //endregion
                 //region Aboriculture
                 GuiTreealyzer.class,
                 //endregion
                 //region Core
                 GuiAlyzer.class,
                 GuiAnalyzer.class,
                 GuiEscritoire.class,
                 GuiForestry.class,
                 GuiForestryTitled.class,
                 GuiNaturalistInventory.class,
                 GuiSolderingIron.class,
                 //endregion
                 //region Energy
                 GuiEngine.class,
                 GuiEngineBiogas.class,
                 GuiEngineElectric.class,
                 GuiEnginePeat.class,
                 GuiGenerator.class,
                 //endregion})
                 //region Factory
                 GuiBottler.class,
                 GuiCarpenter.class,
                 GuiCentrifuge.class,
                 GuiFabricator.class,
                 GuiFermenter.class,
                 GuiMoistener.class,
                 GuiRaintank.class,
                 GuiSqueezer.class,
                 GuiStill.class,
                 GuiWorktable.class,
                 //endregion
                 //region Farming
                 GuiFarm.class,
                 //endregion
                 //region Food
                 GuiInfuser.class,
                 //endregion
                 //region Lepidopterology
                 GuiFlutterlyzer.class,
                 //endregion
                 //region Mail
                 GuiCatalogue.class,
                 GuiStampCollector.class,
                 GuiLetter.class,
                 GuiMailbox.class,
                 GuiTrader.class,
                 //endregion
                 //region Storage
                 GuiBackpack.class,
                 GuiBackpackT2.class
                 //endregion
               },
       remap = false) //this is required because of srg mapping resolution logic in UniMixins' mixin compiler for multitarget injects
public abstract class ForestryGuiMixins extends GuiContainer {
    public ForestryGuiMixins(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"))
    private void injectNewXSize(CallbackInfo ci) {
        this.xSize = 338;
    }
}
