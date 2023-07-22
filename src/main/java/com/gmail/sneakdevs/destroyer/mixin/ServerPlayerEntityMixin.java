package com.gmail.sneakdevs.destroyer.mixin;

import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Either;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity{
    @Shadow public abstract ServerWorld getServerWorld();
    @Shadow public abstract Either<PlayerEntity.SleepFailureReason, Unit> trySleep(BlockPos pos);

    @Shadow public abstract void playerTick();

    private int pureTime;
    private int pureWaterTime;

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
        pureTime = 0;
        pureWaterTime = 0;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void getPathfindingFavorMixin(CallbackInfo ci) {
        ServerPlayerEntity pe = ((ServerPlayerEntity)(Object)this);
        if (pe.isCreative() || pe.isSpectator()) {
            return;
        }
        if (pe.getServerWorld().getBiome(pe.getBlockPos()).matchesId(new Identifier("destroyer", "forsaken_acres"))) {
            if (pe.getHealth() > 10) {
                pe.setHealth(10);
            }
            if (pe.isFallFlying()) {
                if (pe.getHealth() > 5) {
                    pe.setHealth(5);
                }
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 400, 8));
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 400, 8));
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 400, 8));
            }
            if (pe.isSwimming()) {
                if (pe.getHealth() > 5) {
                    pe.setHealth(5);
                }
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 400, 4));
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 400, 4));
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 400, 4));
            }
            pe.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 40, 0));
            pe.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40, 0));
            pe.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 40, 1));
            pe.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 120, 0));
            pe.removeStatusEffect(StatusEffects.RESISTANCE);
            pe.removeStatusEffect(StatusEffects.REGENERATION);
            pe.removeStatusEffect(StatusEffects.STRENGTH);
            pe.removeStatusEffect(StatusEffects.FIRE_RESISTANCE);
            if (pe.getWorld().isNight()) {
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 400, 4));
            }
        }
        if (pe.getServerWorld().getBiome(pe.getBlockPos()).matchesId(new Identifier("destroyer", "primordial_ocean"))) {
            pureTime++;
            pe.setSprinting(false);
            pe.stopFallFlying();
            if (pe.getVehicle() != null && pe.getVehicle().getType() != EntityType.BOAT) {
                pe.dismountVehicle();
            }
            if (pe.isTouchingWaterOrRain()) {
                pureWaterTime++;
                pe.sendMessageToClient(Text.literal("You are being sterilized. Await your fate. " + (int)(pureWaterTime * .05)).formatted(Formatting.BOLD).formatted(Formatting.GOLD), true);
                pe.addExperience(1);
                if (pureWaterTime > 9900 || random.nextInt(10000 - pureWaterTime) == 0) {
                    pe.kill();
                }
            } else {
                pe.sendMessageToClient(Text.literal(String.valueOf((int) (pureTime * .05))).formatted(Formatting.BOLD).formatted(Formatting.GOLD), true);
            }
            if (pureTime * .05 > pe.experienceLevel) {
                pe.sendMessageToClient(Text.literal("Your body has succumbed to Pure Time.").formatted(Formatting.BOLD).formatted(Formatting.GOLD), true);
                if (pe.getHealth() > 2) {
                    pe.setHealth(2);
                }
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, -1, 7));
            }
        } else {
            if (pureWaterTime > 0) {
                pureWaterTime--;
                pureTime -= 2;
            }
        }
    }
}