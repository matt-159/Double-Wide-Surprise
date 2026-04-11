package com.github.matt159.dws.mixin.plugin;

import com.falsepattern.lib.mixin.v2.MixinHelper;
import com.falsepattern.lib.mixin.v2.SidedMixins;
import com.falsepattern.lib.mixin.v2.TaggedMod;
import com.github.matt159.dws.Tags;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.intellij.lang.annotations.Language;

import java.util.function.BooleanSupplier;

import static com.falsepattern.lib.mixin.v2.MixinHelper.avoid;
import static com.falsepattern.lib.mixin.v2.MixinHelper.builder;
import static com.falsepattern.lib.mixin.v2.MixinHelper.require;

@SuppressWarnings("UnstableApiUsage")
@RequiredArgsConstructor
public enum Mixin implements IMixins {
    // @formatter:off

    // region Vanilla
    Vanilla(
            Phase.EARLY,
            common("minecraft.EntityPlayerMixin",
                   "minecraft.FMLNetworkHandlerMixin",
                   "minecraft.ForgeHooksMixin",
                   "minecraft.NetHandlerPlayServerMixin",
                   "minecraft.inventory.ContainerBeaconMixin",
                   "minecraft.inventory.ContainerBrewingStandMixin",
                   "minecraft.inventory.ContainerChestMixin",
                   "minecraft.inventory.ContainerDispenserMixin",
                   "minecraft.inventory.ContainerEnchantmentMixin",
                   "minecraft.inventory.ContainerFurnaceMixin",
                   "minecraft.inventory.ContainerHopperMixin",
                   "minecraft.inventory.ContainerHorseInventoryMixin",
                   "minecraft.inventory.ContainerMerchantMixin",
                   "minecraft.inventory.ContainerMixin",
                   "minecraft.inventory.ContainerPlayerMixin",
                   "minecraft.inventory.ContainerRepairMixin",
                   "minecraft.inventory.ContainerWorkbenchMixin",
                   "minecraft.inventory.InventoryPlayerMixin"),
            client("minecraft.GameSettingsMixin",
                   "minecraft.GuiUtilsMixin",
                   "minecraft.MinecraftMixin",
                   "minecraft.NetHandlerPlayClientMixin",
                   "minecraft.TextureManagerMixin",
                   "minecraft.gui.CreativeTabsMixin",
                   "minecraft.gui.GuiBeaconMixin",
                   "minecraft.gui.GuiBrewingStandMixin",
                   "minecraft.gui.GuiChestMixin",
                   "minecraft.gui.GuiContainerCreativeMixin",
                   "minecraft.gui.GuiContainerMixin",
                   "minecraft.gui.GuiCraftingMixin",
                   "minecraft.gui.GuiDispenserMixin",
                   "minecraft.gui.GuiEnchantmentMixin",
                   "minecraft.gui.GuiFurnaceMixin",
                   "minecraft.gui.GuiHopperMixin",
                   "minecraft.gui.GuiIngameForgeMixin",
                   "minecraft.gui.GuiInventoryMixin",
                   "minecraft.gui.GuiMerchantMixin",
                   "minecraft.gui.GuiMixin",
                   "minecraft.gui.GuiRepairMixin",
                   "minecraft.gui.GuiScreenHorseInventoryMixin",
                   "minecraft.gui.MerchantButtonMixin",
                   "minecraft.inventory.ContainerCreativeMixin",
                   "minecraft.inventory.InventoryPlayerMixin")),
    VanillaNoEndercore(
            Phase.EARLY,
            avoid(TargetMod.EnderCore),
            common("minecraft.inventory.ContainerFurnace_TransferStackInSlotMixin")),
    // endregion
    // region AgriCraft
    AgriCraft(
            Phase.LATE,
            require(TargetMod.Agricraft),
            common("agricraft.ContainerAgricraftMixin",
                   "agricraft.ContainerSeedAnalyzerMixin"),
            client("agricraft.GuiSeedAnalyzerMixin")),
    // endregion
    // region AppliedEnergistics2
    AppliedEnergistics2(
            Phase.EARLY,
            require(TargetMod.AppliedEnergistics2),
            common("appliedenergistics2.AEBaseContainerMixin",
                   "appliedenergistics2.ContainerCellWorkbenchMixin",
                   "appliedenergistics2.ContainerChestMixin",
                   "appliedenergistics2.ContainerCondenserMixin",
                   "appliedenergistics2.ContainerCraftAmountMixin",
                   "appliedenergistics2.ContainerCraftingTermMixin",
                   "appliedenergistics2.ContainerDriveMixin",
                   "appliedenergistics2.ContainerFormationPlaneMixin",
                   "appliedenergistics2.ContainerGrinderMixin",
                   "appliedenergistics2.ContainerIOPortMixin",
                   "appliedenergistics2.ContainerInscriberMixin",
                   "appliedenergistics2.ContainerInterfaceMixin",
                   "appliedenergistics2.ContainerLevelEmitterMixin",
                   "appliedenergistics2.ContainerMACMixin",
                   "appliedenergistics2.ContainerMEMonitorableMixin",
                   "appliedenergistics2.ContainerNetworkToolMixin",
                   "appliedenergistics2.ContainerPatternTermExMixin",
                   "appliedenergistics2.ContainerPatternTermMixin",
                   "appliedenergistics2.ContainerQNBMixin",
                   "appliedenergistics2.ContainerQuartzKnifeMixin",
                   "appliedenergistics2.ContainerSecurityMixin",
                   "appliedenergistics2.ContainerSkyChestMixin",
                   "appliedenergistics2.ContainerSpatialIOPortMixin",
                   "appliedenergistics2.ContainerStorageBusMixin",
                   "appliedenergistics2.ContainerUpgradeableMixin",
                   "appliedenergistics2.ContainerVibrationChamberMixin",
                   "appliedenergistics2.ContainerWirelessMixin"),
            client("appliedenergistics2.AEBaseGuiMixin",
                   "appliedenergistics2.GuiCellWorkbenchMixin",
                   "appliedenergistics2.GuiChestMixin",
                   "appliedenergistics2.GuiCondenserMixin",
                   "appliedenergistics2.GuiCraftAmountMixin",
                   "appliedenergistics2.GuiCraftingTermMixin",
                   "appliedenergistics2.GuiDriveMixin",
                   "appliedenergistics2.GuiFormationPlaneMixin",
                   "appliedenergistics2.GuiIOPortMixin",
                   "appliedenergistics2.GuiInscriberMixin",
                   "appliedenergistics2.GuiInterfaceMixin",
                   "appliedenergistics2.GuiInterfaceTerminalMixin",
                   "appliedenergistics2.GuiLevelEmitterMixin",
                   "appliedenergistics2.GuiMACMixin",
                   "appliedenergistics2.GuiMEMonitorableMixin",
                   "appliedenergistics2.GuiPatternTermExMixin",
                   "appliedenergistics2.GuiPatternTermMixin",
                   "appliedenergistics2.GuiPriorityMixin",
                   "appliedenergistics2.GuiQuartzKnifeMixin",
                   "appliedenergistics2.GuiScrollbarMixin",
                   "appliedenergistics2.GuiSecurityMixin",
                   "appliedenergistics2.GuiStorageBusMixin",
                   "appliedenergistics2.GuiUpgradeableMixin",
                   "appliedenergistics2.GuiVibrationChamberMixin",
                   "appliedenergistics2.ItemRepoMixin")),
    // endregion
    // region Baubles

