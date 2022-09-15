package com.github.matt159.dws.mixin.mixins.client.minecraft.gui;

import com.github.matt159.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiRepair.class)
public abstract class GuiRepairMixin extends GuiContainer implements IDWSGui {

    public GuiRepairMixin(Container container) {
        super(container);
    }

    @ModifyConstant(method = {  "initGui",
                                "drawGuiContainerForegroundLayer",
                                "drawGuiContainerBackgroundLayer"   },
                    constant = {    @Constant(intValue = 59),
                                    @Constant(intValue = 60),
                                    @Constant(intValue = 62),
                                    @Constant(intValue = 99)    },
                    require = 4)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }
}
