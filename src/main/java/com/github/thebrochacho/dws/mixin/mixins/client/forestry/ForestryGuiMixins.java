package com.github.thebrochacho.dws.mixin.mixins.client.forestry;

import forestry.apiculture.gui.*;
import forestry.arboriculture.gui.GuiTreealyzer;
import forestry.core.circuits.GuiSolderingIron;
import forestry.core.gui.*;
import forestry.energy.gui.*;
import forestry.factory.gui.*;
import forestry.farming.gui.GuiFarm;
import forestry.food.gui.GuiInfuser;
import forestry.lepidopterology.gui.GuiFlutterlyzer;
import forestry.mail.gui.*;
import forestry.storage.gui.GuiBackpack;
import forestry.storage.gui.GuiBackpackT2;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
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
               })
public abstract class ForestryGuiMixins extends GuiContainer {
    public ForestryGuiMixins(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void injectNewXSize(CallbackInfo ci) {
        this.xSize = 338;
    }
}
