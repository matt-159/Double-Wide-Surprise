package com.github.matt159.putin.mixins.common.minecraft;

import com.github.matt159.putin.inventory.ContainerPutin;
import com.github.matt159.putin.inventory.InventoryPutin;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin {

    @Shadow public Container inventoryContainer;
    @Shadow public InventoryPlayer inventory;
    @Shadow public Container openContainer;

    @Inject(method = "<init>",
            at = @At(value = "RETURN",
            remap = false))
    public void bypassEntityPlayer(World p_i45324_1_, GameProfile p_i45324_2_, CallbackInfo ci) {
        this.inventory = new InventoryPutin((EntityPlayer) (Object)this);
        this.inventoryContainer = new ContainerPutin(this.inventory, !p_i45324_1_.isRemote, (EntityPlayer) (Object)this);
        this.openContainer = this.inventoryContainer;
    }
}
