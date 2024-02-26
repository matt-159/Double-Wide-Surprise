package com.github.matt159.dws.mixin.mixins.client.thaumcraft;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.Constants;
import lombok.val;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.client.gui.GuiResearchTable;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.research.ResearchNoteData;
import thaumcraft.common.tiles.TileResearchTable;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

import java.util.Arrays;

@Mixin(value = GuiResearchTable.class, priority = 0)
public abstract class GuiResearchTableMixin extends GuiContainer implements IDWSGui {
    private static final int ASPECT_GRID_ROW_COUNT = 7;
    private static final int ASPECT_GRID_ROW_LENGTH = 10;

    @Shadow(remap = false)
    private static boolean RESEARCHER_1;
    @Shadow(remap = false)
    private static boolean RESEARCHDUPE;

    @Shadow(remap = false)
    private String username;

    @Shadow(remap = false)
    private TileResearchTable tileEntity;

    @Shadow(remap = false)
    public ResearchNoteData note;

    @Shadow(remap = false)
    private int lastPage;

    public GuiResearchTableMixin(Container container) {
        super(container);
    }

    @Inject(method = "<init>",
            at = @At("RETURN"),
            require = 1)
    private void injectNewXSize(EntityPlayer player, TileResearchTable e, CallbackInfo ci) {
        this.xSize = Constants.GENERAL_DWS_GUI_WIDTH;
    }

    @Redirect(method = "drawGuiContainerBackgroundLayer",
              at = @At(value = "INVOKE",
                       target = "Lthaumcraft/client/gui/GuiResearchTable;drawTexturedModalRect(IIIIII)V",
                       ordinal = 0),
              require = 1)
    private void drawGuiHalvesTogether(GuiResearchTable instance, int x, int y, int u, int v, int w, int h) {
        instance.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }

    @Redirect(method = "drawGuiContainerBackgroundLayer",
              at = @At(value = "INVOKE",
                       target = "Lthaumcraft/client/gui/GuiResearchTable;drawTexturedModalRect(IIIIII)V",
                       ordinal = 1),
              require = 1)
    private void skipDrawBottomHalf(GuiResearchTable instance, int x, int y, int u, int v, int w, int h) {}

