package com.gmail.sneakdevs.destroyer.mixin;

import com.gmail.sneakdevs.destroyer.interfaces.BlockInterface;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
public class BlockMixin implements BlockInterface {
	public BlockState getCorruptedVersion(ServerWorld world, BlockPos pos, Random random) {
		return null;
	}
	public BlockState getDormantVersion(ServerWorld world, BlockPos pos, Random random) {
		return null;
	}
}
