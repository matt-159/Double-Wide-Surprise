package com.github.matt159.dws.mixin.plugin;

import com.falsepattern.lib.mixin.IMixin;
import com.falsepattern.lib.mixin.ITargetedMod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Predicate;

import static com.falsepattern.lib.mixin.IMixin.PredicateHelpers.always;
import static com.falsepattern.lib.mixin.IMixin.PredicateHelpers.require;
import static com.falsepattern.lib.mixin.IMixin.Side.CLIENT;
import static com.falsepattern.lib.mixin.IMixin.Side.COMMON;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.*;

@Getter
@RequiredArgsConstructor
public enum Mixin implements IMixin {
    // @formatter:off

    // region Double Wide Surprise Slot Injections
    BaublesSlotSyncMixin                              (CLIENT, require(BAUBLES), "dws.baubles.GuiInventoryMixin"),
    BaublesSlotInjectMixin                            (COMMON, require(BAUBLES), "dws.baubles.ContainerPlayerMixin"),
    TinkersSlotInjectMixin                            (COMMON, require(TINKERSCONSTRUCT), "dws.tinkersconstruct.ContainerPlayerMixin"),
    TravellersGearSlotInjectMixin                     (COMMON, require(TRAVELLERSGEAR), "dws.travellersgear.ContainerPlayerMixin"),
    GalacticraftSlotInjectMixin                       (COMMON, require(GALACTICRAFT), "dws.galacticraft.ContainerPlayerMixin"),
    //endregion

    // region Vanilla Mixins
    CreativeTabsMixin                                 (CLIENT, always(), "minecraft.gui.CreativeTabsMixin"),
    EntityPlayerMixin                                 (COMMON, always(), "minecraft.EntityPlayerMixin"),
    ForgeHooksMixin                                   (COMMON, always(), "minecraft.ForgeHooksMixin"),
    InventoryPlayerMixin                              (COMMON, always(), "minecraft.inventory.InventoryPlayerMixin"),
    MinecraftMixin                                    (CLIENT, always(), "minecraft.MinecraftMixin"),
    NetHandlerPlayClientMixin                         (CLIENT, always(), "minecraft.NetHandlerPlayClientMixin"),
    NetHandlerPlayServerMixin                         (COMMON, always(), "minecraft.NetHandlerPlayServerMixin"),
    TextureManagerMixin                               (CLIENT, always(), "minecraft.TextureManagerMixin"),

    ContainerMixin                                    (COMMON, always(), "minecraft.inventory.ContainerMixin"),
    ContainerBeaconMixin                              (COMMON, always(), "minecraft.inventory.ContainerBeaconMixin"),
    ContainerBrewingStandMixin                        (COMMON, always(), "minecraft.inventory.ContainerBrewingStandMixin"),
    ContainerChestMixin                               (COMMON, always(), "minecraft.inventory.ContainerChestMixin"),
    ContainerCreativeMixin                            (CLIENT, always(), "minecraft.inventory.ContainerCreativeMixin"),
    ContainerDispenserMixin                           (COMMON, always(), "minecraft.inventory.ContainerDispenserMixin"),
    ContainerEnchantmentMixin                         (COMMON, always(), "minecraft.inventory.ContainerEnchantmentMixin"),
    ContainerFurnaceMixin                             (COMMON, always(), "minecraft.inventory.ContainerFurnaceMixin"),
    ContainerHopperMixin                              (COMMON, always(), "minecraft.inventory.ContainerHopperMixin"),
    ContainerHorseInventoryMixin                      (COMMON, always(), "minecraft.inventory.ContainerHorseInventoryMixin"),
    ContainerMerchantMixin                            (COMMON, always(), "minecraft.inventory.ContainerMerchantMixin"),
    ContainerPlayerMixin                              (COMMON, always(), "minecraft.inventory.ContainerPlayerMixin"),
    ContainerRepairMixin                              (COMMON, always(), "minecraft.inventory.ContainerRepairMixin"),
    ContainerWorkbenchMixin                           (COMMON, always(), "minecraft.inventory.ContainerWorkbenchMixin"),

    GuiContainerMixin                                 (CLIENT, always(), "minecraft.gui.GuiContainerMixin"),
    GuiContainerCreativeMixin                         (CLIENT, always(), "minecraft.gui.GuiContainerCreativeMixin"),
    GuiIngameForgeMixin                               (CLIENT, always(), "minecraft.gui.GuiIngameForgeMixin"),
    GuiMixin                                          (CLIENT, always(), "minecraft.gui.GuiMixin"),

