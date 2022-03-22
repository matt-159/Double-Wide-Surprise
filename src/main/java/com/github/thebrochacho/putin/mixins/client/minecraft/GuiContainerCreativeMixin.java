package com.github.thebrochacho.putin.mixins.client.minecraft;

import com.github.thebrochacho.putin.Tags;
import com.github.thebrochacho.putin.interfaces.IMinecraftGuiMixin;
import com.github.thebrochacho.putin.inventory.SlotCreative;
import com.github.thebrochacho.putin.util.PutinUtil;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiContainerCreative.class)
public class GuiContainerCreativeMixin {

    @Shadow private static int selectedTabIndex;

    @Shadow public static InventoryBasic field_147060_v;
    private Slot slot;

    private static final String locationPrefix = "textures/minecraft/creative_inventory/tab_";
    private static final int X_SIZE = 357;
    private static final int Y_SIZE = 136;

    static {
        field_147060_v = new InventoryBasic("tmp", true, 90);
    }

    @Inject(method = "<init>",
            at = @At(   value = "RETURN"),
            require = 1)
    private void updateGuiSize(EntityPlayer p_i1088_1_, CallbackInfo ci) {
        ((GuiContainerCreative) (Object) this).xSize = X_SIZE;
        ((GuiContainerCreative) (Object) this).ySize = Y_SIZE;
    }

    @Inject(method = "setCurrentCreativeTab",
            at = @At(   value = "INVOKE",
                        target = "Lnet/minecraft/inventory/Slot;<init>(Lnet/minecraft/inventory/IInventory;III)V",
                        shift = At.Shift.BEFORE),
            require = 1)
    @SuppressWarnings("unchecked")
    private void addSlotsToContainer(CreativeTabs p_setCurrentCreativeTab_1_, CallbackInfo ci) {
        GuiContainerCreative gcc = ((GuiContainerCreative) (Object) (this));
        GuiContainerCreative.ContainerCreative containerCreative = (GuiContainerCreative.ContainerCreative) gcc.inventorySlots;
        containerCreative.inventorySlots.clear();

        Container container = ((GuiContainerCreative) (Object) this).mc.thePlayer.inventoryContainer;

        for (int slotIndex = 0; slotIndex < container.inventorySlots.size(); ++slotIndex) {
            SlotCreative slot = new SlotCreative(gcc, (Slot) container.inventorySlots.get(slotIndex), slotIndex);

            int offset;
            if (slotIndex < 5) { // crafting slots are stored in indices 0 - 4
                slot.xDisplayPosition = -2000;
                slot.yDisplayPosition = -2000;
                //vanilla does this to render them offscreen
            } else if (slotIndex < 9) { // armor slots are stored in indices 5 - 8
                offset = slotIndex - 5;

                slot.xDisplayPosition = 9 + (offset / 2) * 54;
                slot.yDisplayPosition = 6 + (offset % 2) * 27;
            } else { // all other slots
                offset = slotIndex - 9;

                slot.xDisplayPosition = 9 + (offset % 18) * 18;
                slot.yDisplayPosition = (slotIndex < 54) ? 54 + (offset / 18) * 18 : 112;
            }

            containerCreative.inventorySlots.add(slot);
        }
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/renderer/texture/TextureManager;bindTexture(Lnet/minecraft/util/ResourceLocation;)V",
                            ordinal = 3,
                            remap = false),
                require = 1)
    private void rerouteBindTexture(TextureManager instance, ResourceLocation p_bindTexture_1_) {
        CreativeTabs creativeTabs = CreativeTabs.creativeTabArray[selectedTabIndex];
        String texturePath = locationPrefix + creativeTabs.getBackgroundImageName();
        instance.bindTexture(new ResourceLocation(Tags.MODID, texturePath));
    }

    @Redirect(  method = "drawGuiContainerBackgroundLayer",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/client/gui/inventory/GuiContainerCreative;drawTexturedModalRect(IIIIII)V",
                            ordinal = 0,
                            remap = false),
            require = 1)
    private void rerouteDrawCall(GuiContainerCreative instance, int x, int y, int u, int v, int w, int h) {
        GuiContainerCreative gcc = (GuiContainerCreative) (Object) (this);
        float zLevel = ((IMinecraftGuiMixin) (Object) (this)).getZLevel();

        int x1 = (gcc.width - gcc.xSize) / 2;
        int y1 = (gcc.height - gcc.ySize) / 2;

        PutinUtil.drawTexturedModalRect(x1, y1, 0, 0, gcc.xSize, gcc.ySize, zLevel);
    }

    @Inject(method = "handleMouseClick",
            at = @At(   value = "HEAD"),
            require = 1)
    private void captureSlot(Slot p_handleMouseClick_1_, int p_handleMouseClick_2_, int p_handleMouseClick_3_, int p_handleMouseClick_4_, CallbackInfo ci) {
        slot = p_handleMouseClick_1_;
    }

    @Redirect(  method = "handleMouseClick",
                at = @At(   value = "INVOKE",
                            target = "Lnet/minecraft/inventory/Container;slotClick(IIILnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;",
                            ordinal = 0),
                require = 1)
    private ItemStack rerouteSlotClick(Container instance, int p_slotClick_1_, int p_slotClick_2_, int p_slotClick_3_, EntityPlayer player) {
        try {
            return instance.slotClick(slot == null ? p_slotClick_2_ : slot.slotNumber, p_slotClick_2_, p_slotClick_3_, player);
        }
        finally {
            slot = null;
        }
    }

    @ModifyConstant(method = "initGui",
                    constant = @Constant(intValue = 12),
                    require = 1)
    private int updateTabCount(int constant) {
        return 22;
    }

    @ModifyConstant(method = {"initGui", "setCurrentCreativeTab"},
            constant = { @Constant(intValue = 82), @Constant(intValue = 171) },
            require = 1)
    private int modifySearchBarOffset(int constant) {
        return 333;
    }

    @ModifyConstant(method = "handleMouseInput",
                    constant = @Constant(intValue = 9),
                    require = 1)
    private int updateDivisor(int constant) {
        return 18;
    }

    @ModifyConstant(method =    {   "drawScreen", "drawGuiContainerBackgroundLayer" },
                    constant =  {   @Constant(  intValue = 10,
                                                ordinal = 0),
                                    @Constant(  intValue = 10,
                                                ordinal = 1)
                                },
                    require = 1)
    private int updateTabPageOffset(int constant) {
        return 20;
    }
}