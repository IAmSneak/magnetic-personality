package com.gmail.sneakdevs.destroyer.blocks;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import com.gmail.sneakdevs.destroyer.util.BifronicHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class DormantBifronicCarbon extends Block {

    public DormantBifronicCarbon(Settings settings) {
        super(settings);
    }

    public BlockState getCorruptedVersion(ServerWorld world, BlockPos pos, Random random) {
        return DestroyerBlocks.BIFRONIC_CARBON.getDefaultState();
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
