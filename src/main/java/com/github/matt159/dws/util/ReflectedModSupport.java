package com.github.matt159.dws.util;

import net.minecraft.item.ItemStack;

import java.lang.reflect.Method;

public class ReflectedModSupport {
    private static Class<?> class_IBaubles = null;

    private static Class<?> class_IAccessory = null;
    private static Method IAccessory_canEquipAccessory = null;

    private static Class<?> class_ITravellersGear = null;
    private static Method ITravellersGear_getSlot = null;

    private static Class<?> class_ContainerCreativeInv = null;

    public static Class<?> getClass_IBauble() {
        try {
            if (class_IBaubles == null) {
                class_IBaubles = Class.forName("baubles.api.IBauble");
            }
        } catch (Exception e) {}

        return class_IBaubles;
    }

    public static boolean instanceof_IBauble(Object object) {
        return getClass_IBauble().isAssignableFrom(object.getClass());
    }

    public static Class<?> getClass_IAccessory() {
        try {
            if (class_IAccessory == null) {
                class_IAccessory = Class.forName("tconstruct.library.accessory.IAccessory");
            }
        } catch (Exception e) {}

        return class_IAccessory;
    }

    public static boolean instanceof_IAccessory(Object object) {
        return getClass_IAccessory().isAssignableFrom(object.getClass());
    }

    public static boolean IAccessory_canEquipAccessory(ItemStack itemStack, int slotIndex) {
        try {
            if (IAccessory_canEquipAccessory == null) {
                IAccessory_canEquipAccessory = getClass_IAccessory().getDeclaredMethod("canEquipAccessory", ItemStack.class, Integer.class);
            }

            return (Boolean) IAccessory_canEquipAccessory.invoke(getClass_IAccessory().cast(itemStack.getItem()), itemStack, slotIndex);
        } catch (Exception e) {
            return false;
        }
    }

    public static Class<?> getClass_ITravellersGear() {
        try {
            if (class_ITravellersGear == null) {
                class_ITravellersGear = Class.forName("travellersgear.api.ITravellersGear");
            }
        } catch (Exception e) {}

        return class_ITravellersGear;
    }

    public static boolean instanceof_ITravellersGear(Object object) {
        return getClass_ITravellersGear().isAssignableFrom(object.getClass());
    }

    public static Integer ITravellersGear_getSlot(ItemStack itemStack) {
        try {
            if (ITravellersGear_getSlot == null) {
                ITravellersGear_getSlot = getClass_ITravellersGear().getDeclaredMethod("getSlot", ItemStack.class);
            }

            return (Integer) ITravellersGear_getSlot.invoke(itemStack.getItem(), itemStack);
        } catch (Exception e) {
            return -1;
        }
    }

    public static Class<?> getClass_ContainerCreativeInv() {
        try {
            if (class_ContainerCreativeInv == null) {
                class_ContainerCreativeInv = Class.forName("codechicken.nei.ContainerCreativeInv");
            }
        } catch (Exception e) {}

        return class_ContainerCreativeInv;
    }

    public static boolean instanceof_ContainerCreativeInv(Object object) {
        Class<?> class_ContainerCreativeInv = getClass_ContainerCreativeInv();

        if (class_ContainerCreativeInv != null) {
            return getClass_ContainerCreativeInv().isAssignableFrom(object.getClass());
        } else {
            return false;
        }
    }
}
