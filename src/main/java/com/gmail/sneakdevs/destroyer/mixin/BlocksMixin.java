package com.gmail.sneakdevs.destroyer.mixin;

import com.gmail.sneakdevs.destroyer.blocks.DirtBlock;
import com.gmail.sneakdevs.destroyer.blocks.LogBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Blocks.class)
public class BlocksMixin {
	@Redirect(method = "<clinit>", at = @At(value = "NEW", target = "net/minecraft/block/Block", ordinal = 0), slice = @Slice(from = @At(value = "CONSTANT", args="stringValue=dirt")))
	private static Block dirt(AbstractBlock.Settings settings) {
		return new DirtBlock(settings);
	}

	@Redirect(method = "<clinit>", at = @At(value = "NEW", target = "net/minecraft/block/Block", ordinal = 0), slice = @Slice(from = @At(value = "CONSTANT", args="stringValue=podzol")))
	private static Block podzol(AbstractBlock.Settings settings) {
		return new DirtBlock(settings);
	}

	@Inject(method = "createLogBlock", at = @At(value = "HEAD"), cancellable = true)
	private static void createLogBlock(MapColor topMapColor, MapColor sideMapColor, CallbackInfoReturnable<PillarBlock> cir) {
		cir.setReturnValue(new LogBlock(AbstractBlock.Settings.create().mapColor((state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));
	}

}
