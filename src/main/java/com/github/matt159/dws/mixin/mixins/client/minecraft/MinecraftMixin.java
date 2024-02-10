package com.github.matt159.dws.mixin.mixins.client.minecraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

import java.util.List;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    // inventorySlots.size() can't be trusted to provide a constant number of slots
    // since accessory slots are added to the ContainerPlayer object
    @Redirect(  method = "func_147112_ai",
                at = @At(   value = "INVOKE",
                            target = "Ljava/util/List;size()I"),
                require = 1)
    private int redirectGetMainInventorySize(List instance) {
        return 9 + 72;
    }

    @ModifyConstant(method="func_147112_ai",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyHotbarOffset(int constant) {
        return 18;
    }

    @Redirect(method = "runTick",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/client/settings/KeyBinding;isPressed()Z"),
              slice = @Slice(from = @At(value = "INVOKE",
                                        target = "Lcpw/mods/fml/common/FMLCommonHandler;fireKeyInput()V"),
                             to = @At(value = "FIELD",
                                      target = "Lnet/minecraft/client/settings/GameSettings;chatVisibility:Lnet/minecraft/entity/player/EntityPlayer$EnumChatVisibility;")),
              require = 1)
    private boolean skipKeybindIsPressed(KeyBinding instance) {
        //skipping this because the callback in KeyHandler calls isPressed()
        return false;
    }
}