package com.github.thebrochacho.dws.mixins.plugin;

import com.github.thebrochacho.dws.Tags;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import org.spongepowered.asm.mixin.throwables.MixinException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.github.thebrochacho.dws.mixins.plugin.TargetedMod.*;

public enum Mixin {

    // Vanilla
    CreativeTabsMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.CreativeTabsMixin")),
    EntityPlayerMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.EntityPlayerMixin")),
    InventoryPlayerMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.inventory.InventoryPlayerMixin")),
    MinecraftMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.MinecraftMixin")),
    NetHandlerPlayServerMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.NetHandlerPlayServerMixin")),
    TextureManagerMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.TextureManagerMixin")),

    ContainerBeaconMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.inventory.ContainerBeaconMixin")),
    ContainerBrewingStandMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.inventory.ContainerBrewingStandMixin")),
    ContainerChestMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.inventory.ContainerChestMixin")),
    ContainerCreativeMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.inventory.ContainerCreativeMixin")),
    ContainerDispenserMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.inventory.ContainerDispenserMixin")),
    ContainerEnchantmentMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.inventory.ContainerEnchantmentMixin")),
    ContainerFurnaceMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.inventory.ContainerFurnaceMixin")),
    ContainerHopperMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.inventory.ContainerHopperMixin")),
    ContainerHorseInventoryMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.inventory.ContainerHorseInventoryMixin")),
    ContainerMerchantMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.inventory.ContainerMerchantMixin")),
    ContainerRepairMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.inventory.ContainerRepairMixin")),
    ContainerWorkbenchMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.inventory.ContainerWorkbenchMixin")),

    GuiContainerMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiContainerMixin")),
    GuiContainerCreativeMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiContainerCreativeMixin")),
    GuiMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiMixin")),

    GuiBeaconMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiBeaconMixin")),
    GuiBrewingStandMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiBrewingStandMixin")),
    GuiEnchantmentMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiEnchantmentMixin")),
    GuiFurnaceMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiFurnaceMixin")),
    GuiInventoryMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiInventoryMixin")),
    GuiMerchantMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiMerchantMixin")),
    GuiRepairMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiRepairMixin")),
    GuiScreenHorseInventoryMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiScreenHorseInventoryMixin")),

    //CodeChickenLib
    InventoryRangeMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "codechickenlib.InventoryRangeMixin")),

    //NEI
    ClientUtilsMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "nei.NEIClientUtilsMixin")),
    ItemPanelMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "nei.ItemPanelMixin")),
    LayoutMangerMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "nei.LayoutManagerMixin")),

    //Galacticraft
//    IGalacticWearableMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "galacticraft.GalacticWearableMixin")),
    ContainerExtendedInventoryMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "galacticraft.ContainerExtendedInventoryMixin")),

    //TravellersGear
//    GuiButtonGearMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "travellersgear.GuiButtonGearMixin"));
    ClientProxyMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "travellersgear.ClientProxyMixin")),

    //Baubles
    GuiEventsMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "baubles.GuiEventsMixin")),

    //Ironchest
    ContainerIronChestMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "ironchest.ContainerIronChestMixin")),
    GUIChestMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "ironchest.GUIChestMixin")),

    //Gregtech
    GT_ContainerMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_ContainerMixin")),
    GT_Container_1by1Mixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_1by1Mixin")),
    GT_Container_2by2Mixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_2by2Mixin")),
    GT_Container_3by3Mixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_3by3Mixin")),
    GT_Container_4by4Mixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_4by4Mixin")),
    GT_Container_BasicMachineMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_BasicMachineMixin")),
    GT_Container_BasicTankMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_BasicTankMixin")),
    GT_Container_BoilerMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_BoilerMixin")),
    GT_Container_ChestBufferMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_ChestBufferMixin")),
    GT_Container_FilterMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_FilterMixin")),
    GT_Container_ItemDistributorMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_ItemDistributorMixin")),
    GT_Container_MaintenanceHatchMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_MaintenanceHatchMixin")),
    GT_Container_MicrowaveEnergyTransmitterMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_MicrowaveEnergyTransmitterMixin")),
    GT_Container_OutputHatchMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_OutputHatchMixin")),
    GT_Container_PrimitiveBlastFurnaceMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "gregtech.GT_Container_PrimitiveBlastFurnaceMixin")),

    GT_GUIContainerMetaTile_MachineMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "gregtech.GT_GUIContainerMetaTile_MachineMixin")),
    GT_GUIContainer_BasicMachine(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "gregtech.GT_GUIContainer_BasicMachineMixin")),
    GT_GUIContainer_BoilerMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "gregtech.GT_GUIContainer_BoilerMixin")),
    GT_GUIContainer_FusionReactorMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "gregtech.GT_GUIContainer_FusionReactorMixin")),
    GT_GUIContainer_IntegratedCircuitMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "gregtech.GT_GUIContainer_IntegratedCircuitMixin")),
    GT_GUIContainer_MicrowaveEnergyTransmitterMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "gregtech.GT_GUIContainer_MicrowaveEnergyTransmitterMixin")),
    GT_GUIContainer_MultiMachineMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "gregtech.GT_GUIContainer_MultiMachineMixin")),
    GT_GUIContainer_OutputHatchMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "gregtech.GT_GUIContainer_OutputHatchMixin")),
    GT_GUIContainer_PrimitiveBlastFurnaceMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "gregtech.GT_GUIContainer_PrimitiveBlastFurnaceMixin")),

    GT_RectHandlerMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "gregtech.GT_RectHandlerMixin")),
    ;

    public final MixinUnit[] units;
    public final Set<TargetedMod> targetedMods;
    private final Side side;

    Mixin(Builder builder) {
        this.units = builder.units.toArray(new MixinUnit[0]);
        this.targetedMods = builder.targetedMods;
        this.side = builder.side;
    }

    public boolean shouldLoad(List<TargetedMod> loadedMods) {
        return (side == Side.COMMON
                || side == Side.SERVER && FMLLaunchHandler.side().isServer()
                || side == Side.CLIENT && FMLLaunchHandler.side().isClient())
                && loadedMods.containsAll(targetedMods);
    }

    public String getBestAlternativeForTier(CompatibilityTier tier) {
        for (MixinUnit unit: units) {
            if (unit.tier.isTierBetterThan(tier)) return unit.mixinClass;
        }
        throw new MixinException("Failed to retrieve mixin alternative for " + this.name() + " in mod " + Tags.MODID);
    }

    private static Builder builder(Side side) {
        return new Builder(side).target(VANILLA)
                                .target(GALACTICRAFT)
                                .target(TRAVELLERSGEAR)
                                .target(IRONCHEST)
                                .target(BAUBLES)
                                .target(TINKERS)
                                .target(CODECHICKENLIB);
    }

    private static class Builder {
        public final ArrayList<MixinUnit> units = new ArrayList<>();
        public final Side side;
        public final Set<TargetedMod> targetedMods = new HashSet<>();

        public Builder(Side side) {
            this.side = side;
        }

        public Builder unit(CompatibilityTier tier, String mixinClass) {
            units.add(new MixinUnit(tier, side.name().toLowerCase() + "." + mixinClass));
            return this;
        }

        public Builder target(TargetedMod mod) {
            targetedMods.add(mod);
            return this;
        }
    }

    private static class MixinUnit {
        public final CompatibilityTier tier;
        public final String mixinClass;

        public MixinUnit(CompatibilityTier tier, String mixinClass) {
            this.tier = tier;
            this.mixinClass = mixinClass;
        }
    }

    private enum Side {
        COMMON,
        CLIENT,
        SERVER
    }
}

