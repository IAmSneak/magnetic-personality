package com.gmail.sneakdevs.destroyer.blocks;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import com.gmail.sneakdevs.destroyer.registry.DestroyerTags;
import com.gmail.sneakdevs.destroyer.util.BifronicHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.CarpetBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class BifronicVein extends CarpetBlock {

    public BifronicVein(Settings settings) {
        super(settings);
    }

    public BlockState getDormantVersion(ServerWorld world, BlockPos pos, Random random) {
        return DestroyerBlocks.DORMANT_BIFRONIC_VEIN.getDefaultState();
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BifronicHelper.bifronicRandomTick(state, world, pos, random);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return !world.isAir(pos.down()) && !world.getBlockState(pos.down()).isIn(DestroyerTags.CANNOT_SUPPORT_BIFRONIC_VEINS);
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && world.getDifficulty() != Difficulty.PEACEFUL) {
            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity instanceof PlayerEntity) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 40, 0));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40, 0));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 40, 1));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 120, 0));
                }
                if (livingEntity.isUndead()) {
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 120, 0));
                    livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 120, 1));
                }
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, getDormantVersion(world, pos, random));
        for (int yB = world.getBottomY(); yB < world.getTopY(); yB += 2) {
            BifronicHelper.setBiome(world, pos.getX(), yB, pos.getZ(), world.getServer().getRegistryManager().get(RegistryKeys.BIOME).getEntry(RegistryKey.of(RegistryKeys.BIOME, new Identifier("destroyer", "tainted_barrens"))).orElse(null), BifronicHelper::updateChunk);
        }
        world.playSound(null, pos, SoundEvents.BLOCK_SOUL_SAND_BREAK, SoundCategory.BLOCKS, 0.5f, 1f);
        super.scheduledTick(state, world, pos, random);
    }
}
