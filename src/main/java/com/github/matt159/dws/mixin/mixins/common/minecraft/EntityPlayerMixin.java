package com.github.matt159.dws.mixin.mixins.common.minecraft;

import com.github.matt159.dws.interfaces.minecraft.IEntityPlayerMixin;
import com.github.matt159.dws.util.DWSUtil;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin implements IEntityPlayerMixin {
    boolean isReorganizedForFallbackSupport = false;

    @Override
    public void setInventoryReorganized(boolean value) {
        isReorganizedForFallbackSupport = value;
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
