package com.gmail.sneakdevs.destroyer.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public interface BlockInterface {
    BlockState getCorruptedVersion(ServerWorld world, BlockPos pos, Random random);
    BlockState getDormantVersion(ServerWorld world, BlockPos pos, Random random);
}