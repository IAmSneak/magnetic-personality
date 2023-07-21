package com.gmail.sneakdevs.destroyer.blocks;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import com.gmail.sneakdevs.destroyer.util.BifronicHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class DormantBifronicGrassBlock extends Block {

    public DormantBifronicGrassBlock(Settings settings) {
        super(settings);
    }

    public Block getCorruptedVersion(ServerWorld world, BlockPos pos, Random random) {
        return DestroyerBlocks.BIFRONIC_GRASS_BLOCK;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BifronicHelper.dormantBifronicRandomTick(state, world, pos, random);
    }
}
