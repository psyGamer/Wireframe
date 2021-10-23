package dev.psygamer.wireframe.entity;

import dev.psygamer.wireframe.internal.entity.InternalLivingEntity;

public interface LivingEntity extends Entity {
	
	static LivingEntity get(final net.minecraft.entity.LivingEntity internalEntity) {
		if (internalEntity == null)
			return null;
		
		return new InternalLivingEntity(internalEntity);
	}
	
	/* Health */
	
	int getMaxHealthLevel();
	
	int getHealthLevel();
	
	void setHealthLevel(final int healthLevel);
	
	/* Leashing */
	
	boolean canBeLeashedTo(Player player);
	
	boolean isLeashedTo(Player player);
	
	Player getLeashHolder();
	
	void setLeashHolder(Player player);
	
	void unleash();
	
	/* Internal */
	
	@Override
	net.minecraft.entity.LivingEntity getInternal();
}
