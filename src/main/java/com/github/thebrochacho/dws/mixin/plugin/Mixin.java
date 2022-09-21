package com.github.thebrochacho.dws.mixin.plugin;

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
import static com.github.thebrochacho.dws.mixin.plugin.TargetedMod.*;

@Getter
@RequiredArgsConstructor
public enum Mixin implements IMixin {
    // @formatter:off

    // Vanilla
    CreativeTabsMixin                                 (CLIENT, always(), "minecraft.gui.CreativeTabsMixin"),
    ForgeHooksMixin                                   (COMMON, always(), "minecraft.ForgeHooksMixin"),
    InventoryPlayerMixin                              (COMMON, always(), "minecraft.inventory.InventoryPlayerMixin"),
    MinecraftMixin                                    (CLIENT, always(), "minecraft.MinecraftMixin"),
    NetHandlerPlayServerMixin                         (COMMON, always(), "minecraft.NetHandlerPlayServerMixin"),
    TextureManagerMixin                               (CLIENT, always(), "minecraft.TextureManagerMixin"),

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
    GuiFurnaceMixin                                   (CLIENT, always(), "minecraft.gui.GuiFurnaceMixin"),
    GuiInventoryMixin                                 (CLIENT, always(), "minecraft.gui.GuiInventoryMixin"),

    GuiMerchantMixin                                  (CLIENT, always(), "minecraft.gui.GuiMerchantMixin"),
    MerchantButtonMixin                               (CLIENT, always(), "minecraft.gui.MerchantButtonMixin"),

    GuiRepairMixin                                    (CLIENT, always(), "minecraft.gui.GuiRepairMixin"),
    GuiScreenHorseInventoryMixin                      (CLIENT, always(), "minecraft.gui.GuiScreenHorseInventoryMixin"),

    //CodeChickenLib
    InventoryRangeMixin                               (COMMON, require(CODECHICKENLIB),"codechickenlib.InventoryRangeMixin"),

    //NotEnoughItems
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
    TemplateRecipeHandlerMixin                        (COMMON, require(NOTENOUGHITEMS), "nei.TemplateRecipeHandlerMixin"),

    //Galacticraft
    IGalacticWearableMixin                            (COMMON, require(GALACTICRAFT), "galacticraft.GalacticWearableMixin"),
    ContainerExtendedInventoryMixin                   (COMMON, require(GALACTICRAFT), "galacticraft.ContainerExtendedInventoryMixin"),

    //TravellersGear
    ClientProxyMixin                                  (CLIENT, require(TRAVELLERSGEAR), "travellersgear.ClientProxyMixin"),

    //Baubles
    GuiEventsMixin                                    (CLIENT, require(BAUBLES), "baubles.GuiEventsMixin"),

    //Ironchest
    ContainerIronChestMixin                           (COMMON, require(IRONCHEST), "ironchest.ContainerIronChestMixin"),
    GUIChestMixin                                     (CLIENT, require(IRONCHEST), "ironchest.GUIChestMixin"),

    //Gregtech
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

    GT_NEI_DefaultHandlerMixin                        (COMMON, require(GREGTECH), "gregtech.GT_NEI_DefaultHandlerMixin"),
    GT_RectHandlerMixin                               (COMMON, require(GREGTECH), "gregtech.GT_RectHandlerMixin"),

    //bartworks
    BW_NEI_HandlerMixin                               (COMMON, require(BARTWORKS), "bartworks.BW_NEI_HandlerMixin"),

    //TecTech
    TecTech_NEI_HandlerMixin                          (COMMON, require(TECTECH), "tectech.TecTech_NEI_HandlerMixin"),
    TecTech_GT_RectHandlerMixin                       (COMMON, require(TECTECH), "tectech.GT_RectHandlerMixin"),

    // @formatter:on
    ;

    private final Side side;
    private final Predicate<List<ITargetedMod>> filter;
    private final String mixin;
}
