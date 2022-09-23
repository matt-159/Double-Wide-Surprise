package com.github.thebrochacho.dws.mixin.mixins.common.minecraft.inventory;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.github.thebrochacho.dws.interfaces.dws.*;
import com.github.thebrochacho.dws.inventory.slots.SlotDWS;
import com.github.thebrochacho.dws.util.ModCompat;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tconstruct.armor.player.TPlayerStats;
import travellersgear.TravellersGear;
import travellersgear.api.TravellersGearAPI;
import travellersgear.common.inventory.InventoryTG;
import travellersgear.common.network.MessageNBTSync;

import java.util.ArrayList;

import static com.github.thebrochacho.dws.inventory.slots.SlotDWS.SlotType.*;

@Mixin(ContainerPlayer.class)
public abstract class ContainerPlayerMixin extends Container implements IAddsBaubleSlots,
                                                                        IAddsTinkersSlots,
                                                                        IAddsGCSlots,
                                                                        IAddsTGSlots,
                                                                        IAddsNullSlots {


    private final ArrayList<Pair<Integer, Integer>> nullSlots = new ArrayList<>();

    @ModifyConstant(method = "<init>",
                    constant = {    @Constant(intValue = 88),
                                    @Constant(intValue = 144)   },
                    require = 1)
    private int modifyCraftingSlotsXOffset(int constant) {
        return constant + 162;
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int modifyColumnCount(int constant) {
        return 18;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 36),
                    require = 4)
    private int modifyHotbarSlotStart(int constant) {
        return 63;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 45),
                    require = 6)
    private int modifyHotbarSlotEnd(int constant) {
        return 81;
    }



    @Override
    public ArrayList<Pair<Integer, Integer>> getNullSlots() {
        return this.nullSlots;
    }


}
