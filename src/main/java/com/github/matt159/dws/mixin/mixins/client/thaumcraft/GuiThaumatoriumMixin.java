package com.github.matt159.dws.mixin.mixins.client.thaumcraft;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.Constants;
import lombok.val;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thaumcraft.client.gui.GuiThaumatorium;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

@Mixin(GuiThaumatorium.class)
public abstract class GuiThaumatoriumMixin extends GuiContainer implements IDWSGui {
    public GuiThaumatoriumMixin(Container container) {
        super(container);
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = { @Constant(intValue = 176),
                                 @Constant(intValue = 192),
                                 @Constant(intValue = 184),
                                 @Constant(intValue = 200) },
                    require = 10)
    private int modifyTextureUVXOffset2(int constant) {
        val offset = constant - 176;

        return offset + Constants.GENERAL_DWS_GUI_WIDTH;
    }

    @ModifyConstant(method = "drawAspects",
                    constant = @Constant(intValue = 176),
                    remap = false,
                    require = 2)
    private int modifyTextureUVXOffset(int constant) {
        return Constants.GENERAL_DWS_GUI_WIDTH;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = { @Constant(intValue = 32),
                                 @Constant(intValue = 88),
                                 @Constant(intValue = 104),
                                 @Constant(intValue = 128),
                                 @Constant(intValue = 136) },
                    require = 15)
    private int modifyTextureDrawXOffsets(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawAspects",
                    constant = { @Constant(intValue = 40, ordinal = 0),
                                 @Constant(intValue = 41),
                                 @Constant(intValue = 42) },
                    remap = false,
                    require = 3)
    private int modifyAspectXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawOutput",
                    constant = { @Constant(intValue = 112) },
                    remap = false,
                    require = 3)
    private int modifyOutputXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "mouseClicked",
                    constant = { @Constant(intValue = 112),
                                 @Constant(intValue = 128),
                                 @Constant(intValue = 32),
                                 @Constant(intValue = 136) },
                    require = 5)
    private int modifyMouseClickRegionXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }
}
