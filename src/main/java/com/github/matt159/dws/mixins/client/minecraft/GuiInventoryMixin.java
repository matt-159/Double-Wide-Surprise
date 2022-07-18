package com.github.matt159.dws.mixins.client.minecraft;

import com.github.matt159.dws.interfaces.IDWSGui;
import net.minecraft.client.gui.inventory.GuiInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiInventory.class)
public abstract class GuiInventoryMixin implements IDWSGui {
    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 86),
                    require = 1)
    private int modifyDrawStringXOffset(int constant) {
        return constant + 162;
    }
}
