package com.github.thebrochacho.dws.mixin.mixins.client.minecraft;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

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
        return 81;
    }

    @ModifyConstant(method="func_147112_ai",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyHotbarOffset(int constant) {
        return 18;
    }
}