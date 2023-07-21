package com.gmail.sneakdevs.destroyer.items;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import com.gmail.sneakdevs.destroyer.registry.DestroyerGameRules;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemX extends Item {
    public ItemX(Settings settings) {
        super(settings);
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.kill();
        return super.postHit(stack, target, attacker);
    }

    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 10000, 4));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 10000, 2));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 10000, 0));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.BAD_OMEN, 10000, 2));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 10000, 2));
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 10000, 2));
        return ActionResult.PASS;
    }

    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (world.getGameRules().getBoolean(DestroyerGameRules.BLOCK_X_CREATABLE)) {
            world.createExplosion(miner, pos.getX(), pos.getY(), pos.getZ(), 15.0F, World.ExplosionSourceType.BLOCK);
            world.setBlockState(pos, DestroyerBlocks.BLOCK_X.getDefaultState());
        }
        return super.postMine(stack, world, state, pos, miner);
    }

}
