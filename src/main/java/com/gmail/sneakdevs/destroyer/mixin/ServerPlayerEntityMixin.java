package com.gmail.sneakdevs.destroyer.mixin;

import com.gmail.sneakdevs.destroyer.registry.DestroyerItems;
import com.mojang.authlib.GameProfile;
import com.mojang.datafixers.util.Either;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
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

    private int pureTime;
    private int pureWaterTime;

    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
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
                if (pe.getHealth() > 3) {
                    pe.setHealth(3);
                }
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 400, 8));
            }
            if (pe.isSwimming()) {
                if (pe.getHealth() > 3) {
                    pe.setHealth(3);
                }
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 400, 4));
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 400, 4));
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 400, 4));
            }
            if (pe.getInventory().getArmorStack(0).getItem() == DestroyerItems.BIFRONIUM_HAZMAT_BOOTS && pe.getInventory().getArmorStack(1).getItem() == DestroyerItems.BIFRONIUM_HAZMAT_LEGGINGS && pe.getInventory().getArmorStack(2).getItem() == DestroyerItems.BIFRONIUM_HAZMAT_TUNIC && pe.getInventory().getArmorStack(3).getItem() == DestroyerItems.BIFRONIUM_HAZMAT_CAP) {
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100, 4));
                Random random = pe.getRandom();
                if (random.nextInt(8) == 0) {
                    pe.getInventory().getArmorStack(random.nextInt(4)).damage(1, random, pe);
                }
            } else {
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 40, 0));
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 40, 0));
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 40, 1));
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 120, 0));
                if (pe.getWorld().isNight()) {
                    pe.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 400, 4));
                }
            }
            pe.removeStatusEffect(StatusEffects.RESISTANCE);
            pe.removeStatusEffect(StatusEffects.REGENERATION);
            pe.removeStatusEffect(StatusEffects.STRENGTH);
            pe.removeStatusEffect(StatusEffects.SPEED);
            pe.removeStatusEffect(StatusEffects.JUMP_BOOST);
            pe.removeStatusEffect(StatusEffects.HASTE);
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
                pe.sendMessageToClient(Text.literal("You are being sterilized. Await your fate. " + (int)(pureWaterTime * .1)).formatted(Formatting.BOLD).formatted(Formatting.GOLD), true);
                pe.addExperience(1);
                if (pureWaterTime > 4900 || random.nextInt(5000 - pureWaterTime) == 0) {
                    pe.sendMessageToClient(Text.literal("Your body has been sterilized.").formatted(Formatting.BOLD).formatted(Formatting.GOLD), true);
                    MinecraftServer server = pe.getServer();
                    if (server != null) {
                        server.sendMessage(Text.literal(pe.getDisplayName().getString() + " has been sterilized. This is the fate of all, at least eventually.").formatted(Formatting.BOLD).formatted(Formatting.DARK_RED));
                    }
                    if (pe.getHealth() > 2) {
                        pe.setHealth(2);
                    }
                    pe.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, -1, 7));
                    pe.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, -1, 7));
                }
            } else {
                pe.sendMessageToClient(Text.literal(String.valueOf((int) (pureTime * .1))).formatted(Formatting.BOLD).formatted(Formatting.GOLD), true);
            }
            if (pureTime * .1 > pe.experienceLevel) {
                pe.sendMessageToClient(Text.literal("Your body has succumbed to Pure Time.").formatted(Formatting.BOLD).formatted(Formatting.GOLD), true);
                MinecraftServer server = pe.getServer();
                if (server != null) {
                    server.sendMessage(Text.literal(pe.getDisplayName().getString() + " has awoken.").formatted(Formatting.BOLD).formatted(Formatting.GOLD));
                }
                if (pe.getHealth() > 2) {
                    pe.setHealth(2);
                }
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, -1, 7));
                pe.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, -1, 7));
            }
        } else {
            if (pureWaterTime > 0) {
                pureWaterTime--;
                pureTime -= 4;
            } else {
                if (pureTime > 0) {
                    if (this.getRandom().nextInt(200) == 0) {
                        pureTime--;
                    }
                } else if (pureTime < 0) {
                    if (this.getRandom().nextInt(200) == 0) {
                        pureTime++;
                    }
                }
            }
        }
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("pureTime", pureTime);
        nbt.putInt("pureWaterTime", pureWaterTime);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        pureTime = nbt.getInt("pureTime");
        pureWaterTime = nbt.getInt("pureWaterTime");
    }

}