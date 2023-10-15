package com.github.matt159.dws.mixin.mixins.client.thaumcraft;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.Constants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.client.gui.GuiAlchemyFurnace;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

@Mixin(GuiAlchemyFurnace.class)
public abstract class GuiAlchemyFurnaceMixin extends GuiContainer implements IDWSGui {
    public GuiAlchemyFurnaceMixin(Container container) {
        super(container);
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = {
                        @Constant(intValue = 80),
                        @Constant(intValue = 106),
                        @Constant(intValue = 60),
                        @Constant(intValue = 61)
                    },
                    require = 4)
    private int modifyXOffsets(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = {
                        @Constant(intValue = 176),
                        @Constant(intValue = 216),
                        @Constant(intValue = 200),
                        @Constant(intValue = 232)
                    },
                    require = 4)
    private int modifyTextureUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH + (constant - 176);
    }
}
