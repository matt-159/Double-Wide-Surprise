package com.github.matt159.dws.mixin.mixins.common.minecraft.inventory;

import com.github.matt159.dws.interfaces.minecraft.IContainerRepairMixin;
import com.github.matt159.dws.inventory.slots.minecraft.SlotAnvilOutput;
import com.github.matt159.dws.util.Constants;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ContainerRepair.class)
public abstract class ContainerRepairMixin extends Container implements IContainerRepairMixin {

    @Shadow
    private IInventory outputSlot;
    @Shadow
    private IInventory inputSlots;

    @Shadow
    private World theWorld;

    @Shadow
    private int field_82861_i;
    @Shadow
    private int field_82858_j;
    @Shadow
    private int field_82859_k;

    @ModifyConstant(method = "<init>",
                    constant = {    @Constant(intValue = 27),
                                    @Constant(intValue = 76)    },
                    require = 2)
    private int modifyXOffset(int constant) {
        return constant + Constants.GENERAL_X_OFFSET;
    }

    @Redirect(  method = "<init>",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/inventory/ContainerRepair;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                            ordinal = 2),
                require = 1)
    private Slot redirectAddSlotAnvilOutput(ContainerRepair instance, Slot slot) {
        return this.addSlotToContainer(new SlotAnvilOutput( this.outputSlot,
                                                            this.theWorld,
                                                            (ContainerRepair) (Object) this,
                                                            2,
                                                            134 + Constants.GENERAL_X_OFFSET,
                                                            47));
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
    }

    @ModifyConstant(method = "transferStackInSlot",
                    constant = @Constant(intValue = 39),
                    require = 3)
    private int modifyHotbarEndIndex(int constant) {
        return 75;
    }

    public IInventory getInputSlots() {
        return inputSlots;
    }

    public int getXPos() {
        return field_82861_i;
    }

    public int getYPos() {
        return field_82858_j;
    }

    public int getZPos() {
        return field_82859_k;
    }
}
