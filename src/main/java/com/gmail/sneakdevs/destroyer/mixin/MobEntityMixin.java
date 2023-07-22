package com.gmail.sneakdevs.destroyer.mixin;

import com.gmail.sneakdevs.destroyer.interfaces.MobEntityInterface;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin extends LivingEntity implements MobEntityInterface {
	protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	public EntityType getCorruptedVersion() {
		return EntityType.GHAST;
	}

	@Inject(method = "isAffectedByDaylight", at = @At("HEAD"), cancellable = true)
	private void isAffectedByDaylightMixin(CallbackInfoReturnable<Boolean> cir) {
		if (this.getWorld().getBiome(this.getBlockPos()).matchesId(new Identifier("destroyer", "forsaken_acres"))) {
			cir.setReturnValue(false);
		}
	}
}
