package com.github.matt159.dws.util;

import com.github.matt159.dws.Tags;
import net.minecraft.util.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

public final class TextureWhitelist {
    private static final Set<String> whitelist = new HashSet<>();

    public static boolean isBadContext = false;
    public static boolean useDoubleWideTexture = false;

    private TextureWhitelist() {}

    public static boolean addTextureToWhitelist(ResourceLocation resourceLocation) {
        return addTextureToWhitelist(resourceLocation.toString());
    }

    public static boolean addTextureToWhitelist(String path) {
        return whitelist.add(path);
    }

    public static ResourceLocation checkResourceLocation(ResourceLocation rl) {
        useDoubleWideTexture = checkTextureWhitelist(rl) && !isBadContext;

        //transforming from:    modid:textures/blahblahblah
        //to:                   dws:textures/modid/blahblahblah
        if (useDoubleWideTexture) {
            rl = new ResourceLocation(Tags.MODID,
                    rl.getResourcePath().substring(0, 9) + rl.getResourceDomain() + rl.getResourcePath().substring(8));
        }

        return rl;
    }

    private static boolean checkTextureWhitelist(ResourceLocation resourceLocation) {
        String texturePath = resourceLocation.toString();
        return whitelist.contains(texturePath);
    }

    public static void useDoubleWideTexture(boolean value) {
        useDoubleWideTexture = value;
    }

