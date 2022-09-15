package com.github.matt159.dws.mixin.mixins.client.minecraft.gui;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.DWSUtil;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreenHorseInventory.class)
public abstract class GuiScreenHorseInventoryMixin extends GuiContainer implements IDWSGui {

    public GuiScreenHorseInventoryMixin(Container container) {
        super(container);
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = {    @Constant(intValue = 79),
                                    @Constant(intValue = 7),
                                    @Constant(intValue = 51)    },
                    require = 3)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }
}
