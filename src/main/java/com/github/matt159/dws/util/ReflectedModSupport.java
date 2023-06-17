package com.github.matt159.dws.util;

import net.minecraft.item.ItemStack;

import java.lang.reflect.Method;

public class ReflectedModSupport {
    private static Class<?> class_IBaubles = null;
    private static Class<?> class_IAccessory = null;
    private static Method IAccessory_canEquipAccessory = null;

    public static Class<?> getIBaublesInterface() {
        try {
            if (class_IBaubles == null) {
                class_IBaubles = Class.forName("baubles.api.IBauble");
            }
        } catch (Exception e) {}

        return class_IBaubles;
    }

    public static Class<?> getIAccessoryInterface() {
        try {
            if (class_IAccessory == null) {
                class_IAccessory = Class.forName("tconstruct.library.accessory.IAccessory");
            }
        } catch (Exception e) {}

        return class_IAccessory;
    }

    public static boolean isInstanceOfIBauble(Object object) {
        return getIBaublesInterface().isAssignableFrom(object.getClass());
    }

    public static boolean isInstanceOfIAccessory(Object object) {
        return getIAccessoryInterface().isAssignableFrom(object.getClass());
    }

    public static boolean IAccessory_canEquipAccessory(ItemStack itemStack, int slotIndex) {
        try {
            if (IAccessory_canEquipAccessory == null) {
                IAccessory_canEquipAccessory = getIAccessoryInterface().getDeclaredMethod("canEquipAccessory", ItemStack.class, Integer.class);
            }

            return (Boolean) IAccessory_canEquipAccessory.invoke(getIAccessoryInterface().cast(itemStack.getItem()), itemStack, slotIndex);
        } catch (Exception e) {
            return false;
        }
    }
}
