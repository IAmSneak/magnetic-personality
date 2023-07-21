package com.gmail.sneakdevs.destroyer.blocks;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class BlockX extends Block {
    public BlockX(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        boolean didSpread = false;
        for (int i = 0; i < 8; i++) {
            int xOffset = random.nextInt(15) - 7;
            int yOffset = random.nextInt(15) - 7;
            int zOffset = random.nextInt(15) - 7;

            BlockPos toPos = pos.add(xOffset, yOffset, zOffset);
            BlockState toState = world.getBlockState(toPos);

            if (!toState.isAir() && !toState.isOf(DestroyerBlocks.BLOCK_X)) {
                didSpread = true;
                world.setBlockState(toPos, DestroyerBlocks.BLOCK_X.getDefaultState());
            }
        }
        if (!didSpread) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }
}
