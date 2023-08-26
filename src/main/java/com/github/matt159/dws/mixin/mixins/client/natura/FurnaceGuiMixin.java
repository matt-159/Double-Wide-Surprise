package com.github.matt159.dws.mixin.mixins.client.natura;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.Constants;
import mods.natura.gui.FurnaceGui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FurnaceGui.class)
public abstract class FurnaceGuiMixin extends GuiContainer implements IDWSGui {
    public FurnaceGuiMixin(Container container) {
        super(container);
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
            constant = {
                    @Constant(intValue = 56),
                    @Constant(intValue = 79)
            },
            require = 2)
    private int modifyXOffsets(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
            constant = @Constant(intValue = 176),
            require = 2)
    private int modifyUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }
}
