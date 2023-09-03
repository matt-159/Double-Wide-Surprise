package com.github.matt159.dws.mixin.mixins.client.gardencore;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.Constants;
import com.jaquadro.minecraft.gardencore.client.gui.GuiCompostBin;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiCompostBin.class)
public abstract class GuiCompostBinMixin extends GuiContainer implements IDWSGui {
    public GuiCompostBinMixin(Container container) {
        super(container);
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyTextureWidth(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 89),
                    require = 1)
    private int modifyProgressBarXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
