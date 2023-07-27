package com.gmail.sneakdevs.destroyer;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import com.gmail.sneakdevs.destroyer.registry.DestroyerGameRules;
import com.gmail.sneakdevs.destroyer.registry.DestroyerItems;
import com.gmail.sneakdevs.destroyer.registry.DestroyerTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DestroyerMod implements ModInitializer {
    public static String MODID = "destroyer";

    @Override
    public void onInitialize() {
        new DestroyerBlocks();
        new DestroyerItems();
        new DestroyerGameRules();
        new DestroyerTags();
        DestroyerItems.addItemsToGroup();
        Registry.register(Registries.ITEM, new Identifier(MODID, "block_x"), new BlockItem(DestroyerBlocks.BLOCK_X, new FabricItemSettings()));

        Registry.register(Registries.ITEM, new Identifier(MODID, "plated_bifronium"), new BlockItem(DestroyerBlocks.PLATED_BIFRONIUM, new FabricItemSettings()));

        Registry.register(Registries.ITEM, new Identifier(MODID, "bifronic_vein"), new BlockItem(DestroyerBlocks.BIFRONIC_VEIN, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(MODID, "bifronic_dirt"), new BlockItem(DestroyerBlocks.BIFRONIC_DIRT, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(MODID, "bifronic_carbon"), new BlockItem(DestroyerBlocks.BIFRONIC_CARBON, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(MODID, "bifronic_grass_block"), new BlockItem(DestroyerBlocks.BIFRONIC_GRASS_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(MODID, "bifronic_grass"), new BlockItem(DestroyerBlocks.BIFRONIC_GRASS, new FabricItemSettings()));

        Registry.register(Registries.ITEM, new Identifier(MODID, "dormant_bifronic_vein"), new BlockItem(DestroyerBlocks.DORMANT_BIFRONIC_VEIN, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(MODID, "dormant_bifronic_dirt"), new BlockItem(DestroyerBlocks.DORMANT_BIFRONIC_DIRT, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(MODID, "dormant_bifronic_carbon"), new BlockItem(DestroyerBlocks.DORMANT_BIFRONIC_CARBON, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(MODID, "dormant_bifronic_grass_block"), new BlockItem(DestroyerBlocks.DORMANT_BIFRONIC_GRASS_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.ITEM, new Identifier(MODID, "dormant_bifronic_grass"), new BlockItem(DestroyerBlocks.DORMANT_BIFRONIC_GRASS, new FabricItemSettings()));
    }
}
