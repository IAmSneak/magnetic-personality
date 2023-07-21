package com.gmail.sneakdevs.destroyer.mixin;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.FernBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FernBlock.class)
public class FernBlockMixin {
	public Block getCorruptedVersion(ServerWorld world, BlockPos pos, Random random) {
		return DestroyerBlocks.BIFRONIC_GRASS;
	}
}
