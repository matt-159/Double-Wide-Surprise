package com.github.matt159.dws.mixin.mixins.client.thaumcraft;

import com.github.matt159.dws.interfaces.IDWSGui;
import com.github.matt159.dws.util.Constants;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.client.gui.GuiGolem;
import thaumcraft.common.entities.golems.EntityGolemBase;

@Mixin(GuiGolem.class)
public abstract class GuiGolemMixin implements IDWSGui {
    @ModifyConstant(method = "drawGuiContainerBackgroundLayer",
                    constant = @Constant(intValue = 184),
                    require = 1)
    private int modifyUVXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET * 2;
    }

    @Redirect(method = "drawGolem",
              at = @At(value = "INVOKE",
                       target = "Lorg/lwjgl/opengl/GL11;glTranslatef(FFF)V",
                       ordinal = 1),
              remap = false,
              require = 1)
    private void redirectGolemTranslation(float x, float y, float z) {
        GL11.glTranslatef(10, y, z);
    }

    @Redirect(method = "drawGolem",
              at = @At(value = "FIELD",
                       target = "Lthaumcraft/common/entities/golems/EntityGolemBase;renderYawOffset:F",
                       opcode = Opcodes.PUTFIELD,
                       ordinal = 0),
              remap = false,
              require = 1)
    private void redirectGolemRotation(EntityGolemBase instance, float value) {
        instance.renderYawOffset = 10F;
    }

    @Redirect(method = "drawGolem",
              at = @At(value = "INVOKE",
                       target = "Lorg/lwjgl/util/glu/GLU;gluPerspective(FFFF)V"),
              remap = false,
              require = 1)
    private void redirectPerspective(float fovy, float aspect, float zNear, float zFar) {
        GLU.gluPerspective(fovy, aspect, 10F, zFar);
    }
}
