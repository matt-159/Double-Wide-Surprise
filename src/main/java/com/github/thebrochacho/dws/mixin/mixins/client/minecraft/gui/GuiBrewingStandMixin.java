package com.github.thebrochacho.dws.mixin.mixins.client.minecraft.gui;

import com.github.thebrochacho.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntityBrewingStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiBrewingStand.class)
public abstract class GuiBrewingStandMixin extends GuiContainer implements IDWSGui {
    @Shadow private TileEntityBrewingStand tileBrewingStand;

    public GuiBrewingStandMixin(Container container) {
        super(container);
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = {    @Constant(intValue = 65),
                                    @Constant(intValue = 97)    },
                    require = 1)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = {    @Constant(intValue = 176),
                                    @Constant(intValue = 185)   },
                    require = 2)
    private int modifyUVXOffset(int constant) {
        return constant + 162;
    }
}
