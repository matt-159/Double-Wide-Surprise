package com.github.matt159.dws.util;

import lombok.experimental.UtilityClass;
import lombok.val;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Method;
import java.util.Arrays;

@UtilityClass
public final class ReflectedModSupport {
    private static final Class<?> class_IBaubles;

    private static final Class<?> class_IAccessory;
    private static final Method IAccessory_canEquipAccessory;

    private static final Class<?> class_ITravellersGear;
    private static final Method ITravellersGear_getSlot;

    private static final Class<?> class_ContainerCreativeInv;

    private static final Class<?> class_BaublesExpandedSlots;
    private static final Method BaublesExpandedSlots_slotsCurrentlyUsed;
    private static final Method BaublesExpandedSlots_getSlotType;

    private static final Class<?> class_BaublesConfig;
    public static final boolean BaublesConfig_showUnusedSlots;


    static {
        {
            Class<?> tempClass = null;
            try {
                tempClass = Class.forName("baubles.api.IBauble");
            } catch (Exception ignored) {
            } finally {
                class_IBaubles = tempClass;
            }
        }

        {
            Class<?> tempClass = null;
            try {
                tempClass = Class.forName("tconstruct.library.accessory.IAccessory");
            } catch (Exception ignored) {
            } finally {
                class_IAccessory = tempClass;
            }

            Method tempMethod = null;
            try {
                tempMethod = class_IAccessory.getDeclaredMethod("canEquipAccessory",
                                                                ItemStack.class,
                                                                Integer.class);
            } catch (Exception ignored) {
            } finally {
                IAccessory_canEquipAccessory = tempMethod;
            }
        }

        {
            Class<?> tempClass = null;
            try {
                tempClass = Class.forName("travellersgear.api.ITravellersGear");
            } catch (Exception ignored) {
            } finally {
                class_ITravellersGear = tempClass;
            }

            Method tempMethod = null;
            try {
                tempMethod = class_ITravellersGear.getDeclaredMethod("getSlot", ItemStack.class);
            } catch (Exception ignored) {
            } finally {
                ITravellersGear_getSlot = tempMethod;
            }
        }

        {
            Class<?> tempClass = null;
            try {
                tempClass = Class.forName("codechicken.nei.ContainerCreativeInv");
            } catch (Exception ignored) {
            } finally {
                class_ContainerCreativeInv = tempClass;
            }
        }

        {
            Class<?> tempClass = null;
            try {
                tempClass = Class.forName("baubles.api.expanded.BaubleExpandedSlots");
            } catch (Exception ignored) {
            } finally {
                class_BaublesExpandedSlots = tempClass;
            }

            {
                Method tempMethod = null;
                try {
                    tempMethod = class_BaublesExpandedSlots.getDeclaredMethod("slotsCurrentlyUsed");
                } catch (Exception ignored) {
                } finally {
                    BaublesExpandedSlots_slotsCurrentlyUsed = tempMethod;
                }
            }

            {
                Method tempMethod = null;
                try {
                    //I don't why 'getDeclaredMethod' was returning `NoSuchMethodException', but here we are
                    tempMethod = Arrays.stream(class_BaublesExpandedSlots.getDeclaredMethods())
                                       .filter(method -> method.getName().equals("getSlotType"))
                                       .findFirst()
                                       .orElse(null);
                } catch (Exception ignored) {
                } finally {
                    BaublesExpandedSlots_getSlotType = tempMethod;
                }
            }
        }

        {
            Class<?> tempClass = null;
            try {
                tempClass = Class.forName("baubles.common.BaublesConfig");
            } catch (Exception ignored) {
            } finally {
                class_BaublesConfig = tempClass;
            }

            boolean tempField = false;
            try {
                tempField = (boolean) class_BaublesConfig.getField("showUnusedSlots").get(null);
            } catch (Exception ignored) {
            } finally {
                BaublesConfig_showUnusedSlots = tempField;
            }
        }
    }

    public static boolean instanceof_IBauble(Object object) {
        return class_IBaubles != null && class_IBaubles.isAssignableFrom(object.getClass());
    }

    public static boolean instanceof_IAccessory(Object object) {
        return class_IAccessory != null && class_IAccessory.isAssignableFrom(object.getClass());
    }

    public static boolean IAccessory_canEquipAccessory(ItemStack itemStack, int slotIndex) {
        try {
            val item = class_IAccessory.cast(itemStack.getItem());

            return (boolean) IAccessory_canEquipAccessory.invoke(item, itemStack, slotIndex);
        } catch (Exception ignored) {
            return false;
        }
    }

    public static boolean instanceof_ITravellersGear(Object object) {
        return class_ITravellersGear != null && class_ITravellersGear.isAssignableFrom(object.getClass());
    }

    public static Integer ITravellersGear_getSlot(ItemStack itemStack) {
        try {
            return (Integer) ITravellersGear_getSlot.invoke(itemStack.getItem(), itemStack);
        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean instanceof_ContainerCreativeInv(Object object) {
        return class_ContainerCreativeInv != null && class_ContainerCreativeInv.isAssignableFrom(object.getClass());
    }

    public static int BaublesExpandedSlots_slotsCurrentlyUsed() {
        try {
            return (int) BaublesExpandedSlots_slotsCurrentlyUsed.invoke(null);
        } catch (Exception ignored) {
            return 0;
        }
    }

    public static String BaublesExpandedSlots_getSlotType(int slotIndex) {
        try {
            return (String) BaublesExpandedSlots_getSlotType.invoke(null, slotIndex);
        } catch (Exception ignored) {
            return "unknown";
        }
    }
}
