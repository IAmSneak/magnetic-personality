package com.gmail.sneakdevs.destroyer.blocks;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import com.gmail.sneakdevs.destroyer.util.BifronicHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;

public class BifronicGrassBlock extends Block {

    public BifronicGrassBlock(Settings settings) {
        super(settings);
    }

    public BlockState getDormantVersion(ServerWorld world, BlockPos pos, Random random) {
        return DestroyerBlocks.DORMANT_BIFRONIC_GRASS_BLOCK.getDefaultState();
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    private static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.isOf(Blocks.SNOW) && (Integer)blockState.get(SnowBlock.LAYERS) == 1) {
            return true;
        } else if (blockState.getFluidState().getLevel() == 8) {
            return false;
        } else {
            int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
            return i < world.getMaxLightLevel();
        }
    }

    private static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        return canSurvive(state, world, pos) && !world.getFluidState(blockPos).isIn(FluidTags.WATER);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (BifronicHelper.bifronicRandomTick(state, world, pos, random)) {
            return;
        }

        if (!canSurvive(state, world, pos)) {
            world.setBlockState(pos, DestroyerBlocks.BIFRONIC_DIRT.getDefaultState());
            return;
        }

        BlockState blockState = this.getDefaultState();
        for (int i = 0; i < 4; ++i) {
            BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
            if (world.getBlockState(blockPos).isOf(DestroyerBlocks.BIFRONIC_DIRT) && canSpread(blockState, world, blockPos)) {
                world.setBlockState(blockPos, DestroyerBlocks.BIFRONIC_GRASS_BLOCK.getDefaultState());
                return;
            }
        }

        if (random.nextInt(200) == 0 && world.getBlockState(pos.up()).isAir()) {
            if (random.nextInt(30) == 0) {
                world.setBlockState(pos.up(), Blocks.WITHER_ROSE.getDefaultState());
                return;
            }
            world.setBlockState(pos.up(), DestroyerBlocks.BIFRONIC_GRASS.getDefaultState());
        }
    }
}
