package com.gmail.sneakdevs.destroyer.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TallPlantBlock.class)
public class TallPlantBlockMixin {
	@Shadow @Final public static EnumProperty<DoubleBlockHalf> HALF;

	public BlockState getCorruptedVersion(ServerWorld world, BlockPos pos, Random random) {
		if (random.nextInt(15) == 0 && world.getBlockState(pos).get(HALF) == DoubleBlockHalf.LOWER) {
			return Blocks.CAKE.getDefaultState();
		}
		return Blocks.AIR.getDefaultState();
	}
}
