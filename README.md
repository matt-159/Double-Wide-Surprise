

![image](https://user-images.githubusercontent.com/16054364/208403173-a0c88d33-687c-4d63-bace-51af8e79eaf9.png)
# Double Wide Surprise

----
A 1.7.10 Minecraft mod that doubles the size of the minecraft player inventory and hotbar. Baubles, Tinkers, Traveller's Gear, and Galacticraft Accessories are all able to equipped from inside one comprehensive GUI.
Most modded inventories should work, but explicit support has been completed for:
- Vanilla
- Gregtech
- Ironchests
- AE2
- Forestry
- Storage Drawers
- Inventory Tweaks
  
Accessories from the following mods have their slots added to the main inventory when present:
- Baubles 
- Baubles Expanded
- Travellers Gear
- Galacticraft
- Tinkers Construct

Individually Supported Modded Inventories:
- Garden Core
  - Compost Bin
- Agricraft
  - Seed Analyzer

## Dependencies
- [UniMixins](https://github.com/LegacyModdingMC/UniMixins)
- [FalsePatternLib](https://github.com/FalsePattern/FalsePatternLib)

## Mix-'n'-Match Bauble Slots
Adds tag based baubles slots to the player inventory.
You can enable these slots in the `dws.cfg`:
```
# Ignore modded accessory slots and use Mix 'n' Match bauble slots instead
# [default: false]
B:slotOverride=true
```
### THESE SLOTS WILL PREVENT OTHER MODDED ACCESSORY SLOTS FROM BEING ADDED!
These mix and match slots require some alteration to the Baubles Expanded config to work properly.  
You'll want your Baubles Expanded config to look as such:
```
# Manually override slot assignments.
# !Bauble slot types must be configured manually with this option enabled!
#  [default: false]
B:manualSlotSelection=true
```
```
# Slot assignments to use if manualSlotSelection is enabled.
# Any assignents after the first 20 will be ignored.
# !Adding, moving, or removing slots of the amulet, ring, or belt types will reduce compatibility with mods made for original Baubles versions!
#  [default: [amulet], [ring], [ring], [belt]]
S:slotTypeOverrides <
    universal
    universal
    universal
    universal
    universal
    universal
    universal
    universal
    universal
    universal
    universal
    universal
    universal
    universal
    universal
    universal
    universal
    universal
    universal
    universal
 >
```
This will create a 5x4 baubles inventory grid in the player inventory window.  
You can specify the tag list in `dws.cfg` (load the mod once so the config file generates)
```
slots {
    # Ignore modded accessory slots and use Mix 'n' Match bauble slots instead
    # [default: false]
    B:slotOverride=true

    # Item groupings for Mix 'n' Match slots
    # Frequency of values determines how many of corresponding bauble type can be worn
    # "amulet", "ring", "ring", "belt" -> 1 amulet, 2 rings, 1 belt
    # [default: ["amulet", "ring", "ring", "belt"]]
    S:slotGroupings <
        amulet
        ring
        ring
        ring
        ring
        belt
     >
}
```
In this example, you'd have the ability to wear 1 amulet, 4 rings, and 1 belt.  
Do note that Baubles Expanded provides the ability for a bauble to have more than one type!

## FAQ:
- Will you need to use an item to unlock these extra slots?
  - No. You get it for free
  
- Will you add a 3x3 crafting grid inside the player inventory?
  - Nope
  
- Will any version passed 1.7.10 be supported?
  - What are you talking about? 1.7.10 ***is*** the latest version of Minecraft
 
- How do I access the second half of my hotbar?
  - You can quickly double tap a key to get to the corresponding slot in the second half of the hotbar. 1, 1 -> slot 10
