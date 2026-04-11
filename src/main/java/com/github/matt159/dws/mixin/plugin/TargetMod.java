package com.github.matt159.dws.mixin.plugin;

import com.gtnewhorizon.gtnhmixins.builders.ITargetMod;
import com.gtnewhorizon.gtnhmixins.builders.TargetModBuilder;
import lombok.Getter;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public enum TargetMod implements ITargetMod {
    //coremods
    AppliedEnergistics2("appeng.transformer.AppEngCore"),
    EnderCore("com.enderio.core.common.transform.EnderCorePlugin"),
    GalactiCraft("micdoodle8.mods.galacticraft.core.asm.GCLoadingPlugin"),
    GregTech6("gregtech.asm.GT_ASM"),
    IndustrialCraft2("ic2.core.coremod.IC2core"),
    InventoryTweaks("invtweaks.forge.asm.FMLPlugin"),
    Mariculture("mariculture.fishery.VanillaOverride"),
    NotEnoughItems("codechicken.nei.asm.NEICorePlugin"),
    //regular mods
    Agricraft("com.InfinityRaider.AgriCraft.AgriCraft"),
    Baubles("baubles.common.Baubles"),
    Chisel("team.chisel.Chisel"),
    CodeChickenLib("codechicken.lib.asm.ASMHelper"),
    Forestry("forestry.Forestry"),
    ForgeBackpacks("de.eydamos.backpack.Backpack"),
    GardenCore("com.jaquadro.minecraft.gardencore.GardenCore"),
    GregTech5U("gregtech.GT_Mod"),
    IronChest("cpw.mods.ironchest.IronChest"),
    Natura("mods.natura.Natura"),
    RailCraft("mods.railcraft.common.core.Railcraft"),
    StorageDrawers("com.jaquadro.minecraft.storagedrawers.StorageDrawers"),
    TecTech("com.github.technus.tectech.TecTech"),
    ThaumCraft("thaumcraft.common.Thaumcraft"),
    TinkersConstruct("tconstruct.TConstruct"),
    TravellersGear("travellersgear.TravellersGear"),
    ;
    @Getter
    private final TargetModBuilder builder;

    TargetMod(@Language(value = "JAVA",
                        prefix = "import ",
                        suffix = ";") @NotNull String className) {
        this(className, null);
    }

    TargetMod(@Language(value = "JAVA",
                        prefix = "import ",
                        suffix = ";") @NotNull String className, @Nullable Consumer<TargetModBuilder> cfg) {
        builder = new TargetModBuilder();
        builder.setTargetClass(className);
        if (cfg != null) {
            cfg.accept(builder);
        }
    }
}
