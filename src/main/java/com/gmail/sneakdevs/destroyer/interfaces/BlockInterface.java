package com.gmail.sneakdevs.destroyer.interfaces;

import net.minecraft.block.Block;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public interface BlockInterface {
    Block getCorruptedVersion(ServerWorld world, BlockPos pos, Random random);
    Block getDormantVersion(ServerWorld world, BlockPos pos, Random random);
}