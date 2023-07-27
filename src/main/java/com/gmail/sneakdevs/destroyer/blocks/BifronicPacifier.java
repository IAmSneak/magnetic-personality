package com.gmail.sneakdevs.destroyer.blocks;

import com.gmail.sneakdevs.destroyer.interfaces.BlockInterface;
import com.gmail.sneakdevs.destroyer.registry.DestroyerTags;
import com.gmail.sneakdevs.destroyer.util.BifronicHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BifronicPacifier extends Block {
    public BifronicPacifier(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (world instanceof ServerWorld serverWorld) {
            Random random = serverWorld.getRandom();
            for (int x = -3; x < 4; x++) {
                for (int y = -9; y < 2; y++) {
                    for (int z = -3; z < 4; z++) {
                        BlockPos bifPos = pos.add(x, y, z);
                        BlockState bifState = world.getBlockState(bifPos);
                        if (bifState.isIn(DestroyerTags.BIFRONIC_BLOCKS)) {
                            Block block = bifState.getBlock();
                            serverWorld.scheduleBlockTick(bifPos, block, random.nextInt(60 + 1));
                        }
                    }
                }
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }
}
