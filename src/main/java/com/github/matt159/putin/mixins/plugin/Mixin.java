package com.github.matt159.putin.mixins.plugin;

import com.github.matt159.putin.Tags;
import cpw.mods.fml.relauncher.FMLLaunchHandler;
import org.spongepowered.asm.mixin.throwables.MixinException;

import java.util.*;

import static com.github.matt159.putin.mixins.plugin.TargetedMod.VANILLA;

public enum Mixin {

    // Vanilla
    EntityPlayerMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.EntityPlayerMixin")),

    GuiMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiMixin")),

    GuiChestMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiChestMixin")),
    ContainerChestMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.ContainerChestMixin")),

    GuiCraftingMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiCraftingMixin")),
    ContainerWorkbenchMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.ContainerWorkbenchMixin")),

    GuiFurnaceMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiFurnaceMixin")),
    ContainerFurnaceMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.ContainerFurnaceMixin")),

    GuiHopperMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiHopperMixin")),
    ContainerHopperMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.ContainerHopperMixin")),

    GuiDispenserMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiDispenserMixin")),
    ContainerDispenserMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.ContainerDispenserMixin")),

    GuiBrewingStandMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiBrewingStandMixin")),
    ContainerBrewingStandMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.ContainerBrewingStandMixin")),

    GuiScreenHorseInventoryMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiScreenHorseInventoryMixin")),
    ContainerHorseInventoryMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.ContainerHorseInventoryMixin")),

    CreativeTabsMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.CreativeTabsMixin")),

    GuiContainerCreativeMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiContainerCreativeMixin")),
    ContainerCreativeMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.ContainerCreativeMixin")),

    GuiEnchantmentMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiEnchantmentMixin")),
    ContainerEnchantmentMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.ContainerEnchantmentMixin")),

    GuiRepairMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "minecraft.GuiRepairMixin")),
    ContainerRepairMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "minecraft.ContainerRepairMixin")),

    //Forge
    //NetworkRegistryMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "forge.NetworkRegistryMixin")),

    //NEI
    ClientUtilsMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "nei.ClientUtilsMixin")),

    //Galacticraft
    IGalacticWearableMixin(builder(Side.COMMON).unit(CompatibilityTier.Regular, "galacticraft.GalacticWearableMixin")),

    //TravellersGear
    GuiButtonGearMixin(builder(Side.CLIENT).unit(CompatibilityTier.Regular, "travellersgear.GuiButtonGearMixin"));

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
        return new Builder(side).target(VANILLA);
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

