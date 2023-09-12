package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import com.github.matt159.dws.interfaces.minecraft.IEntityPlayerMixin;
import com.github.matt159.dws.util.ReflectedModSupport;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.ref.WeakReference;

@Mixin(Container.class)
public abstract class ContainerMixin {

    @ModifyConstant(method = "slotClick",
                    constant = @Constant(intValue = 9))
    private int modifyHotbarRangeCheck(int constant) {
        return 18;
    }

    @Inject(method = "onContainerClosed",
            at = @At("RETURN"),
            require = 1)
    private void injectPlayerInventoryReorganization(EntityPlayer player, CallbackInfo ci) {
        Container c = ((Container) (Object) this);
        if (c instanceof ContainerPlayer || ReflectedModSupport.instanceof_ContainerCreativeInv(c)) {
            return;
        }

        if (((IEntityPlayerMixin) player).isInventoryReorganized()) {
            ((IEntityPlayerMixin) player).resetInventory();
        }
    }

    @Unique
    private WeakReference<ItemStack> dws$capturedItemStack = null;

    @Inject(method = "mergeItemStack",
            at = @At("HEAD"),
            require = 1)
    private void injectItemStackCapture(ItemStack itemStack,
                                        int start,
                                        int end,
                                        boolean scanReverse,
                                        CallbackInfoReturnable<Boolean> cir) {
        this.dws$capturedItemStack = new WeakReference<>(itemStack);
    }

    @Redirect(method = "mergeItemStack",
              at = @At(value = "INVOKE",
                       target = "Lnet/minecraft/inventory/Slot;getStack()Lnet/minecraft/item/ItemStack;",
                       ordinal = 1),
              require = 1)
    private ItemStack captureSlot(Slot instance) {
        return instance.isItemValid(this.dws$capturedItemStack.get()) ? instance.getStack() : new ItemStack(Blocks.air);
    }
}
