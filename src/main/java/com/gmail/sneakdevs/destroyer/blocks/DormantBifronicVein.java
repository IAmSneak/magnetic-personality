package com.gmail.sneakdevs.destroyer.blocks;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import com.gmail.sneakdevs.destroyer.registry.DestroyerTags;
import com.gmail.sneakdevs.destroyer.util.BifronicHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarpetBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;

public class DormantBifronicVein extends CarpetBlock {

    public DormantBifronicVein(Settings settings) {
        super(settings);
    }

    public BlockState getCorruptedVersion(ServerWorld world, BlockPos pos, Random random) {
        return DestroyerBlocks.BIFRONIC_VEIN.getDefaultState();
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BifronicHelper.dormantBifronicRandomTick(state, world, pos, random);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return !world.isAir(pos.down()) && !world.getBlockState(pos.down()).isIn(DestroyerTags.CANNOT_SUPPORT_BIFRONIC_VEINS);
    }
}
