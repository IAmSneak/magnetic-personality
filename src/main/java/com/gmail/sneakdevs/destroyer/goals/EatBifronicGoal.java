package com.gmail.sneakdevs.destroyer.goals;

import com.gmail.sneakdevs.destroyer.registry.DestroyerBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.function.Predicate;

public class EatBifronicGoal extends Goal {
    private static final int MAX_TIMER = 40;
    private static final Predicate<BlockState> GRASS_PREDICATE;
    private final MobEntity mob;
    private final World world;
    private int timer;

    public EatBifronicGoal(MobEntity mob) {
        this.mob = mob;
        this.world = mob.getWorld();
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK, Control.JUMP));
    }

    public boolean canStart() {
        if (this.mob.getRandom().nextInt(this.mob.isBaby() ? 50 : 1000) != 0) {
            return false;
        } else {
            BlockPos blockPos = this.mob.getBlockPos();
            if (GRASS_PREDICATE.test(this.world.getBlockState(blockPos))) {
                return true;
            } else {
                return this.world.getBlockState(blockPos.down()).isOf(DestroyerBlocks.BIFRONIC_GRASS_BLOCK);
            }
        }
    }

    public void start() {
        this.timer = this.getTickCount(40);
        this.world.sendEntityStatus(this.mob, (byte)10);
        this.mob.getNavigation().stop();
    }

    public void stop() {
        this.timer = 0;
    }

    public boolean shouldContinue() {
        return this.timer > 0;
    }

    public int getTimer() {
        return this.timer;
    }

    public void tick() {
        this.timer = Math.max(0, this.timer - 1);
        if (this.timer == this.getTickCount(4)) {
            BlockPos blockPos = this.mob.getBlockPos();
            if (GRASS_PREDICATE.test(this.world.getBlockState(blockPos))) {
                if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                    this.world.breakBlock(blockPos, false);
                }

                this.mob.onEatingGrass();
            } else {
                BlockPos blockPos2 = blockPos.down();
                if (this.world.getBlockState(blockPos2).isOf(DestroyerBlocks.BIFRONIC_GRASS_BLOCK)) {
                    if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                        this.world.syncWorldEvent(2001, blockPos2, Block.getRawIdFromState(DestroyerBlocks.BIFRONIC_GRASS_BLOCK.getDefaultState()));
                        this.world.setBlockState(blockPos2, DestroyerBlocks.BIFRONIC_DIRT.getDefaultState(), 2);
                    }

                    this.mob.onEatingGrass();

                    this.mob.convertTo(EntityType.GHAST, false);
                    this.mob.setHealth(50F);
                    this.mob.setPersistent();

                }
            }

        }
    }

    static {
        GRASS_PREDICATE = BlockStatePredicate.forBlock(DestroyerBlocks.BIFRONIC_GRASS_BLOCK);
    }
}

