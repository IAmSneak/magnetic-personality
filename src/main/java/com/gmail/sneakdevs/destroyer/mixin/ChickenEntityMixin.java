package com.gmail.sneakdevs.destroyer.mixin;

import com.gmail.sneakdevs.destroyer.goals.EatBifronicGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChickenEntity.class)
public abstract class ChickenEntityMixin extends AnimalEntity {

	protected ChickenEntityMixin(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "initGoals", at = @At("HEAD"))
	private void initGoalsMixin(CallbackInfo ci) {
		this.goalSelector.add(5, new EatBifronicGoal(this));
	}

	public EntityType getCorruptedVersion() {
		return EntityType.CREEPER;
	}
}