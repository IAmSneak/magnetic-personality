package com.gmail.sneakdevs.destroyer.mixin;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FernBlock;
import net.minecraft.block.SculkShriekerBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FernBlock.class)
public class FernBlockMixin {
	public BlockState getCorruptedVersion(ServerWorld world, BlockPos pos, Random random) {
		if (random.nextInt(32) == 0) {
			if (random.nextInt(8) == 0) {
				return Blocks.SCULK_SHRIEKER.getDefaultState().with(SculkShriekerBlock.CAN_SUMMON, true);
			}
			return Blocks.SCULK_SENSOR.getDefaultState();
		}
		return DestroyerBlocks.BIFRONIC_GRASS.getDefaultState();
	}
}