    BaublesEarly(
            Phase.EARLY,
            require(TargetMod.Baubles),
            common("dws.baubles.ContainerPlayerMixin"),
            client("dws.baubles.GuiInventoryMixin")),
    BaublesLate(
            Phase.LATE,
            require(TargetMod.Baubles),
            client("baubles.GuiEventsMixin")),
    // endregion
    // region Chisel
    Chisel(
            Phase.LATE,
            require(TargetMod.Chisel),
            common("chisel.ChiselControllerMixin",
                   "chisel.ContainerAutoChiselMixin",
                   "chisel.ContainerChiselMixin",
                   "chisel.RecipeHandlerChiselMixin"),
            client("chisel.GuiAutoChiselMixin",
                   "chisel.GuiButtonChiselModeMixin",
                   "chisel.GuiChiselMixin",
                   "chisel.GuiScrollbarMixin")),
    // endregion
    // region CodeChickenLib
    CodeChickenLib(
            Phase.LATE,
            require(TargetMod.CodeChickenLib),
            common("codechickenlib.InventoryRangeMixin"),
            client("codechickenlib.CCRenderStateMixin")),
    // endregion
    // region EnderCore
    EnderCore(
            Phase.EARLY,
            require(TargetMod.EnderCore),
            common("endercore.EnderCoreMethodsMixin")),
    // endregion
    // region Forestry
    Forestry(
            Phase.LATE,
            require(TargetMod.Forestry),
            common("forestry.SlotUtilMixin",
                   "forestry.apiculture.gui.ContainerAlvearyHygroregulatorMixin",
                   "forestry.apiculture.gui.ContainerAlvearySieveMixin",
                   "forestry.apiculture.gui.ContainerAlvearySwarmerMixin",
                   "forestry.apiculture.gui.ContainerBeeHelperMixin",
                   "forestry.apiculture.gui.ContainerHabitatLocatorMixin",
                   "forestry.apiculture.gui.ContainerImprinterMixin",
                   "forestry.core.circuits.ContainerSolderingIronMixin",
                   "forestry.core.gui.ContainerAlyzerMixin",
                   "forestry.core.gui.ContainerAnalyzerMixin",
                   "forestry.core.gui.ContainerEscritoireMixin",
                   "forestry.core.gui.ContainerForestryMixin",
                   "forestry.core.gui.ContainerNaturalistInventoryMixin",
                   "forestry.energy.gui.ContainerEngineBiogasMixin",
                   "forestry.energy.gui.ContainerEngineElectricMixin",
                   "forestry.energy.gui.ContainerEnginePeatMixin",
                   "forestry.energy.gui.ContainerGeneratorMixin",
                   "forestry.factory.gui.ContainerBottlerMixin",
                   "forestry.factory.gui.ContainerCarpenterMixin",
                   "forestry.factory.gui.ContainerCentrifugeMixin",
                   "forestry.factory.gui.ContainerFabricatorMixin",
                   "forestry.factory.gui.ContainerFermenterMixin",
                   "forestry.factory.gui.ContainerMoistenerMixin",
                   "forestry.factory.gui.ContainerRaintankMixin",
                   "forestry.factory.gui.ContainerSqueezerMixin",
                   "forestry.factory.gui.ContainerStillMixin",
                   "forestry.factory.gui.ContainerWorktableMixin",
                   "forestry.farming.gui.ContainerFarmMixin",
                   "forestry.storage.gui.ContainerBackpackMixin"),
            client("forestry.ForestryGuiMixins",
                   "forestry.apiculture.gui.GuiAlvearyMixin",
                   "forestry.apiculture.gui.GuiBeeHousingMixin",
                   "forestry.apiculture.gui.GuiBeealyzerMixin",
                   "forestry.apiculture.gui.GuiHabitatLocatorMixin",
                   "forestry.apiculture.gui.GuiImprinterMixin",
                   "forestry.arboriculture.gui.GuiTreealyzerMixin",
                   "forestry.core.circuits.GuiSolderingIronMixin",
                   "forestry.core.gui.GuiAlyzerMixin",
                   "forestry.core.gui.GuiAnalyzerMixin",
                   "forestry.core.gui.GuiEscritoireMixin",
                   "forestry.core.gui.GuiNaturalistInventoryMixin",
                   "forestry.core.widgets.TankWidgetMixin",
                   "forestry.core.widgets.WidgetsMixin",
                   "forestry.energy.gui.GuiEngineBiogasMixin",
                   "forestry.energy.gui.GuiEngineElectricMixin",
                   "forestry.energy.gui.GuiEnginePeatMixin",
                   "forestry.energy.gui.GuiGeneratorMixin",
                   "forestry.factory.gui.GuiBottlerMixin",
                   "forestry.factory.gui.GuiCarpenterMixin",
                   "forestry.factory.gui.GuiCentrifugeMixin",
                   "forestry.factory.gui.GuiFabricatorMixin",
                   "forestry.factory.gui.GuiFermenterMixin",
                   "forestry.factory.gui.GuiMoistenerMixin",
                   "forestry.factory.gui.GuiRaintankMixin",
                   "forestry.factory.gui.GuiSqueezerMixin",
                   "forestry.factory.gui.GuiStillMixin",
                   "forestry.factory.gui.GuiWorktableMixin",
                    // "forestry.factory.nei.CachedBottlerRecipeMixin"
                    // "forestry.factory.nei.CachedCarpenterRecipeMixin"
                    // "forestry.factory.nei.CachedFermenterRecipeMixin"
                    // "forestry.factory.nei.CachedMoistenerRecipeMixin"
                    // "forestry.factory.nei.CachedSqueezerRecipeMixin"
                    // "forestry.factory.nei.CachedStillRecipeMixin"
                   "forestry.farming.gui.GuiFarmMixin")),
    // endregion
    // region ForgeBackpacks
    ForgeBackpacks(
            Phase.LATE,
            require(TargetMod.ForgeBackpacks),
            common("backpack.BackpackSaveMixin",
                   "backpack.ContainerAdvancedMixin",
                   "backpack.FactoryBackpackNormalMixin",
                   "backpack.FactoryPersonalSlotMixin",
                   "backpack.InventoryPickupMixin")
            //todo finish these
            //common("backpack.FactoryWorkbenchBackpackMixin")
            //client("backpack.FactoryWorkbenchBackpackMixin")
    ),
    // endregion
    // region GalactiCraft
    GalactiCraft(
            Phase.EARLY,
            require(TargetMod.GalactiCraft),
            common("dws.galacticraft.ContainerPlayerMixin",
                   "galacticraft.GalacticWearableMixin",
                   "galacticraft.ContainerExtendedInventoryMixin")),
    // endregion
    // region GardenCore
    GardenCore(
            Phase.LATE,
            require(TargetMod.GardenCore),
            common("gardencore.ContainerCompostBinMixin"),
            client("gardencore.GuiCompostBinMixin")),
    // endregion
    // region GregTech5U
    GregTech5U(
            Phase.LATE,
            require(TargetMod.GregTech5U),
            common("gregtech5u.GT_ContainerMixin",
                   "gregtech5u.GT_Container_1by1Mixin",
                   "gregtech5u.GT_Container_2by2Mixin",
                   "gregtech5u.GT_Container_3by3Mixin",
                   "gregtech5u.GT_Container_4by4Mixin",
                   "gregtech5u.GT_Container_BasicMachineMixin",
                   "gregtech5u.GT_Container_BasicTankMixin",
                   "gregtech5u.GT_Container_BoilerMixin",
                   "gregtech5u.GT_Container_ChestBufferMixin",
                   "gregtech5u.GT_Container_FilterMixin",
                   "gregtech5u.GT_Container_ItemDistributorMixin",
                   "gregtech5u.GT_Container_MaintenanceHatchMixin",
                   "gregtech5u.GT_Container_MetaTileMachineMixin",
                   "gregtech5u.GT_Container_MicrowaveEnergyTransmitterMixin",
                   "gregtech5u.GT_Container_MultiMachineMixin",
                   "gregtech5u.GT_Container_OutputHatchMixin",
                   "gregtech5u.GT_Container_PrimitiveBlastFurnaceMixin",
                   "gregtech5u.GT_Container_QuantumChestMixin",
                   "gregtech5u.GT_Container_RegulatorMixin",
                   "gregtech5u.GT_Container_SuperBufferMixin",
                   "gregtech5u.GT_Container_TeleporterMixin",
                   "gregtech5u.GT_Container_TypeFilterMixin",
                    // "gregtech5u.GT_NEI_DefaultHandlerMixin"
                   "gregtech5u.GT_RectHandlerMixin"),
            client("gregtech5u.GT_GUIContainerMetaTile_MachineMixin",
                   "gregtech5u.GT_GUIContainerVolumetricFlaskMixin",
                   "gregtech5u.GT_GUIContainer_BasicMachineMixin",
                   "gregtech5u.GT_GUIContainer_BasicTankMixin",
                   "gregtech5u.GT_GUIContainer_BoilerMixin",
                   "gregtech5u.GT_GUIContainer_FusionReactorMixin",
                   "gregtech5u.GT_GUIContainer_MicrowaveEnergyTransmitterMixin",
                   "gregtech5u.GT_GUIContainer_MultiMachineMixin",
                   "gregtech5u.GT_GUIContainer_OutputHatchMixin",
                   "gregtech5u.GT_GUIContainer_PrimitiveBlastFurnaceMixin",
                   "gregtech5u.GT_GUIContainer_QuantumChestMixin",
                   "gregtech5u.GT_GUIContainer_RegulatorMixin",
                   "gregtech5u.GT_GUIContainer_TeleporterMixin")),
    // endregion
    // region GregTech6
    GregTech6(
            Phase.EARLY,
            require(TargetMod.GregTech6),
            common("gregtech6.ContainerCommonBasicMachineMixin",
                   "gregtech6.ContainerCommonChestMixin",
                   "gregtech6.ContainerCommonMixin",
                   "gregtech6.MultiTileEntityGUICommonAdvancedCraftingTableMixin",
                   "gregtech6.GT_RectHandlerMixin"),
            client("gregtech6.ContainerClientBasicMachineMixin",
                   "gregtech6.ContainerClientMixin")),
    // endregion
    // region IndustrialCraft2
    IndustrialCraft2(
            Phase.EARLY,
            require(TargetMod.IndustrialCraft2),
            common("ic2.ContainerBaseMixin",
                   "ic2.ContainerIronFurnaceMixin"),
            client("ic2.GuiIronFurnaceMixin")),
    // endregion
    // region InventoryTweaks
    InventoryTweaks(
            Phase.EARLY,
            require(TargetMod.InventoryTweaks),
            common("inventorytweaks.InvTweaksConfigInventoryRulesetMixin",
                   "inventorytweaks.InvTweaksContainerManagerMixin",
                    // "inventorytweaks.InvTweaksContainerSectionManagerMixin"
                   "inventorytweaks.InvTweaksMixin",
                   "inventorytweaks.VanillaSlotMapsMixin")),
    // endregion
    // region IronChest
    IronChest(
            Phase.LATE,
            require(TargetMod.IronChest),
            common("ironchest.ContainerIronChestMixin"),
            client("ironchest.GUIChestMixin")),
    // endregion
    // region Mariculture
    Mariculture(
            Phase.EARLY,
            require(TargetMod.Mariculture),
            common("mariculture.ContainerCrucibleMixin",
                   "mariculture.ContainerMaricultureMixin"),
            client("mariculture.GuiCrucibleMixin",
                   "mariculture.GuiMaricultureMixin",
                   "mariculture.feature.FeatureGeneralMixin",
                   "mariculture.feature.FeatureUpgradesMixin")),
    // endregion
    // region Natura
    Natura(
            Phase.LATE,
            require(TargetMod.Natura),
            common("natura.NGuiHandlerMixin"),
            client("natura.FurnaceGuiMixin")),
    // endregion
    // region NotEnoughItems
    NotEnoughItems(
            Phase.EARLY,
            require(TargetMod.NotEnoughItems),
            common("nei.ContainerCreativeInvMixin",
                   "nei.ExtendedCreativeInvMixin",
                   "nei.NEIServerUtilsMixin",
                   "nei.PlayerSaveMixin",
                   "nei.TemplateRecipeHandlerMixin"),
            client("nei.DefaultOverlayHandlerMixin",
                   "nei.GuiExtendedCreativeInvMixin",
                   "nei.LayoutManagerMixin",
                   "nei.NEIClientConfigMixin",
                   "nei.NEIClientUtilsMixin")),
    // endregion
    // region RailCraft
    RailCraft(
            Phase.LATE,
            require(TargetMod.RailCraft),
            common("railcraft.ContainerBoilerFluidMixin",
                   "railcraft.ContainerBoilerSolidMixin",
                   "railcraft.ContainerCokeOvenMixin",
                   "railcraft.ContainerSteamOvenMixin",
                   "railcraft.ContainerTankMixin",
                   "railcraft.RailcraftContainerMixin"),
            client("railcraft.GuiBoilerFluidMixin",
                   "railcraft.GuiBoilerSolidMixin",
                   "railcraft.GuiCokeOvenMixin",
                   "railcraft.GuiSteamOvenMixin",
                   "railcraft.GuiTankMixin")),
    // endregion
    // region StorageDrawers
    StorageDrawers(
            Phase.LATE,
            require(TargetMod.StorageDrawers),
            common("storagedrawers.ContainerDrawersMixin",
                   "storagedrawers.ContainerFramingTableMixin"),
            client("storagedrawers.GuiDrawersMixin",
                   "storagedrawers.GuiFramingMixin")),
    // endregion
    // region TecTech
    TecTech(
            Phase.LATE,
            require(TargetMod.TecTech),
            common("tectech.GT_RectHandlerMixin",
                   "tectech.TecTech_NEI_HandlerMixin")),
    // endregion
    // region ThaumCraft
    ThaumCraft(
            Phase.LATE,
            require(TargetMod.ThaumCraft),
            common("thaumcraft.ContainerAlchemyFurnaceMixin",
                   "thaumcraft.ContainerArcaneWorkbenchMixin",
                   "thaumcraft.ContainerFocusPouchMixin",
                   "thaumcraft.ContainerGolemMixin",
                   "thaumcraft.ContainerResearchTableMixin",
                   "thaumcraft.ContainerThaumatoriumMixin",
                   "thaumcraft.WandManagerMixin"),
            client("thaumcraft.GuiAlchemyFurnaceMixin",
                   "thaumcraft.GuiArcaneWorkbenchMixin",
                   "thaumcraft.GuiFocusPouchMixin",
                   "thaumcraft.GuiGolemMixin",
                   "thaumcraft.GuiResearchTableMixin",
                   "thaumcraft.GuiThaumatoriumMixin",
                   "thaumcraft.REHWandHandlerMixin")),
    // endregion
    // region TinkersConstruct
    TinkersConstructEarly(
            Phase.EARLY,
            require(TargetMod.TinkersConstruct),
            common("dws.tinkersconstruct.ContainerPlayerMixin")),
    // endregion
    // region TravellersGear
    TravellersGearEarly(
            Phase.EARLY,
            require(TargetMod.TravellersGear),
            common("dws.travellersgear.ContainerPlayerMixin")),
    TravellersGear(
            Phase.LATE,
            require(TargetMod.TravellersGear),
            client("travellersgear.ClientProxyMixin")),
    // endregion

