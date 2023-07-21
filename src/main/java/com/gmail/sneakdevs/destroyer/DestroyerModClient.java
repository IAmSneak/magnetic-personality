package com.gmail.sneakdevs.destroyer;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class DestroyerModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(DestroyerBlocks.BIFRONIC_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DestroyerBlocks.DORMANT_BIFRONIC_GRASS, RenderLayer.getCutout());
    }
}
