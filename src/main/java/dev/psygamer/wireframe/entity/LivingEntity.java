package dev.psygamer.wireframe.entity;

public interface LivingEntity extends Entity {
	
	int getMaxHealthLevel();
	
	int getHealthLevel();
	
	void setHealthLevel(final int healthLevel);
	
	boolean canBeLeashedTo(Player player);
	
	boolean isLeashedTo(Player player);
	
	Player getLeashHolder();
	
	void setLeashHolder(Player player);
	
	void unleash();
	
	@Override
	net.minecraft.entity.LivingEntity getInternal();
}
