package com.gmail.sneakdevs.destroyer.registry;

import com.gmail.sneakdevs.destroyer.DestroyerMod;
import com.gmail.sneakdevs.destroyer.armor.BifroniumHazmatArmorMaterial;
import com.gmail.sneakdevs.destroyer.items.ItemX;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DestroyerItems {
    public static final ArmorMaterial BIFRONIUM_HAZMAT_ARMOR_MATERIAL = new BifroniumHazmatArmorMaterial();

    public static final ItemX ITEM_X = Registry.register(Registries.ITEM, new Identifier(DestroyerMod.MODID, "item_x"), new ItemX(new FabricItemSettings().maxCount(1)));
    public static final Item BIFRONIC_STRAW = Registry.register(Registries.ITEM, new Identifier(DestroyerMod.MODID, "bifronic_straw"), new Item(new FabricItemSettings().maxCount(64)));
    public static final Item BIFRONIUM = Registry.register(Registries.ITEM, new Identifier(DestroyerMod.MODID, "bifronium"), new Item(new FabricItemSettings().maxCount(64)));

    public static final ArmorItem BIFRONIUM_HAZMAT_CAP = Registry.register(Registries.ITEM, new Identifier(DestroyerMod.MODID, "bifronium_hazmat_cap"), new ArmorItem(BIFRONIUM_HAZMAT_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new FabricItemSettings().maxCount(1)));
    public static final ArmorItem BIFRONIUM_HAZMAT_TUNIC = Registry.register(Registries.ITEM, new Identifier(DestroyerMod.MODID, "bifronium_hazmat_tunic"), new ArmorItem(BIFRONIUM_HAZMAT_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().maxCount(1)));
    public static final ArmorItem BIFRONIUM_HAZMAT_LEGGINGS = Registry.register(Registries.ITEM, new Identifier(DestroyerMod.MODID, "bifronium_hazmat_leggings"), new ArmorItem(BIFRONIUM_HAZMAT_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new FabricItemSettings().maxCount(1)));
    public static final ArmorItem BIFRONIUM_HAZMAT_BOOTS = Registry.register(Registries.ITEM, new Identifier(DestroyerMod.MODID, "bifronium_hazmat_boots"), new ArmorItem(BIFRONIUM_HAZMAT_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new FabricItemSettings().maxCount(1)));


    public static void addItemsToGroup() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(ITEM_X));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.add(BIFRONIC_STRAW));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> entries.add(BIFRONIUM));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.add(BIFRONIUM_HAZMAT_CAP));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.add(BIFRONIUM_HAZMAT_TUNIC));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.add(BIFRONIUM_HAZMAT_LEGGINGS));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.add(BIFRONIUM_HAZMAT_BOOTS));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.BLOCK_X));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> entries.add(DestroyerBlocks.PLATED_BIFRONIUM));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.BIFRONIC_GRASS_BLOCK));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.DORMANT_BIFRONIC_GRASS_BLOCK));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.BIFRONIC_DIRT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.DORMANT_BIFRONIC_DIRT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.BIFRONIC_CARBON));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.DORMANT_BIFRONIC_CARBON));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.BIFRONIC_VEIN));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.DORMANT_BIFRONIC_VEIN));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.BIFRONIC_GRASS));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.DORMANT_BIFRONIC_GRASS));
    }
}
