package com.github.thebrochacho.dws.mixin.mixins.client.minecraft.gui;

import com.github.thebrochacho.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiFurnace.class)
public abstract class GuiFurnaceMixin extends GuiContainer implements IDWSGui {

    public GuiFurnaceMixin(Container container) {
        super(container);
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = {
                        @Constant(intValue = 56),
                        @Constant(intValue = 79)
                    },
                    require = 1)
    private int modifyXOffsets(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 176),
                    require = 1)
    private int modifyUVXOffset(int constant) {
        return 338;
    }
}