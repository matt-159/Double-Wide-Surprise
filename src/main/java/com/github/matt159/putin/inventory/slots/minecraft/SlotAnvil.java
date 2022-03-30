package com.github.matt159.putin.inventory.slots.minecraft;

import com.github.matt159.putin.interfaces.IContainerRepairMixin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class SlotAnvil extends Slot {
    private static final String __OBFID = "CL_00001734";
    
    private final World world;
    private final ContainerRepair container;

    public SlotAnvil(IInventory outputSlot, World world, ContainerRepair container, int slotIndex, int xDisplayPosition, int yDisplayPosition) {
        super(outputSlot, slotIndex, xDisplayPosition, yDisplayPosition);
        
        this.world = world;
        this.container = container;
    }
    
    public boolean isItemValid(ItemStack itemStack) {
        return false;
    }

    public boolean canTakeStack(EntityPlayer player) {
        return (player.capabilities.isCreativeMode || player.experienceLevel >= container.maximumCost) && container.maximumCost > 0 && this.getHasStack();
    }

    public void onPickupFromSlot(EntityPlayer player, ItemStack itemStack) {
        IContainerRepairMixin container = (IContainerRepairMixin) this.container;
        IInventory inputSlots = container.getInputSlots();
        int x = container.getXPos();
        int y = container.getYPos();
        int z = container.getZPos();

        if (!player.capabilities.isCreativeMode) {
            player.addExperienceLevel(-this.container.maximumCost);
        }

        float breakChance = ForgeHooks.onAnvilRepair(player, itemStack, inputSlots.getStackInSlot(0), inputSlots.getStackInSlot(1));
        inputSlots.setInventorySlotContents(0, (ItemStack)null);
        if (this.container.stackSizeToBeUsedInRepair > 0) {
            ItemStack itemstack1 = inputSlots.getStackInSlot(1);
            if (itemstack1 != null && itemstack1.stackSize > this.container.stackSizeToBeUsedInRepair) {
                itemstack1.stackSize -= this.container.stackSizeToBeUsedInRepair;
                inputSlots.setInventorySlotContents(1, itemstack1);
            } else {
                inputSlots.setInventorySlotContents(1, (ItemStack)null);
            }
        } else {
            inputSlots.setInventorySlotContents(1, (ItemStack)null);
        }

        this.container.maximumCost = 0;
        if (!player.capabilities.isCreativeMode && !world.isRemote && world.getBlock(x, y, z) == Blocks.anvil && player.getRNG().nextFloat() < breakChance) {
            int i1 = world.getBlockMetadata(x, y, z);
            int k = i1 & 3;
            int l = i1 >> 2;
            ++l;
            if (l > 2) {
                world.setBlockToAir(x, y , z);
                world.playAuxSFX(1020, x, y , z, 0);
            } else {
                world.setBlockMetadataWithNotify(x, y , z, k | l << 2, 2);
                world.playAuxSFX(1021, x, y , z, 0);
            }
        } else if (!world.isRemote) {
            world.playAuxSFX(1021, x, y , z, 0);
        }
    }
}
