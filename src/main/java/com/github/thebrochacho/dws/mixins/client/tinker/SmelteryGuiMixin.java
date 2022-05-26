package com.github.thebrochacho.dws.mixins.client.tinker;

import com.github.thebrochacho.dws.Tags;
import com.github.thebrochacho.dws.interfaces.IDWSGui;
import com.github.thebrochacho.dws.util.DWSUtil;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tconstruct.smeltery.gui.ActiveContainerGui;
import tconstruct.smeltery.gui.SmelteryGui;
import tconstruct.smeltery.inventory.ActiveContainer;
import tconstruct.smeltery.inventory.SmelteryContainer;
import tconstruct.smeltery.logic.SmelteryLogic;

@Mixin(SmelteryGui.class)
public abstract class SmelteryGuiMixin extends ActiveContainerGui implements IDWSGui {

    @Shadow(remap = false)
    @Final
    private int smelterySize;

    @Shadow(remap = false)
    private boolean isScrolling;

    @Shadow(remap = false)
    private float currentScroll;

    @Shadow(remap = false)
    private int slotPos;

    @Shadow(remap = false)
    private int prevSlotPos;

    @Shadow(remap = false)
    private boolean wasClicking;

    @Shadow(remap = false)
    public SmelteryLogic logic;

    @Shadow(remap = false)
    @Final
    @Mutable
    public static int maxRows = 3;

    @Shadow(remap = false)
    @Final
    @Mutable
    private int columns;

    private static final ResourceLocation SMELTERY_GUI = new ResourceLocation(Tags.MODID, "textures/tinker/smeltery.png");
    private static final ResourceLocation SMELTERY_SIDE = new ResourceLocation(Tags.MODID, "textures/tinker/smelteryside.png");

