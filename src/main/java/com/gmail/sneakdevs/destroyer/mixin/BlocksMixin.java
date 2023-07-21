package com.gmail.sneakdevs.destroyer.mixin;

import com.gmail.sneakdevs.destroyer.blocks.DirtBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class BlocksMixin {
	@Redirect(method = "<clinit>", at = @At(value = "NEW", target = "net/minecraft/block/Block", ordinal = 0), slice = @Slice(from = @At(value = "CONSTANT", args="stringValue=dirt")))
	private static Block dirt(AbstractBlock.Settings settings) {
		return new DirtBlock(settings);
	}

}
