package com.github.matt159.dws.mixin.mixins.common.minecraft;

import com.github.matt159.dws.interfaces.minecraft.IEntityPlayerMixin;
import com.github.matt159.dws.mixin.plugin.TargetedMod;
import com.github.matt159.dws.util.DWSUtil;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin implements IEntityPlayerMixin {
    boolean isReorganizedForFallbackSupport = false;

    @Inject(method = "openGui",
            at = @At(value = "INVOKE",
                     target = "Lcpw/mods/fml/common/network/internal/FMLNetworkHandler;openGui(Lnet/minecraft/entity/player/EntityPlayer;Ljava/lang/Object;ILnet/minecraft/world/World;III)V",
                     shift = At.Shift.BEFORE),
            remap = false,
            require = 1)
    private void injectPlayerInventoryReorganization(Object mod, int modGuiId, World world, int x, int y, int z, CallbackInfo ci) {
        if (world.isRemote) {
            return;
        }

        ModContainer mc = FMLCommonHandler.instance().findContainerFor(mod);
        this.isReorganizedForFallbackSupport = Arrays.stream(TargetedMod.values()).noneMatch(targetedMod -> {
            return targetedMod.getModName().equalsIgnoreCase(mc.getName()) ||
                   targetedMod.getModName().equalsIgnoreCase(mc.getModId());
        });

        if (this.isReorganizedForFallbackSupport) {
            DWSUtil.ReorganizeInventoryForFallbackSupport((EntityPlayer) (Object) this, DWSUtil.Reorganization.Do);
        }
    }

    @Override
    public boolean isInventoryReorganized() {
        return this.isReorganizedForFallbackSupport;
    }

    @Override
    public void resetInventory() {
        if (this.isReorganizedForFallbackSupport) {
            DWSUtil.ReorganizeInventoryForFallbackSupport((EntityPlayer) (Object) this, DWSUtil.Reorganization.Undo);
        }
    }
}
