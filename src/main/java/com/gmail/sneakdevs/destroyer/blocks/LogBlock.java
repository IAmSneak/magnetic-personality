package com.gmail.sneakdevs.destroyer.blocks;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class LogBlock extends PillarBlock {
    public LogBlock(Settings settings) {
        super(settings);
    }

    public BlockState getCorruptedVersion(ServerWorld world, BlockPos pos, Random random) {
        return DestroyerBlocks.BIFRONIC_CARBON.getDefaultState();
    }
}
