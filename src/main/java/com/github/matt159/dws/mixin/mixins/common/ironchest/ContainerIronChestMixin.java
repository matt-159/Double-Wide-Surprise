package com.github.matt159.dws.mixin.mixins.common.ironchest;

import com.github.matt159.dws.util.DWSUtil;
import cpw.mods.ironchest.ContainerIronChest;
import cpw.mods.ironchest.IronChestType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerIronChest.class)
public abstract class ContainerIronChestMixin extends Container {

    @Inject(method = "<init>",
            at = @At(value = "RETURN"),
            require = 1)
    private void injectDWSSlots(IInventory playerInventory, IInventory chestInventory, IronChestType type, int xSize, int ySize, CallbackInfo ci) {
        this.inventorySlots.clear();

        int xOffsetChestInventory = 0;
        int yOffsetChestInventory = 0;
        int yOffsetPlayerMainInventory = 0;

        switch (type.name()) {
            case "DIRTCHEST9000":
                xOffsetChestInventory = 161;
                yOffsetChestInventory = 44;
                yOffsetPlayerMainInventory = 102;
                break;
            case "COPPER":
                xOffsetChestInventory = 89;
                yOffsetChestInventory = 9;
                yOffsetPlayerMainInventory = 103;
                break;
            case "IRON":
                xOffsetChestInventory = 89;
                yOffsetChestInventory = 9;
                yOffsetPlayerMainInventory = 121;
                break;
            case "SILVER":
            case "STEEL":
                xOffsetChestInventory = 89;
                yOffsetChestInventory = 9;
                yOffsetPlayerMainInventory = 157;
                break;
            case "GOLD":
                xOffsetChestInventory = 89;
                yOffsetChestInventory = 9;
                yOffsetPlayerMainInventory = 175;
                break;
            case "DIAMOND":
            case "CRYSTAL":
            case "OBSIDIAN":
                xOffsetChestInventory = 62;
                yOffsetChestInventory = 9;
                yOffsetPlayerMainInventory = 175;
                break;
        }

        int yOffsetPlayerHotbar = yOffsetPlayerMainInventory + 58;

        if (type == IronChestType.DIRTCHEST9000) {
            this.addSlotToContainer(new Slot(chestInventory, 0, 161, 44));
        } else {
            for (int y = 0; y < type.getRowCount(); y++) {
                for (int x = 0; x < type.getRowLength(); x++) {
                    this.addSlotToContainer(new Slot(chestInventory,
                                            x + y * type.getRowLength(),
                                            xOffsetChestInventory + x * 18,
                                            yOffsetChestInventory + y * 18));
                }
            }
        }

        DWSUtil.addDWSSlotsToContainer(this, playerInventory, 8, yOffsetPlayerMainInventory, yOffsetPlayerHotbar);
    }
}
