package com.github.matt159.dws.mixin.mixins.common.minecraft;

import com.github.matt159.dws.interfaces.IDWSContainer;
import com.github.matt159.dws.interfaces.minecraft.IEntityPlayerMixin;
import com.github.matt159.dws.util.DWSUtil;
import com.github.matt159.dws.util.ModCompat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;

import lombok.val;

@Mixin(value = FMLNetworkHandler.class,
       remap = false)
public abstract class FMLNetworkHandlerMixin {
    @Redirect(method = "openGui",
              at = @At(value = "INVOKE",
                       target = "Lcpw/mods/fml/common/network/NetworkRegistry;getRemoteGuiContainer(Lcpw/mods/fml/common/ModContainer;Lnet/minecraft/entity/player/EntityPlayerMP;ILnet/minecraft/world/World;III)Lnet/minecraft/inventory/Container;"),
              require = 1)
    private static Container reorganizeOnOpen(NetworkRegistry instance, ModContainer mc, EntityPlayerMP player, int modGuiId, World world, int x, int y, int z) {
        val guiContainer = NetworkRegistry.INSTANCE.getRemoteGuiContainer(mc,
                                                                          player,
                                                                          modGuiId,
                                                                          world,
                                                                          x,
                                                                          y,
                                                                          z);
        val reorganized = !(ModCompat.hasDWSCompat(mc) ||
                            guiContainer == null ||
                            guiContainer instanceof IDWSContainer);

        if (reorganized) {
            DWSUtil.ReorganizeInventoryForFallbackSupport(player, DWSUtil.Reorganization.Do);
        }

        ((IEntityPlayerMixin)player).setInventoryReorganized(reorganized);

        return guiContainer;
    }
}
