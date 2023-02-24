package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.AEBaseGui;
import appeng.client.gui.implementations.GuiCraftAmount;
import appeng.client.gui.widgets.GuiNumberBox;
import appeng.client.gui.widgets.GuiTabButton;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(GuiCraftAmount.class)
public abstract class GuiCraftAmountMixin extends AEBaseGui {
    @Shadow(remap = false)
    private GuiTabButton originalGuiBtn;

    @Shadow(remap = false)
    private GuiNumberBox amountToCraft;

    public GuiCraftAmountMixin(Container container) {
        super(container);
    }

    @ModifyArgs(method = "initGui",
                at = @At(value = "INVOKE",
                         target = "Lnet/minecraft/client/gui/GuiButton;<init>(IIIIILjava/lang/String;)V"),
                require = 9)
    private void modifyGuiButtonArgs(Args args) {
        args.set(1, (int) args.get(1) + 78);
        args.set(2, (int) args.get(2) - 8);
    }

    @Redirect(method = "initGui",
              at = @At(value = "FIELD",
                       target = "Lappeng/client/gui/implementations/GuiCraftAmount;originalGuiBtn:Lappeng/client/gui/widgets/GuiTabButton;",
                       opcode = Opcodes.PUTFIELD),
              require = 1)
    private void redirectOriginalGuiBtnInstantiation(GuiCraftAmount instance, GuiTabButton value) {
        value.xPosition = this.guiLeft + this.xSize - 22;
        this.originalGuiBtn = value;
    }

    @Redirect(method = "initGui",
              at = @At(value = "FIELD",
                       target = "Lappeng/client/gui/implementations/GuiCraftAmount;amountToCraft:Lappeng/client/gui/widgets/GuiNumberBox;",
                       opcode = Opcodes.PUTFIELD),
              require = 1)
    private void redirectNewTextboxPosition(GuiCraftAmount instance, GuiNumberBox value) {
        value.xPosition += 78;
        value.yPosition -= 8;
        this.amountToCraft = value;
    }
}