    // @formatter:on
    //region boilerplate
    ;
    @Getter
    private final MixinBuilder builder;

    Mixin(Phase phase, SidedMixins... mixins) {
        this(builder(mixins).setPhase(phase));
    }

    Mixin(Phase phase, BooleanSupplier cond, SidedMixins... mixins) {
        this(builder(cond, mixins).setPhase(phase));
    }

    Mixin(Phase phase, TaggedMod mod, SidedMixins... mixins) {
        this(builder(mod, mixins).setPhase(phase));
    }

    Mixin(Phase phase, TaggedMod[] mods, SidedMixins... mixins) {
        this(builder(mods, mixins).setPhase(phase));
    }

    Mixin(Phase phase, BooleanSupplier cond, TaggedMod mod, SidedMixins... mixins) {
        this(builder(cond, mod, mixins).setPhase(phase));
    }

    Mixin(Phase phase, BooleanSupplier cond, TaggedMod[] mods, SidedMixins... mixins) {
        this(builder(cond, mods, mixins).setPhase(phase));
    }

    private static SidedMixins common(@Language(value = "JAVA",
                                                prefix = "import " + Tags.ROOT_PKG + ".mixin.mixins.common.",
                                                suffix = ";") String... mixins) {
        return MixinHelper.common(mixins);
    }

    private static SidedMixins client(@Language(value = "JAVA",
                                                prefix = "import " + Tags.ROOT_PKG + ".mixin.mixins.client.",
                                                suffix = ";") String... mixins) {
        return MixinHelper.client(mixins);
    }

    private static SidedMixins server(@Language(value = "JAVA",
                                                prefix = "import " + Tags.ROOT_PKG + ".mixin.mixins.server.",
                                                suffix = ";") String... mixins) {
        return MixinHelper.server(mixins);
    }
    //endregion
}
