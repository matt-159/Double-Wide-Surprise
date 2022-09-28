package com.github.thebrochacho.dws.mixin.mixins.client.appliedenergistics2;

import appeng.api.implementations.IUpgradeableHost;
import appeng.client.gui.implementations.GuiInterface;
import appeng.client.gui.implementations.GuiUpgradeable;
import appeng.client.gui.widgets.GuiTabButton;
import net.minecraft.entity.player.InventoryPlayer;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GuiInterface.class)
public abstract class GuiInterfaceMixin extends GuiUpgradeable {
    @Shadow(remap = false)
    private GuiTabButton priority;

    public GuiInterfaceMixin(InventoryPlayer inventoryPlayer, IUpgradeableHost te) {
        super(inventoryPlayer, te);
    }

    @Redirect(method = "addButtons",
              at = @At(value = "FIELD",
                       target = "Lappeng/client/gui/implementations/GuiInterface;priority:Lappeng/client/gui/widgets/GuiTabButton;",
                       opcode = Opcodes.PUTFIELD),
              remap = false,
              require = 1)
    private void redirectPriorityButtonAssignment(GuiInterface instance, GuiTabButton value) {
        value.xPosition = this.guiLeft + this.xSize - 22;
        this.priority = value;
    }
}
