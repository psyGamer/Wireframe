package dev.psygamer.wireframe.entity;

public interface LivingEntity extends Entity {
	
	boolean canBeLeashedTo(Player entity);
	
	boolean isLeashedTo(Player entity);
	
	Player getLeashHolder();
	
	void setLeashHolder(Player entity);
	
	void unleash();
}