    public SmelteryGuiMixin(ActiveContainer activeContainer) {
        super(activeContainer);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            remap = false,
            require = 1)
    private void setDWSGuiSize(InventoryPlayer inventoryplayer, SmelteryLogic smeltery, World world, int x, int y, int z, CallbackInfo ci) {
        this.xSize = 338;
        this.ySize = 166;

        columns = 10;
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V",
                            ordinal = 0),
                remap = false,
                require = 1)
    private void redirectBindTexture(TextureManager instance, ResourceLocation resourceLocation) {
        instance.bindTexture(SMELTERY_GUI);
    }

    @ModifyVariable(method = {  "drawGuiContainerBackgroundLayer",
                                "drawGuiContainerForegroundLayer",
                                "mouseClicked"  },
                    at = @At("STORE"),
                    name = "cornerX",
                    remap = false,
                    require = 1)
    private int modifyCornerX(int cornerX) {
        return (this.width - this.xSize) / 2;
    }

    @ModifyVariable(method = "drawGuiContainerForegroundLayer",
                    at = @At("STORE"),
                    name = "baseX",
                    remap = false,
                    require = 1)
    private int modifyBaseX(int baseX) {
        return 8;
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Ltconstruct/smeltery/gui/SmelteryGui;drawTexturedModalRect(IIIIII)V",
                            ordinal = 0),
                remap = false,
                require = 1)
    private void redirectDrawCall(SmelteryGui instance, int x, int y, int u, int v, int w, int h) {
        x = (this.width - this.xSize) / 2;
        y = (this.height - this.ySize) / 2;

        DWSUtil.drawTexturedModalRect(x, y, u, v, this.xSize, this.ySize, zLevel);
    }

    @ModifyConstant(method = {  "drawGuiContainerBackgroundLayer",
                                "drawGuiContainerForegroundLayer"   },
                    constant = @Constant(intValue = 117),
                    remap = false,
                    require = 1)
    private int modifyFuelGaugeXOffset(int constant) {
        return 315;
    }

    @ModifyConstant(method = "drawGuiContainerForegroundLayer",
                    constant = @Constant(intValue = 36),
                    remap = false,
                    require = 1)
    private int modifyTooltipXOffset(int constant) {
        return -constant;
    }

    @ModifyConstant(method = {  "drawGuiContainerBackgroundLayer",
                                "drawGuiContainerForegroundLayer",
                                "mouseClicked"  },
                    constant = @Constant(  intValue = 54,
                                            ordinal = 0 ),
                    remap = false,
                    require = 1)
    private int modifyBasePos(int basePos) {
        return 252;
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V",
                            ordinal = 2),
                remap = false,
                require = 1)
    private void redirectBindTexture2(TextureManager instance, ResourceLocation resourceLocation) {
        instance.bindTexture(SMELTERY_GUI);
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Ltconstruct/smeltery/gui/SmelteryGui;drawTexturedModalRect(IIIIII)V",
                            ordinal = 1),
                remap = false,
                require = 1)
    private void redirectDrawCall2(SmelteryGui instance, int x, int y, int u, int v, int w, int h) {
        x = (this.width - this.xSize) / 2;
        y = (this.height - this.ySize) / 2;

        DWSUtil.drawTexturedModalRect(x + 252, y + 16, 338, 0, 52, 52, zLevel);
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V",
                            ordinal = 3),
                remap = false,
                require = 1)
    private void redirectBindTexture3(TextureManager instance, ResourceLocation resourceLocation) {
        instance.bindTexture(SMELTERY_SIDE);
    }

    @Inject(method = "drawGuiContainerBackgroundLayer",
            at = @At(   value = "INVOKE",
                        target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V",
                        ordinal = 3,
                        shift = At.Shift.AFTER),
            cancellable = true,
            remap = false,
            require = 1)
    private void renderSmelterySlots(float f, int mouseX, int mouseY, CallbackInfo ci) {
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;

        DWSUtil.drawTexturedModalRect(x, y, 0, 0, 251, 166, zLevel);

        int rows = smelterySize / 10;

        int sx = x + 232;
        int sy = (int) (y + 16 + 37 * currentScroll);

        if (smelterySize > 0) {
            int dx = x + 7;
            int dy = y + 15;

            int iter;
            int slotCount = Math.min(smelterySize, 3 * columns);

            for (iter = 0; iter < slotCount && iter + slotPos * columns < smelterySize; iter++) {
                int slotTemp = logic.getTempForSlot(iter + slotPos * columns) - 20;
                int maxTemp = logic.getMeltingPointForSlot(iter + slotPos * columns) - 20;
                if (slotTemp > 0 && maxTemp > 0)
                {
                    int size = 16 * slotTemp / maxTemp + 1;
                    DWSUtil.drawTexturedModalRect(x + 8 + (iter % columns) * 22, y + (iter / columns) * 18 + 32 - size, 251, 15 + 16 - size, 5, size, zLevel);
                }
            }

            int maxSlots = maxRows * 10;

            for (; iter < maxSlots; iter++){
                DWSUtil.drawTexturedModalRect(dx + (iter % columns) * 22, dy + (iter / columns) * 18, 251, 47, 22, 18, zLevel);
            }

            if (rows > 3) {
                if (isScrolling || (mouseX >= sx && mouseX <= sx + 12 && mouseY >= sy && mouseY <= sy + 15)) {
                    DWSUtil.drawTexturedModalRect(sx, sy, 275, 0, 12, 15, zLevel);
                } else {
                    DWSUtil.drawTexturedModalRect(sx, sy, 251, 0, 12, 15, zLevel);
                }
            } else {
                DWSUtil.drawTexturedModalRect(sx, sy, 263, 0, 12, 15, zLevel);
            }
        }

        ci.cancel();
    }

    @Inject(method = "updateScrollbar",
            at = @At("HEAD"),
            cancellable = true,
            remap = false,
            require = 1)
    protected void updateScrollbar(int mouseX, int mouseY, float par3, CallbackInfo ci)
    {
        if (smelterySize > columns * 3)
        {
            boolean mouseDown = Mouse.isButtonDown(0);
            int lefto = this.guiLeft;
            int topo = this.guiTop;
            int xScroll = lefto + 232;
            int yScroll = topo + 16;
            int scrollWidth = xScroll + 14;
            int scrollHeight = yScroll + 54;

            if (!this.wasClicking && mouseDown && mouseX >= xScroll && mouseY >= yScroll && mouseX < scrollWidth && mouseY < scrollHeight)
            {
                this.isScrolling = true;
            }

            if (!mouseDown)
            {
                this.isScrolling = false;
            }

            if (wasClicking && !isScrolling && slotPos != prevSlotPos)
            {
                prevSlotPos = slotPos;
            }

            this.wasClicking = mouseDown;

            if (this.isScrolling)
            {
                this.currentScroll = (mouseY - yScroll - 7.5F) / (scrollHeight - yScroll - 15.0F);

                if (this.currentScroll < 0.0F)
                {
                    this.currentScroll = 0.0F;
                }

                if (this.currentScroll > 1.0F)
                {
                    this.currentScroll = 1.0F;
                }

                int s = ((SmelteryContainer) this.inventorySlots).scrollTo(this.currentScroll);
                if (s != -1)
                    slotPos = s;
            }
        }

        ci.cancel();
    }
}
