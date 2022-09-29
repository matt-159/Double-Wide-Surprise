package com.github.thebrochacho.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.AEBaseGui;
import appeng.client.gui.implementations.GuiInterfaceTerminal;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(GuiInterfaceTerminal.class)
public abstract class GuiInterfaceTerminalMixin extends AEBaseGui {
    public GuiInterfaceTerminalMixin(Container container) {
        super(container);
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 195),
                    remap = false,
                    require = 1)
    private int modifyXSize(int constant) {
        return constant + 143;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 175),
                    remap = false,
                    require = 1)
    private int modifyScrollbarXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = { "drawFG",
                               "drawBG" },
                    constant = { @Constant(intValue = 7,
                                           ordinal = 0),
                                 @Constant(intValue = 8,
                                           ordinal = 2),
                                 @Constant(intValue = 10) },
                    remap = false,
                    require = 3)
    private int modifySlotXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 104),
                    remap = false,
                    require = 1)
    private int modifySearchBarXOffset(int constant) {
        return 128;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 65),
                    remap = false,
                    require = 1)
    private int modifySearchBarWidth(int constant) {
        return 122;
    }
}
