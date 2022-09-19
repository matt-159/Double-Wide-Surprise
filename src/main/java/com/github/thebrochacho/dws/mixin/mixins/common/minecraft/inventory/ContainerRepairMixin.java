package com.github.thebrochacho.dws.mixin.mixins.common.minecraft.inventory;

import com.github.thebrochacho.dws.interfaces.minecraft.IContainerRepairMixin;
import com.github.thebrochacho.dws.inventory.slots.minecraft.SlotAnvilOutput;
import net.minecraft.inventory.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

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
        return constant + 81;
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
                                                            134 + 81,
                                                            47));
    }

    @ModifyConstant(method = "<init>",
                    constant = @Constant(intValue = 9),
                    require = 4)
    private int modifyPlayerInventorySize(int constant) {
        return 18;
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