    GuiBeaconMixin                                    (CLIENT, always(), "minecraft.gui.GuiBeaconMixin"),
    GuiBrewingStandMixin                              (CLIENT, always(), "minecraft.gui.GuiBrewingStandMixin"),
    GuiChestMixin                                     (CLIENT, always(), "minecraft.gui.GuiChestMixin"),
    GuiCraftingMixin                                  (CLIENT, always(), "minecraft.gui.GuiCraftingMixin"),
    GuiDispenserMixin                                 (CLIENT, always(), "minecraft.gui.GuiDispenserMixin"),
    GuiEnchantmentMixin                               (CLIENT, always(), "minecraft.gui.GuiEnchantmentMixin"),
    GuiHopperMixin                                    (CLIENT, always(), "minecraft.gui.GuiHopperMixin"),
    GuiFurnaceMixin                                   (CLIENT, always(), "minecraft.gui.GuiFurnaceMixin"),
    GuiInventoryMixin                                 (CLIENT, always(), "minecraft.gui.GuiInventoryMixin"),

    GuiMerchantMixin                                  (CLIENT, always(), "minecraft.gui.GuiMerchantMixin"),
    MerchantButtonMixin                               (CLIENT, always(), "minecraft.gui.MerchantButtonMixin"),

    GuiRepairMixin                                    (CLIENT, always(), "minecraft.gui.GuiRepairMixin"),
    GuiScreenHorseInventoryMixin                      (CLIENT, always(), "minecraft.gui.GuiScreenHorseInventoryMixin"),
    //endregion

    // region CodeChickenLib Mixins
    InventoryRangeMixin                               (COMMON, require(CODECHICKENLIB),"codechickenlib.InventoryRangeMixin"),
    //endregion

    //region NEI Mixins
    ClientUtilsMixin                                  (CLIENT, require(NOTENOUGHITEMS),"nei.NEIClientUtilsMixin"),
    ContainerCreativeInvMixin                         (COMMON, require(NOTENOUGHITEMS), "nei.ContainerCreativeInvMixin"),
    ExtendedCreativeInvMixin                          (COMMON, require(NOTENOUGHITEMS), "nei.ExtendedCreativeInvMixin"),
    GuiExtendedCreativeInvMixin                       (CLIENT, require(NOTENOUGHITEMS), "nei.GuiExtendedCreativeInvMixin"),
    LayoutManagerMixin                                (CLIENT, require(NOTENOUGHITEMS), "nei.LayoutManagerMixin"),
    NEIClientConfigMixin                              (CLIENT, require(NOTENOUGHITEMS), "nei.NEIClientConfigMixin"),
    NEIServerUtilsMixin                               (COMMON, require(NOTENOUGHITEMS), "nei.NEIServerUtilsMixin"),
    PlayerSaveMixin                                   (COMMON, require(NOTENOUGHITEMS), "nei.PlayerSaveMixin"),

    BrewingRecipeHandlerMixin                         (COMMON, require(NOTENOUGHITEMS), "nei.BrewingRecipeHandlerMixin"),
    FurnaceRecipeHandlerMixin                         (COMMON, require(NOTENOUGHITEMS), "nei.FurnaceRecipeHandlerMixin"),
    ShapedRecipeHandlerMixin                          (COMMON, require(NOTENOUGHITEMS), "nei.ShapedRecipeHandlerMixin"),
//    TemplateRecipeHandlerMixin                        (COMMON, require(NOTENOUGHITEMS), "nei.TemplateRecipeHandlerMixin"),
    //endregion

    // region Galacticraft Mixins
    IGalacticWearableMixin                            (COMMON, require(GALACTICRAFT), "galacticraft.GalacticWearableMixin"),
    ContainerExtendedInventoryMixin                   (COMMON, require(GALACTICRAFT), "galacticraft.ContainerExtendedInventoryMixin"),
    //endregion

    // region TravellersGear
    ClientProxyMixin                                  (CLIENT, require(TRAVELLERSGEAR), "travellersgear.ClientProxyMixin"),
    //endregion

    // region Baubles Mixins
    GuiEventsMixin                                    (CLIENT, require(BAUBLES), "baubles.GuiEventsMixin"),
    //endregion

    // region Ironchest Mixins
    ContainerIronChestMixin                           (COMMON, require(IRONCHEST), "ironchest.ContainerIronChestMixin"),
    GUIChestMixin                                     (CLIENT, require(IRONCHEST), "ironchest.GUIChestMixin"),
    //endregion

