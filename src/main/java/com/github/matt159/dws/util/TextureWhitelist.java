package com.github.matt159.dws.util;

import com.github.matt159.dws.Tags;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class TextureWhitelist {
    private static final Set<String> whitelist = new HashSet<>();

    public static boolean useOversizedTexture = false;

    private TextureWhitelist() {}

    public static boolean addTextureToWhitelist(ResourceLocation resourceLocation) {
        return addTextureToWhitelist(resourceLocation.toString());
    }

    public static boolean addTextureToWhitelist(String path) {
        return whitelist.add(path);
    }

    public static ResourceLocation checkResourceLocation(ResourceLocation rl) {
        boolean isNEIRendering = Arrays.stream(Thread.currentThread().getStackTrace())
                .map(StackTraceElement::toString)
                .anyMatch(string -> string.contains("codechicken"));

        if (!isNEIRendering) {
            useOversizedTexture = checkTextureWhitelist(rl);

            if (useOversizedTexture) {
                //transforming from:    modid:textures/blahblahblah
                //to:                   dws:textures/modid/blahblahblah
                rl = new ResourceLocation(Tags.MODID,
                        rl.getResourcePath().substring(0, 9) + rl.getResourceDomain() + rl.getResourcePath().substring(8));
            }
        }

        return rl;
    }

    private static boolean checkTextureWhitelist(ResourceLocation resourceLocation) {
        String texturePath = resourceLocation.toString();
        return whitelist.stream().anyMatch(t -> t.equalsIgnoreCase(texturePath));
    }

    public static void useOversizedTexture(boolean value) {
        useOversizedTexture = value;
    }

    static {
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
        addTextureToWhitelist("minecraft:textures/gui/container/villager.png");

        addTextureToWhitelist("minecraft:textures/gui/container/creative_inventory/tab_inventory.png");
        addTextureToWhitelist("minecraft:textures/gui/container/creative_inventory/tab_item_search.png");
        addTextureToWhitelist("minecraft:textures/gui/container/creative_inventory/tab_items.png");
        addTextureToWhitelist("minecraft:textures/gui/container/creative_inventory/tabs.png");

        addTextureToWhitelist("ironchest:textures/ironchest/copper_chest.png");
        addTextureToWhitelist("ironchest:textures/ironchest/diamond_chest.png");
        addTextureToWhitelist("ironchest:textures/ironchest/dirt_chest.png");
        addTextureToWhitelist("ironchest:textures/ironchest/gold_chest.png");
        addTextureToWhitelist("ironchest:textures/ironchest/iron_chest.png");
        addTextureToWhitelist("ironchest:textures/ironchest/silver_chest.png");
        addTextureToWhitelist("ironchest:textures/ironchest/steel_chest.png");

        addTextureToWhitelist("gregtech:textures/gui/1by1.png");
        addTextureToWhitelist("gregtech:textures/gui/2by2.png");
        addTextureToWhitelist("gregtech:textures/gui/3by3.png");
        addTextureToWhitelist("gregtech:textures/gui/4by4.png");
        addTextureToWhitelist("gregtech:textures/gui/BasicTank.png");
        addTextureToWhitelist("gregtech:textures/gui/BrickedBlastFurnace.png");
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
        addTextureToWhitelist("gregtech:textures/gui/basicmachines/Recycle.png");
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
    }
}
