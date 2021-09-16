package dev.psygamer.wireframe.entity;

public interface LivingEntity extends Entity {
	
	int getHealthLevel();
	
	void setHealthLevel(final int healthLevel);
	
	boolean canBeLeashedTo(Player entity);
	
	boolean isLeashedTo(Player entity);
	
	Player getLeashHolder();
	
	void setLeashHolder(Player entity);
	
	void unleash();
}
