package com.gmail.sneakdevs.destroyer.mixin;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LeavesBlock.class)
public class LeavesBlockMixin {
	public BlockState getCorruptedVersion(ServerWorld world, BlockPos pos, Random random) {
		return DestroyerBlocks.BIFRONIC_CARBON.getDefaultState();
	}
}