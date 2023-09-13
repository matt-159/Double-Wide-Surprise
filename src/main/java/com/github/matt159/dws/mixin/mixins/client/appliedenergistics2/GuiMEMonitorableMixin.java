package com.github.matt159.dws.mixin.mixins.client.appliedenergistics2;

import appeng.client.gui.AEBaseMEGui;
import appeng.client.gui.implementations.GuiMEMonitorable;
import appeng.integration.IntegrationRegistry;
import appeng.integration.IntegrationType;
import net.minecraft.inventory.Container;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMEMonitorable.class)
public abstract class GuiMEMonitorableMixin extends AEBaseMEGui {
    @Shadow(remap = false)
    private int perRow;

    @Shadow(remap = false)
    private int standardSize;

    public GuiMEMonitorableMixin(Container container) {
        super(container);
    }

    @Inject(method = "initGui",
            at = @At(value = "INVOKE",
                     target = "Lappeng/integration/IntegrationRegistry;isEnabled(Lappeng/integration/IntegrationType;)Z"),
            require = 1)
    private void injectPerRowValue(CallbackInfo ci) {
        this.perRow = 13;
    }

    @ModifyArg(method = "initGui",
               at = @At(value = "INVOKE",
                        target = "Lappeng/client/me/InternalSlotME;<init>(Lappeng/client/me/ItemRepo;III)V"),
               index = 2,
               require = 1)
    private int modifyInternalMESlotXOffset(int xOffset) {
        return xOffset + 45;
    }

    @ModifyConstant(method = { "<init>(Lnet/minecraft/entity/player/InventoryPlayer;Lappeng/api/storage/ITerminalHost;Lappeng/container/implementations/ContainerMEMonitorable;)V",
                               "drawBG" },
                    constant = { @Constant(intValue = 185),
                                 @Constant(intValue = 197) },
                    remap = false,
                    require = 7)
    private int modifyXSize(int constant) {
        return 340;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 197),
                    require = 1)
    private int modifyXSizeCheck(int constant) {
        return 340;
    }

    @Redirect(method = "initGui",
              at = @At(value = "FIELD",
                       target = "Lappeng/client/gui/implementations/GuiMEMonitorable;xSize:I",
                       opcode = Opcodes.PUTFIELD),
              require = 1)
    private void redirectXSizeAssignment(GuiMEMonitorable instance, int value) {
        this.xSize = this.standardSize;
    }

    @ModifyConstant(method = "setScrollBar",
                    constant = @Constant(intValue = 175),
                    remap = false,
                    require = 1)
    private int modifyScrollBarXOffset(int constant) {
        return constant + 117;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 80),
                    require = 1)
    private int modifySearchBarXOffset(int constant) {
        return constant + 45;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 90),
                    require = 1)
    private int modifySearchBarWidth(int constant) {
        return 162;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 25),
                    require = 1)
    private int modifyMaxSearchBarStringLength(int constant) {
        return 26;
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 170),
                    require = 1)
    private int modifyCraftingStatusButtonXOffset(int constant) {
        return this.xSize - 22 - 38;
    }

    @Redirect(method = "initGui",
              at = @At(value = "INVOKE",
                       target = "Lappeng/integration/IntegrationRegistry;isEnabled(Lappeng/integration/IntegrationType;)Z"),
              require = 1)
    private boolean redirectNEIIsEnabled(IntegrationRegistry instance, IntegrationType type) {
        return true;
    }
}
