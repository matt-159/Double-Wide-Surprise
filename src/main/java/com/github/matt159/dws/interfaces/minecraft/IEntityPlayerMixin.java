package com.github.matt159.dws.interfaces.minecraft;

public interface IEntityPlayerMixin {
    void setInventoryReorganized(boolean value);
    boolean isInventoryReorganized();

    void resetInventory();
}
