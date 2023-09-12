package com.github.matt159.dws.mixin.mixins.common.minecraft;

import com.github.matt159.dws.interfaces.IDWSContainer;
import com.github.matt159.dws.interfaces.minecraft.IEntityPlayerMixin;
import com.github.matt159.dws.util.DWSUtil;
import com.github.matt159.dws.util.ModCompat;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin implements IEntityPlayerMixin {
    boolean isReorganizedForFallbackSupport = false;

//    @Inject(method = "openGui",
//            at = @At(value = "INVOKE",
//                     target = "Lcpw/mods/fml/common/network/internal/FMLNetworkHandler;openGui(Lnet/minecraft/entity/player/EntityPlayer;Ljava/lang/Object;ILnet/minecraft/world/World;III)V",
//                     shift = At.Shift.BEFORE),
//            remap = false,
//            require = 1)
//    private void injectPlayerInventoryReorganization(Object mod, int modGuiId, World world, int x, int y, int z, CallbackInfo ci) {
//        if (world.isRemote) {
//            return;
//        }
//
//        ModContainer mc = FMLCommonHandler.instance().findContainerFor(mod);
//        Container remoteGuiContainer = NetworkRegistry.INSTANCE.getRemoteGuiContainer(mc, entityPlayerMP, modGuiId, world, x, y, z);
//
//        this.isReorganizedForFallbackSupport = !ModCompat.hasDWSCompat(mc);
//
//        if (this.isReorganizedForFallbackSupport) {
//            DWSUtil.ReorganizeInventoryForFallbackSupport((EntityPlayer) (Object) this, DWSUtil.Reorganization.Do);
//        }
//    }

    @Redirect(method = "openGui",
              at = @At(value = "INVOKE",
                       target = "Lcpw/mods/fml/common/network/internal/FMLNetworkHandler;openGui(Lnet/minecraft/entity/player/EntityPlayer;Ljava/lang/Object;ILnet/minecraft/world/World;III)V"),
              require = 1,
              remap = false)
    private void redirectOpenGui(EntityPlayer player, Object mod, int modGuiId, World world, int x, int y, int z) {
        if (!world.isRemote && player instanceof EntityPlayerMP) {
            ModContainer mc = FMLCommonHandler.instance().findContainerFor(mod);
            Container guiContainer = NetworkRegistry.INSTANCE.getRemoteGuiContainer(mc, (EntityPlayerMP) player, modGuiId, world, x, y, z);

            this.isReorganizedForFallbackSupport = !(ModCompat.hasDWSCompat(mc) || guiContainer instanceof IDWSContainer);

            if (this.isReorganizedForFallbackSupport) {
                DWSUtil.ReorganizeInventoryForFallbackSupport((EntityPlayer) (Object) this, DWSUtil.Reorganization.Do);
            }
        }

        FMLNetworkHandler.openGui(player, mod, modGuiId, world, x, y, z);
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

        this.isReorganizedForFallbackSupport = false;
    }
}
