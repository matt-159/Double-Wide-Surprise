package com.github.matt159.dws.mixin.plugin;

import com.falsepattern.lib.mixin.IMixin;
import com.falsepattern.lib.mixin.ITargetedMod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Predicate;

import static com.falsepattern.lib.mixin.IMixin.PredicateHelpers.always;
import static com.falsepattern.lib.mixin.IMixin.PredicateHelpers.avoid;
import static com.falsepattern.lib.mixin.IMixin.PredicateHelpers.require;
import static com.falsepattern.lib.mixin.IMixin.Side.CLIENT;
import static com.falsepattern.lib.mixin.IMixin.Side.COMMON;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.AGRICRAFT;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.APPLIEDENERGISTICS2;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.BARTWORKS;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.BAUBLES;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.BAUBLESEXPANDED;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.CHISEL;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.CODECHICKENLIB;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.ENDERCORE;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.FORESTRY;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.FORGEBACKPACKS;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.GALACTICRAFT;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.GARDENCORE;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.GREGTECH;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.INDUSTRIALCRAFT2;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.INVENTORYTWEAKS;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.IRONCHEST;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.MARICULTURE;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.NATURA;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.NOTENOUGHITEMS;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.RAILCRAFT;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.STORAGEDRAWERS;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.TECTECH;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.THAUMCRAFT;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.TINKERSCONSTRUCT;
import static com.github.matt159.dws.mixin.plugin.TargetedMod.TRAVELLERSGEAR;

@Getter
@RequiredArgsConstructor
public enum Mixin implements IMixin {
    // @formatter:off

    // region Double Wide Surprise Slot Injections
    BaublesSlotSyncMixin                              (CLIENT, require(BAUBLES).or(require(BAUBLESEXPANDED)), "dws.baubles.GuiInventoryMixin"),
    BaublesSlotInjectMixin                            (COMMON, require(BAUBLES).or(require(BAUBLESEXPANDED)), "dws.baubles.ContainerPlayerMixin"),
    TinkersSlotInjectMixin                            (COMMON, require(TINKERSCONSTRUCT), "dws.tinkersconstruct.ContainerPlayerMixin"),
    TravellersGearSlotInjectMixin                     (COMMON, require(TRAVELLERSGEAR), "dws.travellersgear.ContainerPlayerMixin"),
    GalacticraftSlotInjectMixin                       (COMMON, require(GALACTICRAFT), "dws.galacticraft.ContainerPlayerMixin"),
    //endregion
    // region Vanilla Mixins
    CreativeTabsMixin                                 (CLIENT, always(), "minecraft.gui.CreativeTabsMixin"),
    EntityPlayerMixin                                 (COMMON, always(), "minecraft.EntityPlayerMixin"),
    ForgeHooksMixin                                   (COMMON, always(), "minecraft.ForgeHooksMixin"),
    GameSettingsMixin                                 (CLIENT, always(), "minecraft.GameSettingsMixin"),
    GuiUtilsMixin                                     (CLIENT, always(), "minecraft.GuiUtilsMixin"),
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
    ContainerFurnace_TransferStackInSlotMixin         (COMMON, avoid(ENDERCORE), "minecraft.inventory.ContainerFurnace_TransferStackInSlotMixin"),
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
    CCRenderStateMixin                                (CLIENT, require(CODECHICKENLIB), "codechickenlib.CCRenderStateMixin"),
    InventoryRangeMixin                               (COMMON, require(CODECHICKENLIB), "codechickenlib.InventoryRangeMixin"),
    //endregion
    //region NEI Mixins
    ClientUtilsMixin                                  (CLIENT, require(NOTENOUGHITEMS), "nei.NEIClientUtilsMixin"),
    ContainerCreativeInvMixin                         (COMMON, require(NOTENOUGHITEMS), "nei.ContainerCreativeInvMixin"),
    ExtendedCreativeInvMixin                          (COMMON, require(NOTENOUGHITEMS), "nei.ExtendedCreativeInvMixin"),
    GuiExtendedCreativeInvMixin                       (CLIENT, require(NOTENOUGHITEMS), "nei.GuiExtendedCreativeInvMixin"),
    LayoutManagerMixin                                (CLIENT, require(NOTENOUGHITEMS), "nei.LayoutManagerMixin"),
    NEIClientConfigMixin                              (CLIENT, require(NOTENOUGHITEMS), "nei.NEIClientConfigMixin"),
    NEIServerUtilsMixin                               (COMMON, require(NOTENOUGHITEMS), "nei.NEIServerUtilsMixin"),
    PlayerSaveMixin                                   (COMMON, require(NOTENOUGHITEMS), "nei.PlayerSaveMixin"),

