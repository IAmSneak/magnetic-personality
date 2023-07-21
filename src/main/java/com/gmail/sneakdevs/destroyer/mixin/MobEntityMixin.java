package com.gmail.sneakdevs.destroyer.mixin;

import com.gmail.sneakdevs.destroyer.interfaces.MobEntityInterface;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MobEntity.class)
public class MobEntityMixin implements MobEntityInterface {

	@Override
	public EntityType getCorruptedVersion() {
		return EntityType.GHAST;
	}
}
