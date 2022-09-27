package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.api.implementations.IUpgradeableHost;
import appeng.client.gui.implementations.GuiDrive;
import appeng.client.gui.implementations.GuiFormationPlane;
import appeng.client.gui.implementations.GuiUpgradeable;
import appeng.client.gui.widgets.GuiTabButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiFormationPlane.class)
public abstract class GuiFormationPlaneMixin extends GuiUpgradeable {

    @Shadow(remap = false)
    private GuiTabButton priority;

    public GuiFormationPlaneMixin(InventoryPlayer inventoryPlayer, IUpgradeableHost te) {
        super(inventoryPlayer, te);
    }

    @Redirect(method = "addButtons",
              at = @At(value = "FIELD",
                       target = "Lappeng/client/gui/implementations/GuiFormationPlane;priority:Lappeng/client/gui/widgets/GuiTabButton;",
                       opcode = Opcodes.PUTFIELD),
              remap = false,
              require = 1)
    private void redirectPriorityLocation(GuiFormationPlane instance, GuiTabButton value) {
        value.xPosition = this.guiLeft + this.xSize - 22 - (35 * (this.hasToolbox() ? 2 : 1));
        this.priority = value;
    }
}
