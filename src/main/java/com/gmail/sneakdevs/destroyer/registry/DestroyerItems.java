package com.gmail.sneakdevs.destroyer.registry;

import com.gmail.sneakdevs.destroyer.DestroyerMod;
import com.gmail.sneakdevs.destroyer.items.ItemX;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DestroyerItems {
    public static final ItemX ITEM_X = Registry.register(Registries.ITEM, new Identifier(DestroyerMod.MODID, "item_x"), new ItemX(new FabricItemSettings().maxCount(1)));

    public static void addItemsToGroup() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(ITEM_X));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.BLOCK_X));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> entries.add(DestroyerBlocks.PLATED_BIFRONIUM));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.BIFRONIC_VEIN));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.DORMANT_BIFRONIC_VEIN));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.BIFRONIC_DIRT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.DORMANT_BIFRONIC_DIRT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.BIFRONIC_CARBON));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.DORMANT_BIFRONIC_CARBON));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.BIFRONIC_GRASS_BLOCK));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.DORMANT_BIFRONIC_GRASS_BLOCK));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.BIFRONIC_GRASS));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> entries.add(DestroyerBlocks.DORMANT_BIFRONIC_GRASS));
    }
}
