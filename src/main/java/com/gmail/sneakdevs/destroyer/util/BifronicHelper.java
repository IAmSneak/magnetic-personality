package com.gmail.sneakdevs.destroyer.util;

import com.gmail.sneakdevs.destroyer.interfaces.BlockInterface;
import com.gmail.sneakdevs.destroyer.mixin.BiomeAccessAccessor;
import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import com.gmail.sneakdevs.destroyer.registry.DestroyerGameRules;
import com.gmail.sneakdevs.destroyer.registry.DestroyerTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeCoords;
import net.minecraft.world.chunk.PalettedContainer;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.List;
import java.util.function.Consumer;

public class BifronicHelper {

    public static boolean bifronicRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getGameRules().getInt(DestroyerGameRules.BIFRONIC_SPREAD_CHANCE) <= 0) {
            return false;
        }
        if (random.nextInt(world.getGameRules().getInt(DestroyerGameRules.BIFRONIC_SPREAD_CHANCE)) != 0) {
            return false;
        }
        if (pos.getY() < 15 || !world.getDimensionEntry().matchesId(DimensionTypes.OVERWORLD_ID) || Math.pow(Math.abs(pos.getX()), 2) + Math.pow(Math.abs(pos.getZ()), 2) > 1440000) {
            if (((BlockInterface) state.getBlock()).getDormantVersion(world, pos, random) != null) {
                world.setBlockState(pos, ((BlockInterface) state.getBlock()).getDormantVersion(world, pos, random));
                world.playSound(null, pos, SoundEvents.BLOCK_SOUL_SAND_BREAK, SoundCategory.BLOCKS, 0.5f, 1f);
                return true;
            }
        }

        for (int i = 0; i < 8; ++i) {
            BlockPos spreadPos = pos.add(random.nextInt(7) - 3, random.nextInt(5) - 2, random.nextInt(7) - 3);
            BlockState spreadState = world.getBlockState(spreadPos);
            if (((BlockInterface)spreadState.getBlock()).getCorruptedVersion(world, spreadPos, random) != null && !spreadState.isIn(DestroyerTags.DORMANT_BIFRONIC_BLOCKS)) {
                for (int x = -1; x < 2; x++) {
                    for (int y = -2; y < 1; y++) {
                        for (int z = -1; z < 2; z++) {
                            if (world.getBiome(spreadPos.add(x, y, z)).matchesId(new Identifier("destroyer", "primordial_ocean"))) {
                                return false;
                            }
                        }
                    }
                }
                for (int x = -3; x < 4; x++) {
                    for (int y = -9; y < 3; y++) {
                        for (int z = -3; z < 4; z++) {
                            if (world.getBlockState(pos.add(x, y, z)).isIn(DestroyerTags.PACIFIES_BIFRONIC_BLOCKS)) {
                                if (((BlockInterface) state.getBlock()).getDormantVersion(world, pos, random) != null) {
                                    world.setBlockState(pos, ((BlockInterface) state.getBlock()).getDormantVersion(world, pos, random));
                                    for (int yB = world.getBottomY(); yB < world.getTopY(); yB+=2) {
                                        setBiome(world, pos.getX(), yB, pos.getZ(), world.getServer().getRegistryManager().get(RegistryKeys.BIOME).getEntry(RegistryKey.of(RegistryKeys.BIOME, new Identifier("destroyer", "tainted_barrens"))).orElse(null), BifronicHelper::updateChunk);
                                    }
                                    world.playSound(null, pos, SoundEvents.BLOCK_SOUL_SAND_BREAK, SoundCategory.BLOCKS, 0.5f, 1f);
                                    return true;
                                }
                            }
                        }
                    }
                }
                world.setBlockState(spreadPos, ((BlockInterface)spreadState.getBlock()).getCorruptedVersion(world, spreadPos, random));
                int spreadPosX = spreadPos.getX();
                int spreadPosZ = spreadPos.getZ();
                RegistryEntry<Biome> biome = world.getServer().getRegistryManager().get(RegistryKeys.BIOME).getEntry(RegistryKey.of(RegistryKeys.BIOME, new Identifier("destroyer", "forsaken_acres"))).orElse(null);
                for (int y = world.getBottomY(); y < world.getTopY(); y+=2) {
                    setBiome(world, spreadPosX, y, spreadPosZ, biome, BifronicHelper::updateChunk);
                }
                world.playSound(null, pos, SoundEvents.BLOCK_SOUL_SOIL_PLACE, SoundCategory.BLOCKS, 0.5f, 1f);
                return true;
            }
            if (spreadState.isIn(BlockTags.SMALL_FLOWERS)) {
                world.setBlockState(spreadPos, Blocks.WITHER_ROSE.getDefaultState());
                world.playSound(null, pos, SoundEvents.ENTITY_WITHER_SKELETON_STEP, SoundCategory.BLOCKS, 0.5f, 1f);
                return true;
            }
            if (spreadState.isAir() && DestroyerBlocks.BIFRONIC_VEIN.canPlaceAt(spreadState, world, spreadPos)) {
                for (int x = -1; x < 2; x++) {
                    for (int y = -2; y < 1; y++) {
                        for (int z = -1; z < 2; z++) {
                            if (world.getBiome(spreadPos.add(x, y, z)).matchesId(new Identifier("destroyer", "primordial_ocean"))) {
                                return false;
                            }
                        }
                    }
                }
                for (int x = -3; x < 4; x++) {
                    for (int y = -9; y < 3; y++) {
                        for (int z = -3; z < 4; z++) {
                            if (world.getBlockState(pos.add(x, y, z)).isIn(DestroyerTags.PACIFIES_BIFRONIC_BLOCKS)) {
                                if (((BlockInterface) state.getBlock()).getDormantVersion(world, pos, random) != null) {
                                    world.setBlockState(pos, ((BlockInterface) state.getBlock()).getDormantVersion(world, pos, random));
                                    for (int yB = world.getBottomY(); yB < world.getTopY(); yB+=2) {
                                        setBiome(world, pos.getX(), yB, pos.getZ(), world.getServer().getRegistryManager().get(RegistryKeys.BIOME).getEntry(RegistryKey.of(RegistryKeys.BIOME, new Identifier("destroyer", "tainted_barrens"))).orElse(null), BifronicHelper::updateChunk);
                                    }
                                    world.playSound(null, pos, SoundEvents.BLOCK_SOUL_SAND_BREAK, SoundCategory.BLOCKS, 0.5f, 1f);
                                    return true;
                                }
                            }
                        }
                    }
                }
                world.setBlockState(spreadPos, DestroyerBlocks.BIFRONIC_VEIN.getDefaultState());
                int spreadPosX = spreadPos.getX();
                int spreadPosZ = spreadPos.getZ();
                RegistryEntry<Biome> biome = world.getServer().getRegistryManager().get(RegistryKeys.BIOME).getEntry(RegistryKey.of(RegistryKeys.BIOME, new Identifier("destroyer", "forsaken_acres"))).orElse(null);
                for (int y = world.getBottomY(); y < world.getTopY(); y+=2) {
                    setBiome(world, spreadPosX, y, spreadPosZ, biome, BifronicHelper::updateChunk);
                }
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

        if (pos.getY() < 15) {
            return;
        }
        if (!world.getDimensionEntry().matchesId(DimensionTypes.OVERWORLD_ID) || Math.pow(Math.abs(pos.getX()), 2) + Math.pow(Math.abs(pos.getZ()), 2) > 1440000) {
            return;
        }

        for (int x = -3; x < 4; x++) {
            for (int y = -9; y < 2; y++) {
                for (int z = -3; z < 4; z++) {
                    if (world.getBlockState(pos.add(x, y, z)).isIn(DestroyerTags.PACIFIES_BIFRONIC_BLOCKS)) {
                        return;
                    }
                }
            }
        }

        for (int x = -1; x < 2; x++) {
            for (int y = -2; y < 1; y++) {
                for (int z = -1; z < 2; z++) {
                    if (world.getBiome(pos.add(x, y, z)).matchesId(new Identifier("destroyer", "primordial_ocean"))) {
                        return;
                    }
                }
            }
        }
        if (((BlockInterface) state.getBlock()).getCorruptedVersion(world, pos, random) != null) {
            world.setBlockState(pos, ((BlockInterface) state.getBlock()).getCorruptedVersion(world, pos, random));
            for (int y = world.getBottomY(); y < world.getTopY(); y+=2) {
                setBiome(world, pos.getX(), y, pos.getZ(), world.getServer().getRegistryManager().get(RegistryKeys.BIOME).getEntry(RegistryKey.of(RegistryKeys.BIOME, new Identifier("destroyer", "forsaken_acres"))).orElse(null), BifronicHelper::updateChunk);
            }
            world.playSound(null, pos, SoundEvents.BLOCK_SOUL_SAND_PLACE, SoundCategory.BLOCKS, 0.5f, 1f);
        }
    }

    /*
    From Patbox's Biome Technologies mod
     */
    public static void setBiome(ServerWorld world, int x, int y, int z, RegistryEntry<Biome> biome, Consumer<WorldChunk> dirtyChunkConsumer) {
        {
            int i = x - 2;
            int j = y - 2;
            int k = z - 2;
            int l = i >> 2;
            int m = j >> 2;
            int n = k >> 2;
            double d = (double) (i & 3) / 4.0D;
            double e = (double) (j & 3) / 4.0D;
            double f = (double) (k & 3) / 4.0D;
            int o = 0;
            double g = 1.0D / 0.0;

            int p;
            for (p = 0; p < 8; ++p) {
                boolean bl = (p & 4) == 0;
                boolean bl2 = (p & 2) == 0;
                boolean bl3 = (p & 1) == 0;
                int q = bl ? l : l + 1;
                int r = bl2 ? m : m + 1;
                int s = bl3 ? n : n + 1;
                double h = bl ? d : d - 1.0D;
                double t = bl2 ? e : e - 1.0D;
                double u = bl3 ? f : f - 1.0D;
                double v = BiomeAccessAccessor.callMethod_38106(((BiomeAccessAccessor) world.getBiomeAccess()).getSeed(), q, r, s, h, t, u);
                if (g > v) {
                    o = p;
                    g = v;
                }
            }

            x = (o & 4) == 0 ? l : l + 1;
            y = (o & 2) == 0 ? m : m + 1;
            z = (o & 1) == 0 ? n : n + 1;
        }

        var chunk = world.getChunk(BiomeCoords.toChunk(x), BiomeCoords.toChunk(z));

        var id = chunk.getSectionIndex(BiomeCoords.toBlock(y));

        if (id < 0 || id >= chunk.getSectionArray().length) {
            return;
        }

        var section = chunk.getSection(id);

        if (section.getBiomeContainer() instanceof PalettedContainer<RegistryEntry<Biome>> container && container.get(
                x & 3, y & 3, z & 3
        ) != biome) {
            container.swapUnsafe(
                    x & 3, y & 3, z & 3,
                    biome
            );

            chunk.setNeedsSaving(true);
            dirtyChunkConsumer.accept(chunk);
        }

    }

    public static void updateChunk(WorldChunk chunk) {
        ((ServerWorld) chunk.getWorld()).getChunkManager().threadedAnvilChunkStorage.sendChunkBiomePackets(List.of(chunk));
    }
}
