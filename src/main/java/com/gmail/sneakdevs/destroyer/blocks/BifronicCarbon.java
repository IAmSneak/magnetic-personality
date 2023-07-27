package com.gmail.sneakdevs.destroyer.blocks;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import com.gmail.sneakdevs.destroyer.util.BifronicHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class BifronicCarbon extends Block {

    public BifronicCarbon(Settings settings) {
        super(settings);
    }

    public BlockState getDormantVersion(ServerWorld world, BlockPos pos, Random random) {
        return DestroyerBlocks.DORMANT_BIFRONIC_CARBON.getDefaultState();
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BifronicHelper.bifronicRandomTick(state, world, pos, random);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, getDormantVersion(world, pos, random));
        for (int yB = world.getBottomY(); yB < world.getTopY(); yB += 2) {
            BifronicHelper.setBiome(world, pos.getX(), yB, pos.getZ(), world.getServer().getRegistryManager().get(RegistryKeys.BIOME).getEntry(RegistryKey.of(RegistryKeys.BIOME, new Identifier("destroyer", "tainted_barrens"))).orElse(null), BifronicHelper::updateChunk);
        }
        world.playSound(null, pos, SoundEvents.BLOCK_SOUL_SAND_BREAK, SoundCategory.BLOCKS, 0.5f, 1f);
        super.scheduledTick(state, world, pos, random);
    }
}
