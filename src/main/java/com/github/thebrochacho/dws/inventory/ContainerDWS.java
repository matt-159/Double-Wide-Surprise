package com.github.thebrochacho.dws.inventory;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.github.thebrochacho.dws.Config;
import com.github.thebrochacho.dws.interfaces.galacticraft.IGalacticWearable;
import com.github.thebrochacho.dws.inventory.slots.SlotDWS;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import micdoodle8.mods.galacticraft.api.item.IItemThermal;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.inventory.InventoryExtended;
import micdoodle8.mods.galacticraft.core.items.*;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.apache.commons.lang3.tuple.Pair;
import tconstruct.armor.player.ArmorExtended;
import tconstruct.armor.player.TPlayerStats;
import tconstruct.library.accessory.IAccessory;
import travellersgear.api.ITravellersGear;
import travellersgear.api.TravellersGearAPI;
import travellersgear.common.inventory.InventoryTG;

import java.util.ArrayList;

import static com.github.thebrochacho.dws.inventory.slots.SlotDWS.SlotType.*;

public class ContainerDWS extends Container {
    //Offset so that itemslots don't get mapped to each other
    private static final int CRAFTING_SLOT_X_OFFSET = 162;

    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
    public IInventory craftResult = new InventoryCraftResult();
    public InventoryBaubles baubles;
    public ArmorExtended tinkers;
    public InventoryTG travellers;
    public InventoryExtended gc;

    public boolean isLocalWorld;
    private final EntityPlayer thePlayer;

    //Offset so that itemslots don't get mapped to each other
    public static int BAUBLES_SLOT_START = -1;
    public static int TINKERS_SLOT_START = -1;
    public static int TG_SLOT_START = -1;
    public static int GC_SLOT_START = -1;

    public static ArrayList<Pair<Integer, Integer>> nullSlots = new ArrayList<>();

