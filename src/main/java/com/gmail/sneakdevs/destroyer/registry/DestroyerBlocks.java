package com.gmail.sneakdevs.destroyer.registry;

import com.gmail.sneakdevs.destroyer.DestroyerMod;
import com.gmail.sneakdevs.destroyer.blocks.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class DestroyerBlocks {
    public static final BlockX BLOCK_X = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "block_x"), new BlockX(FabricBlockSettings.create().strength(-1F)));

    public static final BifronicDirt BIFRONIC_DIRT = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "bifronic_dirt"), new BifronicDirt(FabricBlockSettings.create().strength(.5F)));
    public static final BifronicGrassBlock BIFRONIC_GRASS_BLOCK = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "bifronic_grass_block"), new BifronicGrassBlock(FabricBlockSettings.create().strength(.6F)));
    public static final BifronicGrass BIFRONIC_GRASS = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "bifronic_grass"), new BifronicGrass(FabricBlockSettings.create().strength(0.0F).noCollision().nonOpaque().luminance(2).replaceable().slipperiness(3F).pistonBehavior(PistonBehavior.DESTROY)));

    public static final DormantBifronicDirt DORMANT_BIFRONIC_DIRT = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "dormant_bifronic_dirt"), new DormantBifronicDirt(FabricBlockSettings.create().strength(.5F)));
    public static final DormantBifronicGrassBlock DORMANT_BIFRONIC_GRASS_BLOCK = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "dormant_bifronic_grass_block"), new DormantBifronicGrassBlock(FabricBlockSettings.create().strength(.6F)));
    public static final DormantBifronicGrass DORMANT_BIFRONIC_GRASS = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "dormant_bifronic_grass"), new DormantBifronicGrass(FabricBlockSettings.create().strength(0.0F).noCollision().nonOpaque().luminance(2).replaceable().slipperiness(3F).pistonBehavior(PistonBehavior.DESTROY)));

}
