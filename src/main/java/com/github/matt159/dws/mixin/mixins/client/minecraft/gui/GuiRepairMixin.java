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

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 62),
                    require = 1)
    private int modifyTextXOffset(int constant) {
        return 143;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 59),
                    require = 1)
    private int modifyTextFieldXOffset(int constant) {
        return 140;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 99),
                    require = 1)
    private int modifyArrowXOffset(int constant) {
        return 180;
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 60),
                    require = 1)
    private int modifyDisplayTextXOffset(int constant) {
        return 141;
    }
}
