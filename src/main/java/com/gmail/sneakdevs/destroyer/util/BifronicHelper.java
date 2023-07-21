package com.gmail.sneakdevs.destroyer.util;

import com.gmail.sneakdevs.destroyer.interfaces.BlockInterface;
import com.gmail.sneakdevs.destroyer.registry.DestroyerGameRules;
import com.gmail.sneakdevs.destroyer.registry.DestroyerTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.dimension.DimensionTypes;

public class BifronicHelper {
    public static boolean bifronicRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getGameRules().getInt(DestroyerGameRules.BIFRONIC_SPREAD_CHANCE) <= 0) {
            return false;
        }
        if (random.nextInt(world.getGameRules().getInt(DestroyerGameRules.BIFRONIC_SPREAD_CHANCE)) != 0) {
            return false;
        }
        if (Math.abs(pos.getX()) > 1500 || Math.abs(pos.getZ()) > 1500 || !world.getDimensionEntry().matchesId(DimensionTypes.OVERWORLD_ID) || world.getBiome(pos).matchesId(new Identifier("mushroom_fields")) || world.getBiome(pos).matchesId(new Identifier("primordial_waters"))) {
            if (((BlockInterface) world.getBlockState(pos).getBlock()).getDormantVersion(world, pos, random) != null) {
                world.setBlockState(pos, ((BlockInterface) world.getBlockState(pos).getBlock()).getDormantVersion(world, pos, random).getDefaultState());
                world.playSound(null, pos, SoundEvents.BLOCK_SOUL_SAND_BREAK, SoundCategory.BLOCKS, 0.5f, 1f);
                return true;
            }
        }
        for (int x = -2; x < 3; x++) {
            for (int y = 0; y < 2; y++) {
                for (int z = -2; z < 3; z++) {
                    if (world.getBlockState(pos.add(x, y, z)).isIn(DestroyerTags.PACIFIES_BIFRONIC_BLOCKS)) {
                        if (((BlockInterface) world.getBlockState(pos).getBlock()).getDormantVersion(world, pos, random) != null) {
                            world.setBlockState(pos, ((BlockInterface) world.getBlockState(pos).getBlock()).getDormantVersion(world, pos, random).getDefaultState());
                            world.playSound(null, pos, SoundEvents.BLOCK_SOUL_SAND_BREAK, SoundCategory.BLOCKS, 0.5f, 1f);
                            return true;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 8; ++i) {
            BlockPos spreadPos = pos.add(random.nextInt(7) - 3, random.nextInt(5) - 2, random.nextInt(7) - 3);
            if (((BlockInterface)world.getBlockState(spreadPos).getBlock()).getCorruptedVersion(world, spreadPos, random) != null) {
                world.setBlockState(spreadPos, ((BlockInterface)world.getBlockState(spreadPos).getBlock()).getCorruptedVersion(world, spreadPos, random).getDefaultState());
                world.playSound(null, pos, SoundEvents.BLOCK_SOUL_SOIL_PLACE, SoundCategory.BLOCKS, 0.5f, 1f);
                return true;
            }
            if (world.getBlockState(spreadPos).isIn(BlockTags.SMALL_FLOWERS)) {
                world.setBlockState(spreadPos, Blocks.WITHER_ROSE.getDefaultState());
                world.playSound(null, pos, SoundEvents.ENTITY_WITHER_SKELETON_STEP, SoundCategory.BLOCKS, 0.5f, 1f);
                return true;
            }
        }
        return false;
    }

    public static void dormantBifronicRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getGameRules().getInt(DestroyerGameRules.BIFRONIC_SPREAD_CHANCE) <= 0) {
            return;
        }
        if (random.nextInt(world.getGameRules().getInt(DestroyerGameRules.BIFRONIC_SPREAD_CHANCE)) != 0) {
            return;
        }
        if (Math.abs(pos.getX()) > 1500 || Math.abs(pos.getZ()) > 1500 || !world.getDimensionEntry().matchesId(DimensionTypes.OVERWORLD_ID) || world.getBiome(pos).matchesId(new Identifier("mushroom_fields")) || world.getBiome(pos).matchesId(new Identifier("primordial_waters"))) {
            return;
        }
        for (int x = -2; x < 3; x++) {
            for (int y = 0; y < 2; y++) {
                for (int z = -2; z < 3; z++) {
                    if (world.getBlockState(pos.add(x, y, z)).isIn(DestroyerTags.PACIFIES_BIFRONIC_BLOCKS)) {
                        return;
                    }
                }
            }
        }
        if (((BlockInterface) world.getBlockState(pos).getBlock()).getCorruptedVersion(world, pos, random) != null) {
            world.setBlockState(pos, ((BlockInterface) world.getBlockState(pos).getBlock()).getCorruptedVersion(world, pos, random).getDefaultState());
            world.playSound(null, pos, SoundEvents.BLOCK_SOUL_SAND_PLACE, SoundCategory.BLOCKS, 0.5f, 1f);
        }
    }
}
