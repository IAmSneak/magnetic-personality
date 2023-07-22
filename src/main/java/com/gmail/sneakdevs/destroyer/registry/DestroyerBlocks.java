package com.gmail.sneakdevs.destroyer.registry;

import com.gmail.sneakdevs.destroyer.DestroyerMod;
import com.gmail.sneakdevs.destroyer.blocks.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class DestroyerBlocks {
    public static final BlockX BLOCK_X = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "block_x"), new BlockX(FabricBlockSettings.create().strength(-1F)));

    public static final Block PLATED_BIFRONIUM = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "plated_bifronium"), new Block(FabricBlockSettings.create().strength(.8F)));

    public static final BifronicDirt BIFRONIC_DIRT = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "bifronic_dirt"), new BifronicDirt(FabricBlockSettings.create().strength(.8F).sounds(BlockSoundGroup.ROOTED_DIRT)));
    public static final DormantBifronicDirt DORMANT_BIFRONIC_DIRT = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "dormant_bifronic_dirt"), new DormantBifronicDirt(FabricBlockSettings.create().strength(.8F).sounds(BlockSoundGroup.ROOTED_DIRT)));
    public static final BifronicCarbon BIFRONIC_CARBON = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "bifronic_carbon"), new BifronicCarbon(FabricBlockSettings.create().strength(1.5F).sounds(BlockSoundGroup.BONE)));
    public static final DormantBifronicCarbon DORMANT_BIFRONIC_CARBON = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "dormant_bifronic_carbon"), new DormantBifronicCarbon(FabricBlockSettings.create().strength(1.5F).sounds(BlockSoundGroup.BONE)));
    public static final BifronicGrassBlock BIFRONIC_GRASS_BLOCK = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "bifronic_grass_block"), new BifronicGrassBlock(FabricBlockSettings.create().strength(1.0F).sounds(BlockSoundGroup.SOUL_SOIL)));
    public static final DormantBifronicGrassBlock DORMANT_BIFRONIC_GRASS_BLOCK = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "dormant_bifronic_grass_block"), new DormantBifronicGrassBlock(FabricBlockSettings.create().strength(1.0F).sounds(BlockSoundGroup.SOUL_SOIL)));
    public static final BifronicGrass BIFRONIC_GRASS = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "bifronic_grass"), new BifronicGrass(FabricBlockSettings.create().strength(0.2F).noCollision().nonOpaque().luminance(2).replaceable().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.GRASS)));
    public static final DormantBifronicGrass DORMANT_BIFRONIC_GRASS = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "dormant_bifronic_grass"), new DormantBifronicGrass(FabricBlockSettings.create().strength(0.2F).noCollision().nonOpaque().luminance(6).replaceable().pistonBehavior(PistonBehavior.DESTROY).sounds(BlockSoundGroup.GRASS)));
    public static final BifronicVein BIFRONIC_VEIN = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "bifronic_vein"), new BifronicVein(FabricBlockSettings.create().strength(.7F).noCollision().pistonBehavior(PistonBehavior.DESTROY).nonOpaque().notSolid().sounds(BlockSoundGroup.SCULK_VEIN)));
    public static final DormantBifronicVein DORMANT_BIFRONIC_VEIN = Registry.register(Registries.BLOCK, new Identifier(DestroyerMod.MODID, "dormant_bifronic_vein"), new DormantBifronicVein(FabricBlockSettings.create().strength(.7F).noCollision().pistonBehavior(PistonBehavior.DESTROY).nonOpaque().notSolid().sounds(BlockSoundGroup.SCULK_VEIN)));

}