    DefaultOverlayHandlerMixin                        (CLIENT, require(NOTENOUGHITEMS), "nei.DefaultOverlayHandlerMixin"),
    TemplateRecipeHandlerMixin                        (COMMON, require(NOTENOUGHITEMS), "nei.TemplateRecipeHandlerMixin"),
    //endregion
    // region Galacticraft Mixins
    IGalacticWearableMixin                            (COMMON, require(GALACTICRAFT), "galacticraft.GalacticWearableMixin"),
    ContainerExtendedInventoryMixin                   (COMMON, require(GALACTICRAFT), "galacticraft.ContainerExtendedInventoryMixin"),
    //endregion
    // region TravellersGear
    ClientProxyMixin                                  (CLIENT, require(TRAVELLERSGEAR), "travellersgear.ClientProxyMixin"),
    //endregion
    // region Baubles Mixins
    GuiEventsMixin                                    (CLIENT, require(BAUBLES).or(require(BAUBLESEXPANDED)), "baubles.GuiEventsMixin"),
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

    //    GT_NEI_DefaultHandlerMixin                        (COMMON, require(GREGTECH),            "gregtech.GT_NEI_DefaultHandlerMixin"),
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

    AEBaseGuiMixin                                    (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.AEBaseGuiMixin"),
    ItemRepoMixin                                     (CLIENT, require(APPLIEDENERGISTICS2), "appliedenergistics2.ItemRepoMixin"),
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
    GuiBeeHousingMixin                                (CLIENT, require(FORESTRY), "forestry.apiculture.gui.GuiBeeHousingMixin"),
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

    ContainerSolderingIronMixin                       (COMMON, require(FORESTRY), "forestry.core.circuits.ContainerSolderingIronMixin"),

    GuiSolderingIronMixin                             (CLIENT, require(FORESTRY), "forestry.core.circuits.GuiSolderingIronMixin"),

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

//    CachedBottlerRecipeMixin                          (CLIENT, require(FORESTRY),            "forestry.factory.nei.CachedBottlerRecipeMixin"),
//    CachedCarpenterRecipeMixin                        (CLIENT, require(FORESTRY),            "forestry.factory.nei.CachedCarpenterRecipeMixin"),
//    CachedFermenterRecipeMixin                        (CLIENT, require(FORESTRY),            "forestry.factory.nei.CachedFermenterRecipeMixin"),
//    CachedMoistenerRecipeMixin                        (CLIENT, require(FORESTRY),            "forestry.factory.nei.CachedMoistenerRecipeMixin"),
//    CachedSqueezerRecipeMixin                         (CLIENT, require(FORESTRY),            "forestry.factory.nei.CachedSqueezerRecipeMixin"),
//    CachedStillRecipeMixin                            (CLIENT, require(FORESTRY),            "forestry.factory.nei.CachedStillRecipeMixin"),
    //endregion
    //region Farming
    ContainerFarmMixin                                (COMMON, require(FORESTRY), "forestry.farming.gui.ContainerFarmMixin"),

    GuiFarmMixin                                      (CLIENT, require(FORESTRY), "forestry.farming.gui.GuiFarmMixin"),
    //endregion
    //region Storage
    ContainerBackpackMixin                            (COMMON, require(FORESTRY), "forestry.storage.gui.ContainerBackpackMixin"),
    //endregion
    ForestryGuiMixins                                 (CLIENT, require(FORESTRY), "forestry.ForestryGuiMixins"),
    SlotUtilMixin                                     (COMMON, require(FORESTRY), "forestry.SlotUtilMixin"),
    //endregion
    //region Storage Drawers
    ContainerDrawersMixin                             (COMMON, require(STORAGEDRAWERS), "storagedrawers.ContainerDrawersMixin"),
    ContainerFramingTableMixin                        (COMMON, require(STORAGEDRAWERS), "storagedrawers.ContainerFramingTableMixin"),

    GuiDrawersMixin                                   (CLIENT, require(STORAGEDRAWERS), "storagedrawers.GuiDrawersMixin"),
    GuiFramingMixin                                   (CLIENT, require(STORAGEDRAWERS), "storagedrawers.GuiFramingMixin"),
    //endregion
    //region Inventory Tweaks
    InvTweaksConfigInventoryRuleset                   (COMMON, require(INVENTORYTWEAKS), "inventorytweaks.InvTweaksConfigInventoryRulesetMixin"),
    InvTweaksContainerManagerMixin                    (COMMON, require(INVENTORYTWEAKS), "inventorytweaks.InvTweaksContainerManagerMixin"),
//    InvTweaksContainerSectionManagerMixin             (COMMON, require(INVENTORYTWEAKS), "inventorytweaks.InvTweaksContainerSectionManagerMixin"),
    InvTweaksMixin                                    (COMMON, require(INVENTORYTWEAKS), "inventorytweaks.InvTweaksMixin"),
    VanillaSlotMapsMixin                              (COMMON, require(INVENTORYTWEAKS), "inventorytweaks.VanillaSlotMapsMixin"),
    //endregion
    //region EnderCore
    EnderCoreMethodsMixin                             (COMMON, require(ENDERCORE), "endercore.EnderCoreMethodsMixin"),
    //endregion
    //region Chisel
    ChiselControllerMixin                             (COMMON, require(CHISEL), "chisel.ChiselControllerMixin"),
    ContainerAutoChiselMixin                          (COMMON, require(CHISEL), "chisel.ContainerAutoChiselMixin"),
    ContainerChiselMixin                              (COMMON, require(CHISEL), "chisel.ContainerChiselMixin"),

    GuiAutoChiselMixin                                (CLIENT, require(CHISEL), "chisel.GuiAutoChiselMixin"),
    GuiButtonChiselModeMixin                          (CLIENT, require(CHISEL), "chisel.GuiButtonChiselModeMixin"),
    GuiChiselMixin                                    (CLIENT, require(CHISEL), "chisel.GuiChiselMixin"),
    Chisel_GuiScrollbarMixin                          (CLIENT, require(CHISEL), "chisel.GuiScrollbarMixin"),

    RecipeHandlerChiselMixin                          (COMMON, require(CHISEL), "chisel.RecipeHandlerChiselMixin"),
    //endregion
    //region GardenCore
    ContainerCompostBinMixin                          (COMMON, require(GARDENCORE), "gardencore.ContainerCompostBinMixin"),

    GuiCompostBinMixin                                (CLIENT, require(GARDENCORE), "gardencore.GuiCompostBinMixin"),
    //endregion
    //region Agricraft
    ContainerAgricraftMixin                           (COMMON, require(AGRICRAFT), "agricraft.ContainerAgricraftMixin"),
    ContainerSeedAnalyzerMixin                        (COMMON, require(AGRICRAFT), "agricraft.ContainerSeedAnalyzerMixin"),

    GuiSeedAnalyzerMixin                              (CLIENT, require(AGRICRAFT), "agricraft.GuiSeedAnalyzerMixin"),
    //endregion
    //region Mariculture
    ContainerCrucibleMixin                            (COMMON, require(MARICULTURE), "mariculture.ContainerCrucibleMixin"),
    ContainerMaricultureMixin                         (COMMON, require(MARICULTURE), "mariculture.ContainerMaricultureMixin"),

    FeatureGeneralMixin                               (CLIENT, require(MARICULTURE), "mariculture.feature.FeatureGeneralMixin"),
    FeatureUpgradesMixin                              (CLIENT, require(MARICULTURE), "mariculture.feature.FeatureUpgradesMixin"),

    GuiCrucibleMixin                                  (CLIENT, require(MARICULTURE), "mariculture.GuiCrucibleMixin"),
    GuiMaricultureMixin                               (CLIENT, require(MARICULTURE), "mariculture.GuiMaricultureMixin"),
    //endregion
    //region Natura
    NGuiHandlerMixin                                  (COMMON, require(NATURA), "natura.NGuiHandlerMixin"),

    FurnaceGuiMixin                                   (CLIENT, require(NATURA), "natura.FurnaceGuiMixin"),
    //endregion
    //region Thaumcraft
    ContainerAlchemyFurnaceMixin                      (COMMON, require(THAUMCRAFT), "thaumcraft.ContainerAlchemyFurnaceMixin"),
    ContainerArcaneWorkbenchMixin                     (COMMON, require(THAUMCRAFT), "thaumcraft.ContainerArcaneWorkbenchMixin"),
    ContainerResearchTableMixin                       (COMMON, require(THAUMCRAFT), "thaumcraft.ContainerResearchTableMixin"),

    GuiAlchemyFurnaceMixin                            (CLIENT, require(THAUMCRAFT), "thaumcraft.GuiAlchemyFurnaceMixin"),
    GuiArcaneWorkbenchMixin                           (CLIENT, require(THAUMCRAFT), "thaumcraft.GuiArcaneWorkbenchMixin"),
    GuiResearchTableMixin                             (CLIENT, require(THAUMCRAFT), "thaumcraft.GuiResearchTableMixin"),
    //endregion
    //region IndustrialCraft 2
    GuiIronFurnaceMixin                               (CLIENT, require(INDUSTRIALCRAFT2), "ic2.GuiIronFurnaceMixin"),

    ContainerBaseMixin                                (COMMON, require(INDUSTRIALCRAFT2), "ic2.ContainerBaseMixin"),
    ContainerIronFurnaceMixin                         (COMMON, require(INDUSTRIALCRAFT2), "ic2.ContainerIronFurnaceMixin"),
    //endregion
    //region Railcraft
    GuiBoilerFluidMixin                               (CLIENT, require(RAILCRAFT), "railcraft.GuiBoilerFluidMixin"),
    GuiBoilerSolidMixin                               (CLIENT, require(RAILCRAFT), "railcraft.GuiBoilerSolidMixin"),
    GuiCokeOvenMixin                                  (CLIENT, require(RAILCRAFT), "railcraft.GuiCokeOvenMixin"),
    GuiSteamOvenMixin                                 (CLIENT, require(RAILCRAFT), "railcraft.GuiSteamOvenMixin"),
    GuiTankMixin                                      (CLIENT, require(RAILCRAFT), "railcraft.GuiTankMixin"),

    ContainerBoilerFluidMixin                         (COMMON, require(RAILCRAFT), "railcraft.ContainerBoilerFluidMixin"),
    ContainerBoilerSolidMixin                         (COMMON, require(RAILCRAFT), "railcraft.ContainerBoilerSolidMixin"),
    ContainerCokeOvenMixin                            (COMMON, require(RAILCRAFT), "railcraft.ContainerCokeOvenMixin"),
    ContainerSteamOvenMixin                           (COMMON, require(RAILCRAFT), "railcraft.ContainerSteamOvenMixin"),
    ContainerTankMixin                                (COMMON, require(RAILCRAFT), "railcraft.ContainerTankMixin"),

    RailcraftContainerMixin                           (COMMON, require(RAILCRAFT), "railcraft.RailcraftContainerMixin"),
    //endregion
    //region Forge Backpacks
    BackpackSaveMixin                                 (COMMON, require(FORGEBACKPACKS), "backpack.BackpackSaveMixin"),

    ContainerAdvancedMixin                            (COMMON, require(FORGEBACKPACKS), "backpack.ContainerAdvancedMixin"),
    FactoryBackpackNormalMixin                        (COMMON, require(FORGEBACKPACKS), "backpack.FactoryBackpackNormalMixin"),
    FactoryPersonalSlotMixin                          (COMMON, require(FORGEBACKPACKS), "backpack.FactoryPersonalSlotMixin"),
    //todo finish these
//    FactoryWorkbenchBackpackMixin_COMMON              (COMMON, require(FORGEBACKPACKS), "backpack.FactoryWorkbenchBackpackMixin"),
//    FactoryWorkbenchBackpackMixin_CLIENT              (CLIENT, require(FORGEBACKPACKS), "backpack.FactoryWorkbenchBackpackMixin"),

    InventoryPickupMixin                              (COMMON, require(FORGEBACKPACKS), "backpack.InventoryPickupMixin"),
    //endregion
    // @formatter:on
    ;

    private final Side side;
    private final Predicate<List<ITargetedMod>> filter;
    private final String mixin;
}