    // region Gregtech Mixins
    GT_ContainerMixin                                 (COMMON, require(GREGTECH), "gregtech.GT_ContainerMixin"),
    GT_Container_1by1Mixin                            (COMMON, require(GREGTECH), "gregtech.GT_Container_1by1Mixin"),
    GT_Container_2by2Mixin                            (COMMON, require(GREGTECH), "gregtech.GT_Container_2by2Mixin"),
    GT_Container_3by3Mixin                            (COMMON, require(GREGTECH), "gregtech.GT_Container_3by3Mixin"),
    GT_Container_4by4Mixin                            (COMMON, require(GREGTECH), "gregtech.GT_Container_4by4Mixin"),
    GT_Container_BasicMachineMixin                    (COMMON, require(GREGTECH), "gregtech.GT_Container_BasicMachineMixin"),
    GT_Container_BasicTankMixin                       (COMMON, require(GREGTECH), "gregtech.GT_Container_BasicTankMixin"),
    GT_Container_BoilerMixin                          (COMMON, require(GREGTECH), "gregtech.GT_Container_BoilerMixin"),
    GT_Container_ChestBufferMixin                     (COMMON, require(GREGTECH), "gregtech.GT_Container_ChestBufferMixin"),
    GT_Container_FilterMixin                          (COMMON, require(GREGTECH), "gregtech.GT_Container_FilterMixin"),
    GT_Container_ItemDistributorMixin                 (COMMON, require(GREGTECH), "gregtech.GT_Container_ItemDistributorMixin"),
    GT_Container_MaintenanceHatchMixin                (COMMON, require(GREGTECH), "gregtech.GT_Container_MaintenanceHatchMixin"),
    GT_Container_MicrowaveEnergyTransmitterMixin      (COMMON, require(GREGTECH), "gregtech.GT_Container_MicrowaveEnergyTransmitterMixin"),
    GT_Container_MultiMachineMixin                    (COMMON, require(GREGTECH), "gregtech.GT_Container_MultiMachineMixin"),
    GT_Container_OutputHatchMixin                     (COMMON, require(GREGTECH), "gregtech.GT_Container_OutputHatchMixin"),
    GT_Container_PrimitiveBlastFurnaceMixin           (COMMON, require(GREGTECH), "gregtech.GT_Container_PrimitiveBlastFurnaceMixin"),
    GT_Container_QuantumChestMixin                    (COMMON, require(GREGTECH), "gregtech.GT_Container_QuantumChestMixin"),
    GT_Container_RegulatorMixin                       (COMMON, require(GREGTECH), "gregtech.GT_Container_RegulatorMixin"),
    GT_Container_SuperBufferMixin                     (COMMON, require(GREGTECH), "gregtech.GT_Container_SuperBufferMixin"),
    GT_Container_TeleporterMixin                      (COMMON, require(GREGTECH), "gregtech.GT_Container_TeleporterMixin"),
    GT_Container_TypeFilterMixin                      (COMMON, require(GREGTECH), "gregtech.GT_Container_TypeFilterMixin"),

    GT_GUIContainerMetaTile_MachineMixin              (CLIENT, require(GREGTECH), "gregtech.GT_GUIContainerMetaTile_MachineMixin"),
    GT_GUIContainer_BasicMachineMixin                 (CLIENT, require(GREGTECH), "gregtech.GT_GUIContainer_BasicMachineMixin"),
    GT_GUIContainer_BasicTankMixin                    (CLIENT, require(GREGTECH), "gregtech.GT_GUIContainer_BasicTankMixin"),
    GT_GUIContainer_BoilerMixin                       (CLIENT, require(GREGTECH), "gregtech.GT_GUIContainer_BoilerMixin"),
    GT_GUIContainer_FusionReactorMixin                (CLIENT, require(GREGTECH), "gregtech.GT_GUIContainer_FusionReactorMixin"),
    GT_GUIContainer_MicrowaveEnergyTransmitterMixin   (CLIENT, require(GREGTECH), "gregtech.GT_GUIContainer_MicrowaveEnergyTransmitterMixin"),
    GT_GUIContainer_MultiMachineMixin                 (CLIENT, require(GREGTECH), "gregtech.GT_GUIContainer_MultiMachineMixin"),
    GT_GUIContainer_OutputHatchMixin                  (CLIENT, require(GREGTECH), "gregtech.GT_GUIContainer_OutputHatchMixin"),
    GT_GUIContainer_PrimitiveBlastFurnaceMixin        (CLIENT, require(GREGTECH), "gregtech.GT_GUIContainer_PrimitiveBlastFurnaceMixin"),
    GT_GUIContainer_QuantumChestMixin                 (CLIENT, require(GREGTECH), "gregtech.GT_GUIContainer_QuantumChestMixin"),
    GT_GUIContainer_RegulatorMixin                    (CLIENT, require(GREGTECH), "gregtech.GT_GUIContainer_RegulatorMixin"),
    GT_GUIContainer_TeleporterMixin                   (CLIENT, require(GREGTECH), "gregtech.GT_GUIContainer_TeleporterMixin"),
    GT_GUIContainerVolumetricFlaskMixin               (CLIENT, require(GREGTECH), "gregtech.GT_GUIContainerVolumetricFlaskMixin"),

//    GT_NEI_DefaultHandlerMixin                        (COMMON, require(GREGTECH), "gregtech.GT_NEI_DefaultHandlerMixin"),
    GT_RectHandlerMixin                               (COMMON, require(GREGTECH), "gregtech.GT_RectHandlerMixin"),
    //endregion