    static {
        // region Minecraft Vanilla Textures
        addTextureToWhitelist("minecraft:textures/gui/container/anvil.png");
        addTextureToWhitelist("minecraft:textures/gui/container/beacon.png");
        addTextureToWhitelist("minecraft:textures/gui/container/brewing_stand.png");
        addTextureToWhitelist("minecraft:textures/gui/container/crafting_table.png");
        addTextureToWhitelist("minecraft:textures/gui/container/dispenser.png");
        addTextureToWhitelist("minecraft:textures/gui/container/enchanting_table.png");
        addTextureToWhitelist("minecraft:textures/gui/container/furnace.png");
        addTextureToWhitelist("minecraft:textures/gui/container/generic_54.png");
        addTextureToWhitelist("minecraft:textures/gui/container/hopper.png");
        addTextureToWhitelist("minecraft:textures/gui/container/horse.png");
        addTextureToWhitelist("minecraft:textures/gui/container/inventory.png");
        addTextureToWhitelist("minecraft:textures/gui/container/villager.png");

        addTextureToWhitelist("minecraft:textures/gui/widgets.png");

        addTextureToWhitelist("minecraft:textures/gui/container/creative_inventory/tab_inventory.png");
        addTextureToWhitelist("minecraft:textures/gui/container/creative_inventory/tab_item_search.png");
        addTextureToWhitelist("minecraft:textures/gui/container/creative_inventory/tab_items.png");
        addTextureToWhitelist("minecraft:textures/gui/container/creative_inventory/tabs.png");
        //endregion

        // region NEI Textures
        addTextureToWhitelist("nei:textures/gui/inv.png");
        //endregion

        // region Ironchest Textures
        addTextureToWhitelist("ironchest:textures/gui/coppercontainer.png");
        addTextureToWhitelist("ironchest:textures/gui/diamondcontainer.png");
        addTextureToWhitelist("ironchest:textures/gui/dirtcontainer.png");
        addTextureToWhitelist("ironchest:textures/gui/goldcontainer.png");
        addTextureToWhitelist("ironchest:textures/gui/ironcontainer.png");
        addTextureToWhitelist("ironchest:textures/gui/silvercontainer.png");
        addTextureToWhitelist("ironchest:textures/gui/steel_chest.png");
        //endregion

        //region Gregtech Textures
        addTextureToWhitelist("gregtech:textures/gui/1by1.png");
        addTextureToWhitelist("gregtech:textures/gui/2by2.png");
        addTextureToWhitelist("gregtech:textures/gui/3by3.png");
        addTextureToWhitelist("gregtech:textures/gui/4by4.png");
        addTextureToWhitelist("gregtech:textures/gui/BasicTank.png");
        addTextureToWhitelist("gregtech:textures/gui/BrickedBlastFurnace.png");
        addTextureToWhitelist("gregtech:textures/gui/BronzeBlastFurnace.png");
        addTextureToWhitelist("gregtech:textures/gui/BronzeBoiler.png");
        addTextureToWhitelist("gregtech:textures/gui/ChestBuffer.png");
        addTextureToWhitelist("gregtech:textures/gui/CropHarvester.png");
        addTextureToWhitelist("gregtech:textures/gui/DataAccess2by2.png");
        addTextureToWhitelist("gregtech:textures/gui/DataAccess4by4.png");
        addTextureToWhitelist("gregtech:textures/gui/ElectricBufferSmall.png");
        addTextureToWhitelist("gregtech:textures/gui/ElectricItemCleaner.png");
        addTextureToWhitelist("gregtech:textures/gui/Filter.png");
        addTextureToWhitelist("gregtech:textures/gui/Implosion.png");
        addTextureToWhitelist("gregtech:textures/gui/InventoryManager.png");
        addTextureToWhitelist("gregtech:textures/gui/ItemDistributor.png");
        addTextureToWhitelist("gregtech:textures/gui/MagicAbsorber.png");
        addTextureToWhitelist("gregtech:textures/gui/Maintenance.png");
        addTextureToWhitelist("gregtech:textures/gui/OutputHatch.png");
        addTextureToWhitelist("gregtech:textures/gui/RedstoneCircuitBlock.png");
        addTextureToWhitelist("gregtech:textures/gui/Regulator.png");
        addTextureToWhitelist("gregtech:textures/gui/Safe.png");
        addTextureToWhitelist("gregtech:textures/gui/SolarBoiler.png");
        addTextureToWhitelist("gregtech:textures/gui/SolarHPBoiler.png");
        addTextureToWhitelist("gregtech:textures/gui/SteelBoiler.png");
        addTextureToWhitelist("gregtech:textures/gui/SuperBuffer.png");
        addTextureToWhitelist("gregtech:textures/gui/Teleporter.png");
        addTextureToWhitelist("gregtech:textures/gui/Tradeomat_Inventory.png");
        addTextureToWhitelist("gregtech:textures/gui/Tradeomat_Main.png");
        addTextureToWhitelist("gregtech:textures/gui/Trademat_Settings.png");
        addTextureToWhitelist("gregtech:textures/gui/TypeFilter.png");

        addTextureToWhitelist("gregtech:textures/gui/basicmachines/AlloySmelter.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Amplifabricator.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/ArcFurnace.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Assembler.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Assembler2.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Autoclave.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Autoclave3.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Autoclave4.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Bender.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/BronzeAlloySmelter.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/BronzeCompressor.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/BronzeExtractor.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/BronzeFurnace.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/BronzeHammer.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/BronzeMacerator.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Canner.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Centrifuge.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/ChemicalBath.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/ChemicalReactor.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/CircuitAssembler.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Compressor.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Cutter.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Cutter2.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Default.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Disassembler.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/DistillationTower.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Distillery.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/E_Furnace.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/E_Oven.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Electrolyzer.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/ElectromagneticSeparator.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Extractor.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Extruder.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Fermenter.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/FluidCanner.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/FluidCannerNEI.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/FluidExtractor.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/FluidHeater.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/FluidSolidifier.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/FusionReactor.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Hammer.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/LaserEngraver.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Lathe.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Macerator1.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Macerator2.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Macerator3.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Macerator4.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/MassFabricator.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Miner.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Mixer.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Mixer2.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Mixer4.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Mixer6.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/NineXNine.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/OilCracker.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/OreWasher.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Packager.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/PlasmaArcFurnace.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Polarizer.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/PotionBrewer.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Press.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Printer.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Recycler.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Replicator.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/RockBreaker.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Scanner.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Sifter.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Slicer.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/SteelAlloySmelter.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/SteelCompressor.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/SteelExtractor.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/SteelFurnace.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/SteelHammer.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/SteelMacerator.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/ThermalCentrifuge.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Unpackager.png");
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Wiremill.png");

        addTextureToWhitelist("gregtech:textures/gui/multimachines/Advanced_Miner2.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/AssemblyLine.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/DistillationTower.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/DrillingRig.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/ElectricBlastFurnace.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/ImplosionCompressor.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/FusionComputer.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/LargeBoiler.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/LargeChemicalReactor.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/LargeDieselEngine.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/LargeExtremeDieselEngine.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/LargeHeatExchanger.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/LargeTurbine.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/MultiblockDisplay.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/MultiFurnace.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/OilCrackingUnit.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/OreDrillingPlant.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/ProcessingArray.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/PyrolyseOven.png");
        addTextureToWhitelist("gregtech:textures/gui/multimachines/VacuumFreezer.png");
        //endregion

        // region Applied Energistics 2 Textures
        addTextureToWhitelist("appliedenergistics2:textures/guis/bus.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/cellworkbench.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/chest.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/condenser.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/craftAmt.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/crafting.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/drive.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/grinder.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/inscriber.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/interface.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/interface2.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/interface3.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/interface4.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/interfaceterminal.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/ioport.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/lvlemitter.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/mac.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/pattern.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/pattern2.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/pattern3.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/pattern4.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/priority.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/quartzknife.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/security.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/skychest.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/spatialio.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/storagebus.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/terminal.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/toolbox.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/vibchamber.png");
        addTextureToWhitelist("appliedenergistics2:textures/guis/wireless.png");
        //endregion

        //region Forestry Textures
        addTextureToWhitelist("forestry:textures/gui/alveary.png");
        addTextureToWhitelist("forestry:textures/gui/alyzer.png");
        addTextureToWhitelist("forestry:textures/gui/analyzer.png");
        addTextureToWhitelist("forestry:textures/gui/apiaristinventory.png");
        addTextureToWhitelist("forestry:textures/gui/apiary.png");
        addTextureToWhitelist("forestry:textures/gui/backpack.png");
        addTextureToWhitelist("forestry:textures/gui/backpackT2.png");
        addTextureToWhitelist("forestry:textures/gui/beealyzer2.png");
        addTextureToWhitelist("forestry:textures/gui/bioengine.png");
        addTextureToWhitelist("forestry:textures/gui/biomefinder.png");
        addTextureToWhitelist("forestry:textures/gui/bottler.png");
        addTextureToWhitelist("forestry:textures/gui/carpenter.png");
        addTextureToWhitelist("forestry:textures/gui/centrifugesocket.png");
        addTextureToWhitelist("forestry:textures/gui/electricalengine.png");
        addTextureToWhitelist("forestry:textures/gui/escritoire.png");
        addTextureToWhitelist("forestry:textures/gui/fabricator.png");
        addTextureToWhitelist("forestry:textures/gui/fermenter.png");
        addTextureToWhitelist("forestry:textures/gui/generator.png");
        addTextureToWhitelist("forestry:textures/gui/hygroregulator.png");
        addTextureToWhitelist("forestry:textures/gui/imprinter.png");
        addTextureToWhitelist("forestry:textures/gui/infuser.png");
        addTextureToWhitelist("forestry:textures/gui/letter.png");
        addTextureToWhitelist("forestry:textures/gui/mailbox.png");
        addTextureToWhitelist("forestry:textures/gui/mailtrader2.png");
        addTextureToWhitelist("forestry:textures/gui/mfarm.png");
        addTextureToWhitelist("forestry:textures/gui/moistener.png");
        addTextureToWhitelist("forestry:textures/gui/peatengine.png");
        addTextureToWhitelist("forestry:textures/gui/philatelist.png");
        addTextureToWhitelist("forestry:textures/gui/raintank.png");
        addTextureToWhitelist("forestry:textures/gui/sieve.png");
        addTextureToWhitelist("forestry:textures/gui/solder.png");
        addTextureToWhitelist("forestry:textures/gui/squeezersocket.png");
        addTextureToWhitelist("forestry:textures/gui/swarmer.png");
        addTextureToWhitelist("forestry:textures/gui/still.png");
        addTextureToWhitelist("forestry:textures/gui/worktable2.png");
        //endregion

        //region Storage Drawers
        addTextureToWhitelist("storagedrawers:textures/gui/drawers_1.png");
        addTextureToWhitelist("storagedrawers:textures/gui/drawers_2.png");
        addTextureToWhitelist("storagedrawers:textures/gui/drawers_4.png");
        addTextureToWhitelist("storagedrawers:textures/gui/drawers_comp.png");
        addTextureToWhitelist("storagedrawers:textures/gui/framing.png");
        //endregion

        //region Chisel
        addTextureToWhitelist("chisel:textures/autochisel-gui.png");
        addTextureToWhitelist("chisel:textures/chisel2Gui.png");
        addTextureToWhitelist("chisel:textures/chisel2GuiAlt.png");
        addTextureToWhitelist("chisel:textures/chisel2GuiScroll.png");
        addTextureToWhitelist("chisel:textures/chisel-gui.png");
        addTextureToWhitelist("chisel:textures/chisel-gui-24.png");
        //endregion

        //region GardenStuff
        addTextureToWhitelist("gardencore:textures/gui/compostBin.png");
        //endregion

        //region Agricraft
        addTextureToWhitelist("agricraft:textures/gui/GuiSeedAnalyzer.png");
        //endregion

        //region Mariculture
        addTextureToWhitelist("mariculture:textures/gui/liquifier.png");
        //endregion

        //region Thaumcraft
        addTextureToWhitelist("thaumcraft:textures/gui/gui_alchemyfurnace.png");
        addTextureToWhitelist("thaumcraft:textures/gui/gui_arcaneworkbench.png");
        addTextureToWhitelist("thaumcraft:textures/gui/guiresearchtable2.png");
        //endregion

        //region Industrialcraft 2
        addTextureToWhitelist("ic2:textures/gui/GUIFurnace.png");
        //endregion

        //region Railcraft
        addTextureToWhitelist("railcraft:textures/gui/gui_boiler_liquid.png");
        addTextureToWhitelist("railcraft:textures/gui/gui_boiler_solid.png");
        addTextureToWhitelist("railcraft:textures/gui/gui_coke_oven.png");
        addTextureToWhitelist("railcraft:textures/gui/gui_steam_oven.png");
        addTextureToWhitelist("railcraft:textures/gui/gui_tank_water.png");
        //endregion
    }
}
