package com.github.thebrochacho.dws.mixin.mixins.common.minecraft.inventory;

import com.github.thebrochacho.dws.util.DWSUtil;
import cpw.mods.fml.common.Mod;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerMerchant.class)
public abstract class ContainerMerchantMixin extends Container {

    @ModifyConstant(method = "<init>",
                    constant = {    @Constant(intValue = 36),
                                    @Constant(intValue = 62),
                                    @Constant(intValue = 120)   },
                    require = 3)
    private int modifyXOffset(int constant) {
        return constant + 81;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }
}