    public ContainerDWS(InventoryPlayer inventoryPlayer, boolean p_i1819_2_, EntityPlayer player) {
        this.isLocalWorld = p_i1819_2_;
        this.thePlayer = player;

        this.inventorySlots.clear();

        /*=========================================================================================================
         * Vanilla Slots + DWSInventory
         *========================================================================================================*/
        this.addSlotToContainer(new SlotCrafting(inventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 144 + CRAFTING_SLOT_X_OFFSET, 36));
        int i;
        int j;

        //Crafting grid
        for (i = 0; i < 2; ++i) {
            for (j = 0; j < 2; ++j) {
                this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 2, 88 + CRAFTING_SLOT_X_OFFSET + j * 18, 26 + i * 18));
            }
        }

        //Armor slots
        for (i = 0; i < 4; ++i) {
            final int k = i;
            this.addSlotToContainer(new Slot(inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 8, 8 + i * 18) {
                /**
                 * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1
                 * in the case of armor slots)
                 */
                public int getSlotStackLimit()
                {
                    return 1;
                }
                /**
                 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
                 */
                public boolean isItemValid(ItemStack itemStack) {
                    if (itemStack == null) return false;
                    return itemStack.getItem().isValidArmor(itemStack, k, player);
                }
                /**
                 * Returns the icon index on items.png that is used as background image of the slot.
                 */
                @SideOnly(Side.CLIENT)
                public IIcon getBackgroundIconIndex()
                {
                    return ItemArmor.func_94602_b(k);
                }
            });
        }

        //main inventory
        for (i = 0; i < 3; ++i) {
            for (j = 0; j < 18; ++j) {
                this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 18 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        //left half of hotbar
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }

        //right half of hotbar
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(inventoryPlayer, i + 63, 8 + (i + 9) * 18, 142));
        }

        this.onCraftMatrixChanged(this.craftMatrix);

        int xOffset = 80;

        baubles = null;
        if (Config.isBaublesLoaded) {
            addBaublesSlots(player, xOffset);
            xOffset += 18;
        }

        tinkers = null;
        if (Config.isTinkersLoaded) {
            addTinkersSlots(player, xOffset);
            xOffset += 36;
        }

        travellers = null;
        if (Config.isTravellersGearLoaded) {
            addTravellersGearSlots(player, xOffset);
            xOffset += 18;
        }

        if (Config.isGalacticraftLoaded) {
            addGalacticraftSlots(player, xOffset);
            xOffset += 54;
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);

        if (!player.worldObj.isRemote) {
            if (Config.isBaublesLoaded) {
                PlayerHandler.setPlayerBaubles(player, baubles);
            }

            if (Config.isTravellersGearLoaded) {
                TravellersGearAPI.setExtendedInventory(player, travellers.stackList);
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {
        ItemStack itemStack = null;
        final Slot slot = (Slot) this.inventorySlots.get(slotNumber);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStackInSlot = slot.getStack();
            itemStack = itemStackInSlot.copy();

            if (slotNumber >= 0 && slotNumber < 9) { //Crafting Matrix + Crafting Result + Armor Slots
                if (!mergeItemStack(itemStackInSlot, 9, 81, false))
                    return null;
            }
            else if (((itemStack.getItem() instanceof ItemArmor)) && (!((Slot)this.inventorySlots.get(((ItemArmor)itemStack.getItem()).armorType)).getHasStack())) {
                int armorType = 5 + ((ItemArmor)itemStack.getItem()).armorType;
                if (!mergeItemStack(itemStackInSlot, armorType, armorType + 1, false))
                    return null;
            } else if (slotNumber < 63) { //main inventory to hotbar
                this.mergeItemStackIntoAccessorySlot(itemStack, itemStackInSlot, player);

                if (itemStackInSlot.stackSize != 0 && !this.mergeItemStack(itemStackInSlot, 63, 81, false)) {
                    return null;
                }
            } else if (slotNumber < 81) { //hotbar to main inventory
                this.mergeItemStackIntoAccessorySlot(itemStack, itemStackInSlot, player);

                if (itemStackInSlot.stackSize != 0 && !this.mergeItemStack(itemStackInSlot, 9, 63, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(itemStackInSlot, 9, 81, false)) {
                return null;
            }

            if (itemStackInSlot.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }

            if (itemStackInSlot.stackSize == itemStack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemStackInSlot);
        }

        return itemStack;
    }

    private ItemStack mergeItemStackIntoAccessorySlot(ItemStack itemStack, ItemStack itemStackInSlot, EntityPlayer player) {
        if (Config.isBaublesLoaded && itemStack.getItem() instanceof IBauble &&
                ((IBauble) itemStack.getItem()).getBaubleType(itemStack) != null) {
            IBauble bauble = (IBauble) itemStack.getItem();
            BaubleType type = bauble.getBaubleType(itemStack);

            if (type == BaubleType.AMULET &&
                    bauble.canEquip(itemStack, player) &&
                    !((Slot) this.inventorySlots.get(BAUBLES_SLOT_START + 0)).getHasStack()) {
                if (!mergeItemStack(itemStackInSlot, BAUBLES_SLOT_START + 0, BAUBLES_SLOT_START + 1, false))
                    return null;
            }
            else if (type == BaubleType.RING &&
                    bauble.canEquip(itemStack, player) &&
                    !((Slot) this.inventorySlots.get(BAUBLES_SLOT_START + 1)).getHasStack()) {
                if (!mergeItemStack(itemStackInSlot, BAUBLES_SLOT_START + 1, BAUBLES_SLOT_START + 2, false))
                    return null;
            }
            else if (type == BaubleType.RING &&
                    bauble.canEquip(itemStack, player) &&
                    !((Slot) this.inventorySlots.get(BAUBLES_SLOT_START + 2)).getHasStack()) {
                if (!mergeItemStack(itemStackInSlot, BAUBLES_SLOT_START + 2, BAUBLES_SLOT_START + 3, false))
                    return null;
            }
            else if (type == BaubleType.BELT &&
                    bauble.canEquip(itemStack, player) &&
                    !((Slot) this.inventorySlots.get(BAUBLES_SLOT_START + 3)).getHasStack()) {
                if (!mergeItemStack(itemStackInSlot, BAUBLES_SLOT_START + 3, BAUBLES_SLOT_START + 4, false))
                    return null;
            }
        }
        /**=========================================================================================================
         * Tinkers Construct
         *========================================================================================================*/
        else if (Config.isTinkersLoaded &&
                itemStackInSlot.getItem() instanceof IAccessory) {

            IAccessory accessory = ((IAccessory) itemStackInSlot.getItem());
            int slotIndex;
            for (slotIndex = 0; slotIndex < 7; slotIndex++) {
                if (accessory.canEquipAccessory(itemStackInSlot, slotIndex)) {
                    break;
                }
            }

            switch (slotIndex) {
                case 0: //Tinkers Mask
                case 1: //Travel Glove
                    break;
                case 2: //Knapsack
                    slotIndex = 3;
                    break;
                case 3: //Travel Belt
                    slotIndex = 2;
                    break;
                case 4: //Green Canister
                    slotIndex = 6;
                    break;
                case 5: //Yellow Canister
                    break;
                case 6: //Red Canister
                    slotIndex = 4;
                    break;
            }

            if (!mergeItemStack(itemStackInSlot, TINKERS_SLOT_START + slotIndex, TINKERS_SLOT_START + slotIndex + 1, false)) {
                return null;
            }
        }
        /**=========================================================================================================
         * Traveller's Gear
         *========================================================================================================*/
        else if (Config.isTravellersGearLoaded &&
                itemStack.getItem() instanceof ITravellersGear) {
            ITravellersGear travellersGear = (ITravellersGear) itemStack.getItem();

            int tgSlot = travellersGear.getSlot(itemStack);

            if (!mergeItemStack(itemStackInSlot, TG_SLOT_START + tgSlot, TG_SLOT_START + tgSlot + 1, false))
                return null;
        }
        /**=========================================================================================================
         * Galacticraft
         *========================================================================================================*/
        else if (Config.isGalacticraftLoaded &&
                itemStack.getItem() instanceof IGalacticWearable) {
            IGalacticWearable gcItem = (IGalacticWearable) itemStack.getItem();

            if (gcItem instanceof IItemThermal) {
                int i = 0;
                for (; i < 4; i++) {
                    if (((IItemThermal) gcItem).isValidForSlot(itemStack, i)) {
                        break;
                    }
                }

                if (!mergeItemStack(itemStackInSlot, GC_SLOT_START + i, GC_SLOT_START + i + 1, false)) {
                    return null;
                }
            }
            else if (gcItem instanceof ItemOxygenMask &&
                    !mergeItemStack(itemStackInSlot, GC_SLOT_START + 5, GC_SLOT_START + 6, false)) {
                return null;
            }
            else if (gcItem instanceof ItemOxygenGear &&
                    !mergeItemStack(itemStackInSlot, GC_SLOT_START + 8, GC_SLOT_START + 9, false)) {
                return null;
            }
            else if (gcItem instanceof ItemOxygenTank &&
                        (!mergeItemStack(itemStackInSlot, GC_SLOT_START + 6, GC_SLOT_START + 7, false) &&
                         !mergeItemStack(itemStackInSlot, GC_SLOT_START + 9, GC_SLOT_START + 10, false))) {
                return null;
            }
            else if (gcItem instanceof ItemBasic &&
                    itemStack.getItemDamage() == 19 &&
                    !mergeItemStack(itemStackInSlot, GC_SLOT_START + 7, GC_SLOT_START + 8, false)) {
                return null;
            }
            else if (gcItem instanceof ItemParaChute &&
                    !mergeItemStack(itemStackInSlot, GC_SLOT_START + 4, GC_SLOT_START + 5, false)) {
                return null;
            }
        }

        return itemStackInSlot.stackSize == 0 ? null : itemStackInSlot;
    }

    @Override
    public void putStacksInSlots(ItemStack[] p_75131_1_) {
        if (Config.isBaublesLoaded) {
            baubles.blockEvents = true;
        }
        super.putStacksInSlots(p_75131_1_);
    }

    private void addBaublesSlots(EntityPlayer player, int xOffset) {
        if (BAUBLES_SLOT_START == -1) {
            BAUBLES_SLOT_START = this.inventorySlots.size();
        }

        baubles = new InventoryBaubles(player);
        baubles.setEventHandler(this);

        if (!player.worldObj.isRemote) {
            baubles.stackList = PlayerHandler.getPlayerBaubles(player).stackList;
        }

        this.addSlotToContainer(new SlotDWS(baubles, 0, xOffset,8 + 0 * 18, player, BAUBLE_AMULET));
        this.addSlotToContainer(new SlotDWS(baubles, 1, xOffset,8 + 1 * 18, player, BAUBLE_RING));
        this.addSlotToContainer(new SlotDWS(baubles, 2, xOffset,8 + 2 * 18, player, BAUBLE_RING));
        this.addSlotToContainer(new SlotDWS(baubles, 3, xOffset,8 + 3 * 18, player, BAUBLE_BELT));
    }

    private void addTinkersSlots(EntityPlayer player, int xOffset) {
        if (TINKERS_SLOT_START == -1) {
            TINKERS_SLOT_START = this.inventorySlots.size();
        }

        tinkers = TPlayerStats.get(player).armor;

        this.addSlotToContainer(new SlotDWS(tinkers, 0, xOffset, 8 + 0 * 18, player, TINKERS_MASK));
        this.addSlotToContainer(new SlotDWS(tinkers, 1, xOffset, 8 + 1 * 18, player, TINKERS_GLOVE));
        this.addSlotToContainer(new SlotDWS(tinkers, 3, xOffset, 8 + 2 * 18, player, TINKERS_BELT));
        this.addSlotToContainer(new SlotDWS(tinkers, 2, xOffset, 8 + 3 * 18, player, TINKERS_KNAPSACK));
        xOffset += 18;

        this.addSlotToContainer(new SlotDWS(tinkers, 6, xOffset, 8 + 0 * 18, player, TINKERS_HEART_RED));
        this.addSlotToContainer(new SlotDWS(tinkers, 5, xOffset, 8 + 1 * 18, player, TINKERS_HEART_YELLOW));
        this.addSlotToContainer(new SlotDWS(tinkers, 4, xOffset, 8 + 2 * 18, player, TINKERS_HEART_GREEN));
        nullSlots.add(Pair.of(xOffset, 8 + 3 * 18));
    }

    private void addTravellersGearSlots(EntityPlayer player, int xOffset) {
        if (TG_SLOT_START == -1) {
            TG_SLOT_START = this.inventorySlots.size();
        }

        travellers = new InventoryTG(this, player);
        if(!player.worldObj.isRemote) {
            travellers.stackList = TravellersGearAPI.getExtendedInventory(player);
        }

        this.addSlotToContainer(new SlotDWS(travellers, 0, xOffset, 8 + 0 * 18, player, TRAVEL_CLOAK));
        this.addSlotToContainer(new SlotDWS(travellers, 1, xOffset, 8 + 1 * 18, player, TRAVEL_PAULDRON));
        this.addSlotToContainer(new SlotDWS(travellers, 2, xOffset, 8 + 2 * 18, player, TRAVEL_VAMBRACE));
        this.addSlotToContainer(new SlotDWS(travellers, 3, xOffset, 8 + 3 * 18, player, TRAVEL_TITLE));
    }

    private void addGalacticraftSlots(EntityPlayer player, int xOffset) {
        if (GC_SLOT_START == -1) {
            GC_SLOT_START = this.inventorySlots.size();
        }

        if (!player.worldObj.isRemote) {
            EntityPlayerMP playerMP = PlayerUtil.getPlayerBaseServerFromPlayer(player, false);
            gc = GCPlayerStats.get(playerMP).extendedInventory;
        } else {
            gc = ClientProxyCore.dummyInventory;
        }

        if (gc != null) {
            this.addSlotToContainer(new SlotDWS(gc, 6, xOffset, 8 + 0 * 18, player, GC_THERMAL_HELM));
            this.addSlotToContainer(new SlotDWS(gc, 7, xOffset, 8 + 1 * 18, player, GC_THERMAL_CHEST));
            this.addSlotToContainer(new SlotDWS(gc, 8, xOffset, 8 + 2 * 18, player, GC_THERMAL_LEGS));
            this.addSlotToContainer(new SlotDWS(gc, 9, xOffset, 8 + 3 * 18, player, GC_THERMAL_BOOTS));
            xOffset += 18;

            this.addSlotToContainer(new SlotDWS(gc, 4, xOffset, 8 + 0 * 18, player, GC_PARACHUTE));
            this.addSlotToContainer(new SlotDWS(gc, 0, xOffset, 8 + 1 * 18, player, GC_OXYGEN_MASK));
            this.addSlotToContainer(new SlotDWS(gc, 2, xOffset, 8 + 2 * 18, player, GC_OXYGEN_TANK));
            nullSlots.add(Pair.of(xOffset, 8 + 3 * 18));
            xOffset += 18;

            this.addSlotToContainer(new SlotDWS(gc, 5, xOffset, 8 + 0 * 18, player, GC_FREQUENCY_MODULE));
            this.addSlotToContainer(new SlotDWS(gc, 1, xOffset, 8 + 1 * 18, player, GC_OXYGEN_GEAR));
            this.addSlotToContainer(new SlotDWS(gc, 3, xOffset, 8 + 2 * 18, player, GC_OXYGEN_TANK));
            nullSlots.add(Pair.of(xOffset, 8 + 3 * 18));
        }
    }
}
