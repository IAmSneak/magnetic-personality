package com.gmail.sneakdevs.destroyer.blocks;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import com.gmail.sneakdevs.destroyer.registry.DestroyerTags;
import com.gmail.sneakdevs.destroyer.util.BifronicHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class DormantBifronicGrass extends PlantBlock {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);

    public DormantBifronicGrass(Settings settings) {
        super(settings);
    }

    public Block getCorruptedVersion(ServerWorld world, BlockPos pos, Random random) {
        return DestroyerBlocks.BIFRONIC_GRASS;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BifronicHelper.dormantBifronicRandomTick(state, world, pos, random);
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
