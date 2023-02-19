package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.AEBaseGui;
import appeng.client.gui.implementations.GuiDrive;
import appeng.client.gui.widgets.GuiTabButton;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiDrive.class)
public abstract class GuiDriveMixin extends AEBaseGui {
    @Shadow(remap = false)
    private GuiTabButton priority;

    public GuiDriveMixin(Container container) {
        super(container);
    }

    @Redirect(method = "initGui",
              at = @At(value = "FIELD",
                       target = "Lappeng/client/gui/implementations/GuiDrive;priority:Lappeng/client/gui/widgets/GuiTabButton;",
                       opcode = Opcodes.PUTFIELD),
              remap = false,
              require = 1)
    private void redirectPriorityLocation(GuiDrive instance, GuiTabButton value) {
        value.xPosition = this.guiLeft + this.xSize - 22;
        this.priority = value;
    }
}