    @ModifyArg(method = "drawGuiContainerBackgroundLayer",
               at = @At(value = "INVOKE",
                        target = "Lthaumcraft/client/gui/GuiResearchTable;drawResearchData(IIII)V",
                        remap = false),
               index = 0,
               require = 1)
    private int modifySheetXPosition(int xPosition) {
        return xPosition + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "checkClickedHex",
                    constant = @Constant(intValue = 169),
                    remap = false,
                    require = 1)
    private int modifyClickedHexPosition(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @ModifyConstant(method = "drawScreen",
                    constant = @Constant(intValue = 169),
                    require = 1)
    private int modifyMouseXPosition(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    //region aspect grid
    @ModifyConstant(method = {
                        "drawScreen",
                        "drawGuiContainerBackgroundLayer"
                    },
                    constant = {
                        @Constant(intValue = 10),
                        @Constant(intValue = 40)
                    },
                    require = 7)
    private int modifyAspectGridLocation(int constant) {
        return constant + 1;
    }

    @ModifyConstant(method = "drawScreen",
                    constant = @Constant(intValue = 80, ordinal = 0),
                    require = 1)
    private int modifyAspectGridBoundX(int constant) {
        return 160;
    }

    @ModifyConstant(method = "drawScreen",
                    constant = @Constant(intValue = 80, ordinal = 1),
                    require = 1)
    private int modifyAspectGridBoundY(int constant) {
        return 112;
    }

    @Redirect(method = "drawGuiContainerBackgroundLayer",
              at = @At(value = "FIELD",
                       target = "Lthaumcraft/client/gui/GuiResearchTable;page:I",
                       ordinal = 0,
                       remap = false),
              require = 1)
    private int dontDrawAspectPageLeftButton(GuiResearchTable instance) {
        return this.lastPage;
    }

    @Redirect(method = "mouseClicked",
              at = @At(value = "FIELD",
                       target = "Lthaumcraft/client/gui/GuiResearchTable;page:I",
                       ordinal = 0,
                       remap = false),
              require = 1)
    private int dontCheckAspectPageLeftButtonClick(GuiResearchTable instance) {
        return this.lastPage;
    }

    @Redirect(method = "drawGuiContainerBackgroundLayer",
              at = @At(value = "FIELD",
                       target = "Lthaumcraft/client/gui/GuiResearchTable;page:I",
                       ordinal = 1,
                       remap = false),
              require = 1)
    private int dontDrawAspectPageRightButton(GuiResearchTable instance) {
        return 0;
    }

    @Redirect(method = "mouseClicked",
              at = @At(value = "FIELD",
                       target = "Lthaumcraft/client/gui/GuiResearchTable;lastPage:I",
                       ordinal = 0,
                       remap = false),
              require = 1)
    private int dontCheckAspectPageRightButtonClick(GuiResearchTable instance, int value) {
        return 0;
    }

    @Redirect(method = "drawAspects",
              at = @At(value = "INVOKE",
                       target = "Lthaumcraft/api/aspects/AspectList;getAspectsSorted()[Lthaumcraft/api/aspects/Aspect;",
                       remap = false),
              remap = false,
              require = 1)
    private Aspect[] cancelDrawAspectGrid(AspectList instance) {
        return new Aspect[0];
    }

    @Inject(method = "drawAspects",
            at = @At(value = "INVOKE",
                     target = "Lthaumcraft/api/aspects/AspectList;size()I",
                     remap = false,
                     shift = At.Shift.AFTER),
            remap = false,
            require = 1)
    private void injectDrawDoubleWideAspectGrid(int x, int y, CallbackInfo ci) {
        AspectList aspects = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(this.username);

        Aspect[] aspectsSorted = aspects.getAspectsSorted();

        val rows = 1 + (aspectsSorted.length / ASPECT_GRID_ROW_LENGTH);

        for (int i = 0; i < aspectsSorted.length; i++) {
            Aspect aspect = aspectsSorted[i];

            int xx = (i / rows) * 16;
            int yy = (i % rows) * 16;

            boolean faded = aspects.getAmount(aspect) <= 0 && this.tileEntity.bonusAspects.getAmount(aspect) <= 0;

            float amount = (float) aspects.getAmount(aspect);
            int bonus = this.tileEntity.bonusAspects.getAmount(aspect);

            UtilsFX.drawTag(x + xx, y + yy, aspect, amount, bonus, this.zLevel, 771, faded ? 0.33F : 1.0F);
        }
    }

    @Inject(method = "getClickedAspect",
            at = @At("HEAD"),
            cancellable = true,
            remap = false,
            require = 1)
    private void getClickedAspect(int mx, int my, int gx, int gy, boolean ignoreZero, CallbackInfoReturnable<Aspect> cir) {
        cir.setReturnValue(null);

        AspectList aspects = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(this.username);
        if (aspects != null) {
            Aspect[] aspectsSorted = aspects.getAspectsSorted();

            val rows = 1 + (aspectsSorted.length / ASPECT_GRID_ROW_LENGTH);

            for (int i = 0; i < aspectsSorted.length; i++) {
                val aspect = aspectsSorted[i];
                val aspectXOffset = (i / rows) * 16;
                val aspectYOffset = (i % rows) * 16;

                val aspectXPos = mx - (gx + aspectXOffset + 11);
                val aspectYPos = my - (gy + aspectYOffset + 41);

                val isMouseInBounds = (aspectXPos >= 0 && aspectXPos < 16) && (aspectYPos >= 0 && aspectYPos < 16);

                val bonusAspectCount = this.tileEntity.bonusAspects.getAmount(aspect);
                val hasAspect = (ignoreZero || aspects.getAmount(aspect) > 0 || bonusAspectCount > 0);

                if (hasAspect && isMouseInBounds) {
                    cir.setReturnValue(aspect);
                    break;
                }
            }
        }

        cir.cancel();
    }

    @Inject(method = "drawAspectText",
            at = @At("HEAD"),
            remap = false,
            cancellable = true,
            require = 1)
    private void drawAspectText(int x, int y, int mx, int my, CallbackInfo ci) {
        AspectList aspects = Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(this.username);

        if (aspects != null) {
            val aspectsSorted = aspects.getAspectsSorted();
            val rows = 1 + (aspectsSorted.length / ASPECT_GRID_ROW_LENGTH);

            for (int i = 0; i < aspectsSorted.length; i++) {
                val aspect = aspectsSorted[i];

                val aspectXOffset = (i / rows) * 16;
                val aspectYOffset = (i % rows) * 16;

                val aspectXPos = mx - (x + aspectXOffset);
                val aspectYPos = my - (y + aspectYOffset);
                val isMouseInBounds = (aspectXPos >= 0 && aspectXPos < 16) && (aspectYPos >= 0 && aspectYPos < 16);

                if (isMouseInBounds) {
                    UtilsFX.drawCustomTooltip(this,
                                              itemRender,
                                              this.fontRendererObj,
                                              Arrays.asList(aspect.getName(), aspect.getLocalizedDescription()),
                                              mx,
                                              my - 8,
                                              11);

                    if (RESEARCHER_1 && !aspect.isPrimal()) {
                        GL11.glPushMatrix();
                        GL11.glEnable(3042);
                        GL11.glBlendFunc(770, 771);
                        UtilsFX.bindTexture("textures/aspects/_back.png");
                        GL11.glPushMatrix();
                        GL11.glTranslated((double)(mx + 6), (double)(my + 6), 0.0);
                        GL11.glScaled(1.25, 1.25, 0.0);
                        UtilsFX.drawTexturedQuadFull(0, 0, 0.0);
                        GL11.glPopMatrix();
                        GL11.glPushMatrix();
                        GL11.glTranslated((double)(mx + 24), (double)(my + 6), 0.0);
                        GL11.glScaled(1.25, 1.25, 0.0);
                        UtilsFX.drawTexturedQuadFull(0, 0, 0.0);
                        GL11.glPopMatrix();
                        UtilsFX.drawTag(mx + 26, my + 8, aspect.getComponents()[1], 0.0F, 0, 0.0);
                        UtilsFX.drawTag(mx + 8, my + 8, aspect.getComponents()[0], 0.0F, 0, 0.0);
                        GL11.glDisable(3042);
                        GL11.glPopMatrix();
                    }

                    ci.cancel();
                }
            }
        }
    }

    @Redirect(method = "drawAspectText",
              at = @At(value = "INVOKE",
                       target = "Lthaumcraft/api/aspects/AspectList;getAspectsSorted()[Lthaumcraft/api/aspects/Aspect;",
                       remap = false),
              remap = false,
              require = 1)
    private Aspect[] cancelDrawAspectText(AspectList instance) {
        return new Aspect[0];
    }

    @ModifyConstant(method = {
                        "drawAspects",
                        "drawAspectText"
                    },
                    constant = {
                        @Constant(intValue = 3),
                        @Constant(intValue = 61)
                    },
                    remap = false,
                    require = 4)
    private int modifySelectedAspectXOffset(int constant) {
        return constant + 39;
    }

    @ModifyConstant(method = {
                        "drawAspects",
                        "drawAspectText"
                    },
                    constant = @Constant(intValue = 99),
                    remap = false,
                    require = 4)
    private int modifySelectedAspectYOffset(int constant) {
        return -32;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = {
                        @Constant(intValue = 35),
                        @Constant(intValue = 43)
                    },
                    require = 4)
    private int modifyCombineButtonXOffset(int constant) {
        return constant + 40;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 139),
                    require = 4)
    private int modifyCombineButtonYOffset(int constant) {
        return 9;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = {
                            @Constant(intValue = 184, ordinal = 0),
                            @Constant(intValue = 184, ordinal = 2),
                            @Constant(intValue = 184, ordinal = 4),
                    },
                    slice = @Slice(from = @At(value = "INVOKE",
                                              target = "Ljava/lang/System;nanoTime()J",
                                              ordinal = 0,
                                              remap = false),
                                   to = @At(value = "INVOKE",
                                            target = "Lthaumcraft/common/lib/research/ResearchNoteData;isComplete()Z",
                                            remap = false)),
                    require = 3)
    private int modifyCombineButtonTextureUVXOffset(int constant) {
        return constant + 162;
    }

    @ModifyConstant(method = "mouseClicked",
                    constant = {
                        @Constant(intValue = 35),
                        @Constant(intValue = 11),
                        @Constant(intValue = 71)
                    },
                    require = 3)
    private int modifyAspectButtonsXOffset(int constant) {
        return constant + 41;
    }

    @ModifyConstant(method = "mouseClicked",
                    constant = {
                        @Constant(intValue = 137),
                        @Constant(intValue = 139)
                    },
                    require = 3)
    private int modifyAspectButtonsYOffset(int constant) {
        return 9;
    }

    //endregion
    //region research duplication button
    @Redirect(method = "drawGuiContainerBackgroundLayer",
              slice = @Slice(from = @At(value = "INVOKE",
                                        target = "Lthaumcraft/common/lib/research/ResearchNoteData;isComplete()Z",
                                        remap = false),
                             to = @At(value = "INVOKE",
                                      target = "Lthaumcraft/client/gui/GuiResearchTable;drawAspects(II)V",
                                      remap = false)),
              at = @At(value = "INVOKE",
                       target = "Lthaumcraft/client/gui/GuiResearchTable;drawTexturedModalRect(IIIIII)V"),
              require = 1)
    private void dontDrawResearchDupeButton(GuiResearchTable instance, int x, int y, int u, int v, int w, int h) {}

    @Inject(method = "drawGuiContainerBackgroundLayer",
            at = @At("TAIL"),
            require = 1)
    private void injectDrawResearchDupeButton(float par1, int par2, int par3, CallbackInfo ci) {
        if (this.note != null && RESEARCHDUPE && this.note.isComplete()) {
            UtilsFX.bindTexture("textures/gui/guiresearchtable2.png");
            GL11.glEnable(GL11.GL_BLEND);
            RenderHelper.enableStandardItemLighting();
            this.drawTexturedModalRect(this.guiLeft + 176,
                                       this.guiTop + 9,
                                       394,
                                       200,
                                       24,
                                       24);
            RenderHelper.disableStandardItemLighting();
        }
    }

    @ModifyConstant(method = "drawScreen",
                    constant = @Constant(intValue = 37),
                    require = 1)
    private int modifyResearchDupeButtonXOffset(int constant) {
        return 176;
    }

    @ModifyConstant(method = "drawScreen",
                    constant = @Constant(intValue = 5),
                    require = 1)
    private int modifyResearchDupeButtonYOffset(int constant) {
        return 9;
    }

    @ModifyConstant(method = "drawScreen",
                    constant = @Constant(intValue = 100),
                    require = 3)
    private int modifyResearchDupeCostXOffset(int constant) {
        return constant + 100;
    }

    @ModifyConstant(method = "drawScreen",
                    constant = @Constant(intValue = 184),
                    slice = @Slice(from = @At(value = "INVOKE",
                                              target = "Lthaumcraft/common/lib/research/ResearchNoteData;isComplete()Z",
                                              remap = false),
                                   to = @At(value = "INVOKE",
                                            target = "Lnet/minecraft/client/renderer/RenderHelper;disableStandardItemLighting()V")),
                    require = 1)
    private int modifyResearchDupeCostUVXOffset(int constant) {
        return constant + 162;
    }

    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 232),
                    slice = @Slice(from = @At(value = "INVOKE",
                                              target = "Lthaumcraft/common/lib/research/ResearchNoteData;isComplete()Z",
                                              remap = false),
                                   to = @At(value = "INVOKE",
                                            target = "Lthaumcraft/client/gui/GuiResearchTable;drawAspects(II)V",
                                            remap = false)),
                    require = 1)
    private int modifyResearchDupeButtonUVXOffset(int constant) {
        return constant + 162;
    }

    @Shadow(remap = false)
    protected abstract void playButtonClick();

    @Inject(method = "mouseClicked",
            at = @At(value = "INVOKE",
                     target = "Lthaumcraft/client/gui/GuiResearchTable;checkClickedHex(IIII)V",
                     remap = false,
                     shift = At.Shift.AFTER),
            cancellable = true,
            require = 1)
    private void injectNewResearchDupeClickCheck(int mx, int my, int par3, CallbackInfo ci) {
        int gx = (this.width - this.xSize) / 2;
        int gy = (this.height - this.ySize) / 2;

        if (RESEARCHDUPE && this.note.isComplete()) {
            val dupeButtonX = mx - (gx + 176);
            val dupeButtonY = my - (gy + 9);
            if (dupeButtonX >= 0 && dupeButtonY >= 0 && dupeButtonX < 24 && dupeButtonY < 24) {
                this.mc.playerController.sendEnchantPacket(this.inventorySlots.windowId, 5);
                this.playButtonClick();
                ci.cancel();
            }
        }
    }

    @Redirect(method = "mouseClicked",
              at = @At(value = "INVOKE",
                       target = "Lthaumcraft/common/lib/research/ResearchNoteData;isComplete()Z",
                       remap = false),
              require = 1)
    private boolean cancelResearchDupeClickCheck(ResearchNoteData instance) {
        return false;
    }

    //endregion
}
