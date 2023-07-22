package com.gmail.sneakdevs.destroyer.mixin;

import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HostileEntity.class)
public class HostileEntityMixin {
	@Inject(method = "isSpawnDark", at = @At("RETURN"), cancellable = true)
	private static void isSpawnDarkMixin(ServerWorldAccess world, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBiome(pos).matchesId(new Identifier("destroyer", "forsaken_acres"))) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "getPathfindingFavor", at = @At("RETURN"), cancellable = true)
    private void getPathfindingFavorMixin(BlockPos pos, WorldView world, CallbackInfoReturnable<Float> cir) {
        if (world.getBiome(pos).matchesId(new Identifier("destroyer", "forsaken_acres"))) {
            cir.setReturnValue(1.0F);
        }
    }
}