    // region Bartworks Mixins
    BW_NEI_HandlerMixin                               (COMMON, require(BARTWORKS), "bartworks.BW_NEI_HandlerMixin"),
    //endregion

    // region TecTech Mixins
    TecTech_NEI_HandlerMixin                          (COMMON, require(TECTECH), "tectech.TecTech_NEI_HandlerMixin"),
    TecTech_GT_RectHandlerMixin                       (COMMON, require(TECTECH), "tectech.GT_RectHandlerMixin"),
    //endregion

    // region Applied Energistics 2 Mixins
    AEBaseContainerMixin                              (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.AEBaseContainerMixin"),
    ContainerCellWorkbenchMixin                       (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerCellWorkbenchMixin"),
    ContainerChestMixin_AE                            (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerChestMixin"),
    ContainerCondenserMixin                           (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerCondenserMixin"),
    ContainerCraftAmountMixin                         (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerCraftAmountMixin"),
    ContainerCraftingTermMixin                        (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerCraftingTermMixin"),
    ContainerFormationPlaneMixin                      (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerFormationPlaneMixin"),
    ContainerDriveMixin                               (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerDriveMixin"),
    ContainerGrinderMixin                             (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerGrinderMixin"),
    ContainerInscriberMixin                           (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerInscriberMixin"),
    ContainerInterfaceMixin                           (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerInterfaceMixin"),
    ContainerIOPortMixin                              (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerIOPortMixin"),
    ContainerLevelEmitterMixin                        (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerLevelEmitterMixin"),
    ContainerMACMixin                                 (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerMACMixin"),
    ContainerMEMonitorableMixin                       (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerMEMonitorableMixin"),
    ContainerNetworkToolMixin                         (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerNetworkToolMixin"),
    ContainerPatternTermMixin                         (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerPatternTermMixin"),
    ContainerPatternTermExMixin                       (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerPatternTermExMixin"),
    ContainerQuartzKnifeMixin                         (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerQuartzKnifeMixin"),
    ContainerQNBMixin                                 (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerQNBMixin"),
    ContainerSecurityMixin                            (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerSecurityMixin"),
    ContainerSkyChestMixin                            (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerSkyChestMixin"),
    ContainerSpatialIOPortMixin                       (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerSpatialIOPortMixin"),
    ContainerStorageBusMixin                          (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerStorageBusMixin"),
    ContainerUpgradeableMixin                         (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerUpgradeableMixin"),
    ContainerVibrationChamberMixin                    (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerVibrationChamberMixin"),
    ContainerWirelessMixin                            (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.ContainerWirelessMixin"),
    NEIAERecipeHandlerMixin                           (COMMON, require(APPLIEDENERGISTICS2), "appliedenergistics2.NEIAERecipeHandlerMixin"),

    AEBaseGuiMixin                                    (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.AEBaseGuiMixin"),
    GuiCellWorkbenchMixin                             (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiCellWorkbenchMixin"),
    GuiChestMixin_AE                                  (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiChestMixin"),
    GuiCondenserMixin                                 (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiCondenserMixin"),
    GuiCraftAmountMixin                               (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiCraftAmountMixin"),
    GuiCraftingTermMixin                              (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiCraftingTermMixin"),
    GuiFormationPlaneMixin                            (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiFormationPlaneMixin"),
    GuiDriveMixin                                     (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiDriveMixin"),
    GuiInscriberMixin                                 (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiInscriberMixin"),
    GuiInterfaceMixin                                 (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiInterfaceMixin"),
    GuiInterfaceTerminalMixin                         (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiInterfaceTerminalMixin"),
    GuiIOPortMixin                                    (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiIOPortMixin"),
    GuiLevelEmitterMixin                              (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiLevelEmitterMixin"),
    GuiMACMixin                                       (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiMACMixin"),
    GuiMEMonitorableMixin                             (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiMEMonitorableMixin"),
    GuiPatternTermMixin                               (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiPatternTermMixin"),
    GuiPatternTermExMixin                             (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiPatternTermExMixin"),
    GuiPriorityMixin                                  (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiPriorityMixin"),
    GuiQuartzKnifeMixin                               (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiQuartzKnifeMixin"),
    GuiSecurityMixin                                  (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiSecurityMixin"),
    GuiScrollbarMixin                                 (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiScrollbarMixin"),
    GuiStorageBusMixin                                (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiStorageBusMixin"),
    GuiUpgradeableMixin                               (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiUpgradeableMixin"),
    GuiVibrationChamberMixin                          (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.GuiVibrationChamberMixin"),
    //endregion

    //region Forestry
    //region Apiculture
    ContainerAlvearyHygroregulatorMixin               (COMMON, require(FORESTRY), "forestry.apiculture.gui.ContainerAlvearyHygroregulatorMixin"),
    ContainerAlvearySieveMixin                        (COMMON, require(FORESTRY), "forestry.apiculture.gui.ContainerAlvearySieveMixin"),
    ContainerAlvearySwarmerMixin                      (COMMON, require(FORESTRY), "forestry.apiculture.gui.ContainerAlvearySwarmerMixin"),
    ContainerBeeHelperMixin                           (COMMON, require(FORESTRY), "forestry.apiculture.gui.ContainerBeeHelperMixin"),
    ContainerHabitatLocatorMixin                      (COMMON, require(FORESTRY), "forestry.apiculture.gui.ContainerHabitatLocatorMixin"),
    ContainerImprinterMixin                           (COMMON, require(FORESTRY), "forestry.apiculture.gui.ContainerImprinterMixin"),

    GuiAlvearyMixin                                   (CLIENT, require(FORESTRY), "forestry.apiculture.gui.GuiAlvearyMixin"),
    GuiBeealyzerMixin                                 (CLIENT, require(FORESTRY), "forestry.apiculture.gui.GuiBeealyzerMixin"),
    GuiHabitatLocatorMixin                            (CLIENT, require(FORESTRY), "forestry.apiculture.gui.GuiHabitatLocatorMixin"),
    GuiImprinterMixin                                 (CLIENT, require(FORESTRY), "forestry.apiculture.gui.GuiImprinterMixin"),
    //endregion
    //region Arboriculture
    GuiTreealyzerMixin                                (CLIENT, require(FORESTRY), "forestry.arboriculture.gui.GuiTreealyzerMixin"),
    //endregion
    //region Core
    ContainerAlyzerMixin                              (COMMON, require(FORESTRY), "forestry.core.gui.ContainerAlyzerMixin"),
    ContainerAnalyzerMixin                            (COMMON, require(FORESTRY), "forestry.core.gui.ContainerAnalyzerMixin"),
    ContainerEscritoireMixin                          (COMMON, require(FORESTRY), "forestry.core.gui.ContainerEscritoireMixin"),
    ContainerForestryMixin                            (COMMON, require(FORESTRY), "forestry.core.gui.ContainerForestryMixin"),
    ContainerNaturalistInventoryMixin                 (COMMON, require(FORESTRY), "forestry.core.gui.ContainerNaturalistInventoryMixin"),

    GuiAlyzerMixin                                    (CLIENT, require(FORESTRY), "forestry.core.gui.GuiAlyzerMixin"),
    GuiAnalyzerMixin                                  (CLIENT, require(FORESTRY), "forestry.core.gui.GuiAnalyzerMixin"),
    GuiEscritoireMixin                                (CLIENT, require(FORESTRY), "forestry.core.gui.GuiEscritoireMixin"),
    GuiNaturalistInventoryMixin                       (CLIENT, require(FORESTRY), "forestry.core.gui.GuiNaturalistInventoryMixin"),

    GuiWidgetsMixin                                   (CLIENT, require(FORESTRY), "forestry.core.widgets.WidgetsMixin"),
    TankWidgetMixin                                   (CLIENT, require(FORESTRY), "forestry.core.widgets.TankWidgetMixin"),
    //endregion
    //region Energy
    ContainerEngineBiogasMixin                        (COMMON, require(FORESTRY), "forestry.energy.gui.ContainerEngineBiogasMixin"),
    ContainerEngineElectricMixin                      (COMMON, require(FORESTRY), "forestry.energy.gui.ContainerEngineElectricMixin"),
    ContainerEnginePeatMixin                          (COMMON, require(FORESTRY), "forestry.energy.gui.ContainerEnginePeatMixin"),
    ContainerGeneratorMixin                           (COMMON, require(FORESTRY), "forestry.energy.gui.ContainerGeneratorMixin"),

    GuiEngineBiogasMixin                              (CLIENT, require(FORESTRY), "forestry.energy.gui.GuiEngineBiogasMixin"),
    GuiEngineElectricMixin                            (CLIENT, require(FORESTRY), "forestry.energy.gui.GuiEngineElectricMixin"),
    GuiEnginePeatMixin                                (CLIENT, require(FORESTRY), "forestry.energy.gui.GuiEnginePeatMixin"),
    GuiGeneratorMixin                                 (CLIENT, require(FORESTRY), "forestry.energy.gui.GuiGeneratorMixin"),
    //endregion
    //region Factory
    ContainerBottlerMixin                             (COMMON, require(FORESTRY), "forestry.factory.gui.ContainerBottlerMixin"),
    ContainerCarpenterMixin                           (COMMON, require(FORESTRY), "forestry.factory.gui.ContainerCarpenterMixin"),
    ContainerCentrifugeMixin                          (COMMON, require(FORESTRY), "forestry.factory.gui.ContainerCentrifugeMixin"),
    ContainerFabricatorMixin                          (COMMON, require(FORESTRY), "forestry.factory.gui.ContainerFabricatorMixin"),
    ContainerFermenterMixin                           (COMMON, require(FORESTRY), "forestry.factory.gui.ContainerFermenterMixin"),
    ContainerMoistenerMixin                           (COMMON, require(FORESTRY), "forestry.factory.gui.ContainerMoistenerMixin"),
    ContainerRaintankMixin                            (COMMON, require(FORESTRY), "forestry.factory.gui.ContainerRaintankMixin"),
    ContainerStillMixin                               (COMMON, require(FORESTRY), "forestry.factory.gui.ContainerStillMixin"),
    ContainerSqueezerMixin                            (COMMON, require(FORESTRY), "forestry.factory.gui.ContainerSqueezerMixin"),
    ContainerWorktableMixin                           (COMMON, require(FORESTRY), "forestry.factory.gui.ContainerWorktableMixin"),

    GuiBottlerMixin                                   (CLIENT, require(FORESTRY), "forestry.factory.gui.GuiBottlerMixin"),
    GuiCarpenterMixin                                 (CLIENT, require(FORESTRY), "forestry.factory.gui.GuiCarpenterMixin"),
    GuiCentrifugeMixin                                (CLIENT, require(FORESTRY), "forestry.factory.gui.GuiCentrifugeMixin"),
    GuiFabricatorMixin                                (CLIENT, require(FORESTRY), "forestry.factory.gui.GuiFabricatorMixin"),
    GuiFermenterMixin                                 (CLIENT, require(FORESTRY), "forestry.factory.gui.GuiFermenterMixin"),
    GuiMoistenerMixin                                 (CLIENT, require(FORESTRY), "forestry.factory.gui.GuiMoistenerMixin"),
    GuiRaintankMixin                                  (CLIENT, require(FORESTRY), "forestry.factory.gui.GuiRaintankMixin"),
    GuiSqueezerMixin                                  (CLIENT, require(FORESTRY), "forestry.factory.gui.GuiSqueezerMixin"),
    GuiStillMixin                                     (CLIENT, require(FORESTRY), "forestry.factory.gui.GuiStillMixin"),
    GuiWorktableMixin                                 (CLIENT, require(FORESTRY), "forestry.factory.gui.GuiWorktableMixin"),

    //endregion
    ForestryGuiMixins                                 (CLIENT, require(FORESTRY), "forestry.ForestryGuiMixins"),
    SlotUtilMixin                                     (COMMON, require(FORESTRY), "forestry.SlotUtilMixin"),
    //endregion

    // @formatter:on
    ;

    private final Side side;
    private final Predicate<List<ITargetedMod>> filter;
    private final String mixin;
}
